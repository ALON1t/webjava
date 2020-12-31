package lesson5;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,  //corePoolSize:核心线程数 ——》正式员工
                10, //最大线程数——》正式员工+临时工
                60,
                TimeUnit.SECONDS, //idle线程的空闲时间 ：临时工最大的存活时间，超过时间就解雇
                new LinkedBlockingQueue<>(), //阻塞队列：任务存放的地方（快递仓库）
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {

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
                }, //创建线程的工厂类：线程池创建线程时，调用该工厂的方法创建线程-》招聘员工的标准
                new ThreadPoolExecutor.AbortPolicy()
                /**
                 * 拒绝策略：达到最大线程数且阻塞队列已满，采取拒绝策略
                 * AbortPolicy:直接抛RejectExecutionException(不提供handler时的默认策略)
                 * CallerRunsPolicy:谁（某个线程）交给我（线程池）任务，我拒绝执行，由谁自己执行
                 * DiscardPolicy:交给我的任务,直接丢弃掉
                 * DiscardOldestPolicy:丢弃阻塞队列中最旧的任务
                 */
        );
        for(int i = 0; i < 1000;i++) {
            //线程池执行任务 execute()  submit()->提交执行一个任务
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
