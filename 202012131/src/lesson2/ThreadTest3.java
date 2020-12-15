package lesson2;

public class ThreadTest3 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t");
            }
        });//申请系统创建线程t
        t.start(); //申请系统执行线程t：创建态转变为就绪态，由系统决定什么时候转变为运行态
        System.out.println("main");
        //t和main同时并发执行，但因为main正在运行态执行代码，所以很快执行后续代码
        //打印t和main本应该是乱序随机，但是先打印main的概率更高
    }

}
