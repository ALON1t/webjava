package lesson2;

public class SleepTest {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new Runnable() { //Thread线程 等待
            @Override
            public void run() {
                try{
                    Thread.sleep(9999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        //Thread.sleep(999999);
        while (true) { //main线程里的代码 可执行状态

        }
    }
}
