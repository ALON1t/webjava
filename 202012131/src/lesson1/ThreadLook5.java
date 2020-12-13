package lesson1;

public class ThreadLook5 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 1000;i++) {
                    /**
                     * 某个线程异常，如果跑到run方法，整个线程直接终止
                     * 但是不会影响其他线程
                     * 线程中，异常处理的方式：线程对象.setUncaughtExceptionHandler()
                     */

                    if(i == 20) {
                        throw new RuntimeException();
                    }
                    System.out.println(i);
                }
            }
        };
        t.start();
    }
}
