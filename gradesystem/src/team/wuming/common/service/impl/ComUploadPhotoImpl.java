package team.wuming.common.service.impl;

import team.wuming.common.service.CommonUploadPhoto;
import team.wuming.modules.admin.dao.AdminDao;
import team.wuming.modules.admin.dao.impl.AdminDaoImpl;
import team.wuming.modules.experts.dao.ExpertDao;
import team.wuming.modules.experts.dao.impl.ExpertDaoImpl;
import team.wuming.modules.users.dao.UserDao;
import team.wuming.modules.users.dao.impl.UserDaoImpl;

public class ComUploadPhotoImpl implements CommonUploadPhoto {

	private AdminDao adminDao = new AdminDaoImpl();
	private ExpertDao expertDao = new ExpertDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	@Override
	public void saveStudentPhoto(String userId, String filePath) {
		userDao.savePhotoPath(userId, filePath);

	}

	@Override
	public void saveExpertPhoto(String userId, String filePath) {
		expertDao.savePhotoPath(userId, filePath);

	}

	@Override
	public void saveAdminPhoto(String userId, String filePath) {
		adminDao.savePhotoPath(userId, filePath);

	}

}
