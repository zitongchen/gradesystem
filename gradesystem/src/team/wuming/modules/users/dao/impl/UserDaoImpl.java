package team.wuming.modules.users.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.dao.UserDao;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据用户账号查询用户(登陆功能)
	 */
	public User findByUserid(String userid) {
		try {
			String sql = "select user_acount,password,nickname from users where user_acount=?";
			return qr.query(sql, new BeanHandler<User>(User.class), userid);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public User findUserMessageById(String userId) {
		String sql = "select * from users where user_acount=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), userId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}






	@Override
	public void uploadStudentPhote(String userId, String photo) {
		String sql = "update users set photo=? where user_acount=?";
		try {
			qr.update(sql, photo, userId);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	// 根据账号修改密码
	@Override
	public void updateUserPasswordById(String user_acount, String password) {
		String sql = "update users set password=? where user_acount=?";
		try {
			qr.update(sql, password, user_acount);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 保存图片的途径
	@Override
	public void savePhotoPath(String userId, String filePath) {
		String sql = "update users set photo=? where user_acount=?";
		try {
			qr.update(sql, filePath, userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
