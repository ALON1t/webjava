package lesson3;

public class safeThread {
    private static int COUNT = 0;
    //一个变量COUNT=0,同时启动20个线程，每个线程循环1000次，每次循环执行COUNT++
    //等待20个子线程执行完毕之后，在main线程打印COUNT（预期20000）
     public static void main(String[] args) throws InterruptedException {
         Class clazz = safeThread.class;
        //尽量同时启动，不让new Thread 耗时影响
        Thread[] threads = new Thread[20];
        for(int i = 0; i < 20;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 1000;j++) {
                        synchronized (clazz){  //实现安全问题
                            COUNT++;
                        }
                    }
                }
            });
        }
        for(Thread t : threads) {
            t.start();
        }
        //让main线程阻塞等待所有20个子线程执行完毕
        for(Thread t : threads) {
            t.join();
        }
        System.out.println(COUNT);
    }
}

