package team.wuming.modules.users.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.wuming.common.dao.StudentGradeDao;
import team.wuming.common.dao.impl.StudentGradeDaoImpl;
import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.users.dao.UserDao;
import team.wuming.modules.users.dao.impl.UserDaoImpl;
import team.wuming.modules.users.domain.User;
import team.wuming.modules.users.service.UserException;
import team.wuming.modules.users.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	public User login(User form) throws UserException {
		User user = userDao.findByUserid(form.getUser_acount());
		if (user == null)
			throw new UserException("用户名不存在");
		if (!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误");
		return user;
	}

	// 更新学生信息
	public void updateUserMessage(User user) {
		userDao.updateUserMessageById(user);
	}

	// 更新学生密码
	public void updateUserPassword(User user) {
		userDao.updateUserPasswordById(user);
	}

	// 忘记密码
	public void forgetUserPassword(User user) {

	}

	// 学生查询成绩
	public List<StudentGrade> queryUserGrade(String userId) {
		return userDao.queryGradeByUserId(userId);
	}

	public User findUserMessage(String userId) {
		return userDao.findUserMessageById(userId);
	}



}
