package team.wuming.modules.experts.service;

import team.wuming.modules.experts.domain.Expert;

public interface ExpertService {
	public Expert login(Expert form) throws ExpertException;

	public void updateExpertMessage(Expert form);

	public Expert findExpertMessage(String expertId);

	public void updateExpertPassword(Expert form);
}
