package lesson3;



public class SynchronizedTest3 {

    private static int COUNT = 50;
    public static void main(String[] args) {
        new Thread(new Task(10)).start();
        new Thread(new Task(20)).start();
        new Thread(new Task(20)).start();

    }

    private static class Task implements Runnable{
        private int number; //控制一个老师可以安排的数量

        public Task(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100;i++) {
                synchronized (Task.class) {
                    if (COUNT > 0 && number > 0) {
                        COUNT--;
                        number--;
                        System.out.printf("%s:count=%s,num=%s\n" ,Thread.currentThread().getName(),COUNT,number);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
