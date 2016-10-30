package com.tenXen.core.domain;

import com.tenXen.core.domain.example.BaseDomain;

/**
 * Created by wt on 2016/10/30.
 */
public class Message extends BaseDomain {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
