package lesson3;

public class SynchronizedTest {
    private static int COUNT = 0;
    //synchronized加在static之前之后都可以
//    public synchronized static void increment() {
//        COUNT++;
//    }

    public static synchronized void decrement(){
        COUNT--;
    }
    public static synchronized void increment(){
        COUNT++;
        decrement();
    }


    public static void increment1() {
        synchronized (new SynchronizedTest()) {
            COUNT++;
        }
    }
    //对this对象加锁
    public synchronized void increment11(){
        COUNT++;
    }




    ////有一个COUNT = 0 变量，同时启动20个 线程，每个线程执行1000次，每次循环COUNT++
    //        //等这20个子线程执行完毕后，再main线程打印COUNT（预期2000）
    public static void main(String[] args) throws InterruptedException {
        Class cl = SynchronizedTest.class;
        SynchronizedTest st = new SynchronizedTest();
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
//                        synchronized (cl) { //加锁
//                            COUNT++;
//                        }
//                        increment();//静态同步方法调用
//                        st.increment(); //实例方法同步调用
                          synchronized (st) {
                              COUNT++;
                          }
                    }
                }
            });
        }
        for (int i = 0; i < 1; i++) {
            threads[19 + i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        st.increment();
                    }
                }
            });
        }


        for (Thread t : threads) {
            if (t != null)
             t.start();
        }

        for (Thread t : threads) {
            if (t != null)
             t.join();
        }
        System.out.println(COUNT);
    }
}
