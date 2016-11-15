package com.tenXen.common.constant;

/**
 * The type Constants.
 */
public class Constants {
    /**
     * 服务端IP
     */
//    public static final String SERVER_HOST = "127.0.0.1";
    public static final String SERVER_HOST = "10.10.1.63";
//    public static final String SERVER_HOST = "192.168.31.104";
    /**
     * 服务端端口
     */
    public static final int SERVER_PORT = 8099;
    /**
     * 客户端IP
     */
    public static final String LOCAL_HOST = "127.0.0.1";
    /**
     * 登录请求handleCode
     */
    public static final int LOGIN_CODE = 1;
    /**
     * 注册请求handleCode
     */
    public static final int REGISTER_CODE = 2;
    /**
     * 登出请求handleCode
     */
    public static final int LOGOUT_CODE = 3;
    /**
     * 更新在线用户handleCode
     */
    public static final int UPDATE_ONLINE_USER = 9;
    /**
     * 处理结果：成功
     */
    public static final int RESULT_SUC = 1;
    /**
     * 处理结果：失败
     */
    public static final int RESULT_FAIL = 2;
    /**
     * 是
     */
    public static final int YES = 1;
    /**
     * 否
     */
    public static final int NO = 0;
    /**
     * 更新表情请求Code
     */
    public static final int UPDATE_CODE_EMOTION = 1;
    /**
     * 在线消息handleCode
     */
    public static final int MSG_ONLINE_CODE = 1;

    /**
     * 离线消息handleCode
     */
    public static final int MSG_OFFLINE_CODE = 2;


    /**
     * 分页查询每页条数
     */
    public static final Integer PAGE_SIZE = 20;

}
