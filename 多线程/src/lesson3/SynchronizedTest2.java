package lesson3;

public class SynchronizedTest2 {
    private static int STUDENT = 50;
    public static void main(String[] args) throws InterruptedException {
        Class cl = SynchronizedTest2.class;
        Thread[] thacher = new Thread[3];
        for (int i = 0; i < 3;i++) {
            thacher[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (STUDENT > 0) {
                        synchronized (cl){ //加锁
                            if (STUDENT > 0) {
                                STUDENT--;
                                System.out.println(Thread.currentThread().getName() + ",还有" + STUDENT);
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        for (Thread t : thacher) {
            t.start();
        }
    }

}
