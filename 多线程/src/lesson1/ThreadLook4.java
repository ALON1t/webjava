package lesson1;

public class ThreadLook4 {
    public static void main(String[] args) {

        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + "  Thread + this + run"); //Thread-0  Thread + this + run
                //System.out.println(t.getName()); //编译错误
                System.out.println(Thread.currentThread().getName() + "  Thread + current + run"); //Thread-0  Thread + current + run
            }
        };
        t.start();
        //System.out.println(this.getName); //编译错误
        System.out.println(t.getName() + "  Thread + t.getName"); //Thread-0  Thread + t.getName
        System.out.println(Thread.currentThread().getName() + "  Thread + current"); //main  Thread + current


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println(this.getName); //编译错误
                //System.out.println(t1.getName()); //编译错误
                System.out.println(Thread.currentThread().getName() + "  Runnable + current + run"); //Thread-1  Runnable + current + run
            }
        });
        t1.start();
        //System.out.println(this.getName); //编译错误
        System.out.println(t1.getName() + "  Runnable + t.getName");   //Thread-1  Runnable + t.getName
        System.out.println(Thread.currentThread().getName() + "  Runnable + current"); //main  Runnable + current


        Thread.currentThread().setName("主线程");
        System.out.println(Thread.currentThread().getName()); //主线程
    }
}
