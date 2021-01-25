package org.example.util;

import org.example.model.DocInfo;
import org.example.model.Weight;

import javax.imageio.stream.ImageInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建索引：
 * 1.正排索引：从本地文件数据中读取到Java内存 （类似数据库保存的数据）
 * 2.倒排索引：构建Map<String,List<信息>> （类似数据库hash索引 ）
 * Map键：关键词（分词来做）
 * （1）docInfo对象引用或是docInf的id
 * （2）权重（标题对应关键词数量*10 + 正文关键词数量*1） 10：1 可灵活调整
 * （3）关键词（是否需要待定）
 */
public class Index {
    //正排索引
    private  static final List<DocInfo> FORWARD_INDEX = new ArrayList<>();
    //倒排索引
    private static final Map<String,List<Weight>> INVERTED_INDEX = new HashMap<>();
    /**
     * 构建正排索引的内容：从本地raw_data.text读取并保存
     */
    public static void buildForwardIndex() {
        try {
            FileReader fr = new FileReader(Parser.RAW_DATA);
            BufferedReader br = new BufferedReader(fr);
            int id = 0; //行号设置为docInfo的id
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) continue;
                //一行对应一个docInfo对象 类似数据库一行数据对应一个Java对象
                DocInfo doc = new DocInfo();
                doc.setId(++id);
                String[] parts = line.split("\3");//每一行按\3间隔符来切分
                doc.setTitle(parts[0]);
                doc.setUrl(parts[1]);
                doc.setContent(parts[2]);
                //到正排索引
                FORWARD_INDEX.add(doc);
            }
        } catch (IOException e) {
            //不要吃异常,初始化操作有异常让线程不捕获异常，从而结束程序
            //初始化（启动tomcat），有问题尽早发现
            throw new RuntimeException(e);
        }
    }
    /**
     * 构建倒排索引：从Java内存中正排索引获取文档信息来构建
     */
    public static void buildInvertedIndex() {
        for (DocInfo doc : FORWARD_INDEX) {
            //一个doc，分别对标题和正文分词：每一个分词生成一个weight对象，需要计算权重
            //如标题为：清华大学/计算机/专业/使用/计算机/炒菜
            
        }
    }


    public static void main(String[] args) {
        //测试
        Index.buildForwardIndex();
        FORWARD_INDEX.stream().forEach(System.out::println);
    }
}
