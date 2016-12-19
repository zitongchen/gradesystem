package team.wuming.modules.admin.service.impl;

import java.util.List;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objecenter;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.dao.impl.AdminDaoImpl;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.users.domain.User;

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


	public void updateAdminPassword(Admin form) {
		adminDao.updateAdminPassword(form);

	}

	@Override
	public void inputStudentMessage(List<User> userList) {
		adminDao.inputStudentMessage(userList);
	}

	@Override
	public void addObjecter(Objecenter objecenter) {
		adminDao.addObjecter(objecenter);

	}

	@Override
	public void addMaijor(Maijor maijor) {
		adminDao.addMaijor(maijor);

	}

	@Override
	public void addXuexid(Xuexid xuexid) {
		adminDao.addXuexid(xuexid);
	}
}
