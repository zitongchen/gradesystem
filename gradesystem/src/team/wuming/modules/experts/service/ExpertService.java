package team.wuming.modules.experts.service;

import team.wuming.modules.experts.dao.ExpertDao;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;

public class ExpertService {
	private ExpertDao expertDao = new ExpertDao();

	public Expert login(Expert form) throws Exception {
		Expert expert = expertDao.findExertByExperId(form);
		if (expert == null) {
			throw new Exception("用户名不存在！");
		}
		if (!expert.getPassword().equals(form.getPassword())) {
			throw new Exception("用户名或密码有误！");
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
