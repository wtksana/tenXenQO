package com.tenXen.core.model;

/**
 * 分页请求参数
 * 
 * @author zheng hualiang
 * 
 */
public class BasePageRequest {
	private Integer page;

	private Integer rows;

	private String sort;

	private String order;

	public Integer getRows() {
		return (null == rows || rows < 1) ? 20 : rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public int getPage() {
		return (null == rows) ? 1 : page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
