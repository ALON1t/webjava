package lesson;

import java.io.*;

public class FileOutput {

    public static void main(String[] args) throws IOException {
        //路径上没有该文件，new File不会报错，但是操作输入输出流会抛FileNotFountException
        File file = new File("E:/tmp/1.txt");
        //把a-z换行输出到某个文件，需要考虑文件是否存在的问题
        if(!file.exists()){ //不存在就创建
            file.createNewFile();
        }
        //类似输入的几种写法都是ok
//        new FileWriter() 不带缓冲的字符输出流
//        new FileOutputStream()  文件字节输出流
//        new BufferedWriter() 带缓冲的字符输出流
//        new PrintWriter()

        //缓冲字符输出流
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        bw.write("\n");

        //打印输出流
        //1.
//        PrintWriter pw = new PrintWriter(new FileWriter(file));
        //2.
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        //快速打印a-z
        for(int i='a'; i<='z'; i++){
            pw.println((char)i);
        }
        pw.flush();
    }
}
