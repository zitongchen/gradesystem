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
	 * 根据用户账号查询用户
	 */
	public User findByUserid(String userid) {
		try {
			String sql = "select * from users where user_acount=?";
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


	// 修改用户信息
	public void updateUserMessageById(User user) {
		String sql = "update users set realname=?,telephone=?,qq=?,email=?,weixin=?,"
				+ "city=?,occupation=?,education=?,marry=?,hobby=?,province=?,"
				+ "address=?,postCode=? where user_acount=? ";
		try {
			qr.update(sql, user.getRealname(), user.getTlelphone(),
					user.getQq(), user.getEmail(), user.getWeixin(),
					user.getCity(), user.getOccupation(), user.getEducation(),
					user.getMarry(), user.getHobby(), user.getProvince(),
					user.getAddress(), user.getPostCode(),
					user.getUser_acount());
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 更新用户密码
	 */
	public void updateUserPasswordById(User user) {
		String sql = "update users set password=? where user_acount=?";
		try {
			qr.update(sql, user.getPassword(), user.getUser_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	/**
	 * 查询学生本人成绩
	 */
	public List<StudentGrade> queryGradeByUserId(String userId)
 {
		String sql = "select * from studentgrade where user_acount=?";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			return qr.query(sql, new BeanListHandler<StudentGrade>(
					StudentGrade.class));
		} catch (Exception e) {
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
}
