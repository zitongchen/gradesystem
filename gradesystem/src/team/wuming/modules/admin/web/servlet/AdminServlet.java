package team.wuming.modules.admin.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.omg.CORBA.UserException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import team.wuming.common.domain.Maijor;
import team.wuming.common.domain.Objcenter;
import team.wuming.common.domain.Xuexid;
import team.wuming.modules.admin.domain.Admin;
import team.wuming.modules.admin.service.AdminService;
import team.wuming.modules.admin.service.impl.AdminServiceImpl;
import team.wuming.modules.admin.util.InputStudentMessageUitl;
import team.wuming.modules.experts.domain.Expert;
import team.wuming.modules.experts.service.ExpertService;
import team.wuming.modules.experts.service.impl.ExpertServiceImpl;
import team.wuming.modules.users.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminServiceImpl();
	private ExpertService expertService = new ExpertServiceImpl();
	/*
	 * 管理员登陆页面
	 */
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
			return "f:/jsps/common/homepage.jsp";
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

	//管理员信息 更新
	public String updateAdminMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
		adminService.updateAdminMessage(form);
		return "r:/AdminServlet?method=findAdminMessage";
	}

	// 管理员更新密码
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

	// 管理员上传学生信息
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
					return "f:/jsps/admin/add_student_message.jsp";
				}
				try {
					userList = inputStudentMessageUtil
							.studentMessageToList(fileItem.getInputStream());
				} catch (Exception e) {
					request.setAttribute("errorMessage", "上传失败！");
					return "f:jsps/admin/add_student_message.jsp";
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
				return "f:jsps/admin/add_student_message.jsp";
			}
			request.setAttribute("successMessage", "学生信息录入成功！");
		}
		return "f:/jsps/admin/add_student_message.jsp";
	}
	
	//学生信息Excel样表的下载
	public String downloadUserExcel(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		String fileName = "学生数据导入格式表.xls";
		String path = request.getServletContext().getRealPath(
				"/WEB-INF/resource/" + fileName);
		File file = new File(path);
		if (!file.exists()) {
			request.setAttribute("errorMessage", "你要下载的文件不存在！");
			return "f:/jsps/admin/input_student_message.jsp";
		}
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.addHeader("content-disposition", "attachment;filename="
				+ fileName);
		IOUtils.copy(new FileInputStream(file), response.getOutputStream());
		return "f:/jsps/admin/input_student_message.jsp";
	}

	//代老师管理成绩：根据教师编号查询教师所教的班级
	public String findClassByExpertId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			UserException {
		String expacount = request.getParameter("expertId");
		List<Object> classLists = expertService
				.findClassNameByExpert(expacount);
		List<String> classNameList = new ArrayList<String>();
		for (Object object : classLists) {
			String className = String.valueOf(object);
			classNameList.add(className);
		}
		request.setAttribute("classList", classNameList);
		request.setAttribute("expacount", expacount);
		return "f:/jsps/admin/search_experts.jsp";
	}
	/*
	 * 添加学科信息
	 */
	public String addObjcenter_photo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		sfu.setSizeMax(1*1024*1024);//设置上传的图片为1M
		try {
			List<FileItem> fileItems=sfu.parseRequest(request);
			Map<String ,String> mapList=new HashMap<String , String>();
			String filename = null;
			FileItem uploadFile = null;
			for (FileItem fileItem : fileItems) {
				if(fileItem.isFormField()){
					mapList.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
				}else{
					filename=fileItem.getName();//获取上传文件的名称
					if(filename!=null){//判断是否上传文件，若上传便进行处理，反之不进行处理
						uploadFile=fileItem;
						if(filename.endsWith(".jpg")){
							request.setAttribute("errorMessage", "你上次的照片不是jpg格式，请上传jpg格式的照片！");
							request.getRequestDispatcher("#").forward(request, response);
						}
					}
				}
			}
			Objcenter objecenter = CommonUtils.toBean(mapList, Objcenter.class);
			if (uploadFile != null) {
				String savepath = this.getServletContext().getRealPath(
						"/WEB-INF/Objcenter");
				filename = CommonUtils.uuid() + "_" + filename;
				objecenter.setPicture(savepath + "/" + filename);// 保存图片的路径
				File file = new File(savepath, filename);
				uploadFile.write(file);// 保存文件到指定的文职
			}
			adminService.addObjcenter(objecenter);
		} catch (Exception e) {
			//若文件超出限制便报错
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("errorMessage", "你上传的文件超过了1M！");
				request.getRequestDispatcher("#").forward(request, response);
			}
		}
		request.setAttribute("successMessage", "设置学科课程成功！");
		return "f:/jsps/admin/#.jsp";
	}

	// 添加课程信息，不涉及到图片的上传
	public String addObjcenter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Objcenter objecenter = CommonUtils.toBean(request.getParameterMap(),
				Objcenter.class);
		// 把对应字段从字符串类型转换到int类型
		if (request.getParameter("sthours") != "") {
			Integer sthours = Integer.valueOf(request.getParameter("sthours"));
			objecenter.setSthours(sthours);
		}
		if (request.getParameter("classhour") != "") {
			Integer classhour = Integer.valueOf(request
					.getParameter("classhour"));
			objecenter.setClasshour(classhour);
		}
		if (request.getParameter("sbhour") != "") {
			Integer sbhour = Integer.valueOf(request.getParameter("sbhour"));

			objecenter.setSbhour(sbhour);
		}
		if (request.getParameter("score") != "") {
			Integer score = Integer.valueOf(request.getParameter("score"));
			objecenter.setScore(score);
		}
		adminService.addObjcenter(objecenter);
		request.setAttribute("successMessage", "课程添加成功！");
		return "f:/jsps/common/active_message.jsp";
	}
	// 添加专业
	public String addMaijor(HttpServletRequest request,
			HttpServletResponse response) {
		// 把表单内容封装到Maijor对象中
		Maijor maijor = CommonUtils.toBean(request.getParameterMap(),
				Maijor.class);
		adminService.addMaijor(maijor);
		request.setAttribute("successMessage", "专业添加成功！");
		return "f:/jsps/common/active_message.jsp";
	}

	// 添加学习地点
	public String addXuexid(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		Xuexid xuexid = CommonUtils.toBean(request.getParameterMap(),
				Xuexid.class);

		if (request.getParameter("yongdate") != "") {
			// 把Stringe格式转换为Date格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date yongdate = formatter.parse(request.getParameter("yongdate"));
			xuexid.setYongdate(yongdate);
		}
		adminService.addXuexid(xuexid);
		request.setAttribute("successMessage", "学习地点添加成功！");
		return "f:/jsps/admin/active_message.jsp";
	}

	// 查询专业-用于添加课程页面的专业选择
	public void findMaijor(HttpServletRequest request,
			HttpServletResponse response) {
		List<Maijor> maijorList = adminService.findMaijor();
		String output = null;
		if (!maijorList.isEmpty()) {
			output = JSON.toJSONString(maijorList);

		}
		try {
			response.getWriter().println(output);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// 根据专业编号查询课程
	public void findObjcenterByZydm(HttpServletRequest request,
			HttpServletResponse response) {
		String zydm = request.getParameter("zydm");
		List<Objcenter> objcenterList = adminService.findObjcenterByZydm(zydm);
		String output = null;
		if (!objcenterList.isEmpty()) {
			output = JSON.toJSONString(objcenterList);

		}
		try {
			response.getWriter().println(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findExpertId(HttpServletRequest request,
			HttpServletResponse response) {
		List<Expert> expertList = expertService.findExpertId();
		String output = null;

		if (!expertList.isEmpty()) {
			output = JSON.toJSONString(expertList);
		}
		try {
			response.getWriter().println(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 根据专业代码查询班级-添加班级课程接口
	public void findClassByZydm(HttpServletRequest request,
			HttpServletResponse response) {
		String zydm = request.getParameter("zydm");
		List<Object> classNameLists = adminService.findClassByZydm(zydm);
		List<String> classList = new ArrayList<String>();
		String output = null;
		if (!classNameLists.isEmpty()) {
			output = JSON.toJSONString(classNameLists);
		}

		try {
			response.getWriter().println(output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 管理员根据情况向studentGrade写入内容,并返回操作信息！
	public String addStudentGrade(HttpServletRequest request,
			HttpServletResponse response) {
		String className = request.getParameter("bh");
		String expacount = request.getParameter("expacount");
		String visit_count = request.getParameter("kc");
		adminService.addStudentGrade(className, visit_count, expacount);
		try {
			request.setAttribute("successMessage", "添加班级课程成功！");
			return "f:/jsps/common/active_message.jsp";
		} catch (Exception e) {
			request.setAttribute("errorMessage", "添加班级课程失败！");
			return "f:/jsps/common/active_message.jsp";
		}
	}

}
