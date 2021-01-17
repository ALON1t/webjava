package lesson5;

import java.util.TimerTask;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(
//                5,  //corePoolSize:核心线程数 ——》正式员工
//                10, //最大线程数——》正式员工+临时工
//                60,
//                TimeUnit.SECONDS, //idle线程的空闲时间 ：临时工最大的存活时间，超过时间就解雇
//                new LinkedBlockingQueue<>(), //阻塞队列：任务存放的地方（快递仓库）
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        return new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                System.out.println(Thread.currentThread().getName() + "任务执行了");
////                                long start = System.currentTimeMillis();
////                                r.run();
////                                long end = System.currentTimeMillis();
////                                System.out.println("任务执行了" + (end - start) + "毫秒");
//                            }
//                        });
//                    }
//                }, //创建线程的工厂类：线程池创建线程时，调用该工厂的方法创建线程-》招聘员工的标准
//                new ThreadPoolExecutor.AbortPolicy()
//                /**
//                 * 拒绝策略：达到最大线程数且阻塞队列已满，采取拒绝策略
//                 * AbortPolicy:直接抛RejectExecutionException(不提供handler时的默认策略)
//                 * CallerRunsPolicy:谁（某个线程）交给我（线程池）任务，我拒绝执行，由谁自己执行
//                 * DiscardPolicy:交给我的任务,直接丢弃掉
//                 * DiscardOldestPolicy:丢弃阻塞队列中最旧的任务
//                 */
//        ); //线程池创建以后，只要有任务就执行
//        for(int i = 0; i < 1000;i++) {
//            //线程池执行任务 execute()  submit()->提交执行一个任务
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
        /**
         * 线程池有四个快捷创建方式：（实际工作不使用，作为面试要了解）
         * 阿里Java开发手册，说明不能直接使用以下方法创建线程池
         * 实际工作中需要ThreadPoolExecutor,构造参数自己指定
         */
        ExecutorService pool2 = Executors.newSingleThreadExecutor(); //单线程池
        ExecutorService pool3 = Executors.newCachedThreadPool(); //缓存线程池
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(4);//计划任务线程池
        ExecutorService pool5 = Executors.newFixedThreadPool(4);//固定大小线程池

//        pool4.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        },2,TimeUnit.SECONDS);
        pool4.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        },2,1,TimeUnit.SECONDS);
    }

}
