package lesson;

import java.io.*;
import java.util.jar.JarOutputStream;

public class FileInput {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\LENOVO\\Documents\\Tencent Files\\1029170395\\FileRecv\\0320.txt");

//        //1.文件字节输入流
//        FileInputStream fis = new FileInputStream(file);
//        //输入流比较固定的写法：读取到一个字节、字符数组，
//        // 先定义read的返回值变量，再while循环
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        while ((len = fis.read(bytes)) != -1) { //读取到的长度，数组可能杜曼，可能未读满，当次读取内容,一般使用数组[0，len]
//            String str = new String(bytes,0,len); //字节数组转字符串 模拟
//            System.out.println(str);
//        }
//        //使用完一定要关闭，反向关闭(和创建顺序相反)
//        fis.close();

//        //2.文件的字符输入流
//        FileReader fr = new FileReader(file);
//        char[] chars = new char[1024];
//        int len = 0;
//        while ((len = fr.read(chars)) != -1) {
//            String str = new String(chars,0,len);
//            System.out.println(str);
//        }
//        fr.close();

//        //3.缓冲流缓冲字节输入，缓冲字符输入
//        //字符输入流
//        //new BufferedReader(new InputStreamReader(new FileInputStream()))
//        FileInputStream fis = new FileInputStream(file);
//        //字节流转字符流，有一定要经过字节字符流转换操作，转换时可以指定编码
//        //和文件格式编码要一致，否则会是乱码，这里默认UTF-8
//        InputStreamReader isr = new InputStreamReader(fis);
//        BufferedReader br  = new BufferedReader(isr);
//        String str ;
//        while ((str = br.readLine()) != null) {
//            System.out.println(str);
//        }
//        //关闭
//        br.close();
//        isr.close();
//        fis.close();

        FileInputStream fis = new FileInputStream(file);
        //缓冲的字节输入流
        BufferedInputStream bis = new BufferedInputStream(fis);
        //和之前的字节输入流操作类似 操作byte[]
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            String str = new String(bytes,0,len);
            System.out.println(str);
        }
        bis.close();
        fis.close();
    }
}
