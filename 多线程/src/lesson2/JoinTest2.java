package lesson2;

public class JoinTest2 {
    public static void main(String[] args) throws InterruptedException {
        //new Thread稍微有点耗时
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); //等待三秒
                    System.out.println("t");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }); //申请系统创建线程t
//        t.start(); //申请系统执行线程t，创建态转变为就绪态，由系统决定什么时候转变为运行态(耗时)
//        t.join();  //当前线程(main线程)无条件等待，直到t线程执行完毕后再往后执行
//        System.out.println("main");
//        //先等三秒，再打印t，再打印main

//        t.start();
//        t.join(1000); //当前线程限时等待，直到t线程执行完毕，或者等待时间结束
//        System.out.println("main");
//        //先等一秒打印main,在等两秒打印t

        t.start();
        t.join(4000); //当前线程限时等待，直到t线程执行完毕，或者等待时间结束
        System.out.println("main");
        //等三秒(系统调度t由就绪态变为运行态的时间 + t运行的时间)打印t,然后直接打印main
    }
}
