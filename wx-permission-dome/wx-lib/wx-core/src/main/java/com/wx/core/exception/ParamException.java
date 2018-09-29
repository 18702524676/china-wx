package com.wx.core.exception;


import com.wx.core.enums.EnumError;
import lombok.Data;

/**
 * @ClassName ParamException
 * @Author wx
 * @Description 参数验证异常
 * @Date 2018-08-15-20:52
 */
@Data
public class ParamException extends RuntimeException {
    private static final long serialVersionUID = 850992167974250127L;

    /** 错误编码 */
    private Integer errCode;

    public ParamException(EnumError enumError) {
        super(enumError.getMessageError());
        this.errCode = enumError.getCodeError();
    }

    public ParamException(Integer code, String message) {
        super(message);
        this.errCode = code;
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException() {
    }
}
