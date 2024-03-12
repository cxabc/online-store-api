package com.maidao.edu.store.common.controller;

import com.maidao.edu.store.common.entity.ApiParams;
import com.maidao.edu.store.common.exception.ArgumentServiceException;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.StringUtils;
import com.maidao.edu.store.common.util.WebUtils;
import com.sunnysuperman.commons.bean.Bean;
import com.sunnysuperman.commons.bean.ParseBeanOptions;
import com.sunnysuperman.commons.util.FormatUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * feedback 方法的返回值将会被序列化为 JSON 格式，仅包含其中指定的属性，而忽略其他属性
     * 采用了 JsonView(result)，意味着返回的视图将会将 result 对象序列化为 JSON 格式，仅包含其中指定的属性，而忽略其他属性
     */
    protected static ModelAndView feedback(Object ret) {
        Map<String, Object> result = new HashMap<>();
        if (ret != null) {
            result.put("result", ret);
        }
        // JsonView(result) 可以用来指定序列化该对象时所采用的视图，即只序列化视图中指定的属性，而忽略其他属性
        return new ModelAndView(new JsonView(result));
    }

    protected static int parseInt(Integer i) {
        return FormatUtil.parseIntValue(i, 0);
    }

    protected static int parseInt(Integer i, int defaultValue) {
        return FormatUtil.parseIntValue(i, defaultValue);
    }

    protected static boolean parseBoolean(String b) {
        return FormatUtil.parseBoolean(b, false);
    }

    protected static boolean parseBoolean(String b, boolean defaultValue) {
        return FormatUtil.parseBoolean(b, defaultValue);
    }

    protected static Date parseDate(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return FormatUtil.parseDate(s);
    }

    protected static <T> T parseModel(String modelJSON, T model) throws ServiceException {
        return parseModel(modelJSON, model, null, null);
    }

    protected static <T> T parseModel(String modelJSON, T model, String key) throws ServiceException {
        return parseModel(modelJSON, model, key, null);
    }

    protected static int parsePageNumber(Integer pageNumber) {
        int asInt = FormatUtil.parseIntValue(pageNumber, 0);
        return asInt <= 0 ? 0 : asInt - 1;
    }

    private static <T> T parseModel(String modelJSON, T model, String key, ParseBeanOptions options)
            throws ServiceException {
        try {
            return Bean.fromJson(modelJSON, model, options);
        } catch (Exception e) {
            throw new ArgumentServiceException(key != null ? key : "model");
        }
    }

    protected ModelAndView feedback() {
        return feedback(null);
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ra.getRequest();
    }

    protected String getToken() {
        return WebUtils.getHeader(getRequest(), ApiParams.ADMIN_TOKEN);
    }

    protected String getRemoteAddress() {
        return WebUtils.getRemoteAddress(getRequest());
    }

}
