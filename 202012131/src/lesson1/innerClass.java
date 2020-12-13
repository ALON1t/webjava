package lesson1;

public class innerClass {
    public static void main(String[] args) {
        //匿名内部类：重新定义了一个A的类
        A a = new A(){
            @Override
            public void x() {
                System.out.println("A");
            }
        };
    }
    public static class A{
        public  void x () {
            System.out.println("A");
        }
    }
}
