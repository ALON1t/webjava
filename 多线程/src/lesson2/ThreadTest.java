package lesson2;

public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20;i++) { //TIMED-WAITING状态  限时等待
            final int n = i;
            //子线程休眠三秒之后，同时执行（无序执行，由系统调度）
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {//内部类使用外部的变量，必须用final修饰
                    try{
                        Thread.sleep(3000);
                        System.out.println(n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        while (true) {
            System.out.println("处于RUNNABLE状态 可执行状态");
        }
        //该共有两个线程
    }
}
