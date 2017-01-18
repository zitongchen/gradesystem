package team.wuming.modules.experts.service;

import java.util.List;

import team.wuming.modules.experts.domain.Expert;

public interface ExpertService {
	public Expert login(Expert form) throws ExpertException;

	public Expert findExpertMessage(String expertId);

	public void updateExpertPassword(String expacount, String password);

	public List<Object> findClassNameByExpert(String expacount);

	public void registExpert(Expert expert);

	public int quertExpertNumber();

	public List<Expert> findExpertId();

	public void changeGradeState(String bh, String kcId);

	public List<Object> findClassNameFail(String expacount);

}
