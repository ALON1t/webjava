package lesson5;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    ThreadPoolExecutor pool = new ThreadPoolExecutor(
            5,  //corePoolSize:核心线程数 ——》正式员工
            10, //最大线程数——》正式员工+临时工
            60,
            TimeUnit.SECONDS, //idle线程的空闲时间 ：临时工最大的存活时间，超过时间就解雇
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread();
                }
            },
            new ThreadPoolExecutor.AbortPolicy()
    );
}
