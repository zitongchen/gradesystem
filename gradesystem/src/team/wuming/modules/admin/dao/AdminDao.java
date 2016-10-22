package team.wuming.modules.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import team.wuming.modules.admin.domain.Admin;
import cn.itcast.jdbc.TxQueryRunner;

public class AdminDao {
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
		String sql = "select *　from admin where admin_account=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void updateAdminPassword(Admin form) {
		String sql = "update admin set password=? where admin_account=?";
		try {
			qr.update(sql, form.getPassword(), form.getAdmin_account());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public Admin findAdminByAdminId(Admin form) {
		String sql = "select * from admin  admin_account=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class),
					form.getAdmin_account());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
