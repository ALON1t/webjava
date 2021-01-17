package lesson5;

public class SequencePrint2 {
    public static void main(String[] args) {
        Thread a = new Thread(new Tesk("A"));
        Thread b = new Thread(new Tesk("B"));
        Thread c = new Thread(new Tesk("C"));
        c.start();
        b.start();
        a.start();

    }
    private static class Tesk implements Runnable{

        private String content;
        //顺序打印的内容：可以循环打印
        private static String[] arr = {"A","B","C"};
        private static int INDEX; //从数组那个索引打印

        public Tesk(String content) {
            this.content = content;
        }
        @Override
        public void run() {
            try {
                for(int i = 0; i < 10;i++) {
                    synchronized (arr){ //三个线程使用同一把锁
                        //从数组索引位置打印，如果当前线程要打印的内容不一致，释放对象锁等待
                        while (!content.equals(arr[INDEX])) {
                            arr.wait();
                        }
                        //如果数组要打印的内容和当前线程要打印的一致就打印，并把数组索引切换到一个位置，通知其他线程
                        System.out.print(content);
                        if(INDEX == arr.length-1) {
                            System.out.println();
                        }
                        INDEX = (INDEX+1)%arr.length;
                        arr.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
