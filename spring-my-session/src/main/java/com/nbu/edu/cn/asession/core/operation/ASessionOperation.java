package com.nbu.edu.cn.asession.core.operation;

import com.nbu.edu.cn.asession.core.ASession;
import com.nbu.edu.cn.exception.BaseException;

public interface ASessionOperation<S extends ASession> {

    /**
     * 创建
     * @param aSessionId
     * @return
     */
    S create(String aSessionId);

    /**
     * 保存
     * @param aSession
     */
    void save(S aSession) throws BaseException;

    /**
     * 获取
     * @param aSessionId
     * @return
     */
    S get(String aSessionId) throws BaseException;

    /**
     * 删除
     * @param aSessionId
     */
    void delete(String aSessionId) throws BaseException;

    /**
     * 是否有效
     * @param aSessionId
     * @return
     */
    boolean isValid(String aSessionId);
}
