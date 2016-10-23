package team.wuming.modules.admin.service.impl;

import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.dao.impl.AdminDaoImpl;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao = new AdminDaoImpl();

	public Admin login(Admin form) throws Exception {
		Admin admin = adminDao.findAdminByAdminId(form);
		if (admin == null) {
			throw new Exception("用户名不存在！");
		}
		if (!admin.getPassword().equals(form.getPassword())) {
			throw new Exception("用户名或密码有误！");
		}
		return admin;
	}

	public void updateAdminMessage(Admin form) {
		adminDao.updateAdminMessageById(form);

	}

	public Admin findAdminMessage(String AdminId) {
		return adminDao.findAdminMessageById(AdminId);
	}

	public void updateAdminPassword(Admin form) {
		adminDao.updateAdminPassword(form);

	}
}
