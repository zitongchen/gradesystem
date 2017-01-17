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

	/**
	 * 学生登陆，验证用户是否存在
	 */
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



	// 忘记密码
	public void forgetUserPassword(User user) {

	}


	// 查询学生信息，人员信息展示功能
	public User findUserMessage(String userId) {
		return userDao.findUserMessageById(userId);
	}

	@Override
	public void uploadStudentPhoto(String userId, String photo) {
		userDao.uploadStudentPhote(userId, photo);

	}

	// 根据账号更改密码
	@Override
	public void updateUserPassword(String user_acount, String password) {
		userDao.updateUserPasswordById(user_acount, password);

	}

}
