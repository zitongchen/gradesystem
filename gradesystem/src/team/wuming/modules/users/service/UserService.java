package team.wuming.modules.users.service;

import java.sql.SQLException;
import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.users.domain.User;

public interface UserService {
	public User login(User form) throws UserException;

	// 更新学生信息
	public void updateUserMessage(User user);

	// 更新学生密码
	public void updateUserPassword(User user);

	// 忘记密码
	public void forgetUserPassword(User user);

	// 学生查询成绩
	public List<StudentGrade> queryUserGrade(String userId);

	public User findUserMessage(String userId);



}
