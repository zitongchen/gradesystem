package team.wuming.modules.admin.service;

import java.util.List;

import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.users.domain.User;

public interface AdminService {
	public Admin login(Admin form) throws Exception;

	public void updateAdminMessage(Admin form);

	public void updateAdminPassword(Admin form);

	public void inputStudentMessage(List<User> userList);
}
