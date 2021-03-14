package lesson2;

public class StopThreadTest {

    private static volatile boolean STOP = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //执行任务，执行时间较长
                    for (int i = 0; i < 1000 && !STOP;i++) {
                        System.out.println(i);
                        //模拟中断线程
                        Thread.sleep(1000);
                        //通过标志位自行实现无法解决线程阻塞，导致无法中断
                        //Thread.sleep(100000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        System.out.println("t start");
        //模拟t执行5秒之后还没有结束，要中断，停止t线程
        Thread.sleep(5000);
        STOP = true;
        System.out.println("t stop");
        //先打印t start,每隔一秒打印一个数字，五秒后，打印t stop
    }
}
