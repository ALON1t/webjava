package lesson3;

class SingletonTest {
//    //饿汉模式
//    private static SingletonTest instance = new SingletonTest();
//    private SingletonTest(){}
//    public static SingletonTest getInstance(){
//        return instance;
//    }

//    //懒汉模式---单线程
//    private static SingletonTest instance = null;
//    private SingletonTest(){}
//    public static SingletonTest getInstance(){
//        if (instance == null) { //同时多个线程进入，每个线程new出不同的对象，不满足单例要求
//            instance = new SingletonTest();
//        }
//        return instance;
//    }

//    //懒汉模式---多线程，低性能
//    private static SingletonTest instance = null;
//    private SingletonTest(){}
//    public synchronized static SingletonTest getInstance(){
//        //第一次实例化之后，应该允许多线程并行并发执行获取同一个对象
//        if (instance == null) {
//            instance = new SingletonTest();
//        }
//        return instance;
//    }

    //懒汉模式---多线程，二次判断，高性能
    private static volatile SingletonTest instance = null;
    private SingletonTest(){}
    public  static SingletonTest getInstance(){
        if (instance == null) {
            synchronized (SingletonTest.class) {
                if (instance == null) {
                    instance = new SingletonTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

    }
}
