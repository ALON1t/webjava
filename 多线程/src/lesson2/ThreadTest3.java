package lesson2;

public class ThreadTest3 {
    public static void main(String[] args) {
        //new Thread稍微有点耗时
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t");
            }
        }); //申请系统创建线程t
        t.start(); //申请系统执行线程t，创建态转变为就绪态，由系统决定什么时候转变为运行态(耗时)
        //t和main同时并行并发执行，但因为main正在运行态执行代码，所以后续代码很快执行
        //打印main和t本来是随机执行，但是先打印main的概率较高
        System.out.println("main");
    }
}
