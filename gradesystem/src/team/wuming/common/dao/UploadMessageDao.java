package team.wuming.common.dao;

public interface UploadMessageDao {

	void savePersonMessage(String type, String userId, String key, String val);

}
