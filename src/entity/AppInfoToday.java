package entity;

import java.util.List;

public class AppInfoToday {

	private int pageindex = 1;

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPageCountSum() {
		return pageCountSum;
	}

	public void setPageCountSum(int pageCountSum) {
		this.pageCountSum = pageCountSum;
	}

	public int getTodayPageCountSum() {
		return todayPageCountSum;
	}

	public void setTodayPageCountSum(int todayPageCountSum) {
		if (todayPageCountSum != 0) {
			this.todayPageCountSum = todayPageCountSum;
			this.pageCountSum = (todayPageCountSum % this.pageSize == 0) ? (todayPageCountSum / this.pageSize)
					: (todayPageCountSum / this.pageSize) + 1;
		}

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

	private int pageCountSum;

	private int todayPageCountSum;

	private int pageSize = 5;

	private List<app_info> list;
	
	private int word;

	public int getWord() {
		this.word = (this.pageindex-1)*this.pageSize;
		return word;
	}

	public void setWord(int word) {
		
		this.word = word;
	}
}
