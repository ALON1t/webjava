import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFile {
    public static void main(String[] args) {
        File dir = new File("C:\\Program Files\\Java\\jdk1.8.0_192");
        List<File> files = listDir2(dir);
        //jdk1.8集合框架使用stream操作，可以lambda表达式
        files.stream()
                .forEach(System.out::println);
    }
    public static List<File> listDir(File dir) {
        List<File> list = new ArrayList<>();
        if(dir.isFile()) { //效率不太好：如果是文件，返回一个list
            list.add(dir);
        } else if(dir.isDirectory()){
            File[] children = dir.listFiles();
            if(children != null) {
                for(File child : children) {
                    List<File> filels = listDir(child);
                    list.addAll(filels);
                }
            }
        }
        return list;
    }
    public static List<File> listDir2(File dir) {
        List<File> list = new ArrayList<>();
        //获取目录下一级的子文件、子文件类
        File[] children = dir.listFiles();
        if(children != null) {
            for(File child :children) {
//                //不包含文件夹
//                if(child.isDirectory()){ //递归
//                    list.addAll(listDir2(child));
//                } else {
//                    list.add(child);
//                }
                //如果要包含
                list.add(child);
                if(child.isDirectory()) {
                    list.addAll(listDir2(child));
                }
            }
        }
        return list;
    }
}
