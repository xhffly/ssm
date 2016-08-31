package com.xhf.test.ssm.dto;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

	/**
	 * serialVersionUID:
	 * @since Ver 1.1
	 */
	private static final long serialVersionUID = -430684722080924635L;
	
	
	private List<T> resultList;
	
	private int pageSize;
	
	private int pageNo;
	
	private long totleCount;
	
	

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getTotleCount() {
		return totleCount;
	}

	public void setTotleCount(long totleCount) {
		this.totleCount = totleCount;
	}
	

}
