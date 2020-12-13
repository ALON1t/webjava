package lesson1;

public class ThreadLook5 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 1000;i++) {
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
