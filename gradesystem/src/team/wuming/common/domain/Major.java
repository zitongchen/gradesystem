package team.wuming.common.domain;

import java.io.Serializable;

/**
 * @author Tony 专业类
 */
public class Major implements Serializable {
	private String zydm;// 专业编号（主键）
	private String zymc;// 专业名称
	private String xxxs;// 学习形式
	private String xxxdm;// 学习形式代码
	private String pycc; // 培养层次代码
	private String xznx;// 学制年限
	private int xz;// 学制
	private String bz;// 备注

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXxxs() {
		return xxxs;
	}

	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}

	public String getXxxdm() {
		return xxxdm;
	}

	public void setXxxdm(String xxxdm) {
		this.xxxdm = xxxdm;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getXznx() {
		return xznx;
	}

	public void setXznx(String xznx) {
		this.xznx = xznx;
	}

	public int getXz() {
		return xz;
	}

	public void setXz(int xz) {
		this.xz = xz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Override
	public String toString() {
		return "Major [zydm=" + zydm + ", zymc=" + zymc + ", xxxs=" + xxxs
				+ ", xxxdm=" + xxxdm + ", pycc=" + pycc + ", xznx=" + xznx
				+ ", xz=" + xz + ", bz=" + bz + "]";
	}

}
