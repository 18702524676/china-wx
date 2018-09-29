package com.wx.core.exception.handler;


import com.baidu.unbiz.fluentvalidator.exception.RuntimeValidateException;
import com.wx.core.exception.BusinessJsonException;
import com.wx.core.exception.BusinessPageException;
import com.wx.core.exception.ParamException;
import com.wx.core.exception.RedisOperationException;
import com.wx.core.response.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

import static com.wx.core.constant.SystemConstant.ViewSys.EXCEPTION_VIME;
import static com.wx.core.enums.error.SystemEnumError.SystemError.SYSTEM_ERROR_500;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @Author wx
 * @Description 全局异常处理器(适用于前后端分离，此处暂时不使用)
 * @Date 2018-8-13
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @methodName: BusinessJsonExceptionHandler
	 * @author: wx
	 * @description: json请求异常处理器
	 * @param e
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@ResponseBody
	@ExceptionHandler(value = BusinessJsonException.class)
	public JsonData BusinessJsonExceptionHandler(BusinessJsonException e) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：{}", e.getMessage(), e);
		}
		return JsonData.ResultMsgError(e.getMessage());
	}

	/**
	 * @methodName: BusinessPageExceptionHandler
	 * @author: wx
	 * @description: page页面请求异常处理器
	 * @param e
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	@ExceptionHandler(value = BusinessPageException.class)
	public ModelAndView BusinessPageExceptionHandler(BusinessPageException e) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：", e);
		}
		return new ModelAndView(e.getUrl(), JsonData.toMap(e.getErrCode(),e.getMessage()));
	}
	
	/**
	 * @methodName: ParamExceptionHandler
	 * @author: wx
	 * @description: 参数异常处理器
	 * @param e
	 * @date: 2018/8/15
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@ResponseBody
	@ExceptionHandler(value = ParamException.class)
	public ModelAndView ParamExceptionHandler(ParamException e, HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：{}", e.getMessage(), e);
		}
		return resultModelAndView(request,e.getMessage());

	}

	/**
	 * @methodName: RuntimeValidateExceptionHandler
	 * @author: wx
	 * @description: 验证中发生了一些不可控异常，例如数据库调用失败，RPC连接失效等，会抛出一些异常，
	 * 				如果Validator没有try-catch处理，FluentValidator会将这些异常封装在RuntimeValidateException，
	 * 				然后再re-throw出去，这个情况你应该知晓并作出你认为最正确的处理
	 * @param e
	 * @date: 2018/8/15
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@ResponseBody
	@ExceptionHandler(value = RuntimeValidateException.class)
	public ModelAndView RuntimeValidateExceptionHandler(RuntimeValidateException e) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：", e);
		}
		return new ModelAndView(EXCEPTION_VIME, JsonData.toMap(INTERNAL_SERVER_ERROR.value(),e.getMessage()));
	}

	/**
	 * @methodName: missingServletRequestParameterException
	 * @author: wx
	 * @description: 其他异常处理
	 * @param e
	 * @date: 2018/8/13
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@ResponseBody
	//@ResponseStatus(HTTPSTATUS.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView ExceptionHandler(Exception e,HttpServletRequest request) {
		log.error("错误信息：", e);
		return resultModelAndView(request,SYSTEM_ERROR_500.getMessageError());
	}
	
	/**
	 * @methodName: RedisOperationExceptionHandler
	 * @author: wx
	 * @description: redis操作异常处理
	 * @param e
	 * @param request
	 * @date: 2018/8/26
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	@ResponseBody
	@ExceptionHandler(value = RedisOperationException.class)
	public ModelAndView RedisOperationExceptionHandler(RuntimeValidateException e,HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：", e);
		}
		return resultModelAndView(request,SYSTEM_ERROR_500.getMessageError());
	}

	/**
	 * @methodName: resultModelAndView
	 * @author: wx
	 * @description: 验证请求是否是jons
	 * @param request 请求对象
	 * @param errMessage 错误信息
	 * @date: 2018/8/21
	 * @return: org.springframework.web.servlet.ModelAndView
	 */
	private ModelAndView resultModelAndView(HttpServletRequest request,String errMessage){
		String url = request.getRequestURL().toString();
		return url.endsWith(".json") ? new ModelAndView(new MappingJackson2JsonView(), JsonData.toMap(INTERNAL_SERVER_ERROR.value(),errMessage))
				: new ModelAndView(EXCEPTION_VIME, JsonData.toMap(INTERNAL_SERVER_ERROR.value(),errMessage));
	}

	/**
	 * @methodName: missingServletRequestParameterException
	 * @author: wx
	 * @description: 用与前后端分离使用，有些情况前端只需要捕获http状态码400(暂时不使用)
	 * @param e
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
/*	@Deprecated
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public JsonData missingServletRequestParameterException(MissingServletRequestParameterException e) {
		return JsonData.ResultMsgError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}*/

	/**
	 * @methodName: missingServletRequestParameterException
	 * @author: wx
	 * @description: 有些情况前端只需要捕获http状态码405(暂时不使用)
	 * @param e
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
/*	@Deprecated
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public JsonData httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		if (log.isInfoEnabled()) {
			log.info("错误信息：", e);
		}
		return JsonData.ResultMsgError(HttpStatus.METHOD_NOT_ALLOWED.value(),
			HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
	}*/

}
