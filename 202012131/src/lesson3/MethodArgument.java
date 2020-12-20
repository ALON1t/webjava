package lesson3;

public class MethodArgument {
    //隐藏  int i = 方法调用传入的值
    public static void suibian(int a) { //suibian方法栈帧
        a = 1;
    }
    public static void suibian(user u) { //suibian方法栈帧
        //注释和不注释，结果是u局部变量指向不同的堆对象
      //  u = new user();  //注释之后打印结果为李四    不注释打印结果为张三
        u.username = "李四";
    }
    //main线程执行main方法（栈帧）
    public static void main(String[] args) {
        int i = 0;
        suibian(i);
        System.out.println(i);

        //方法调用产生栈帧
        user u = new user();
        u.username = "张三";
        suibian(u);
        System.out.println(u.username);
    }

    private static class user{
        private String username;
    }
}
