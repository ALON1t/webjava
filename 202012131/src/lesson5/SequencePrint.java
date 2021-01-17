package lesson5;

public class SequencePrint {

    /**
     * 有三个线程，线程名称分别为：a，b，c。
     * 每个线程打印自己的名称。
     * 需要让他们同时启动，并按 c，b，a的顺序打印
     */
    public static void main(String[] args) {


        Thread c = new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }, "C");
        Thread b = new Thread(()->{
            try {
                c.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");
        Thread a = new Thread(()->{
            try {
                b.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");
        //一定是先同时执行
        a.start();
        b.start();
        c.start();
    }
}
