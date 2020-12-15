package lesson2;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread.interrupted()返回当前线程的中断标志位，然后重置中断标志位
                for(int i = 0; i < 10000;i++) {
                    System.out.println(i+":"+Thread.interrupted());
                }

//                for(int i = 0; i < 10000 && !Thread.interrupted();i++) {
//                    try {
//                        System.out.println(i);
//                        Thread.sleep(1000);  //模拟中断线程
//                        //Thread.sleep(100000); //通过标志位自行实现，无法解决线程阻塞导致无法中断
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//                for(int i = 0; i < 10000 && !Thread.currentThread().isInterrupted();i++) {
//                    try {
//                        System.out.println(i);
//                        Thread.sleep(1000);  //模拟中断线程
//                        //Thread.sleep(100000); //通过标志位自行实现，无法解决线程阻塞导致无法中断
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//                try {
//                    for (int i = 0; i < 10000 && !Thread.currentThread().isInterrupted(); i++) {
//                        System.out.println(i);
//                        Thread.sleep(1000);  //模拟中断线程
//                        //Thread.sleep(100000); //通过标志位自行实现，无法解决线程阻塞导致无法中断
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        t.start(); //线程启动 中断标志位为false
        System.out.println("t_start");
        //模拟t执行了5秒还没有结束，要中断、停止t线程
        //让t线程中断掉,停止
        Thread.sleep(5000);
        //告诉t线程要中断了（设置t线程的中断标志位为true） 由t的代码自己决定要不要中断
        //如果t线程处于阻塞状态，会抛出InterruptedException异常，并且重置t线程的中断标志位
        t.interrupt();
        System.out.println("t_stop");

    }
}
