package lesson2;

public class ThreadTest1 {
    public static void main(String[] args) {
        for (int i = 0; i < 20;i++) {
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
        //main线程子线程同时执行
        System.out.println("ok"); //先打印ok
    }
}
