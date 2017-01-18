package team.wuming.common.service.impl;

import team.wuming.common.dao.UploadMessageDao;
import team.wuming.common.dao.impl.UploadMessageDaoImpl;
import team.wuming.common.service.UploadMessageService;

public class UploadMessageServiceImpl implements UploadMessageService {
	private UploadMessageDao ulMessageDao = new UploadMessageDaoImpl();
	@Override
	public void savePersonMessage(String type, String userId, String key,
			String val) {
		ulMessageDao.savePersonMessage(type, userId, key, val);

	}

}
