package lesson3;

public class synchronizedTest2 {
    /**
     * 一个教室 座位50个 同时有三个老师安排同学座位
     * 每个老师安排100个同学到这个教室  模拟使用多线程实现
     * 座位编号1-50/0-49，三个线程同时启动来安排同学
     * 同学可以循环操作来安排  直到座位安排满
     */


    public  static int z = 50;

    private static class task implements Runnable{
        private int num ; //0

        public task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100;i++) {
                synchronized (task.class){ //同一把锁
                    if(z > 0 && num > 0) {
                        z--;
                        num--;
                        System.out.printf("%s:z=%s， num = %s\n", Thread.currentThread().getName(),z,num);
                    }
                }
                try{
                    Thread.sleep(20);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        //设置安排数量
        new Thread(new task(10)).start();
        new Thread(new task(20)).start();
        new Thread(new task(20)).start();

//        task t = new task(3) ;
//        for(int i = 0; i < 3;i++) {
//            new Thread(t).start();
//        }


//        Thread[] threads = new Thread[3];
//        for(int i = 0; i < 3;i++) {
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int j = 0 ; j < 100;j++) {
//                        synchronized (synchronizedTest2.class){
//                            if(z > 0) {
//                                try {
//                                    Thread.sleep(20);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                System.out.println(Thread.currentThread().getName()+":"+z--);
//                            }
//                        }
//                    }
//                }
//            });
//        }
//        for(Thread t : threads) {
//            t.start();
//        }

    }
}
