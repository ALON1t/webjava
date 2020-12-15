package lesson2;

public class JoinTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("t");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });//申请系统创建线程t
        t.start(); //申请系统执行线程t：创建态转变为就绪态，由系统决定什么时候转变为运行态

        //t.join(); //当前线程无条件等待，一直等到t线程执行完毕，当前线程再往后执行

        //t.join(1000);//等待1秒  先等待一秒打印main后再等待两秒打印t
        //当前main线程限时等待，直到t线程执行完毕，或给定时间已到

        t.join(4000);//先等待t执行完，系统调度t由就绪态转变为运行态的时间，加上t的运行时间：等3秒打印t，之后直接打印main
        System.out.println("main");
        //打印t  main
    }
}
