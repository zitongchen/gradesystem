package team.wuming.modules.experts.service;

import java.util.List;

import team.wuming.common.domain.Classes;
import team.wuming.modules.experts.domain.Expert;

public interface ExpertService {
	public Expert login(Expert form) throws ExpertException;

	public void updateExpertMessage(Expert form);

	public Expert findExpertMessage(String expertId);

	public void updateExpertPassword(Expert form);

	public List<Object> findClassNameByExpert(String expacount);

	public void registExpert(Expert expert);

	public int quertExpertNumber();

}
