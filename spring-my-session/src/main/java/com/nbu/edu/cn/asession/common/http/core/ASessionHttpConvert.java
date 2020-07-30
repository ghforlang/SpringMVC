package com.nbu.edu.cn.asession.common.http.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ASessionHttpConvert {

    /**
     * request 转换
     * @param request
     * @param response
     * @return
     */
    HttpServletRequest convertRequest(HttpServletRequest request, HttpServletResponse response);

    /**
     * response 转换
     * @param request
     * @param response
     * @return
     */
    HttpServletResponse convertResponse(HttpServletRequest request,HttpServletResponse response);
}
