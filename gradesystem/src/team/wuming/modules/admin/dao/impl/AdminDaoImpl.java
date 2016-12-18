package team.wuming.modules.admin.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objecenter;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.users.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDaoImpl implements AdminDao {
	private QueryRunner qr = new TxQueryRunner();



	public void updateAdminMessageById(Admin form) {
		String sql = "update admin ";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Admin findAdminMessageById(String adminId) {
		String sql = "select *　from admins where admin_account=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void updateAdminPassword(Admin form) {
		String sql = "update admins set password=? where admin_account=?";
		try {
			qr.update(sql, form.getPassword(), form.getAdmin_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Admin findAdminByAdminId(Admin form) {
		String sql = "select * from admins where admin_acount=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class),
					form.getAdmin_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 把Excel转化成List集合然后传入数据库
	 */
	@Override
	public void inputStudentMessage(List<User> userList) {
		String sql="insert into users(ksh,user_acount,password,nickname,xb,csrq,sfzh,"
				+ "zzmm,mz,yxdm,yxmc,zydm,zymc,xxdd,bh,cc,xz,xxxs,rxrq,dqszj,zczt,yjbyrq)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			for (User user : userList) {
				qr.update(sql, user.getKsh(),user.getUser_acount(),user.getPassword(),user.getNickname(),user.getXb(),user.getCsrq(),user.getSfzh(),
 user.getZzmm(),
						user.getMz(), user.getYxdm(), user.getYxmc(),
						user.getZydm(), user.getZymc(), user.getXxdd(),
						user.getBh(), user.getCc(),
						user.getXz(),user.getXxxs(),user.getRxrq(),user.getDqszj(),user.getZczt(),user.getYjbyrq());
			}
			} catch (SQLException e) {
			throw new RuntimeException();
			}
	}

	@Override
	public void addObjecter(Objecenter obj) {
		String sql = "insert into objecenter value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, obj.getVisit_count(), obj.getTitle(),
					obj.getDescription(), obj.getCreatetime(), obj.getStates(),
					obj.getRemark(), obj.getPicture(), obj.getZydm(),
					obj.getTermth(), obj.getSthours(), obj.getClasshour(),
					obj.getSbhour(), obj.getLyid(), obj.getScore());
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	@Override
	public void addMaijor(Maijor maijor) {
		String sql = "insert into maijor value(?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, maijor.getZydm(), maijor.getZymc(),
					maijor.getXxxs(), maijor.getPycc(), maijor.getXznx(),
					maijor.getXz(), maijor.getBz(), maijor.getLyid());
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
