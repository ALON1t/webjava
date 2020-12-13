package lesson1;

public class CreateThread {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() { //任务描述的可执行类：传入线程对象的构造方法
            @Override
            //传入线程对象的构造方法参数
            public void run() {//线程运行态时执行

            }
        };
        Thread thread = new Thread(runnable,"子线程A");
        thread.start();
        //合并的代码
        //new Thread().start();

        new Thread(new Runnable() { //new一个匿名内部类
            @Override
            public void run() {

            }
        },"子线程B").start(); //返回一个对象，start()启动这个对象

        new Thread(()-> {//使用lambda表达式创建Runnable类对象 ，Runnable只有一个接口方法，可以直接用lambda表达式
            System.out.println(); //和run()方法里面写是一样的效果
        },"子线程C").start();
    }
}
