package team.wuming.common.domain;

import java.util.List;

/**
 * @author Tony
 * 
 * @param <T>
 *            分页类
 */
public class PageBean<T> {

	private List<T> beanList;// 当前页面记录
	private int tr;// 总记录数

	private int pc;// /当前页面码
	private int ps;// 每页记录数
	private String url;// 它就是url后的条件

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 计算总页数
	 */
	public int getTp() {
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}

}
