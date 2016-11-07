package com.tenXen.core.dao;

import java.util.List;

/***
 *
 * @author liugang
 * @date 2016年5月17日 上午11:05:01
 * @ClassName: BaseMapper
 * @Description: 数据基本操作类
 * @param <T>
 * 实体对象
 */
public interface BaseMapper<T> {
    /**
     * 根据主键查询
     *
     * @param id
     * @return model
     */
    public T getModelById(Integer id);

    /***
     * 根据主键删除
     *
     * @param id
     */
    public void deleteModelById(Integer id);

    /**
     * 根据模型计数，统计查询的条数
     *
     * @param model
     * @return
     */
    public Integer getCount(T model);

    /**
     * 插入数据
     *
     * @param model
     * @return 返回更新条数
     */
    public Integer saveModel(T model);

    /**
     * 根据主键修改
     *
     * @param model
     */
    public void updateModelById(T model);

    public void updateModelBySelectiveAndId(T model);

    /**
     * 查询list
     *
     * @param model
     */
    public List<T> findModelList(T model);

}
