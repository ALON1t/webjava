package lesson1;

public class ThreadLook2 {
    public static void main(String[] args) {
        //同时启动20个线程 每个线程从 0+1的方式加到99；
        for (int i = 0; i < 20;i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() { //在多线程环境下，即使同一段代码块，也是可以并发并行执行
                    for (int i = 0; i < 100;i++) {
                        if (i == 99 ) {
                            System.out.println(i);
                        }
                    }
                }
            });
            t.start();
        }

    }
}
