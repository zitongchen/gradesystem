package team.wuming.modules.admin.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.admin.util.InputStudentMessageUitl;
import team.wuming.modules.users.domain.User;
import cn.itcast.servlet.BaseServlet;

public class UploadStudentMessageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 管理员上传学生信息
			// 上传三部曲
		AdminService adminService = new AdminServiceImpl();
		DiskFileItemFactory factory = new DiskFileItemFactory();// 创建工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);// 创建解析器
		sfu.setFileSizeMax(1024 * 1024 * 10);// 设置上传的大小为10m
		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 利用解析器来解析request
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
					request.getRequestDispatcher(
							"/jsps/admin/add_student_message.jsp").forward(
							request, response);
					return;
						//return "f:/jsps/admin/add_student_message.jsp";
				}
				int start = fileName.lastIndexOf(".");
				String fileType = fileName.substring(start + 1, fileNameLength);
				if (!fileType.equals("xls")) {
					request.setAttribute("errorMessage",
								"你上传的文件不是Excel文件，请上传正确的文件！");
					request.getRequestDispatcher(
							"/jsps/admin/add_student_message.jsp").forward(
							request, response);
					return;
						//return "f:/jsps/admin/add_student_message.jsp";
				}
				try {
					userList = inputStudentMessageUtil
							.studentMessageToList(fileItem.getInputStream());
				} catch (Exception e) {
					request.setAttribute("errorMessage", "上传失败！");
					request.getRequestDispatcher("jsps/admin/add_student_message.jsp").forward(request, response);
					return ;
					// return "f:jsps/admin/add_student_message.jsp";
				}
			}
			try {
				adminService.inputStudentMessage(userList);
			} catch (Exception e) {
				String message = e.getMessage();
				message = message.substring(message.indexOf("\'") + 1,
						message.indexOf("\'") + 13);
				request.setAttribute("errorMessage", "上传失败！数据库里面已经存在学号为："
						+ message + "，请确认上传的信息表格是否正确！");
				request.getRequestDispatcher(
						"jsps/admin/add_student_message.jsp").forward(request,
						response);
				return;
					//return "f:jsps/admin/add_student_message.jsp";
			}
			request.setAttribute("successMessage", "学生信息录入成功！");
		}
		request.getRequestDispatcher("/jsps/admin/add_student_message.jsp")
				.forward(request, response);
		return;
			//return "f:/jsps/admin/add_student_message.jsp";
	}

}

