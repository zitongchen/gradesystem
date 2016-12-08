package team.wuming.modules.admin.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.CORBA.UserException;

import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.admin.util.InputStudentMessageUitl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminServiceImpl();
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		Admin form = new Admin();
		form.setAdmin_acount(userId);
		form.setPassword(password);
		String verification = request.getParameter("verification");
		try {
			String verificationCode = (String) request.getSession()
					.getAttribute("verificationCode");
			if (!(verification.equals(verificationCode))) {
				request.setAttribute("verificationError", "验证码错误！");
				request.setAttribute("userId", userId);
				request.setAttribute("password", password);
				return "f:/index.jsp";
			}
			Admin Admin = adminService.login(form);
			request.getSession().setAttribute("session_admin", Admin);
			return "f:/jsps/admin/admin_homepage.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("userId", userId);
			request.setAttribute("password", password);
			return "f:/index.jsp";
		}
	}

	// 退出
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UserException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}

	// 更新数据
	public String updateAdminMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		adminService.updateAdminMessage(form);
		return "r:/AdminServlet?method=findAdminMessage";
	}

	// 更新密码
	public String updateAdminPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = new Admin();
		form.setAdmin_acount(request.getParameter("userId"));
		form.setPassword(request.getParameter("password"));
		Admin admin = (Admin) request.getSession()
				.getAttribute("session_admin");
		if (!form.getPassword().equals(admin.getPassword())) {
			request.setAttribute("passwordError", "原密码输入有误");
			return "f:/jsps/admin/update_password.jsp";
		} else {
			form.setPassword(request.getParameter("newPassword"));
		}
		adminService.updateAdminPassword(form);
		request.getSession().invalidate();// 销毁session并让用户重新登录
		return "r:/index.jsp";
	}

	// 上传学生信息
	public String inputStudentMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			FileUploadException {
		// 上传三部曲
		DiskFileItemFactory factory = new DiskFileItemFactory();// 创建工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);// 创建解析器
		sfu.setFileSizeMax(1024 * 1024 * 10);// 设置上传的大小为10m
		List<FileItem> fileItems = sfu.parseRequest(request);// 利用解析器来解析request
		Iterator iter = fileItems.iterator();// 创建迭代器
		String fileName = "";
		List<User> userList = new ArrayList<User>();// 创建保存学生信息的List集合
		InputStudentMessageUitl inputStudentMessageUtil = new InputStudentMessageUitl();// 引入Excel信息转化类
		while (iter.hasNext()) {
			FileItem fileItem = (FileItem) iter.next();
			if (!fileItem.isFormField()) {
				fileName = fileItem.getName();// 获取文件名称
				int fileNameLength = fileName.length();
				if (fileNameLength <= 0) {
					request.setAttribute("errorMessage", "你还没有选择上传的文件");
					return "f:/jsps/admin/input_student_message.jsp";
				}
				int start = fileName.lastIndexOf(".");
				String fileType = fileName.substring(start + 1, fileNameLength);
				if (!fileType.equals("xls")) {
					request.setAttribute("errorMessage",
							"你上传的文件不是Excel文件，请上传正确的文件！");
					return "f:/jsps/admin/input_student_message.jsp";
				}
				try {
					userList = inputStudentMessageUtil
							.studentMessageToList(fileItem.getInputStream());
				} catch (Exception e) {
					request.setAttribute("errorMessage", "上传失败！");
					return "f:jsps/admin/input_student_message.jsp";
				}
			}
			adminService.inputStudentMessage(userList);
			request.setAttribute("successMessage", "学生信息录入成功！");
		}
		return "f:/jsps/admin/input_student_message.jsp";
	}

}
