package com.wx.core.exception;


import com.wx.core.enums.EnumError;

/**
 * @ClassName RedisException
 * @Author wx
 * @Description Redis操作异常
 * @Date 2018-08-26-23:27
 */
public class RedisOperationException extends  RuntimeException{

    private static final long serialVersionUID = -4188444513672395075L;
    /** 错误编码 */
    private Integer errCode;

    public RedisOperationException(EnumError enumError) {
        super(enumError.getMessageError());
        this.errCode = enumError.getCodeError();
    }

    public RedisOperationException(Integer code, String message) {
        super(message);
        this.errCode = code;
    }

    public RedisOperationException(String message) {
        super(message);
    }

    public RedisOperationException() {
    }
}
