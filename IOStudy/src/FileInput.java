import java.io.*;

public class FileInput {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\20210117.png");
        //文件输入字节流
//        FileInputStream fis = new FileInputStream(file);
//        //输入流较为固定的写法：读取到一个字节/字符数组，定义read的返回值变量，while
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        //读取到的长度，数组可能读满，也可能未读满
//        // 一般处理的方式：当次读取内容，一般使用数组[0，len]
//        while ((len = fis.read(bytes)) != -1) { //输入流较为固定的操作  int -> !=-1  String -> !=null
//            String str = new String(bytes,0,len);//模拟
//            System.out.println(str);
//        }
//        //输入输出流使用完之后，一定要关闭  关闭的顺序是反向关闭

//        //2.文件输入字符流
//        FileReader fr = new FileReader(file);
//        char[] chars = new char[1024];
//        int len = 0;
//        while ((len = fr.read(chars)) != -10) {
//
//            String str = new String(chars,0,len);
//            System.out.println(str);
//        }

        //3.缓冲流，缓冲的字节输入，缓字符输入
        FileInputStream fis = new FileInputStream(file); //文件字节输入流
        //和文件编码格式要一致，否则会是乱码
        InputStreamReader irs  = new InputStreamReader(fis,"GBK");//字节流转字符流，一定要经过字节字符转换流来转换，且可以指定编码
        BufferedReader br = new BufferedReader(irs);
        String str ;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        //释放资源：反向释放
        br.close();
        irs.close();
        fis.close();
    }
}
