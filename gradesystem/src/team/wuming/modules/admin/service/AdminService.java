package team.wuming.modules.admin.service;

import team.wuming.modules.admin.domain.Admin;

public interface AdminService {
	public Admin login(Admin form) throws Exception;

	public void updateAdminMessage(Admin form);

	public Admin findAdminMessage(String AdminId);

	public void updateAdminPassword(Admin form);
}
