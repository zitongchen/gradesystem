package team.wuming.common.domain;

import java.io.Serializable;
import java.util.Date;

public class Xuexid implements Serializable {

	private String deparid;// 学习地点编码
	private String xxdd;// 学习点名称
	private String dizhi;// 学习地址
	private String telephone;// 电话
	private String lianxiren;// 联系人
	private String emial;// 邮箱
	private String ytdw;// 依托单位
	private String leixing;// 单位类型
	private Date yongdate;// 建立日期
	private String huze;// 负责人

	public String getDeparid() {
		return deparid;
	}

	public void setDeparid(String deparid) {
		this.deparid = deparid;
	}

	public String getXxdd() {
		return xxdd;
	}

	public void setXxdd(String xxdd) {
		this.xxdd = xxdd;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}

	public String getYtdw() {
		return ytdw;
	}

	public void setYtdw(String ytdw) {
		this.ytdw = ytdw;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public Date getYongdate() {
		return yongdate;
	}

	public void setYongdate(Date yongdate) {
		this.yongdate = yongdate;
	}

	public String getHuze() {
		return huze;
	}

	public void setHuze(String huze) {
		this.huze = huze;
	}

	@Override
	public String toString() {
		return "Xuexid [deparid=" + deparid + ", xxdd=" + xxdd + ", dizhi="
				+ dizhi + ", telephone=" + telephone + ", lianxiren="
				+ lianxiren + ", emial=" + emial + ", ytdw=" + ytdw
				+ ", leixing=" + leixing + ", yongdate=" + yongdate + ", huze="
				+ huze + "]";
	}

}
