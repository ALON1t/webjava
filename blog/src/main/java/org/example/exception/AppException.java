package org.example.exception;

/**
 * 自定义异常类
 * 自定义异常类：业务代码抛自定义异常或者其他异常
 * 自定义异常返回给定的错误吗，其他异常返回其他错误码
 */

/**
 * 异常：
 * 关系：
 *  1.Throwable  (顶级父类)
 *     1.1 Error（比较大的错误，程序处理不好）
 *     1.2 Exception  （继承Exception的为编译时异常）
 *         1.2.1 其他
 *         1.2.2 RuntimeException （继承RuntimeException的为运行时异常）
 *
 *         （1）区别：编译时异常需要显示捕获
 *         （2）使用场景：自己无法处理，交给调用方处理，就抛出编译时异常
 *  如何使用异常：1.自己处理  使用try{} catch(要捕获的异常 e){}
 *                要注意异常类是否能捕获到
 *             2.交给调用方处理
 *                编译时异常向上抛  /运行时异常隐式向上抛4
 *
 *
 *   tomcat启动 --》执行tomcat中启动类的main()方法
 *                  通过反射创建Servlet对象（class.newInstance()）
 *                  servlet.init()
 *                  ...
 *                  启动完毕
 *
 *    http请求
 *       pool = new tomcat线程池()
 *       //每次http请求都是一个任务提交到线程池（线程中，假如方法调用链不处理异常的话，
 *       //也遵循方法执行流程，run()方法中没有捕获，线程结束）
 *       pool.execute(new Runnable()){
 *           @Override
 *           public void run() {
 *               servlet.service();
 *           }
 *       }
 *方法的执行流程：查看打印的堆栈信息
 *  方法从下往上调用  方法执行完毕从上往下返回（执行完毕，包括方法异常）
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
