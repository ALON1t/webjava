package lesson1;

public class ThreadStartVSRun {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).run();
        /**
         * main直接调用Thread对象和run方法。会直接在main线程运行Thread对象的run()-->传入的runnable对象。run()
         * 结果：main线程直接运行while（true）
         * 对比通过start()
         */

       // t.start();
    }

}
