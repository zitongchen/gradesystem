package team.wuming.modules.experts.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import cn.itcast.commons.CommonUtils;

public class ExpertRegisterServlet extends HttpServlet {
	private ExpertService expertService = new ExpertServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1 * 1024 * 1024);// 设置上传 的图片最大为1M
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);// 获取文件条目

			Map<String, String> map = new HashMap<String, String>();
			FileItem pictureItem = null;
			String filename = null;
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					map.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));
				} else {
					pictureItem = fileItem;
				}
			}

			// 提取验证码
			String code = map.get("code");
			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			request.getSession().removeAttribute("verificationCode");
			if (!code.equals(verificationCode)) {
				request.setAttribute("errorMessage", "你输入的验证码错误！");
				request.getRequestDispatcher(
						"/jsps/common/teacher_register.jsp").forward(request,
						response);
			}
			String savepath = this.getServletContext().getRealPath(
					"/WEB-INF/resource/Expert_picture");
			if (pictureItem != null) {
				filename = pictureItem.getName();// 获取照片的名字
				/*
				 * 检验文件的扩展名,出错回显
				 */
				if (!filename.toLowerCase().endsWith("jpg")) {
					request.setAttribute("errorMessage", "你上传的图片不是JPG扩展名！");
					request.getRequestDispatcher(
							"/jsps/common/teacher_register.jsp").forward(
							request, response);
				}
				// 设置图片的名称为uuid+.jpg
				int point = filename.indexOf(".");
				int legth = filename.length();
				filename = CommonUtils.uuid()
						+ filename.substring(point, legth);
				savepath = savepath + "/";
				File destFile = new File(savepath, filename);
				//查看照片的目录是否存在，若是存在不存在便创建一个
				pictureItem.write(destFile);
			}
			// 设置教师的编号
			int expertNumber = expertService.quertExpertNumber();
			String expacount = String.valueOf(expertNumber + 100100);// 设置教师账号从100100开始
			// 把表单的信息利用工具封装到javabean里面
			Expert expert = CommonUtils.toBean(map,
					Expert.class);
			String sheng = map.get("sheng");
			String shi = map.get("shi");
			if (sheng != "" || shi != " ") {
				expert.setCity(sheng + shi);
			}
			expert.setPicture("/WEB-INF/resource/Expert_picture/" + filename);// 保存教师图片的保存路径
			expert.setExpacount(expacount);// 保存用户账号
			expertService.registExpert(expert);
			request.setAttribute("successMessage", "注册成功！");
			request.setAttribute("successTxt", "你的教师账号为：" + expacount
					+ "，请等待管理员审核资料。");
			request.getRequestDispatcher("/jsps/common/active_message.jsp")
					.forward(request, response);
		} catch (Exception e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("errorMessage", "您上传的文件超出了1M");
				System.out.println("test");
				request.getRequestDispatcher(
						"/jsps/common/teacher_register.jsp")
						.forward(request, response);
			}
			request.setAttribute("errorMessage", "注册成功！");
			request.setAttribute("errorTxt", "系统出现了小问题，请重试！");
			request.getRequestDispatcher("/jsps/common/active_message.jsp")
					.forward(request, response);
			throw new ServletException(e);
		}
	}

}
