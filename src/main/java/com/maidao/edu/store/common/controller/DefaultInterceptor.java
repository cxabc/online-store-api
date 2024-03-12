package com.maidao.edu.store.common.controller;

import com.maidao.edu.store.api.admin.authority.AdminPermission;
import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.authority.SessionUtil;
import com.maidao.edu.store.common.context.Context;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.context.SessionThreadLocal;
import com.maidao.edu.store.common.context.SessionWrap;
import com.maidao.edu.store.common.entity.ApiParams;
import com.maidao.edu.store.common.exception.ErrorCode;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.WebUtils;
import com.sunnysuperman.commons.util.FormatUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1. @ControllerAdvice 的主要作用：
 *
 * 全局异常处理：通过在 @ControllerAdvice 注解的类中定义 @ExceptionHandler 方法，可以统一处理应用程序中抛出的异常，而不需要在每个控制器中重复编写异常处理逻辑。
 *
 * 全局数据绑定：通过在 @ControllerAdvice 注解的类中定义 @InitBinder 方法，可以为所有控制器定义全局的数据绑定规则，例如日期格式化、数据验证等。
 *
 * 全局数据预处理：通过在 @ControllerAdvice 注解的类中定义 @ModelAttribute 方法，可以为所有控制器提供全局的模型属性，这些属性会自动添加到每个控制器的模型中，使得这些属性在视图中可用。
 *
 * 2. HandlerInterceptor 是 Spring MVC 中的一个接口，用于拦截器（Interceptor）的定义。
 * 拦截器是在请求处理过程中，对请求和响应进行拦截和处理的一种机制，它可以在请求被处理之前、处理过程中以及请求处理完成之后执行一些额外的操作，
 * HandlerInterceptor接口定义了三个方法，分别是 preHandle()、postHandle() 和 afterCompletion()，它们分别对应请求处理的不同阶段
 */
@ControllerAdvice
public class DefaultInterceptor extends SessionUtil implements HandlerInterceptor, ErrorCode {

    /*
     * 1. HttpServletRequest表示客户端发送给服务器的 HTTP 请求。它提供了访问 HTTP 请求的各种信息和属性的方法，包括请求头、请求参数、请求方法、请求 URL 等。
     * 通过 HttpServletRequest，你可以获取客户端发送的各种信息，以便在服务器端对请求进行处理。
     *
     * 2. 用于表示服务器发送给客户端的 HTTP 响应。它提供了一系列方法来设置响应的状态码、头部信息、内容类型以及发送响应体等。
     * 通过 HttpServletResponse，你可以构建并发送服务器对客户端请求的响应
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (CrossDomainHandler.handle(request, response)) {
            return false;
        }

        Context wrapper = new Context();
        wrapper.setRequestIp(WebUtils.getRemoteAddress(request));
        wrapper.setCustomerId(FormatUtil.parseLong(WebUtils.getHeader(request, ApiParams.CUSTOMER_ID)));
        SessionThreadLocal.getInstance().set(wrapper);

        /*
         * 3. HandlerMethod它封装了处理请求的方法的相关信息，包括方法本身、方法所属的控制器对象以及方法的参数信息等。
         * 通常情况下，Spring MVC 在处理请求时会将请求映射到相应的处理方法上，而这个处理方法就是 HandlerMethod 的实例
         * HandlerMethod 类提供了一些方法来获取处理方法的相关信息，例如
         * getMethod()：获取处理请求的方法对象（Method 类型）。
         * getBean()：获取处理请求的方法所属的控制器对象。
         * getReturnType()：获取处理方法的返回类型。
         * getParameters()：获取处理方法的参数信息。
         */
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean authorized = false;
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        for (AdminType adminType : requiredPermission.adminType()) {
            if (adminType == AdminType.ADMIN) {
                // 多个权限满足其一即可
                for (AdminPermission permission : requiredPermission.adminPermission()) {
                    authorized = findSessionWrap(AdminType.ADMIN, request, permission.name()) != null;
                    if (authorized) {
                        break;
                    }
                }
            } else if (adminType == AdminType.USER) {
                authorized = findSessionWrap(AdminType.USER, request, null) != null;
            } else {
                // no session
                authorized = true;
            }
            if (authorized) {
                break;
            }
        }
        if (!authorized) {
            throw new ServiceException(ERR_SESSION_EXPIRES);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private SessionWrap findSessionWrap(Enum type, HttpServletRequest request, String permission) throws Exception {
        String token = WebUtils.getHeader(request, ApiParams.ADMIN_TOKEN);
        SessionWrap wrap = adminPermissionCheck(type, token, permission);
        Contexts.get().setSession(wrap);
        return wrap;
    }

}