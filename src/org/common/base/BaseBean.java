package org.common.base;

public abstract class BaseBean {
	private int nowPage=1;//当前页面
	private int pageSize=10;//每页现实数量
	private int nextPage;//下一页
	private int prePage;//上一页
	private int pageCount;//总页数
	private int total;
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//下一页计算
	public int getNextPage() {
		nextPage=(nowPage+1)>getPageCount()?0:(nowPage+1);
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	//计算上一页
	public int getPrePage() {
		prePage=(nowPage-1)<1?0:(nowPage-1);
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	//获取总共页数
	public int getPageCount() {
		this.pageCount=total%pageSize==0?total%pageSize:(total/pageSize+1);
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
