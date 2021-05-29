package com.yolyn.learning.model;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/5/29 9:54 PM
 * @project lucene-fileSearch
 * @description
 */
public class SearchResultModel {
    private String content;
    private String downloadUrl;
    private String title;

    @Override
    public String toString() {
        return "SearchResultModel{" +
                "content='" + content + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SearchResultModel(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
