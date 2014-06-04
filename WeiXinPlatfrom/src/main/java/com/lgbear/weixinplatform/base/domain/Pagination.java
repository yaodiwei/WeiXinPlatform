package com.lgbear.weixinplatform.base.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Pagination<T> {
	public final static int DEFAULT_PAGE_ITEM = 20;
	private int pageItem = DEFAULT_PAGE_ITEM;
	private int totalCount;
	private int pageCount;
	private int pageIndex;
	private int skipResults;
	private int endResults;
	private Collection<T> resultSet = new ArrayList<T>();

	public Pagination(int totalCount, int pageIndex) {
		setValue(pageItem, totalCount, pageIndex);
	}

	public Pagination(int totalCount, int pageIndex, int pageItem) {
		setValue(pageItem, totalCount, pageIndex);
	}

	public Collection<T> getResultSet() {
		return resultSet;
	}

	private void setValue(int pageItem, int totalCount, int pageIndex) {
		if (pageIndex < 0)
			pageItem = totalCount;

		if (pageItem < 1)
			pageItem = 10;

		this.pageItem = pageItem;

		this.totalCount = totalCount;

		if (this.totalCount == 0)
			this.pageCount = 1;
		else if (this.totalCount % this.pageItem == 0)
			this.pageCount = this.totalCount / this.pageItem;
		else
			this.pageCount = (this.totalCount / this.pageItem) + 1;

		if (pageIndex <= 1)
			this.pageIndex = 1;
		else if (pageIndex >= this.pageCount)
			this.pageIndex = this.pageCount;
		else
			this.pageIndex = pageIndex;

		this.skipResults = ((this.pageIndex - 1) * this.pageItem);

		this.endResults = this.skipResults + this.pageItem;
		if (this.endResults > this.totalCount)
			this.endResults = this.totalCount;
	}

	public int getPageItem() {
		return pageItem;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getSkipResults() {
		return skipResults;
	}

	public int getEndResults() {
		return endResults;
	}

	public void setResultSet(Collection<T> resultSet) {
		this.resultSet = resultSet;
	}

}
