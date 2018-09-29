package com.wx.core.exception;


import com.wx.core.enums.EnumError;
import lombok.Data;

/**
 * @Author wx
 * @Description 业务json请求异常
 * @Date 2018-8-13
 */
@Data
public class BusinessJsonException extends RuntimeException {

	private static final long serialVersionUID = -4453231439181616823L;

	/** 错误编码 */
	private Integer errCode;

	public BusinessJsonException(EnumError enumError) {
		super(enumError.getMessageError());
		this.errCode = enumError.getCodeError();
	}

	public BusinessJsonException(Integer code, String message) {
		super(message);
		this.errCode = code;
	}

	public BusinessJsonException(String message) {
		super(message);
	}

	public BusinessJsonException() {
	}
}
