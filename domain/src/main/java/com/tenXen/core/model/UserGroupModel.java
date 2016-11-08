package com.tenXen.core.model;

import com.tenXen.core.domain.UserGroup;

/**
 * Created by wt on 2016/11/8.
 */
public class UserGroupModel extends UserGroup {
    private static final long serialVersionUID = 1L;

    private String group_title;
    private String group_remark;
    private String group_picPath;

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getGroup_remark() {
        return group_remark;
    }

    public void setGroup_remark(String group_remark) {
        this.group_remark = group_remark;
    }

    public String getGroup_picPath() {
        return group_picPath;
    }

    public void setGroup_picPath(String group_picPath) {
        this.group_picPath = group_picPath;
    }
}
