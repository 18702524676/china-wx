package com.wx.permission.Interceptor;


import com.wx.core.exception.BusinessJsonException;
import com.wx.permission.annotations.PermissionsDesc;
import com.wx.permission.utils.AclInfoUtils;
import com.wx.permission.utils.BusinessContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

import static com.wx.permission.enums.PermissionEnumError.SysAclEnumError.SYS_ACL_ENUM_ERROR_3;


/**
 * @ClassName HttpInterceptor
 * @Author wx
 * @Description http请求拦截器
 * @Date 2018-08-19-15:42
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    
    /**
     * @methodName: preHandle
     * @author: wx
     * @description: 请求发生之前执行（进入Controller之前）
     *               在实际的Controller执行前执行；有Boolean类型的返回值，如果返回为False，
     *              则Handle本身及postHandle/afterCompletion以及后续的拦截器全部都不会再继续执行；
     *              为True则反之。
     * @param request 请求对象
     * @param response 返回对象
     * @param handler  处理器
     * @date: 2018/8/19
     * @return: boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PermissionsDesc permission = getAnnotation(handlerMethod, PermissionsDesc.class);
            if (permission != null) {
                if (AclInfoUtils.permissionsValidation(permission.value())){
                    return true;
                }else {
                    throw new BusinessJsonException(SYS_ACL_ENUM_ERROR_3.getMessageError());
                }

            }
        }
        return true;
    }
    
    /**
     * @methodName: postHandle
     * @author: wx
     * @description: 请求发生之后执行,Handle执行后视图渲染前执行（执行完Controller后）
     * @param request 请求对象 
     * @param response 返回对象
     * @param handler  处理器
     * @param modelAndView 视图数据
     * @date: 2018/8/19
     * @return: void
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
    }
    
    /**
     * @methodName: afterCompletion
     * @author: wx
     * @description: 请求发送后始终执行,Handle执行且视图渲染完成后执行（这个方法一定会被执行，他是finally）
     * @param request 请求对象
     * @param response 返回对象
     * @param handler  处理器
     * @param ex   异常
     * @date: 2018/8/19
     * @return: void
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) throws Exception {
        //清除上下文
        BusinessContextUtils.celan();
    }


    /**
     * @methodName: getAnnotation
     * @author: wx
     * @description: 注解解析器
     * @param handlerMethod 方法
     * @param clazz         class
     * @date: 2018/9/16
     * @return: T
     */
    private <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod,
                                                   Class<T> clazz) {
        T annotation = handlerMethod.getMethodAnnotation(clazz);
        if (annotation != null) {
            return annotation;
        }
        annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), clazz);
        return annotation;
    }
}
