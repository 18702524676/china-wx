package com.wx.core.exception;


import com.wx.core.enums.EnumError;
import lombok.Data;

import static com.wx.core.constant.SystemConstant.ViewSys.EXCEPTION_VIME;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * @Author wx
 * @Description 业务页面请求异常
 * @Date 2018-8-13
 */
@Data
public class BusinessPageException extends RuntimeException {
	private static final long serialVersionUID = 1318970911150051604L;

	/** 错误编码 */
	private Integer errCode;

	/** 跳转地址 */
	private String url;

	public BusinessPageException(EnumError enumError) {
		super(enumError.getMessageError());
		this.errCode = enumError.getCodeError();
	}

	public BusinessPageException(Integer code, String message) {
		this(code, message, EXCEPTION_VIME);
	}

	public BusinessPageException(String message, String url) {
		this(INTERNAL_SERVER_ERROR.value(), message, url);
	}

	public BusinessPageException(Integer code, String message, String url) {
		super(message);
		this.errCode = code;
		this.url = url;
	}

	public BusinessPageException(String message) {
		super(message);
	}

	public BusinessPageException() {
	}

}
