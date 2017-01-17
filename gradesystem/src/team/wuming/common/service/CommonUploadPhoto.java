package team.wuming.common.service;

public interface CommonUploadPhoto {

	void saveStudentPhoto(String userId, String filePath);

	void saveExpertPhoto(String userId, String filePath);

	void saveAdminPhoto(String userId, String filePath);

}
