package org.example.model;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.Objects;

/**
 * 每一个本地html文件对应一个文档对象
 */

public class DocInfo {
    private Integer id;//类似数据库里的id(主键)，识别不同的文档
    private String title;//标题：html的文件名作为我们的标题
    private String url; //oracle官网api文档下html的url
    private String content;//网页正文：html（<标签>内容</标签>），内容为正文

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocInfo docInfo = (DocInfo) o;
        return Objects.equals(id, docInfo.id) &&
                Objects.equals(title, docInfo.title) &&
                Objects.equals(url, docInfo.url) &&
                Objects.equals(content, docInfo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url, content);
    }
}
