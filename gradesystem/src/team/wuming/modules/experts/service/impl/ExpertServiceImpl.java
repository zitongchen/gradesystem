package team.wuming.modules.experts.service.impl;

import team.wuming.modules.experts.dao.ExpertDao;
import team.wuming.modules.experts.dao.impl.ExpertDaoImpl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertException;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.users.domain.User;

public class ExpertServiceImpl implements ExpertService {
	private ExpertDao expertDao = new ExpertDaoImpl();

	public Expert login(Expert form) throws ExpertException {
		Expert expert = expertDao.findExertByExperId(form);
		if (expert == null) {
			throw new ExpertException("用户名不存在！");
		}
		if (!expert.getPassword().equals(form.getPassword())) {
			throw new ExpertException("用户名或密码有误！");
		}
		return expert;
	}

	public void updateExpertMessage(Expert form) {
		expertDao.updateExpertMessageById(form);

	}

	public Expert findExpertMessage(String expertId) {
		return expertDao.findExpertMessageById(expertId);
	}

	public void updateExpertPassword(Expert form) {
		expertDao.updateExpertPassword(form);

	}
}
