package com.nbu.edu.cn.asession.common.http.wrapper;

import com.nbu.edu.cn.asession.common.http.core.ASessionHttpSession;
import com.nbu.edu.cn.asession.common.http.core.ASessionStrategy;
import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.asession.core.ids.AsessionIdGenerator;
import com.nbu.edu.cn.asession.core.operation.ASessionOperation;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Objects;

@Slf4j
public class ASessionHttpRequestWrapper<S extends ASession> extends HttpServletRequestWrapper {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;
    private ASessionHttpSession<S> aSession;
    private ASessionStrategy aSessionStrategy;
    private ASessionOperation<S> operation;

    private Boolean requestedASessionIdValid;
    private boolean isNewCreate = false;

    public ASessionHttpRequestWrapper(HttpServletRequest request, HttpServletResponse response,
          ServletContext servletContext, ASessionStrategy aSessionStrategy, ASessionOperation<S> operation) {
        super(request);
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
        this.aSessionStrategy = aSessionStrategy;
        this.operation = operation;
    }

    @Override
    public ASessionHttpSession getSession(boolean create) {
        if(Objects.nonNull(aSession)){
            return aSession;
        }

        String requestedASessionId = aSessionStrategy.getRequestedASessionId(request);
        if(StringUtils.isNotBlank(requestedASessionId)){
            S tempSession = operation.get(requestedASessionId);
            if(Objects.nonNull(aSession)){
                aSession = new ASessionHttpSession<>(servletContext,tempSession);
                aSession.setNewCreate(false);
                return aSession;
            }
        }
        if(!create){
            return null;
        }

        String newASessionId = AsessionIdGenerator.genId();
        S newASession = operation.create(newASessionId);
        aSession = new ASessionHttpSession<>(servletContext,newASession);
        aSessionStrategy.onNewASession(aSession.getExpireASession(),this,response);
        isNewCreate = true;
        return aSession;
    }

    public void commit(){
        aSession = this.getSession(false);
        if(Objects.isNull(aSession)){
            aSession = this.getSession(true);
        }else{
            S tempASession = aSession.getExpireASession();
            operation.save(tempASession);
        }
    }

    @Override
    public String changeSessionId() {
        if(Objects.isNull(aSession)){
            log.error("当前reqeust:{}",request);
            throw ResponseEnum.INVALID_A_SESSION.newException("sessionId 不能为空!");
        }

        String aSessionId = AsessionIdGenerator.genId();
        S newASession = operation.create(aSessionId);
        ASessionHttpSession<S> newHttpSession = new ASessionHttpSession<>(servletContext,newASession);
        isNewCreate = true;

        Enumeration<String> attrNames = aSession.getAttributeNames();
        while(attrNames.hasMoreElements()){
            String attrName = attrNames.nextElement();
            Object value = aSession.getAttribute(attrName);
            newHttpSession.setAttribute(attrName,value);
        }
        operation.delete(aSession.getId());
        aSession = newHttpSession;
        return aSession.getId();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        if(Objects.isNull(requestedASessionIdValid)){
            String aSessionId = getRequestedSessionId();
            S aSession = StringUtils.isBlank(aSessionId) ? null : operation.get(aSessionId);
            return isRequestedSessionIdValid(aSession);
        }

        return requestedASessionIdValid;
    }

    private boolean isRequestedSessionIdValid(S aSession){
        if(Objects.isNull(aSession)){
            requestedASessionIdValid = Objects.nonNull(aSession);
        }
        return requestedASessionIdValid;
    }

    @Override
    public String getRequestedSessionId() {
        return this.getSession().getId();
    }

    @Override
    public ASessionHttpSession<S> getSession() {
        return getSession(true);
    }

    @Override
    public ServletContext getServletContext() {
        return Objects.nonNull(servletContext) ? servletContext : super.getServletContext();
    }

    public boolean isNewCreate(){
        return isNewCreate;
    }
}
