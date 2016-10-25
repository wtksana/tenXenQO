package com.tenXen.core.domain;

public class User extends BaseDomain{

    private static final long serialVersionUID = 1L;
    /**
    * 登录用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String pwd;

    /**
    * 昵称
    */
    private String name;

    /**
    * 个姓签名
    */
    private String signature;

    /**
    * 手机号
    */
    private Integer mobile;

    /**
    * 地址
    */
    private String address;

    public User() {

    }

    public User(String userName, String pwd) {
        super();
        this.userName = userName;
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}