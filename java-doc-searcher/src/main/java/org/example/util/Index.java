package org.example.util;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
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
import java.util.stream.Collectors;

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
    private static final List<DocInfo> FORWARD_INDEX = new ArrayList<>();
    //倒排索引
    private static final Map<String, List<Weight>> INVERTED_INDEX = new HashMap<>();

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
                //添加到正排索引
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
        for (DocInfo doc : FORWARD_INDEX) { //doc+分词 对应一个weight对象（doc和分词一对多，分词和weight一对一）
            //一个doc，分别对标题和正文分词：每一个分词生成一个weight对象，需要计算权重
            //如标题为：清华大学/计算机/专业/使用/计算机/炒菜
            //第一次出现的关键词，要new Weight对象，之后出现要获取之前的相同关键词对象，更新权重
            //实现逻辑 ：先构造一个HashMap，保存分词（键）和weight对象（值）
            Map<String, Weight> cache = new HashMap<>();
            List<Term> titleFenCis = ToAnalysis.parse(doc.getTitle()).getTerms();
            for (Term titleFenCi : titleFenCis) {
                //h获取标题分词键对应的weight对象
                Weight w = cache.get(titleFenCi.getName());
                if (w == null) { //如果没有，就创建一个放到map
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyword(titleFenCi.getName());
                    cache.put(titleFenCi.getName(), w);
                }
                //标题分词，权重加10
                w.setWeight(w.getWeight() + 10);
            }
            //正文分词处理，逻辑与标题分词一样
            List<Term> contentFenCis = ToAnalysis.parse(doc.getContent()).getTerms();
            for (Term contentFenCi : contentFenCis) {
                Weight w = cache.get(contentFenCi.getName());
                if (w == null) {
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyword(contentFenCi.getName());
                    cache.put(contentFenCi.getName(), w);
                }
                //正文分词，权重+1
                w.setWeight(w.getWeight() + 1);
            }
            //把临时保存的map数据（keyword_-weight),全部保存到倒排索引
            for (Map.Entry<String, Weight> e : cache.entrySet()) {
                String keyword = e.getKey();
                Weight w = e.getValue();
                //更新保存到倒排索引Map<String,List<Weight>>-->多个文档，同一个关键词保存在一个List
                //现在倒排索引中，通过keyword获取已有的值
                List<Weight> weights = INVERTED_INDEX.get(keyword);
                if (weights == null) { //如果拿不到，就创建一个，并存放到倒排索引
                    weights = new ArrayList<>();
                    INVERTED_INDEX.put(keyword, weights);
                }
                weights.add(w);//倒排中，添加当前文档每个分词对应的weight对象

            }
        }
    }


    //通过关键词（分词），在倒排中查找映射
    public static List<Weight> get(String keyword) {
        return INVERTED_INDEX.get(keyword);
    }

    public static void main(String[] args) {
        Index.buildForwardIndex();
        //测试正排索引是否正确
//        FORWARD_INDEX
//                .stream()
//                .forEach(System.out::println);
        //倒排索引根据正排索引构建
        Index.buildInvertedIndex();
        //测试倒排内容是否正确
        for (Map.Entry<String,List<Weight>> e : INVERTED_INDEX.entrySet()) {
            String keyword = e.getKey();
            System.out.print(keyword + ": ");
            List<Weight> weights = e.getValue(); //不校验weight中的doc对象，正排索引已经测试过
            weights.stream()
                    .map(w-> { //把list中每一个对象转换为其他对象
                        return "("+w.getDoc().getId() + ", " +w.getWeight() +")";
                    }) //转换完，会变为List<String>
  //                  .collect(Collectors.toList());
                    .forEach(System.out::print);
            System.out.println();
            //weights.add(w);
        }
    }


}
