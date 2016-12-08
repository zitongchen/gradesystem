package team.wuming.modules.admin.dao;

import java.util.List;

import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.users.domain.User;

public interface AdminDao {

	public void updateAdminMessageById(Admin form);

	public void updateAdminPassword(Admin form);

	public Admin findAdminByAdminId(Admin form);

	public void inputStudentMessage(List<User> userList);

}