package lesson2;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for(int i = 0; i < 20;i++) { //同时启动20个线程
            final int n = i; //内部类使用外部的变量，必须是final修饰
            //子线程休眠3秒之后同时无序执行（系统调度）
            //new Thread 操作稍微有点耗时
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n); //随机打印
                }
            });
        }
        for(Thread t :threads) {
            t.start();
            //t.join();  //0-19顺序打印  一个启动后并等待执行完，再启动并等待下一个
        }
        //实际工作中不会这么用，学习时简单满足功能：子线程执行完再执行主线程代码+
//        while (Thread.activeCount() > 2) { //当前线程存活数 大于1  debug方式运行>1 run方式>2（idea会自动启动一个main线程）
//            Thread.yield();//让当前线程让步：从运行态转变为就绪态
             //乱序
//        }

//        for(Thread t : threads) {
//            t.join();  //最后打印ok   同时执行20个线程，再等待所有线程执行完毕
//        }

        System.out.println("OK");


//        for(int i = 0; i < 20;i++) { //同时启动20个线程
//            final int n = i; //内部类使用外部的变量，必须是final修饰
//            //子线程休眠3秒之后同时无序执行（系统调度）
//            //new Thread 操作稍微有点耗时
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(n);
//                }
//            });
//            t.start();
//        }
//        System.out.println("OK");
        //同时无序打印
    }
}
