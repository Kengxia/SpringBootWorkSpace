package com.my.work.audit.pojo;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 16:33 2018/7/10
 * @Description:
 */
public class HomeParam {

    private String id;
    private String jobId;
    private String sceneName;
    private String sceneId;
    private String month;
    private String columnName;
    private String columnValues;
    private String queryPerson;
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(String columnValues) {
        this.columnValues = columnValues;
    }

    public String getQueryPerson() {
        return queryPerson;
    }

    public void setQueryPerson(String queryPerson) {
        this.queryPerson = queryPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeParam{" +
                "id='" + id + '\'' +
                ", jobId='" + jobId + '\'' +
                ", sceneName='" + sceneName + '\'' +
                ", sceneId='" + sceneId + '\'' +
                ", month='" + month + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnValues='" + columnValues + '\'' +
                ", queryPerson='" + queryPerson + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}