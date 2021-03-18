package lesson4;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        //synchronized竞争class锁
        synchronized (Test.class) {
//            //当前线程释放对象锁
//            Test.class.wait();

            //通知调用同一个对象的wait()阻塞的线程（唤醒），唤醒后竞争对象锁
            Test.class.notify();
        } //释放对象锁
    }
}
