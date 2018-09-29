package com.wx.commons.enums.error;


import com.wx.core.enums.EnumError;

/**
 * @Author wx
 * @Description 公用异常枚举集合
 * @Date 2018-8-30
 */
public class CommonEnumError{

    
    /**
     * @Author wx
     * @Description JSON转换异常枚举
     * @Date 2018-8-30
     */
    public enum  JsonConvertError implements EnumError {
        /**  json字符串转换为对象时异常 */
        JSON_CONVERT_ERROR_0(0,"json字符串转换为对象时异常"),
        /**  对象转换成json字符串时异常 */
        JSON_CONVERT_ERROR_1(1,"对象转换成json字符串时异常"),
        /**  json字符串转换成带泛型对象时异常 */
        JSON_CONVERT_ERROR_2(2,"json字符串转换成带泛型对象时异常"),
        /**  json字符串转换为map时异常 */
        JSON_CONVERT_ERROR_3(3,"json字符串转换为map时异常"),
        /**  json字符串转换为指定类型的Map时异常 */
        JSON_CONVERT_ERROR_4(4,"json字符串转换为指定类型的Map时异常"),
        /**  json字符串转换为指定类型的list时异常 */
        JSON_CONVERT_ERROR_5(5,"json字符串转换为指定类型的list时异常"),
        /**  Map转换为对象时异常 */
        JSON_CONVERT_ERROR_6(6," Map转换为对象时异常"),
        ;
        /**  错误状态码 */
        private Integer codeError;
        /** 错误提示信息 */
        private  String messageError;
        private JsonConvertError(int codeError,String messageError){
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

    /**
     * @Author wx
     * @Description 比较器异常枚举
     * @Date 2018-8-30
     */
    public enum CompareError implements EnumError{
        COMPARE_ERROR_0(0,"比较数据不能为空"),
        ;
        /**  错误状态码 */
        private Integer codeError;
        /** 错误提示信息 */
        private  String messageError;
        private CompareError(int codeError,String messageError){
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
