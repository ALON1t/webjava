package lesson2;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20;i++) {
            final int n = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {//内部类使用外部的变量，必须用final修饰
                    System.out.println(n);
                }
            });
        }
        for (Thread t : threads) {
            t.start();
            t.join();
        }
        //法一
        for (Thread t : threads) {
            t.join();
        }

        //法二
//        while (Thread.activeCount() > 1) { //存活的线程数
//            Thread.yield(); //让当前线程让步：从运行态转变为就绪态  使最后打印ok
//        }
        //idea会自动启动一个线程
        //所以使用run 写Thread.activeCount() > 2
        //使用debug 写Thread.activeCount() > 1
        //否则不会打印ok

        System.out.println("ok");

//        for (int i = 0; i < 20;i++) {
//            final int n = i;
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {//内部类使用外部的变量，必须用final修饰
//                        System.out.println(n);
//                }
//            });
//            t.start();
//        }
//        //main线程子线程同时执行
//        System.out.println("ok");
    }
}
