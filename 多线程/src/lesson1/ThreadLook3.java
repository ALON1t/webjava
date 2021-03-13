package lesson1;

public class ThreadLook3 {
    public static void main(String[] args) {
         Thread t = new Thread(new Runnable() { //子线程
             @Override
             public void run() {
                 for(int i = 0; i < 10;i++) {
                     if(i == 6) {
                         //某个线程抛run异常 整个线程结束 但是不会影响其他线程
                         //线程中处理异常的方式 线程对象.setUncaughtExceptionHandler()
                         //或者自己在run()方法里捕获
                         throw new RuntimeException();
                     }
                     System.out.println(i);
                 }
             }
         },"main子线程"); //命名
         t.start();
         while (true) { //main线程

         }
         //子线程结束 main线程继续
    }
}
