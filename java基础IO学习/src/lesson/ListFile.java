package lesson;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFile {

    public static List<File> listDir1(File file) {
        List<File> list = new ArrayList<>();
        if(file.isFile()) { //效率不太好，如果是文件，返回一个list只存放一个元素
            list.add(file);
            return list;
        } else if(file.isDirectory()){
            File[] children = file.listFiles();
            if (children != null) {
                for (File ch : children) {
                    List<File> files = listDir(ch); //递归
                    list.addAll(files);
                }
            }
        }
        return list;
    }
    public static List<File> listDir(File file) {
        List<File> list = new ArrayList<>();
        //if(file.isDirectory()){
            //获取目录下一级的子文件、子文件夹
            File[] children = file.listFiles();
            if (children != null) {
                for (File ch : children) {
//                    //返回的文件列表不包含文件夹
//                    if (ch.isDirectory()) { //如果是子文件夹，递归调用获取
//                        list.addAll(listDir(ch));
//                    } else {
//                        list.add(ch);
//                    }
                    //包含文件夹
                    list.add(ch);
                    if (ch.isDirectory()) {
                        list.addAll(listDir(ch));
                    }
                }
            }
        //}
        return list;
    }
    public static void main(String[] args) {
        File dir = new File("F:\\docs\\api");
        List<File> files = listDir(dir);
        //jdk1.8集合框架使用stream操作（对集合操作使用流操作），可以使用lambda表达式
        files.stream().
                forEach(System.out::println);
    }
}
