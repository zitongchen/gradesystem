package team.wuming.modules.users.dao;

import java.sql.SQLException;
import java.util.List;

import team.wuming.common.domain.PageBean;
import team.wuming.common.domain.StudentGrade;
import team.wuming.modules.users.domain.User;

public interface UserDao {
	public User findByUserid(String userid);

	public User findUserMessageById(String userId);




	public void uploadStudentPhote(String userId, String photo);

	public void updateUserPasswordById(String user_acount, String password);

	public void savePhotoPath(String userId, String filePath);
			
}

