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

import team.wuming.common.domain.Docourse;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.dao.UserDao;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new TxQueryRunner();

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
		String sql = "update users set ";

	}

	public void updateUserPasswordById(User user) {
		String sql = "update users set password=? where user_acount=?";
		try {
			qr.update(sql, user.getPassword(), user.getUser_acount());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public List<StudentGrade> queryGradeByUserId(String userId)
 {
		String sql = "select * from studentgrade where user_acount=?";
		List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
		try {
			List<Map<String, Object>> maps = qr.query(sql,
					new MapListHandler(), userId);
			for (Map<String, Object> map : maps) {
				Docourse docourse=CommonUtils.toBean(map, Docourse.class);
				Expert expert=CommonUtils.toBean(map, Expert.class);
				StudentGrade studentGrade = CommonUtils.toBean(map,
						StudentGrade.class);
				studentGrade.setDocourse(docourse);
				studentGrade.setExpert(expert);
				studentGrades.add(studentGrade);
			}
			return studentGrades;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}



}
