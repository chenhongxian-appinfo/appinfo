package entity;

import java.util.List;

public class AppInfoToday {

	private int currentPageNo = 1;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount != 0) {
			this.totalCount = totalCount;
			this.totalPageCount = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
		}

	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<app_info> getList() {
		return list;
	}

	public void setList(List<app_info> list) {
		this.list = list;
	}

	private int totalCount;

	private int totalPageCount;

	private int pageSize = 5;

	private List<app_info> list;

	private int word;

	public int getWord() {
		this.word = (this.currentPageNo - 1) * this.pageSize;
		return word;
	}

	public void setWord(int word) {

		this.word = word;
	}
}
