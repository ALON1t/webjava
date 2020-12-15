package lesson2;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t");
            }
        });//申请系统创建线程t
        t.start(); //申请系统执行线程t：创建态转变为就绪态，由系统决定什么时候转变为运行态
        t.join(); //当前线程无条件等待，一直等到t线程执行完毕，当前线程再往后执行
        System.out.println("main");
        //打印t  main
    }
}
