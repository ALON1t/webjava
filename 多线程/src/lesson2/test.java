package lesson2;

public class test {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10;i++) {
                    System.out.println(i + ":" + Thread.interrupted());
                }

            }
        });
        t.start(); //线程启动，中断标志位为false
        System.out.println("t start");
        //模拟t执行5秒之后还没有结束，要中断，停止t线程
        t.interrupt();//告诉t线程要中断（设置t线程的中断标志位为true），由t的代码自己决定是否要中断
        //如果线程处于阻塞状态，会抛出InterruptException 异常
        System.out.println("t stop");
    }
}
