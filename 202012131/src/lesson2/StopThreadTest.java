package lesson2;

public class StopThreadTest { //中断
    private static volatile boolean STOP = false;//静态变量——》大写  关键字：volatile
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //...执行任务，执行时间可能比较长
                for(int i = 0; i < 10000 && !STOP;i++) {
                    try {
                        System.out.println(i);
                        Thread.sleep(1000);  //模拟中断线程
                        //Thread.sleep(100000); //通过标志位自行实现，无法解决线程阻塞导致无法中断
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        System.out.println("t_start");
        //模拟t执行了5秒还没有结束，要中断、停止t线程
        //让t线程中断掉,停止
        Thread.sleep(5000);
        STOP = true;
        System.out.println("t_stop");
    }
}
