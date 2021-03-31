package org.example.exception;

/**
 * 自定义异常在
 * 自定义异常类：业务代码抛自定义异常或者其他异常
 * 自定义异常返回给定的错误吗，其他异常返回其他错误码
 */
public class AppException extends RuntimeException{

    //给前端返回的json字符串中，保存错误码
    private String code;
    public AppException(String code,String message) {
        //两种方法均可
//        super(message);
//        this.code = code;
        this(code,message,null); //当前类的构造方法
    }

    public AppException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {//使用
        return code;
    }
}
