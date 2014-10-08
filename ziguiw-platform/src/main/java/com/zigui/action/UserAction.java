package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.*;
import com.zigui.utils.*;
import com.zigui.utils.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAction extends BaseAction implements ServletContextAware {

	private static final long serialVersionUID = 1301341137225408989L;

	private static transient final Log logger = LogFactory
			.getLog(UserAction.class);

	private UserBase user;
	private String oldPassword;
	private String secondPassword;
	private Page<UserBase> users;
	private Map<String, String> query;
	private int pageNo = 1;
	private int pageSize = 10;
	private String orderBy = "createTime";
	private Boolean isAsc = false;
	private String loginPreUrl;

	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;

	private long recommendRegionId;

	private static final String IMG_PATH = "img_path";

	private String countPerPage = "10";

	private String currentPage = "1";

	@Autowired
	private transient UserServiceImpl userService;

	@Autowired
	private transient UserQueryServiceImpl userQueryService;

	@Autowired
	private transient UserMoodServiceImpl userMoodService;

	@Autowired
	private transient UserAlbumServiceImpl userAlbumService;

	@Autowired
	private transient CommonServiceImpl commonService;

	@Autowired
	private transient SchoolServiceImpl schoolService;

	@Autowired
	private transient JavaMailSenderImpl mailSenderService1;

	// 短信发送服务
	@Autowired
	private SmsServiceImpl smsService;

	private int moodCount;

	private int albumCount;

	private ServletContext context;

	private String validateCode;

	private Long[] opIds;

	private School school;

	private SchoolInfo schoolInfo;

	private String hostUserName;

	private Teacher teacher;

	private Student student;

	private Parent parent;

    private String emailUrl = ConfigUtils.getProperty("email_Url");

	private Date currentTime = new Date();

	// 积分历史
	private Page<PointsHistory> pointHistory;

	// 班级管理
	private Clasz clasz;

	// 手机验证码
	private String mobileValidateCode;

	/**
	 * 注册校验
	 */
	public void validateRegister() {
        log.info(String.format("UserAction's validateRegister method is running......"));
		// 注册可以从后台配置中心关闭
		if (ConfigUtils.getBoolByKey("register_close", false)) {
			this.addFieldError("user.email", "注册功能暂时关闭");
			return;
		}

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		String c = (String) session
				.get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		// 如果是邮件注册，校验验证码
		if (StringUtils.isNotEmpty(validateCode)&&StringUtils.isNotEmpty(user.getEmail())
				&& (StringUtils.isEmpty(c) || StringUtils.isEmpty(validateCode) || !StringUtils
						.equals(validateCode, c))) {
			this.addFieldError("validateCode", "验证码不正确");
			return;
		}

		// 如果是手机注册，校验手机验证码
		if (StringUtils.isNotEmpty(mobileValidateCode)&&StringUtils.isNotEmpty(user.getMobile())
				&& (!session.get(Constant.MOBILE_VALIDATE_CODE).equals(
						mobileValidateCode) || StringUtils
						.isEmpty(mobileValidateCode))) {
			this.addFieldError("validateCode", "短信验证码不正确");
			return;
		}

		// 邮箱和手机必须选择一个
		if (StringUtils.isEmpty(user.getEmail())
				&& StringUtils.isEmpty(user.getMobile())) {
			this.addFieldError("user.email", "邮箱/手机不能为空");
			return;
		}

		// 如果邮箱不空，校验邮箱格式
		if (StringUtils.isNotEmpty(user.getEmail())
				&& !isEmail(user.getEmail())) {
			this.addFieldError("user.email", "请输入正确的邮箱格式");
			return;
		}

		// 密码不能为空
		if (StringUtils.isEmpty(user.getPassword())) {
			this.addFieldError("user.password", "密码不能为空");
			return;
		}

		// 两次密码是否一直
		if (!user.getPassword().equals(secondPassword)) {
			this.addFieldError("user.password", "两次输入密码不一致");
			return;
		}

		// 注册帐号不能为空
		if (StringUtils.isEmpty(user.getNickName())) {
			this.addFieldError("user.nickName", "ID不能为空");
			return;
		}

		// 帐号只能是数字和字母
		if (!isAccountId(user.getNickName())) {
			this.addFieldError("user.nickName", "ID只能是数字、英语或者下划线");
			return;
		}

		UserBase tmpUser = null;
		// 校验邮箱是否已经被注册
		if (StringUtils.isNotEmpty(user.getEmail())) {
			tmpUser = userService.getUserBaseByEmail(user.getEmail());
			if (tmpUser != null) {
				this.addFieldError("user.email", "该邮箱已经被人注册");
				return;
			}
		}

		// 校验用户手机是否注册
		if (StringUtils.isNotEmpty(user.getMobile())) {
			tmpUser = userService.getUserBaseByMobile(user.getMobile());
			if (tmpUser != null) {
				this.addFieldError("user.mobile", "该手机已经被人注册");
				return;
			}
		}

		tmpUser = userService.getUserBaseByNickName(user.getNickName());
		if (tmpUser != null) {
			this.addFieldError("user.nickName", "该用户名已经被人注册");
			return;
		}

	}

	/**
	 * 注册
	 *
	 * @return
	 */
	public String register() {
        log.info(String.format("UserAction's register method is running......"));
		HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		String result = Action.INPUT;

		if (StringUtils.isNotEmpty(user.getEmail())) {
			// 给用户发送激活邮件，首先需要生成验证码，目前验证码的有效期是30分钟
			ValidateCode code = new ValidateCode();
			code.setValidateCode(RandomUtils.nextInt());
			code.setInvalidTime(new Date(System.currentTimeMillis() + 1000 * 60
					* 60 * 24 * 365));

			commonService.save(code);

			user.setPassword(DigestUtils.md5Hex(user.getPassword()));

			userService.saveOrUpdate(user);

			// 发送邮件
			MimeMessage msg = mailSenderService1.createMimeMessage();
			// 创建MimeMessageHelper对象，处理MimeMessage的辅助类
			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true,
						"UTF-8");
				helper.setTo(user.getEmail());
                helper.setFrom("admin@ziguiw.com");
				helper.setSubject("子贵网的注册激活邮件");
                request=ServletActionContext.getRequest();
				helper.setText(
						"<html><head></head><body>您好，谢谢您注册了子贵网，您在子贵网注册的用户名是："
								+ user.getNickName()
								+ "<br><a href='"
								+ ConfigUtils
										.getStringByKey("active_register",
                                                emailUrl)
								+ "?user.id=" + user.getId() + "&activeCode"
								+ code.getValidateCode()
								+ "'>点击此处激活</a></body></html>", true);

				mailSenderService1.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

			result = "PENDING_ACTIVITE";
		} else if (StringUtils.isNotEmpty(user.getMobile())) {
			// 发送短信
			if (session.get(Constant.MOBILE_VALIDATE_CODE).equals(
					mobileValidateCode)
					&& StringUtils.isNotEmpty(mobileValidateCode)) {
				user.setState(2);

				result = Action.SUCCESS;
			} else {
				result = Action.INPUT;
			}

			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            user.setType(0);
			userService.saveOrUpdate(user);
		}

		// 清空session中的旧有用户信息
		session.remove("VALID_USER");
		session.put("VALID_USER", user);

		 request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");

		if (StringUtils.isEmpty(loginPreUrl)) {
			loginPreUrl = "/index.jsp";
		}

		// 给用户发放注册积分
		PointsHistory ph = new PointsHistory();
		ph.setUser(user);
		ph.setFlag(Constant.REGISTER);

		ph.setPointsChange(Constant.REGISTER_POINT);
		userService.changePoints(ph);

		CookieUtils.setCookie(response, "loginMemberUsername",
                user.getNickName());

		// 内容提醒，注册提醒
		ContentNotify contentNotify = new ContentNotify();
		contentNotify.setType(Constant.REGISTER_NOTIFY);
		contentNotify.setContentId(user.getId());

		commonService.save(contentNotify);

		return result;

	}

	/**
	 * 发送手机短信验证码
	 *
	 * @return
	 */
	public String geneMobileValidateCode() {
		// 给用户发送验证码短信，首先需要生成验证码，目前验证码的有效期是30分钟
		ValidateCode code = new ValidateCode();
		code.setValidateCode(RandomUtils.nextInt());
		code.setInvalidTime(new Date(System.currentTimeMillis() + 1000 * 60
				* 60 * 24 * 365));

		commonService.save(code);

        // 发送短信
		smsService.sendSms(user.getMobile(), "您好，谢谢您注册了子贵网，您在子贵网注册的用户名是："
				+ user.getNickName() + "。您的注册验证码是" + code.getValidateCode());

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		session.put(Constant.MOBILE_VALIDATE_CODE,
				String.valueOf(code.getValidateCode()));

		System.out.println("code.getValidateCode()" + code.getValidateCode());

		return Action.NONE;
	}

	/**
	 * 激活用户
	 *
	 * @return
	 */
	public String activeUser() {
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		// 清空session中的旧有用户信息
		session.remove("VALID_USER");

		user = userService.getUserBaseById(user.getId());
		user.setState(2);
		user.setLastLoginTime(new Date());
		userService.saveOrUpdate(user);
		session.put("VALID_USER", user);
		ServletActionContext.getRequest().setAttribute("userNickName", user.getNickName());
		return Action.SUCCESS;
	}

	/**
	 * 查询注册用户，管理后台用
	 */
	@SuppressWarnings("unchecked")
	public String listByCondition() {
		if (query == null) {
			query = new HashMap<String, String>();
		}
		users = userQueryService.getNewsByCondition(query, new Integer(
				currentPage).intValue(), new Integer(countPerPage).intValue(),
				orderBy, isAsc, this.queryString);

		return Action.SUCCESS;
	}

	/**
	 * 登录校验
	 */
	public void validateLogin() {
        log.info(String.format("UserAction's validateLogin method is running......"));
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		String c = (String) session
				.get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		if (StringUtils.isEmpty(c) || StringUtils.isEmpty(validateCode)
				|| !StringUtils.equals(validateCode, c)) {
			this.addFieldError("validateCode", "验证码不正确");
			return;
		}

		if (user == null) {
			this.addFieldError("user", "用户不能为空");
			return;
		}

		if (StringUtils.isEmpty(user.getNickName())) {
			this.addFieldError("user.nickName", "邮箱不能为空");
			return;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			this.addFieldError("user.password", "密码不能为空");
			return;
		}

		UserBase tmpUser = null;

		if ((tmpUser = userService.getUserBaseByNickName(user.getNickName())) == null) {
			this.addFieldError("user.nickName", "该用户名不存在");
			return;
		}

		if (!tmpUser.getPassword().equals(
				DigestUtils.md5Hex(user.getPassword()))) {
			this.addFieldError("user.password", "密码不正确");
			return;
		}
	}

	/**
	 * 目前采用ajax的登录方式，使用ajaxLogin
     *
	 *
	 * @return
	 */
	@Deprecated
	public String login() {
        log.info(String.format("UserAction's login method is running......"));
		HttpServletResponse response = ServletActionContext.getResponse();
		user = userService.getUserBaseByNickName(user.getNickName());
		user.setLastLoginTime(new Date());

		userService.saveOrUpdate(user);

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("VALID_USER");
		session.put("VALID_USER", user);

		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");

		if (StringUtils.isEmpty(loginPreUrl)) {
			loginPreUrl = "/index.jsp";
		}
		CookieUtils.setCookie(response, "loginMemberUsername",
                user.getNickName());

		return Action.SUCCESS;
	}

	/**
	 * 登录，目前使用的是ajax的登录方式
	 *
	 * @return
	 */
	public String ajaxLogin() {
        log.info(String.format("UserAction's ajaxLogin method is running......"));
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("VALID_USER");

		HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = (HttpServletRequest) context
                .get(ServletActionContext.HTTP_REQUEST);

		String c = (String) session
				.get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		if (StringUtils.isEmpty(c) || StringUtils.isEmpty(validateCode)
				|| !StringUtils.equals(validateCode, c)) {
			print(response, "error:1");
			;
			return Action.NONE;
		}

        if (user == null) {
			print(response, "error:2");
			;
			return Action.NONE;
		}

		if (StringUtils.isEmpty(user.getNickName())) {
			print(response, "error:3");
			;
			return Action.NONE;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			print(response, "error:4");
			;
			return Action.NONE;
		}

		UserBase tmpUser = null;

		if ((tmpUser = userService.getUserBaseByNickName(user.getNickName())) == null) {
			print(response, "error:5");
			;
			return Action.NONE;
		}

		if (!tmpUser.getPassword().equalsIgnoreCase(
				DigestUtils.md5Hex(user.getPassword()))) {
			print(response, "error:6");
			;
			return Action.NONE;
		}

		//未注册
		if (tmpUser.getState() < 2) {
			print(response, "error:7");
			return Action.NONE;
		}

		tmpUser.setLastLoginTime(new Date());
        HttpSession session1 = request.getSession();
        String sessionid = session1.getId();
        tmpUser.setSessionId(sessionid);
        if(tmpUser.getLoginCount()==null){
            tmpUser.setLoginCount(1);
        }else{
        tmpUser.setLoginCount(tmpUser.getLoginCount()+1);//用户每成功登陆一次，次数递增
        }
        tmpUser.setLastLoginIp(request.getRemoteAddr());
		userService.saveOrUpdate(tmpUser);
		session.remove("VALID_USER");

		session.put("VALID_USER", tmpUser);

		System.out.println("VALID_USER:" + tmpUser);

		user = userService.getUserBaseByNickName(user.getNickName());


		loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");

		CookieUtils.setCookie(response, "loginMemberUsername",
                user.getNickName());
		if(user.getType()==1){
			print(response, "success1");
		}else if(user.getType() >1){
			print(response, "success2");
        }else{
            print(response, "success3");
		}


		if (user != null && StringUtils.isNotBlank(user.getNickName())) {
			List<PointsHistory> logonPoints = userService
					.getPointHistoryByType(user, Constant.EVERY_LOGON);
			boolean canModify = true;

			if (CollectionUtils.isNotEmpty(logonPoints)
					&& logonPoints.size() >= 5
					&& currentTime.getTime()
							- logonPoints.get(4).getChangeTime().getTime() < 1000 * 60 * 60 * 24) {
				canModify = false;
			}

			if (canModify) {
				PointsHistory ph = new PointsHistory();
				ph.setUser(user);
				ph.setFlag(1);
				ph.setType(Constant.EVERY_LOGON);
				ph.setPointsChange(1);
				userService.changePoints(ph);
			}

			// 连续5天登录的积分
			boolean[] continueLogonArr = new boolean[] { false, false, false,
					false };
			for (int i = 1; i < 5; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentTime);
				calendar.add(Calendar.DAY_OF_YEAR, -1 * i);
				for (PointsHistory ph : logonPoints) {
					if (ph.getChangeTime().after(calendar.getTime())) {
						calendar.add(Calendar.DAY_OF_YEAR, -1);
						if (ph.getChangeTime().before(calendar.getTime())) {
							continueLogonArr[i - 1] = true;
							break;
						}
					}
				}
			}

			boolean continueLogon = true;
			for (boolean temp : continueLogonArr) {
				continueLogon = continueLogon && temp;
			}

			if (continueLogon) {
				PointsHistory ph = new PointsHistory();
				ph.setUser(user);
				ph.setFlag(1);
				ph.setType(Constant.CONTINUE_LOGON);

				ph.setPointsChange(10);
				userService.changePoints(ph);
			}
		}

		return Action.NONE;
	}

    /**
	 * 退出登录
	 *
	 * @return
	 */
	public String logout() {

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("VALID_USER");


		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");

		if (StringUtils.isEmpty(loginPreUrl)) {
			loginPreUrl = "/index.jsp";
		}

		return Action.SUCCESS;
	}

	/**
	 * 找回密码校验。邮箱和手机至少选一
	 */
	public void validateFindPassword() {

		if (StringUtils.isEmpty(user.getEmail())
				&& StringUtils.isEmpty(user.getMobile())) {
			this.addFieldError("user.email", "该邮箱/手机没有注册");
			return;
		}

		if (StringUtils.isNotEmpty(user.getEmail())) {
			UserBase tmpUser = userService.getUserBaseByEmail(user.getEmail());
			if (tmpUser == null || tmpUser.getId() < 1) {
				this.addFieldError("user.email", "该邮箱没有注册");
				return;
			}
		}

		if (StringUtils.isNotEmpty(user.getMobile())) {
			UserBase tmpUser = userService
					.getUserBaseByMobile(user.getMobile());
			if (tmpUser == null || tmpUser.getId() < 1) {
				this.addFieldError("user.email", "该手机没有注册");
				return;
			}
		}
	}

	/**
	 * 找回密码，分为邮箱找回和短信找回
	 *
	 * @return
	 */
	public String findPassword() {

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("VALID_USER");

		String result = Action.INPUT;

		if (StringUtils.isNotEmpty(user.getEmail())) {
			String c = (String) session
					.get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

			if (StringUtils.isEmpty(c) || StringUtils.isEmpty(validateCode)
					|| !StringUtils.equals(validateCode, c)) {
				return Action.INPUT;
			}

			user = userService.getUserBaseByEmail(user.getEmail());

			if (user == null || user.getId() < 1) {
				result = "emailUnReg";
			}

			ValidateCode code = new ValidateCode();
			code.setValidateCode(RandomUtils.nextInt());
			code.setInvalidTime(new Date(System.currentTimeMillis() + 1000 * 60
					* 60 * 24 * 365));

			commonService.save(code);
			try {
				MimeMessage msg = mailSenderService1.createMimeMessage();
				// 创建MimeMessageHelper对象，处理MimeMessage的辅助类

				MimeMessageHelper helper = new MimeMessageHelper(msg, true,
						"UTF-8");
				helper.setTo(user.getEmail());
                helper.setFrom("admin@ziguiw.com");
				helper.setSubject("子贵网的密码重置邮件");
				helper.setText(
						"<html><head></head><body>您好，您刚才使用了找回子贵网的找回密码功能，您在子贵网注册的用户名是："
								+ user.getNickName()
								+ "<br><a href='"
								+ ConfigUtils
										.getStringByKey("active_register",
                                                "http://www.ziguiw.com/user/modifyPasswd.jsp")
								+ "?user.id=" + user.getId() + "&activeCode"
								+ code.getValidateCode()
								+ "'>点击此处激活</a></body></html>", true);

				mailSenderService1.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

			result = "email_find_passwd_success";

		} else if (StringUtils.isNotEmpty(user.getMobile())) {
			user = userService.getUserBaseByMobile(user.getMobile());
			if (user == null || user.getId() < 1) {
				result = "mobileUnReg";
			}
			// 发送短信
			if (session.get(Constant.MOBILE_VALIDATE_CODE).equals(
					mobileValidateCode)
					&& StringUtils.isNotEmpty(mobileValidateCode)) {
				result = Action.SUCCESS;
			} else {
				result = Action.INPUT;
			}
		}

		return result;
	}

	/**
	 * 将登录前的url存入cookie
	 *
	 * @return
	 */
	public String setLoginPreUrl() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);

		String url = request.getRequestURI();
		if (url.startsWith(request.getContextPath() + "/")) {
			url = url.replace(request.getContextPath(), "");
		}
		CookieUtils.setCookie(response, "login_pre_url", url);

		return Action.SUCCESS;
	}

	/**
	 * 获取用户的登录信息，每个页面都需要调用，需要保持较高的效率
	 *
	 * @return
	 */
	public String ajaxGetLogonInfo() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		user = (UserBase) session.get("VALID_USER");
        if (user != null) {
            if (user.getType() == 3) {
                ParentServiceImpl parentService1 = (ParentServiceImpl) BeanFactoryUtils.getBean("parentService");
                List<Student> students = parentService1.findChildren(user.getNickName());
                if (students != null && students.size() > 0) {
                    user.setNickName(students.get(0).getXs_xming() + "家长");
                }
            }
            user.setRole(null);
            user.setCcczgInfoSousown(null);
            user.setKnowledgeRecommendRegion(null);
            user.setOrders(null);
            user.setCcczInfoSouces(null);
        }
		return Action.SUCCESS;
	}

	/**
	 * 查询用户
	 *
	 * @return
	 */
	@JSON(serialize = false)
	public String getUserPortal() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		user = (UserBase) session.get("VALID_USER");

		this.setMoodCount(userMoodService.userMoodCount(user.getId()));
		this.setAlbumCount(userAlbumService.userAlbumCount(user.getId()));
		return Action.SUCCESS;
	}

	/**
	 * 删除用户
	 *
	 * @return
	 */
	public String fakeDelete() {
		userService.fakeDelete(opIds);
		return Action.SUCCESS;
	}

	/**
	 * 恢复已经删除的用户
	 *
	 * @return
	 */
	public String restore() {
		userService.restore(opIds);
		return Action.SUCCESS;
	}

	/**
	 * 注意，这个Action不能使用过滤器，否则会获取不到参数，怀疑和表单类型有关
	 *
	 * @return
	 */
	public String updatePortrait() {
        log.info(String.format("UserAction's updatePortrait method is running..."));

		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		user = (UserBase) session.get("VALID_USER");

		if (user == null || user.getId() < 1) {
			return "USER_NO_LOGIN";
		}
		String rootDir = ConfigUtils.getProperty("static.savedPath");
		String   webPath = FileUtil.copyAndCompressImage(uploadFile, uploadFileFileName, rootDir, "images", null);

		/*// 保存文件拓展名
		String extName = "";
		// 保存新的文件名
		String newFileName = "";
		// 保存当前时间
		String nowTimeStr = "";

		// 获取项目根路径
		String savePath = ServletActionContext.getServletContext().getRealPath("/");

		// 分目录存放用户图片
		savePath = savePath + "/upload/" + user.getId() + "/";
		// 判断目录是否存在
		File saveFilePath = new File(savePath);
		if (!saveFilePath.exists()) {
			// 不存在则创建目录
			saveFilePath.mkdir();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		// 获取随机数
		int rannum = (int) (new Random().nextDouble() * (99999 - 10000 + 1)) + 10000;
		// 当前时间
		nowTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// 获取拓展名
		if (uploadFileFileName.lastIndexOf(".") >= 0) {
			extName = uploadFileFileName.substring(uploadFileFileName
					.lastIndexOf("."));
		}

		String fileName = nowTimeStr + extName;

		String webPath = context.getContextPath() + "/upload/" + user.getId()
				+ "/" + fileName;

		try {
			// 检查上传的是否是图片
			if (ImageUpload.checkIsImage(extName)) {
				FileUtils.copyFile(uploadFile, new File(savePath + fileName));
				ImageTools.compressImg(savePath + fileName, savePath
                        + nowTimeStr + "_50_50" + extName, 50, 50);
				ImageTools.compressImg(savePath + fileName, savePath
                        + nowTimeStr + "_70_70" + extName, 70, 70);
				ImageTools.compressImg(savePath + fileName, savePath
                        + nowTimeStr + "_120_120" + extName, 120, 120);
				ServletActionContext.getRequest().setAttribute(IMG_PATH,
						webPath);
			} else {
				this.addFieldError("form", "");
			}

		} catch (Throwable e) {
			this.addFieldError("form", "");
		}*/

		user.setPortrait(webPath);
		userService.saveOrUpdate(user);
		return Action.SUCCESS;
	}

	public String updateSetting() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		tmpUser.setSex(user.getSex());
		tmpUser.setProvince(user.getProvince());
		tmpUser.setSignature(user.getSignature());

		userService.saveOrUpdate(tmpUser);

		return Action.SUCCESS;
	}

	/**
	 * 用户审批，这里的审批内容主要是用户的角色信息
	 *
	 * @return
	 */
	public String auditPass() {

		user = userService.getUserBaseById(user.getId());

		user.setState(Constant.USER_AUDIT_PASS_STATE);

		userService.saveOrUpdate(user);

		if (user.getType() == Constant.SCHOOL_TYPE) {
			return "school_audit_success";
		} else if (user.getType() == Constant.TEACHER_TYPE) {
			return "teacher_audit_success";
		} else if (user.getType() == Constant.PARENT_TYPE) {
			return "parent_audit_success";
		} else if (user.getType() == Constant.STUDENT_TYPE) {
			return "student_audit_success";
		} else {
			return Action.INPUT;
		}
	}

	public String updatePassword() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		boolean hasOldPasswd = true;

        if (tmpUser == null || tmpUser.getId() < 1) {
            hasOldPasswd = false;
            tmpUser = userService.getUserBaseById(user.getId());

            session.put("VALID_USER", tmpUser);
        }



        if (tmpUser == null || tmpUser.getId() < 1) {
			hasOldPasswd = false;
			tmpUser = userService.getUserBaseById(user.getId());

			session.put("VALID_USER", tmpUser);
		}

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		if (hasOldPasswd) {
			tmpUser = userService.getUserBaseById(tmpUser.getId());

			if (!tmpUser.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
				this.addFieldError("oldPassword", "旧密码不正确");
				return Action.INPUT;
			}
		}

		if (!StringUtils.equals(user.getPassword(), secondPassword)) {
			this.addFieldError("secondPassword", "两次密码输入不一致");
			return Action.INPUT;
		}

		tmpUser.setPassword(DigestUtils.md5Hex(secondPassword));

		userService.saveOrUpdate(tmpUser);

		return Action.SUCCESS;
	}

	public String recommend() {
		// for(long id : opIds){
		// UserBase tmpUser = userService.getUserBaseById(id);
		//
		// KnowledgeRecommendRegion region = new KnowledgeRecommendRegion();
		// region.setId(Constant.USER_RECOMMEND_REGION_ID);
		// tmpUser.setKnowledgeRecommendRegion(region);
		//
		// userService.saveOrUpdate(tmpUser);
		// }
		userService.recommend(opIds, recommendRegionId);
		return Action.SUCCESS;
	}

	public String ajaxSelectRole() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		tmpUser = userService.getUserBaseById(tmpUser.getId());
		tmpUser.setType(user.getType());

		userService.saveOrUpdate(tmpUser);

		HttpServletResponse response = ServletActionContext.getResponse();
		print(response, "success");

		return null;
	}

	protected void print(HttpServletResponse response, String info) {
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@JSON(serialize = false)
	public String getHotHome() {
		users = userService.getHotHome();

		return Action.SUCCESS;
	}

	public boolean isEmail(String email) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		return m.find();
	}

	public boolean isAccountId(String accountId) {
		String regex = "^\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(accountId);
		return m.find();
	}

	/**
	 * 完善学校资料
	 *
	 * @return
	 */
	public String fillSchoolInfo() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		long schoolId = schoolService.saveOrUpdate(school);

		UserBase user = userService.getUserBaseById(tmpUser.getId());

		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (user.getType() > 0) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.FUFILLMENT_INFO);

			ph.setPointsChange(Constant.FUFILLMENT_INFO_POINT);
			userService.changePoints(ph);
		}

		user.setType(1);
		user.setForeignKey(schoolId);
		user.setState(2);

		userService.saveOrUpdate(user);

		return Action.SUCCESS;
	}

	public String ajaxSaveSchoolSummary() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		SchoolInfo tmpSchoolInfo = schoolService.getInfoBySchoolId(schoolInfo
				.getSchoolId());

		if (tmpSchoolInfo == null) {
			tmpSchoolInfo = new SchoolInfo();
			tmpSchoolInfo.setSchoolId(schoolInfo.getSchoolId());
		}
		try {
			tmpSchoolInfo.setSummary(URLDecoder.decode(schoolInfo.getSummary(),
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		schoolService.saveOrUpdateInfo(tmpSchoolInfo);

		return Action.SUCCESS;
	}

	public String saveSchoolSummary() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		SchoolInfo tmpSchoolInfo = schoolService.getInfoBySchoolId(tmpUser
				.getId());

		if (tmpSchoolInfo == null) {
			tmpSchoolInfo = new SchoolInfo();
			tmpSchoolInfo.setSchoolId(tmpUser.getId());
		}
		tmpSchoolInfo.setSummary(schoolInfo.getSummary());

		schoolService.saveOrUpdateInfo(tmpSchoolInfo);

		return Action.SUCCESS;
	}

	public String saveSchoolSpeech() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		SchoolInfo tmpSchoolInfo = schoolService.getInfoBySchoolId(tmpUser
				.getId());

		if (tmpSchoolInfo == null) {
			tmpSchoolInfo = new SchoolInfo();
			tmpSchoolInfo.setSchoolId(tmpUser.getId());
		}
		tmpSchoolInfo.setSpeech(schoolInfo.getSpeech());

		schoolService.saveOrUpdateInfo(tmpSchoolInfo);

		return Action.SUCCESS;
	}

	public String saveSchoolJob() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		SchoolInfo tmpSchoolInfo = schoolService.getInfoBySchoolId(tmpUser
				.getId());

		if (tmpSchoolInfo == null) {
			tmpSchoolInfo = new SchoolInfo();
			tmpSchoolInfo.setSchoolId(tmpUser.getId());
		}
		tmpSchoolInfo.setJob(schoolInfo.getJob());

		schoolService.saveOrUpdateInfo(tmpSchoolInfo);

		return Action.SUCCESS;
	}

	@JSON(serialize = false)
	public String getSchoolInfoById() {
		if (StringUtils.isEmpty(hostUserName)) {
			ActionContext ctx = ActionContext.getContext();
			Map session = ctx.getSession();
			UserBase user = (UserBase) session.get("VALID_USER");

			if (user == null || user.getId() < 1) {
				return Constant.USER_NO_LOGIN;
			}

			hostUserName = user.getNickName();
		}

		UserBase hostUser = userService.getUserBaseByNickName(hostUserName);

		schoolInfo = schoolService.getInfoBySchoolId(hostUser.getId());

		return Action.SUCCESS;
	}

	public String updateSchoolInfo() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		UserBase tmpUser = (UserBase) session.get("VALID_USER");

		if (tmpUser == null || tmpUser.getId() < 1) {
			return "USER_NO_LOGIN";
		}

		SchoolInfo tmpSchoolInfo = schoolService.getInfoBySchoolId(tmpUser
				.getId());

		if (tmpSchoolInfo == null) {
			tmpSchoolInfo = new SchoolInfo();
			tmpSchoolInfo.setSchoolId(tmpUser.getId());
		}
		tmpSchoolInfo.setContact(schoolInfo.getContact());
		tmpSchoolInfo.setMasterContact(schoolInfo.getMasterContact());

		schoolService.saveOrUpdateInfo(tmpSchoolInfo);

		return Action.SUCCESS;
	}

	/**
	 * 完善资料。老师
	 *
	 * @return
	 */
	public String fillmentTeacher() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		UserBase user = (UserBase) session.get("VALID_USER");

		if (user == null || user.getId() < 0) {
			return "USER_NO_LOGIN";
		}

		long Xx_Id = teacher.getSchool().getXx_ID();
		school = commonService.get(School.class, Xx_Id);

		logger.error("school:" + school);

		teacher.setSchool(school);

		commonService.saveOrUpdate(teacher);

		user = userService.getUserBaseById(user.getId());

		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (user.getType() > 0) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.FUFILLMENT_INFO);

			ph.setPointsChange(Constant.FUFILLMENT_INFO_POINT);
			userService.changePoints(ph);
		}

		user.setType(2);
		user.setForeignKey(teacher.getTeacherID());

		userService.saveOrUpdate(user);

		return Action.SUCCESS;
	}

	/**
	 * 完善资料。家长
	 *
	 * @return
	 */
	public String fillmentParent() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		UserBase user = (UserBase) session.get("VALID_USER");

		if (user == null || user.getId() < 0) {
			return "USER_NO_LOGIN";
		}

		int Xs_Id = parent.getStudent().getXs_id();
		student = commonService.get(Student.class, Xs_Id);
		parent.setStudent(student);
		commonService.saveOrUpdate(parent);

		user = userService.getUserBaseById(user.getId());

		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (user.getType() > 0) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.FUFILLMENT_INFO);

			ph.setPointsChange(Constant.FUFILLMENT_INFO_POINT);
			userService.changePoints(ph);
		}

		user.setType(3);
		user.setForeignKey((long) parent.getPARENTS_ID());

		userService.saveOrUpdate(user);

		return Action.SUCCESS;
	}

	/**
	 * 完善资料。学生
	 *
	 * @return
	 */
	public String fillmentStudent() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		UserBase user = (UserBase) session.get("VALID_USER");

		if (user == null || user.getId() < 0) {
			return "USER_NO_LOGIN";
		}

		long Xx_Id = student.getSchool().getXx_ID();
		school = commonService.get(School.class, Xx_Id);

		student.setSchool(school);

		commonService.saveOrUpdate(student);

		user = userService.getUserBaseById(user.getId());

		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (user.getType() > 0) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.FUFILLMENT_INFO);

			ph.setPointsChange(Constant.FUFILLMENT_INFO_POINT);
			userService.changePoints(ph);
		}

		user.setType(4);
		user.setForeignKey((long) student.getXs_id());

		userService.saveOrUpdate(user);

		return Action.SUCCESS;
	}


	/**
	 * 完善资料。学校
	 *
	 * @return
	 */
	public String fillmentSchool() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		UserBase user = (UserBase) session.get("VALID_USER");

		if (user == null || user.getId() < 0) {
			return "USER_NO_LOGIN";
		}

		commonService.saveOrUpdate(school);

		user = userService.getUserBaseById(user.getId());

		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (user.getType() > 0) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.FUFILLMENT_INFO);

			ph.setPointsChange(Constant.FUFILLMENT_INFO_POINT);
			userService.changePoints(ph);
		}

		user.setType(1);
		user.setForeignKey(school.getXx_ID());

		userService.saveOrUpdate(user);

		return Action.SUCCESS;
	}

	private static Log log = LogFactory.getLog("BUSINESS_DATA");

	/**
	 * 查询学籍档案
	 *
	 * @return
	 */
	@JSON(serialize = false)
	public String getStudentDocument() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		UserBase ub = (UserBase) session.get("VALID_USER");
		String sid = "" + ub.getId();

		/**
		 * 家长查询自己孩子的文档
		 */
		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.PARENT_TYPE
				&& ub.getForeignKey() != 0L) {
			Parent parent = commonService.get(Parent.class, (int) ub
					.getForeignKey().longValue());
			student = commonService.get(Student.class, parent.getStudent()
					.getXs_id());

			user = CommonUtils.getUserByXsID(student.getXs_id());

			if (log.isDebugEnabled()) {
				log.debug("user student document:" + user);
			}

			users = new Page<UserBase>();
			List<UserBase> tmp1 = new ArrayList<UserBase>(1);
			tmp1.add(user);
			users.setList(tmp1);

			if (log.isDebugEnabled()) {
				log.debug("users student document:" + users);
			}

			sid = "" + user.getId();

		}

		/**
		 * 学生查询自己的文档
		 */
		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.STUDENT_TYPE
				&& ub.getForeignKey() != 0L) {
			Student student = commonService.get(Student.class, (int) ub
					.getForeignKey().longValue());
			user = CommonUtils.getUserByXsID(student.getXs_id());

			users = new Page<UserBase>();
			List<UserBase> tmp1 = new ArrayList<UserBase>(1);
			tmp1.add(user);
			users.setList(tmp1);

			sid = "" + user.getId();
		}

		/**
		 * 教师查询自己班级学生的文档
		 */
		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.TEACHER_TYPE
				&& ub.getForeignKey() != 0L) {
			Teacher teacher = commonService.get(Teacher.class, ub
					.getForeignKey().longValue());
			List<Student> students = CommonUtils.getStudentByTeacher(ub);

			Page<UserBase> temp = commonService.getTeacherStudents(new Integer(
					currentPage).intValue(), new Integer(countPerPage)
					.intValue(), students);

			users = temp;
		}

		return Action.SUCCESS;
	}

	/**
	 * 获取积分历史
	 *
	 * @return
	 */
	@JSON(serialize = false)
	public String getPointHistory() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		UserBase ub = (UserBase) session.get("VALID_USER");

		if (ub == null) {
			return Constant.USER_NO_LOGIN;
		}

		pointHistory = userService.getPointHistory(ub, pageNo, pageSize,
				queryString);

		return Action.SUCCESS;
	}

	/**
	 * 增加或者修改班级，学校用户
	 *
	 * @return
	 */
	public String saveOrUpdateClasz() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		UserBase ub = (UserBase) session.get("VALID_USER");

		if (ub == null) {
			return Constant.USER_NO_LOGIN;
		}

		school = schoolService.getById(ub.getForeignKey());

		clasz.setXxID(school.getXxID());

		commonService.saveOrUpdate(clasz);

		return Action.SUCCESS;

	}

	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	public Page<UserBase> getUsers() {
		return users;
	}

	public void setUsers(Page<UserBase> users) {
		this.users = users;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(Boolean isAsc) {
		this.isAsc = isAsc;
	}

	@JSON(serialize = false)
	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@JSON(serialize = false)
	public UserQueryServiceImpl getUserQueryService() {
		return userQueryService;
	}

	public void setUserQueryService(UserQueryServiceImpl userQueryService) {
		this.userQueryService = userQueryService;
	}

	public String getLoginPreUrl() {
		return loginPreUrl;
	}

	public void setLoginPreUrl(String loginPreUrl) {
		this.loginPreUrl = loginPreUrl;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public void setMoodCount(int moodCount) {
		this.moodCount = moodCount;
	}

	public int getMoodCount() {
		return moodCount;
	}

	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}

	public int getAlbumCount() {
		return albumCount;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public long getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(long recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Date getloginPreUrl() {
		return new Date();
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public void setPointHistory(Page<PointsHistory> pointHistory) {
		this.pointHistory = pointHistory;
	}

	public Clasz getClasz() {
		return clasz;
	}

	public void setClasz(Clasz clasz) {
		this.clasz = clasz;
	}

	public String getMobileValidateCode() {
		return mobileValidateCode;
	}

	public void setMobileValidateCode(String mobileValidateCode) {
		this.mobileValidateCode = mobileValidateCode;
	}

}

