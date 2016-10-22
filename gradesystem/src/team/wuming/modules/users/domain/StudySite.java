package team.wuming.modules.users.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tony 学习地点
 */
public class StudySite implements Serializable {
	private String deparid;// 学习地点编码
	private String xxdd;// 学习点名称
	private String dizhi;// 学习地址
	private String lianxi;// 联系电话
	private String lianxiren;// 联系人
	private String email;// 邮箱地址
	private String ytdw;// 依托单位
	private String leixing;// 单位类型
	private Date yongdate;// 建立日期
	private String huze;// 负责人
	private String tell;// 联系电话

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

	public String getLianxi() {
		return lianxi;
	}

	public void setLianxi(String lianxi) {
		this.lianxi = lianxi;
	}

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	@Override
	public String toString() {
		return "StudySite [deparid=" + deparid + ", xxdd=" + xxdd + ", dizhi="
				+ dizhi + ", lianxi=" + lianxi + ", lianxiren=" + lianxiren
				+ ", email=" + email + ", ytdw=" + ytdw + ", leixing="
				+ leixing + ", yongdate=" + yongdate + ", huze=" + huze
				+ ", tell=" + tell + "]";
	}

}
