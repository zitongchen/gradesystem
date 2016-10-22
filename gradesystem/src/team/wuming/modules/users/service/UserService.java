package team.wuming.modules.users.service;

import java.util.List;

import team.wuming.modules.users.dao.UserDao;
import team.wuming.modules.users.domain.StudentGrade;
import team.wuming.modules.users.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();

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
