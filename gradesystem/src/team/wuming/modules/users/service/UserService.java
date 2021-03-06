package team.wuming.modules.users.service;

import java.sql.SQLException;
import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.users.domain.User;

public interface UserService {
	public User login(User form) throws UserException;




	// 忘记密码
	public void forgetUserPassword(User user);

	// 查询学生信息
	public User findUserMessage(String userId);

	public void uploadStudentPhoto(String userId, String photo);

	// 根据账号更改密码
	public void updateUserPassword(String user_acount, String password);


}
