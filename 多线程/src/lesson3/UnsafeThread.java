package lesson3;

public class UnsafeThread {
    private static int COUNT = 0;
    public static void main(String[] args) throws InterruptedException {
        Class cl = UnsafeThread.class;
        //有一个COUNT = 0 变量，同时启动20个 线程，每个线程执行1000次，每次循环COUNT++
        //等这20个子线程执行完毕后，再main线程打印COUNT（预期2000）
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000;j++) {
                        synchronized (cl){ //加锁
                            COUNT++;
                        }
                    }
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        //让main线程阻塞等待所有的20个线程执行完毕
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(COUNT);
        //执行结果每次都是小于20000
    }
}
