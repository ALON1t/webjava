package lesson2;
//改类名 shift+F6
public class ThreadTest1 { //线程让步
    public static void main(String[] args) {
        for(int i = 0; i < 20;i++) { //同时启动20个线程
            final int n = i; //匿名内部类使用外部的变量，必须是final修饰
            //子线程休眠3秒之后同时无序执行（系统调度）
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n);
//                    try {
//                        Thread.sleep(3000);//休眠3秒
//                        //System.out.println(i);
//                        System.out.println(n);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
            t.start();
        }
        System.out.println("OK");
        //运行之后先打印OK
    }
}
