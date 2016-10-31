package com.tenXen.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.dao.BaseMapper;
import com.tenXen.core.domain.BaseDomain;
import com.tenXen.core.model.BasePageRequest;
import com.tenXen.core.service.BaseService;
import com.tenXen.core.util.Pages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <T> 实体对象
 * @author liugang
 * @date 2016年5月17日 上午11:05:52
 * @ClassName: BaseServiceImpl
 * @Description: 定义了基本的操作方法
 */
public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T> {

    protected final Logger L = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public T getModelById(Integer id) {
        if (null == id) {
            L.error("get an model , modelId is null !");
            return null;
        }
        return baseMapper.getModelById(id);
    }


    @Override
    public Integer getCount(T model) throws Exception {
        if (null == model) {
            L.error("query model , the model is null !");
            throw new Exception("查询参数为空！");
        }
        return baseMapper.getCount(model);
    }

    @Override
    public T getNormalModelById(Integer id) {
        T t = getModelById(id);
        if (null != t && t.getId() != null) {
            if (BaseDomain.STATUS.NORMAL.code == t.getStatus()) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void deleteModelById(Integer id) throws Exception {
        if (null == id) {
            L.error("delete model , modelId is null !");
            throw new Exception("删除对象主键为空！");
        }
        baseMapper.deleteModelById(id);
    }

    @Override
    public void deleteLogicByModel(T model) throws Exception {
        if (null == model || null == model.getId()) {
            L.error("update model , the model is null or has no id .");
            throw new Exception("更新对象为空或主键为空！");
        }
        model.setStatus(BaseDomain.STATUS.DELETED.code);
        baseMapper.updateModelBySelectiveAndId(model);
    }

    @Override
    public T saveModel(T model) throws Exception {
        if (null == model) {
            L.error("save model , the model is null !");
            throw new Exception("保存对象为空！");
        }
        model.setStatus(BaseDomain.STATUS.NORMAL.code);
        baseMapper.saveModel(model);
        return model;
    }

    @Override
    public void updateModelById(T model) throws Exception {
        if (null == model || null == model.getId()) {
            L.error("update model , the model is null or has no id .");
            throw new Exception("更新对象为空或主键为空！");
        }
        baseMapper.updateModelById(model);
    }

    @Override
    public void updateModelBySelectiveAndId(T model) throws Exception {
        if (null == model || null == model.getId()) {
            L.error("update model , the model is null or has no id .");
            throw new Exception("更新对象为空或主键为空！");
        }
        baseMapper.updateModelBySelectiveAndId(model);
    }

    @Override
    public List<T> findModelList(T model) throws Exception {
        if (null == model) {
            L.error("param is null.");
            throw new Exception("参数为空");
        }
        if (null == model.getStatus()) {
            model.setStatus(BaseDomain.STATUS.NORMAL.code);
        }
        return baseMapper.findModelList(model);
    }

    @Override
    public Pages<T> findeModelPage(T model, BasePageRequest pageParam) throws Exception {
        if (null == model) {
            L.error("param is null.");
            throw new Exception("参数为空");
        }
        if (null == pageParam) {
            L.info("find page the oderBys is null .");
            return this.findeModelPage(model);
        }
        if (null == model.getStatus()) {
            model.setStatus(BaseDomain.STATUS.NORMAL.code);
        }
        return this.findeModelPage(model, pageParam.getPage(), pageParam.getRows(), pageParam.getSort(),
                pageParam.getOrder());
    }

    @Override
    public Pages<T> findeModelPage(T model) throws Exception {
        if (null == model) {
            L.error("param is null.");
            throw new Exception("参数为空");
        }
        return findeModelPage(model, null, null, null, null);
    }

    @Override
    public Pages<T> findeModelPage(T model, Integer pageNum, Integer pageSize, String sort, String orderBy)
            throws Exception {
        if (null == model) {
            L.error("param is null.");
            throw new Exception("参数为空");
        }
        pageNum = (null == pageNum || pageNum < 0) ? 0 : pageNum;
        pageSize = (null == pageSize || pageSize < 0) ? Constants.PAGE_SIZE : pageSize;
        if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(orderBy)) {
            PageHelper.startPage(pageNum, pageSize, sort + " " + orderBy);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        if (null == model.getStatus()) {
            model.setStatus(BaseDomain.STATUS.NORMAL.code);
        }
        List<T> list = baseMapper.findModelList(model);
        return new Pages<T>((Page<T>) list);
    }
}
