package com.maidao.edu.store.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainHandler {
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String MATCH_ALL = "*";
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "access-control-request-headers";
    private static final String METHOD_ALLOWED = "POST, GET, PUT, DELETE, HEAD, OPTIONS";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String ACL_MAX_AGE_VALUE = "86400";

    public static final boolean handle(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, MATCH_ALL);
        if (request.getMethod().equals(METHOD_OPTIONS)) {
            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, METHOD_ALLOWED);
            response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader(ACCESS_CONTROL_REQUEST_HEADERS));
            response.addHeader(ACCESS_CONTROL_MAX_AGE, ACL_MAX_AGE_VALUE);
            return true;
        }
        return false;
    }

}
