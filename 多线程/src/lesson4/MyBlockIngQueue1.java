package lesson4;
/**
 * 实现阻塞队列：
 * 1. 线程安全问题：在多线程下，put、take不具有原子性，4个属性，不具有可见性
 * 2. put操作，如果存满了，需要阻塞等待;take如果是空，需要阻塞等待
 * @param <T>
 */
public class MyBlockIngQueue1<T> {
    private Object[] queue; //使用数组实现循环队列
    private  int putIndex; //存放元素的索引
    private int takeIndex; //取元素的索引
    private int size; //当前存放元素的数量

    public  MyBlockIngQueue1(int len) {
        queue = new Object[len];
    }

    //存放元素，
    // 需要考虑 1.putIndex超过数组的长度 2.size打到数组最大长度
    public synchronized void put(T e) throws InterruptedException { //synchronized保证线程安全
        //当阻塞等待，到被唤醒并再次竞争成功对象锁，亏负后往下继续执行时
        //条件可能会被其他线程修改  使用while
        while (size == queue.length) { //放满了开始阻塞等待
            this.wait(); //或者wait()
            //throw new RuntimeException("超过最大长度");
        }
        //存放到数组中放元素的位置
        queue[putIndex] = e;
        //存放位置超过数组的最大索引，需要取模操作，将其放在0位置
        putIndex = (putIndex + 1) % queue.length;
        size++;
        this.notifyAll(); //notifyAll()  和synchronized加锁对象一样
    }

    //取元素
    public synchronized T take() throws InterruptedException {
        while (size == 0) {
            wait();
            //throw new RuntimeException("已经没有存放元素了");
        }
        T t = (T) queue[takeIndex];
        queue[takeIndex] = null; //取出来后将该数组的内容置为null
        takeIndex = (takeIndex + 1) % queue.length;
        size--;
        notifyAll(); //唤醒所有线程
        return t;
    }

    //
    public synchronized int size() {
        return size;
    }

    public static void main(String[] args) {
        MyBlockIngQueue1<Integer> queue1 = new MyBlockIngQueue1<>(10);
        //多线程的调试方式：1.打印语句
        //              2.jconsole/jstack
        //              3.debug在有些场景不一定适用
        for (int i =  0; i < 3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 100;j++) {
                            queue1.put(j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i =  0; i < 3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (;;) {
                            int i = queue1.take();
                            System.out.println(Thread.currentThread().getName() + ":" +i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


//        MyBlockIngQueue1<String> queue1 = new MyBlockIngQueue1<>(10);
//        for (int i = 0; i < 10;i++) {
//            queue1.put(String.valueOf(i));
//        }
////        queue1.put("A");
////        queue1.put("B");
////        queue1.put("C");
//        queue1.take();
//        queue1.put("10");
//        for (Object o : queue1.queue) {
//            System.out.println(o);
//        }
    }

}
