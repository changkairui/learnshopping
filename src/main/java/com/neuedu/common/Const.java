package com.neuedu.common;

public class Const {
    public static final Integer SUCCESS_CODE=0;
    public static final Integer SUCCESS_ERROR=1;

    public static final Integer USER_ROLE_CUSTOMER=0; //普通用户

    public static final Integer USER_ROLE_ADMIN=1; //管理员

    public static final String CURRENTUSER="CURRENTUSER";//当前登录用户

    public static final String USERNAME="username";

    public static final String EMAIL="email";

    public enum RoleEnum{
        ROLE_ADMIN(1,"管理员"),
        ROLE_CUSTPMER(0,"普通用户")
        ;
        private int code;
        private String desc;
        private RoleEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum ResponseCodeEnum{

        NEED_LOGIN(2,"需要登录"),
        NO_PRIVILEGE(3,"无权限操作")
        ;

        private int code;
        private String desc;
        private ResponseCodeEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
