package team.wuming.common.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.wuming.common.service.CommonUploadPhoto;
import team.wuming.common.service.impl.ComUploadPhotoImpl;
import Decoder.BASE64Decoder;
import cn.itcast.servlet.BaseServlet;

public class UploadPhoto extends BaseServlet {
	private CommonUploadPhoto upload = new ComUploadPhotoImpl();

	public void uploadPhoto(HttpServletRequest request,
			HttpServletResponse response) {
		String imgData = request.getParameter("imgData");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		PrintWriter write = null;
		int delLength = imgData.indexOf(',') + 1;
		imgData = imgData.substring(delLength, imgData.length());
		imgData = imgData.replaceAll(" ", "+");
		BASE64Decoder decoder = new BASE64Decoder();
		String savePath = null;
		String filePath = null;
		if (type.equals("student")) {
			savePath = this.getServletContext().getRealPath(
					"/resource/images/user_photo");
			filePath = "/resource/images/user_photo/" + userId + ".jpg";
		} else if (type.equals("expert")) {
			savePath = this.getServletContext().getRealPath(
					"/resource/images/expert_photo");
			filePath = "/resource/images/expert_photo/" + userId + ".jpg";
		} else if (type.equals("admin")) {
			savePath = this.getServletContext().getRealPath(
					"/resource/images/admin_photo");
			filePath = "/resource/images/admin_photo/" + userId + ".jpg";
		}
		try {
			byte[] b = decoder.decodeBuffer(imgData);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			savePath = savePath + "/" + userId + ".jpg";

				// 若文件不存在不用删除，直接保存
			File file = new File(savePath);
			if (file.exists() && file.isFile()) {
				file.delete();
			}

			OutputStream out = new FileOutputStream(savePath);
			out.write(b);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (type.equals("student")) {
			upload.saveStudentPhoto(userId, filePath);
		} else if (type.equals("expert")) {
			upload.saveExpertPhoto(userId, filePath);
		} else if (type.equals("admin")) {
			upload.saveAdminPhoto(userId, filePath);
		}
		try {
			write = response.getWriter();
			write.println("上传成功！");
			write.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			write.close();
		}
	}
}
