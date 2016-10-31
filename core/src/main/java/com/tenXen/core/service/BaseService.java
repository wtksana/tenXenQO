package com.tenXen.core.service;


import com.tenXen.core.domain.BaseDomain;

import java.util.List;

/**
 * @param <T> 实体对象
 * @author liugang
 * @date 2016年5月17日 上午11:05:27
 * @ClassName: BaseService
 * @Description: 基本服务类接口
 */
public interface BaseService<T extends BaseDomain> {
    /**
     * 获取单个实体
     *
     * @param id
     * @return
     */
    T getModelById(Integer id);

    /**
     * 获取单个状态值为正常的实体
     *
     * @param id
     * @return
     */
    T getNormalModelById(Integer id);


    Integer getCount(T model) throws Exception;

    /**
     * 删除实体
     *
     * @param id
     */
    void deleteModelById(Integer id) throws Exception;

    /**
     * 逻辑删除对象
     *
     * @param model
     */
    void deleteLogicByModel(T model) throws Exception;

    /**
     * 保存实体
     *
     * @param model
     */
    T saveModel(T model) throws Exception;

    /**
     * 修改实体，必须有id，全更新修改
     *
     * @param model
     */
    void updateModelById(T model) throws Exception;

    /**
     * 修改实体，必须有id,选择性修改
     *
     * @param model
     */
    void updateModelBySelectiveAndId(T model) throws Exception;

    /**
     * 查询列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    List<T> findModelList(T model) throws Exception;

    /**
     * @throws Exception
     * @Title: findeModelPage @param @param model @param @param
     * pageParam @param @return 设定文件 @return Pages<T> 返回类型 @Description:
     * 分页查询对象的基础方法 @throws
     */
//    Pages<T> findeModelPage(T model, BasePageRequest pageParam) throws Exception;

    /**
     * 分页查询
     *
     * @param model
     * @return
     * @throws Exception
     */
//    Pages<T> findeModelPage(T model) throws Exception;

    /**
     * 自定义分页查询
     *
     * @param model    模型
     * @param pageNum  开始页码
     * @param pageSize 每个列表记录
     * @param orderBy  排序 如：id
     * @return
     * @throws Exception
     */
//    Pages<T> findeModelPage(T model, Integer pageNum, Integer pageSize, String sort, String orderBy) throws Exception;

}
