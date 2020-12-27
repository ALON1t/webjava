package lesson4;

/**
 * 面包店
 * 10个生产者 每次生产3个
 * 20个消费者 每次消费1个
 * 升级版需求：面包师傅每个最多生产30次
 *           消费者不再一直消费，把900个面包消费完结束
 *   隐藏信息：面包店每天生产10*30*3=900个
 *           消费者把900个面包消费完结束
 */
public class BreadShop {
    private static int COUNT; //面包店的库存 最大100
    private static int SUM;   //面包店生产的总数，不会消费 最大900


    private static int CONSUMER_NUM =10;  //消费者数量
    private static int CONSUME_COUNT = 5;  //每次消费的面包数
    private static int PRODUCER_NUM = 5;  //生产者数量
    private static int PRODUCER_TIMES = 10;  //生产者生产次数
    private static int PRODUCER_COUNT = 3;   //每次生产的面包数
    private static int MAX_COUNT = 100; //面包店的库存 最大100
    //消费者
    public static class Consumer implements Runnable{

        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            //一直消费
            try {
                while (true){
                    synchronized (BreadShop.class) {
                        if(SUM == PRODUCER_NUM*PRODUCER_TIMES*PRODUCER_COUNT)
                            break;
                        if(COUNT == 0) { //库存到达下线，不能继续消费，需要阻塞等待
                            BreadShop.class.wait(); //释放对象锁
                        } else {
                            //库存大于0  允许消费
                            System.out.printf("消费者 %s 消费者消费了1个面包\n",name);
                            COUNT-= CONSUME_COUNT;
                            SUM++;
                            BreadShop.class.notifyAll(); //使用notifyAll()
                            //模拟消费的耗时
                            Thread.sleep(1000);
                        }
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //生产者
    public static class Producers implements Runnable{

        private String name;

        public Producers(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            //一直生产
            try {
                for(int i = 0; i < PRODUCER_TIMES;i++) {
                    synchronized (BreadShop.class) {
                        if(COUNT + PRODUCER_COUNT > MAX_COUNT) { //库存到达上线，不能继续生产，需要阻塞等待
                            BreadShop.class.wait(); //释放对象锁
                        } else {
                            //库存<98 允许生产
                            System.out.printf("生产者 %s 生产者生产%s个面包\n",name,PRODUCER_COUNT);
                            COUNT += PRODUCER_COUNT;
                            SUM += PRODUCER_COUNT;
                            BreadShop.class.notifyAll(); //使用notifyAll()
                            //模拟生产的耗时
                            Thread.sleep(1000);
                        }
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //同时启动20个消费者线程
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Consumer(String.valueOf(i)));
        }

        //同时启动10个生产者线程
        Thread[] threads1 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(new Producers(String.valueOf(i)));
        }

        //启动
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads1) {
            t.start();
        }
    }
}
