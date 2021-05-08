package com.podong.game.common.bean;

import java.sql.Timestamp;

public class PostFixVO {
    private String description;
    private String insertUuid;
    private String insertName;
    private Timestamp insertDate;
    private String updateUuid;
    private Timestamp updateDate;
    private String updateName;

    public PostFixVO() {
    }

    public String getDescription() {
        return description;
    }

    public PostFixVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getInsertUuid() {
        return insertUuid;
    }

    public PostFixVO setInsertUuid(String insertUuid) {
        this.insertUuid = insertUuid;
        return this;
    }

    public String getInsertName() {
        return insertName;
    }

    public PostFixVO setInsertName(String insertName) {
        this.insertName = insertName;
        return this;
    }

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public PostFixVO setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
        return this;
    }

    public String getUpdateUuid() {
        return updateUuid;
    }

    public PostFixVO setUpdateUuid(String updateUuid) {
        this.updateUuid = updateUuid;
        return this;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public PostFixVO setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public String getUpdateName() {
        return updateName;
    }

    public PostFixVO setUpdateName(String updateName) {
        this.updateName = updateName;
        return this;
    }
}
