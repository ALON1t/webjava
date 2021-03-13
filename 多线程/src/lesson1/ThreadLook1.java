package lesson1;

public class ThreadLook1 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("创建线程方法一：继承Thread类");
        }
    }
    static class MyThread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("创建线程方法二：实现Runnable接口");
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        Thread tT = new Thread(new MyThread1());
        tT.start();

        Thread t1 = new Thread();
        Thread t2 = new Thread(new MyThread1());
        Thread t3 = new Thread("线程名称");
        Thread t4 = new Thread(new MyThread1(),"线程名称");
    }




    public static void main1(String[] args) {

        //创建线程 方法一
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("使用匿名类创建Thread子类对象");
            }
        };
        t1.start();

        //创建线程 方法二
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用匿名类创建Runnable子类对象");
            }
        });
        t2.start();

        //创建线程 方法三
        Thread t3 = new Thread(() -> System.out.println("使用匿名类创建Thread子类对象"));
        Thread t4 = new Thread(() ->{
            System.out.println("使用匿名类创建Thread子类对象");
        });


    }
}
