package com.tenXen.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wt on 2016/10/25.
 */
public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;

    /**
     * 1,正常:normal;2,已删除:deleted
     **/
    private Integer status;
    private String statusFormatter;
    protected Date createTime;
    protected Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusFormatter() {
        return statusFormatter;
    }

    public void setStatusFormatter(String statusFormatter) {
        this.statusFormatter = statusFormatter;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 1,正常:normal<br>
     * 2,已删除:deleted
     **/
    public enum STATUS {

        /**
         * 1,正常:normal
         **/
        NORMAL("正常", 1),

        /**
         * 2,已删除:deleted
         **/
        DELETED("已删除", 2);

        public final int code;
        public final String value;
        private static Map<Integer, String> map = new HashMap<Integer, String>();

        private STATUS(String value, int code) {
            this.code = code;
            this.value = value;
        }

        public static String getValue(Integer code) {
            if (null == code) {
                return null;
            }
            for (STATUS status : STATUS.values()) {
                if (status.code == code) {
                    return status.value;
                }
            }
            return null;
        }

        public static Integer getCode(String value) {
            if (null == value || "".equals(value)) {
                return null;
            }
            for (STATUS status : STATUS.values()) {
                if (status.value.equals(value)) {
                    return status.code;
                }
            }
            return null;
        }

        public static Map<Integer, String> getEnumMap() {
            if (map.size() == 0) {
                for (STATUS status : STATUS.values()) {
                    map.put(status.code, status.value);
                }
            }
            return map;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
    }
}
