package lesson1;

public class ThreadLook1 {
    public static void main(String[]args){
        //第一种创建线程的方式：使用Thread类，重写run()
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        //线程要启动，必须使用start() ==》告诉系统调度本线程
        t.start();
    }
}
