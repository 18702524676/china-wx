package com.wx.core.enums.status;


import com.wx.core.enums.EnumStatus;

/**
 * @Author wx
 * @Description 系统状态枚举集合
 * @Date 2018-8-30
 */
public class SystemEnumStatus {


    /**
     * @Author wx
     * @Description YES or NO枚举状态
     * @Date 2018-8-30
     */
    public enum  YesOrNo implements EnumStatus {
        /**  否 */
        NO_0(0,"否"),
        /**  是 */
        YES_1(1,"是"),
        ;
        /** 状态标识 */
        private  Integer code;
        /** 描述 */
        private  String message;

        private YesOrNo(Integer code, String message){
            this.code = code;
            this.message = message;
        }
        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }

    /**
     * @Author wx
     * @Description 系统状态枚举
     * @Date 2018-8-25
     */
    public enum  SYS_STATUS implements EnumStatus {
        SYS_STATUS_0(0,"冻结"),
        SYS_STATUS_1(1,"正常"),
        SYS_STATUS_2(2,"删除"),
        ;
        /** 状态标识 */
        private  Integer code;
        /** 描述 */
        private  String message;

        private SYS_STATUS(Integer code, String message){
            this.code = code;
            this.message = message;
        }

        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
