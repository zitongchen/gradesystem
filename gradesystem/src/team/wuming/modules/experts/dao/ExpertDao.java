package team.wuming.modules.experts.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;

import team.wuming.modules.experts.domain.Expert;

public interface ExpertDao {
	public Expert findExertByExperId(Expert form);



	public Expert findExpertMessageById(String expertId);

	public void updateExpertPassword(String expacount, String password);

	public List<Object> findClassIdByExpert(String expacount);

	public void registExpert(Expert expert);

	public int quertExpertNumber();

	public void updateExpertPhoto(String expacount, String photoPath);

	public List<Expert> findExpertId();

	public void changeGradeState(String bh, String kcId);

	public void savePhotoPath(String userId, String filePath);

	public List<Object> findClassNameFail(String expacount);

}
