package lesson1;

public class ThreadLook1 {
    public static void main(String[]args){
        //第一种创建线程的方式：使用Thread类，重写run()
        Thread t = new Thread("main中的子线程") {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        //线程要启动，必须使用start() ==》告诉系统调度本线程
        t.start();
        System.out.println(t.getId()); //id
        System.out.println(t.getName()); //name
        System.out.println(t.getPriority()); //优先级
        System.out.println(t.getState()); //状态
        System.out.println(t.isAlive());  //是否还存活  只要未被终止，都是存活的
        System.out.println(t.isDaemon()); //是否后台线程
        System.out.println(t.isInterrupted()); //是否被中断
    }
}
