package orrg.example.test;

public class Test {

    private static class P{ //静态内部类
        protected int x = 3;  //protected  子类继承过去

        public P(){
            System.out.println(x); //3
            s(); //进入子类的s() 方法的执行顺序是从下往上查找 有重写的一定先调用子类重写的


            System.out.println(x);// 6
        }
        protected void s(){
            x = 4;
        }
    }
    //new P()

    private static class C extends P{
//        protected int x = 1;
          protected int x = 1;

        public C(){
            //隐藏supper()
            System.out.println(x);
        }
        protected void s(){
            x = 6;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.x); //
    }
}


