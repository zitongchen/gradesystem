package team.wuming.modules.experts.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import team.wuming.modules.experts.domain.Expert;

public interface ExpertDao {
	public Expert findExertByExperId(Expert form);

	public void updateExpertMessageById(Expert form);

	public Expert findExpertMessageById(String expertId);

	public void updateExpertPassword(Expert form);

}
