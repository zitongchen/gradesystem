package team.wuming.modules.admin.dao;

import team.wuming.modules.admin.domain.Admin;

public interface AdminDao {

	public void updateAdminMessageById(Admin form);

	public Admin findAdminMessageById(String adminId);

	public void updateAdminPassword(Admin form);

	public Admin findAdminByAdminId(Admin form);

}