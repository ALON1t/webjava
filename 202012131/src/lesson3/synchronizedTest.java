package lesson3;

public class synchronizedTest {
    private static int count;
//    public synchronized static void increment(){
//        count++;
//    }
    public  static synchronized void increment(){
        count++;
    }
    public static void main(String[] args) throws InterruptedException {

        Class clazz = synchronizedTest.class;
        //尽量同时启动，不让new Thread 耗时影响
        Thread[] threads = new Thread[20];
        for(int i = 0; i < 19;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 1000;j++) {
                        //synchronized (clazz){  //实现安全问题
                            increment();
                       // }
                    }
                }
            });
        }


        //尽量同时启动，不让new Thread 耗时影响
        //threads = new Thread[10];
//        for(int i = 0; i < 1;i++) {
//            threads[19+i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int j = 0; j < 1000;j++) {
//                        count++;
//                    }
//                }
//            });
//        }
        for(Thread t : threads) {
            if(t != null)
                t.start();
        }
        //让main线程阻塞等待所有20个子线程执行完毕
        for(Thread t : threads) {
            if(t != null)
                t.join();
        }
        System.out.println(count);
//        //两段代码可以并行并发
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (synchronizedTest.class){ //加锁                    increment();
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() { //未加锁
//            @Override
//            public void run() {
//               increment();
//            }
//        }).start();
    }
}
