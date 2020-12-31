package lesson5;

public class MyBlockingQueue<T> { //定义成泛型 <T>

    //使用数组实现循环队列
    private  Object[] queue;
    //存放元素的索引
    private int putIndex;
    //取元素的索引
    private int takeIndex;
    //当前存放元素的数量
    private int size;

    public MyBlockingQueue(int len) {
        queue = new Object[len]; //指定长度

    }

    /**
     *存放元素
     * 需要考虑:
     * 1.putIndex超过数组的长度
     * 2.size达到数组最大长度
     */
    public void put(T e) {
        queue[putIndex] = e; //存放到数组中放元素的位置e


    }

}
