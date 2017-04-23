package com.qun.news.bean;

/**
 * Created by Qun on 2017/4/23.
 */

public class ArrayBean {

    /**
     * dailyTime : 2014-09-05 13:50:43
     * id : 77
     * lastUpdateTime : 2014-09-05 14:23:56
     * reviewNum : 0
     * title : 超级管理员 2014-09-05 的日志
     * content : <p>北京人去纽约<br /></p>
     */

    private String dailyTime;
    private int id;
    private String lastUpdateTime;
    private int reviewNum;
    private String title;
    private String content;

    public String getDailyTime() {
        return dailyTime;
    }

    public void setDailyTime(String dailyTime) {
        this.dailyTime = dailyTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
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
}
