package com.nbu.edu.cn.asession.core;

import com.nbu.edu.cn.asession.common.http.core.ASessionStrategy;
import com.nbu.edu.cn.asession.common.http.wrapper.ASessionHttpRequestWrapper;
import com.nbu.edu.cn.asession.common.http.wrapper.ASessionHttpResponseWrapper;
import com.nbu.edu.cn.asession.core.operation.ASessionOperation;
import com.nbu.edu.cn.exception.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Order(1)
public class ASessionFilter<S extends ASession> implements Filter {

    public static final String SESSION_ATTR_NAME = ASession.class.getName() + ".HTTP.ASESSION.NAME";
    public static final String IS_ALREADY_FILTERED = ASession.class.getName() + "HTTP.ASESSION.FILTERED";

    private ASessionOperation<S> operation;
    private ASessionStrategy strategy;

    public ASessionFilter() {
        super();
    }

    public ASessionFilter(ASessionOperation<S> operation, ASessionStrategy strategy) {
        if(Objects.isNull(operation) || Objects.isNull(strategy)){
           throw ResponseEnum.ILLEGAL_ARGS.newException("operation and strategy can not be null !");
        }
        this.operation = operation;
        this.strategy = strategy;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("初始化开始》》》》》");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)){
            throw ResponseEnum.BAD_REQUEST.newException("just support HTTP requests!");
        }

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        boolean hasAlreadyFilteredAttribute = request.getAttribute(IS_ALREADY_FILTERED) != null;
        if(hasAlreadyFilteredAttribute){
            chain.doFilter(request,response);
        }else{
            request.setAttribute(IS_ALREADY_FILTERED,true);
            try{
                doFilterInternal(httpRequest,httpResponse,chain);
            }finally {
                request.removeAttribute(IS_ALREADY_FILTERED);
            }

        }
    }

    private void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        httpRequest.setAttribute(SESSION_ATTR_NAME,operation);

        ASessionHttpRequestWrapper wrapperRequest = new ASessionHttpRequestWrapper(httpRequest,httpResponse,httpRequest.getServletContext(),strategy,operation);
        ServletRequestAttributes attributes = new ServletRequestAttributes(wrapperRequest);

        LocaleContextHolder.setLocale(httpRequest.getLocale());
        RequestContextHolder.setRequestAttributes(attributes);

        ASessionHttpResponseWrapper wrapperedResponse = new ASessionHttpResponseWrapper(httpResponse,wrapperRequest);
        HttpServletRequest strategyRequest = strategy.convertRequest(wrapperRequest,wrapperedResponse);
        HttpServletResponse strategyResponse = strategy.convertResponse(wrapperRequest,wrapperedResponse);

        try{
            chain.doFilter(strategyRequest,strategyResponse);
        }finally {
            wrapperRequest.commit();
        }
    }


    @Override
    public void destroy() {
        log.debug("destroy is beginning >>>>>>>");
    }

    public ASessionOperation<S> getOperation() {
        return operation;
    }

    public void setOperation(ASessionOperation<S> operation) {
        this.operation = operation;
    }

    public ASessionStrategy getStrategy() {
        return strategy;
    }

    public ASessionFilter setStrategy(ASessionStrategy strategy) {
        this.strategy = strategy;
        return this;
    }
}
