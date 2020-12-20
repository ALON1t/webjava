package lesson2;

public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程
                //Thread.interrupted()返回当前线程的中断标志位，然后重置中断标志位
                for(int i = 0; i < 10;i++) {
                    System.out.println(i+":"+Thread.interrupted());
                }
            }
        });
        //main线程
        t.start(); //线程启动 中断标志位为false
        System.out.println("t_start");
        //模拟t执行了5秒还没有结束，要中断、停止t线程
        //让t线程中断掉,停止

        //告诉t线程要中断了（设置t线程的中断标志位为true） 由t的代码自己决定要不要中断
        //如果t线程处于阻塞状态，会抛出InterruptedException异常，并且重置t线程的中断标志位
        t.interrupt();
        System.out.println("t_stop");
//        t_start  (结果)8
//        t_stop
//        0:false
//        1:true
//        2:false
    }
}
