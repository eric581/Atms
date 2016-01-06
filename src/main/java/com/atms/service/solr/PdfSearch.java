package com.atms.service.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author:eric
 * @date: 2015-12-22 13:46
 */
public class PdfSearch {

    @Field("id")
    private String id;
    @Field("author")
    private String author;
    @Field("title")
    private List<String> title;
    @Field("text")
    private List<String> text;
    @Field("file")
    private List<String> file;

    //高亮内容
    private String hlContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public String getHlContent() {
        return hlContent;
    }

    public void setHlContent(String hlContent) {
        this.hlContent = hlContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PdfSearch{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", title=").append(title);
        sb.append(", text=").append(text);
        sb.append(", file=").append(file);
        sb.append(", hlContent='").append(hlContent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
