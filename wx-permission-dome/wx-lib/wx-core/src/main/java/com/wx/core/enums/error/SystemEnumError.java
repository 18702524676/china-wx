package com.wx.core.enums.error;


import com.wx.core.enums.EnumError;

/**
 * @Author wx
 * @Description 系统异常枚举集合
 * @Date 2018-8-30
 */
public class SystemEnumError {

    /**
     * @Author wx
     * @Description 系统异常枚举
     * @Date 2018-8-30
     */
    public enum SystemError implements EnumError {
        /**  系统繁忙 */
        SYSTEM_ERROR_500(500,"系统繁忙"),
        /**  参数数据不能为空 */
        SYSTEM_ERROR_0(0,"参数数据不能为空"),
        /**  枚举对象不能为空 */
        SYSTEM_ERROR_1(1,"枚举对象不能为空"),
        /**  Mapper对象不存在 */
        SYSTEM_ERROR_2(2,"对象不存在"),
        ;

        /**  错误状态码 */
        private Integer codeError;
        /** 错误提示信息 */
        private  String messageError;

        private SystemError(int codeError,String messageError){
            this.codeError = codeError;
            this.messageError = messageError;
        }
        @Override
        public Integer getCodeError() {
            return codeError;
        }

        @Override
        public String getMessageError() {
            return messageError;
        }
    }

}
