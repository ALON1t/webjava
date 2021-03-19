package lesson4;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        //线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5, //核心线程数 -->正式员工
                10, //最大线程数 --->正式员工 + 临时工
                60, //
                TimeUnit.SECONDS, //idle线程的空闲时间 --->临时工最大的存活时间，超过时间就解雇
                new LinkedBlockingQueue<>(), //阻塞队列：任务存放的地方--->快递仓库
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        //return new Thread(r);

                        //打印
                        return new Thread(new Runnable() {
                            @Override
                            public void run() {
                                long start = System.currentTimeMillis();
                                r.run();
                                long end = System.currentTimeMillis();
                                System.out.println("任务执行了" + (end - start) + "毫秒");
                            }
                        });
                    }
                }, //创建线程的工厂类：线程池创建线程时，调用该工厂的方法来创建线程 --->招聘员工的标准
                new ThreadPoolExecutor.AbortPolicy()
                /**
                 * 拒绝策略：达到最大线程数且阻塞队列已满采取的拒绝策略
                 *        1.AbortPolicy():直接抛RejectExecutionException (不提供handLer时的默认策略)
                 *        2.CallerRunsPolicy():谁（某个线程）交给我（线程池）任务，我（线程池）拒绝执行，由谁（某个线程）自己执行
                 *        3.DiscardPolicy():交给我的任务直接丢弃掉
                 *        4.DiscardOldestPolicy():丢弃阻塞队列中最旧的任务
                 */
        ); //线程池创建以后，只要有任务就自动执行
        for (int i = 0; i < 20;i++) {
            /**
             * 线程池执行任务:
             *     1.execute
             *     2.submit --》提交执行一个任务
             */
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ExecutorService pool1 = Executors.newSingleThreadExecutor();//单线程池
        ExecutorService pool2 = Executors.newCachedThreadPool();// 缓存的线程池
        ScheduledExecutorService pool3 = Executors.newScheduledThreadPool(4); //计划任务线程池
        ExecutorService pool4 = Executors.newFixedThreadPool(4); //固定大小的线程池

//        pool3.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hhh");
//            }
//        },2,TimeUnit.SECONDS);

        pool3.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
            }
        },2,1,TimeUnit.SECONDS); //间隔两秒之后执行，之后每间隔一秒执行一次
    }

}
