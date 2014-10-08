package com.zigui.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.CcczgInfoSousown;
import com.zigui.domain.CourseXml;
import com.zigui.domain.Page.OrderType;
import com.zigui.domain.PointsHistory;
import com.zigui.domain.SouceStugrade;
import com.zigui.domain.SouceSubject;
import com.zigui.domain.SouceVersion;
import com.zigui.domain.SourceType;
import com.zigui.domain.UserBase;
import com.zigui.service.GradeService;
import com.zigui.service.SouceSubjectService;
import com.zigui.service.SourceService;
import com.zigui.service.SousownService;
import com.zigui.service.TypeService;
import com.zigui.service.VersionService;
import com.zigui.service.impl.PointHistoryService;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.ConfigUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.ConvertToSWF;
import com.zigui.utils.FileOperate;
import com.zigui.utils.HtmlUtil;
import com.zigui.utils.JsonUtil;
import com.zigui.utils.URLAvailability;
import com.zigui.webservice.SonNobleness;
import com.zigui.webservice.SonNoblenessLocator;

@Controller
@Scope("prototype")
public class SourceAction extends PageSourceAction {
	private static final long serialVersionUID = 3583455498417422010L;
	public final static int SOURCENUM = 20;
	private static final Log log = LogFactory.getLog(SourceAction.class);

	@Autowired
	private SourceService sourceService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private VersionService versionService;
	@Autowired
	private SouceSubjectService souceSubjectService;
	@Autowired
	private SousownService sousownService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private PointHistoryService pointHistoryService;

	private SonNobleness sn = new SonNoblenessLocator();

	private String tid;

	// 获取外部资源的两个参数
	private Date date;
	private String ftpFile;

	private String type; // 类型
	private String title; // 资源标题

	private String sourcetype;// 类型

	private String sourcegrade;// 年级

	private String sourceversion;// 版本

	private String sourcesubject;// 科目

	private String resourceNotice;// 说明

	private String memberid;// 会员

	private long needpoint;// 资源积分

	private String keys;// 关键字

	// 资源
	private File sourcefile;

	private String sourcefileContentType;

	private String sourcefileFileName;

	private Long resourceNum;// 下载次数

	private long orderList;// 排序

	private CcczgInfoSouce source;// 教学资源对象

	private List<SouceStugrade> gradeList;

	private List<SouceSubject> subjectList;

	private List<SouceVersion> versionList;

	private List<SourceType> typeList;

	private List<CcczgInfoSouce> sourceList;

	private String selstr;// 查询关键字

	private int seltj; // 查询条件

	private String filetype;// 文件后缀名

	private List<CcczgInfoSouce> zuixinList;// 最新推荐

	private List<CcczgInfoSouce> remenList;// 本月热门

	private final Integer LEN = 9;// 排行显示条数
	
	private final static String RESOURCE_SAVED_DIR = "static.savedPath";

	/**
	 * 从智学宝定期获取数据
	 * 
	 * */
	public String getExternalResources() throws Exception {
		if (getCurrLoginUser() == null) {
			return "fail";
		} else {
			String jsonString = sn.getBasicHttpBinding_ISonNobleness()
					.getAllCourseInfo();
			List<CcczgInfoSouce> list = JsonUtil.json2List(jsonString);
			System.out.println(list.size());

			for (CcczgInfoSouce cis : list) {
				sourceService.save(cis);
			}
			return "success";
		}

	}

	/**
	 * 新增前查询
	 * 
	 * @return
	 */

	public String addget() {
		// //加载cookie用户id
		// getSessionMemberId();
		// //加载cookie用户名
		// getSessionMemberUsername();
		// //加载cookie用户昵称
		// getSessionNikename();
		try {
			if (getSession().get("VALID_USER") == null) {
				return "fail";
			} else {
				typeList = typeService
						.getList(" from SourceType where state=" + 1);
				gradeList = gradeService
						.getList(" from SouceStugrade where njZtai=" + 51);
				subjectList = souceSubjectService
						.getList(" from SouceSubject where isCommonUse=" + 1);
				versionList = versionService
						.getList(" from SouceVersion where state=" + 1);

				setAttribute("articleList", gradeList);
				setAttribute("subjectList", subjectList);
				setAttribute("versionList", versionList);
				setAttribute("typeList", typeList);

				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";

		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */

	public String add() throws Exception {
		try {
			if (source == null) {
				source = new CcczgInfoSouce();
			}
			String[] net = getParameterValues("net");
			if (net != null && net.length > 0) {
				String netPath = StringUtils
						.trimToNull(getParameter("netPath"));
				if (netPath.length() > 7
						&& !"http://".equals(netPath.substring(0, 7))) {
					netPath = "http://" + netPath;
				}
				if (!URLAvailability.isConnect(netPath)) {
					this.addActionError("这是个错误的链接");
					addget();
					return INPUT;
				}
				source.setNetPath(netPath);
			} else {
				String filepath = null;
				String filename = null;

				// 上传视频\资源
				if (null != sourcefile) {

					filepath = ConfigUtils.getProperty(RESOURCE_SAVED_DIR);
					filename = FileOperate.generateFileName(sourcefileFileName);

					// 上传资源,返回名称
					this.source.setResourcePath(FileOperate.fileUpload(
							sourcefile, null, null, filepath, filename));

					try {
						if (source.getSourcefileType() == null
								|| "".equals(source.getSourcefileType())) {
							String sourcefileType = filename.substring(filename
									.lastIndexOf(".") + 1);
							source.setSourcefileType(sourcefileType);
						}

						if (!source.getSourcefileType().equals("FLV")
								&& !source.getSourcefileType().equals("RAR")
								&& !source.getSourcefileType().equals("EXE")
								&& !source.getSourcefileType().equals("CHM")) {
							// 生成swf

							// 得到上传资源的绝对路径
							String realfilename = filepath.substring(0,
									filepath.lastIndexOf("/") + 1)
									+ source.getResourcePath();
							// 去掉后缀
							realfilename = realfilename.substring(0,
									realfilename.lastIndexOf("."));

							String pdfpath = realfilename + ".pdf";
							String swfpath = realfilename + ".swf";
							ConvertToSWF conswf = new ConvertToSWF();

							boolean issuc = conswf.convertFile(
									source.getResourcePath(), pdfpath, swfpath);

							if (!issuc) {
								this.addActionMessage("文件转换失败，请重新上传文件");
							}
							swfpath = source.getResourcePath().substring(0,source.getResourcePath().lastIndexOf("."))  + ".swf";
							
							source.setResourceSwfpath(swfpath);
						}
					} catch (Exception e) {
						e.printStackTrace();
						this.addActionMessage("文件转换出现错误，资源上传失败！");
						addget();
						return ERROR;
					}

				}
			}

			source.setTitle(HtmlUtil.splitAndFilterString(title, -1));
			source.setSubjectId(sourcesubject);

			// 与sourceType 建立双向关联关系
			SourceType sourceType = new SourceType();
			sourceType.setTypeId(sourcetype);
			sourceType.getCcczgInfoSouces().add(source);
			source.setSourceType(sourceType);

			// 与sourceType 建立双向关联关系
			SouceVersion souceVersion = new SouceVersion();
			souceVersion.setVersionId(sourceversion);
			souceVersion.getCcczgInfoSouces().add(source);
			source.setSouceVersion(souceVersion);

			// 与subject 建立双向关联关系
			SouceSubject subject = new SouceSubject();
			subject.setSubjectId(sourcesubject);
			subject.getCcczgInfoSouces().add(source);
			source.setSouceSubject(subject);

			// 与stugrade 建立双向关联关系
			SouceStugrade stugrade = new SouceStugrade();
			stugrade.setNjId(sourcegrade);
			stugrade.getCcczgInfoSouces().add(source);
			source.setSouceStugrade(stugrade);

			UserBase user = (UserBase) getSession().get("VALID_USER");
//			user = userService.getUserBaseById(user.getId());
//			user.getCcczInfoSouces().add(source);
			source.setMember(user);
			source.setResourceNotice(HtmlUtil.splitAndFilterString(
					resourceNotice, -1));

			source.setCreateDate(new Date());
			source.setNeedpoint(needpoint);
			source.setKeys(HtmlUtil.splitAndFilterString(keys, -1));

			source.setResourceDownnum(Long.parseLong("0"));
			sourceService.save(source);// 上传
			this.addActionMessage("资源发布成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("文件转换出现错误，资源上传失败！");
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String list() {
		try {
			UserBase user = (UserBase) this.getSession().get("VALID_USER");
			log.info(String.format("query my upload source!"));
			page.setEveryPage(SOURCENUM);
			// 根据statePage进行Page对象设置，并查询
			if (gotoPage == null || gotoPage.length() == 0) {
				gotoPage = "1";
			}
			page.setCurrentPage(Integer.parseInt(gotoPage));
			page.setOrderBy("createDate");
			page.setOrderType(OrderType.desc);
			// String sql = " from CcczgInfoSouce s where s.member.memberid='"
			// + this.getSessionMemberId()
			// + "' order by s.createDate desc";

			String sql = "from CcczgInfoSouce s where s.member.id="
					+ user.getId() + " order by s.createDate desc";
			result = sourceService.findByPager(page, sql);
			this.sourceList = result.getContent();
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("sourceList", result.getContent());
		} catch (Exception e) {
			String errorMsg = "分页查询失败！";
			log.error(errorMsg, e);
			this.addActionMessage(errorMsg + e.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 获得资源 列表分页
	 * 
	 * @return
	 */
	public String list_qt() {
		try {
			// page分页信息
			// 设置每页显示的条数

			page.setEveryPage(SOURCENUM);

			// 根据statePage进行Page对象设置，并查询
			if (gotoPage == null || gotoPage.length() == 0) {
				gotoPage = "1";
			}
			page.setCurrentPage(Integer.parseInt(gotoPage));
			page.setOrderBy("createDate");
			page.setOrderType(OrderType.desc);
			String sql = " from CcczgInfoSouce s where checkSign=1 ";

			// selstr = new String(selstr.getBytes("iso-8859-1"),"UTF-8");
			if (seltj == 1) {
				sql = sql + " and s.souceVersion.VName like '%" + selstr + "%'";
			} else if (seltj == 2) {
				sql = sql + " and  s.souceStugrade.njMcheng like '%" + selstr
						+ "%'";
			} else if (seltj == 3) {
				sql = sql + " and s.souceSubject.subjectName like '%" + selstr
						+ "%'";
			} else if (seltj == 4) {
				sql = sql + " and s.sourceType.typeName like '%" + selstr
						+ "%'";
			} else if (seltj == 10) {
				sql = sql + " and s.title like '%" + selstr + "%'";
			}
			if ((filetype != null) & (!"".equals(filetype))) {
				sql = sql + " and sourcefileType='" + filetype + "'";
			}
			result = sourceService.findByPager(page, sql);
			this.sourceList = result.getContent();
			this.zuixinList = sourceService.getListByLeve(null, LEN);
			this.remenList = sourceService
					.getListByCreateDateOrderResourceDownnum(LEN);
			return LIST;
		} catch (Exception e) {
			this.addActionMessage("分页查询失败！" + e.toString());
			return ERROR;
		}
	}

	public String listMore() {
		try {
			page.setEveryPage(SOURCENUM);
			// 根据statePage进行Page对象设置，并查询
			if (gotoPage == null || gotoPage.length() == 0) {
				gotoPage = "1";
			}
			page.setCurrentPage(Integer.parseInt(gotoPage));
			page.setOrderBy("createDate");
			page.setOrderType(OrderType.desc);
			StringBuffer sql = new StringBuffer(
					"from CcczgInfoSouce t where delsign = 0 and checkSign = 1");
			if (type.equals("1")) {
				this.type = "1";

				sql.append(" and t.sourceType.typeName like '%视频%'");
			} else if (type.equals("2")) {

				this.type = "2";

				sql.append(" and t.sourceType.typeName like '%试卷%'");
			} else {
				this.type = "3";

				sql.append(" and t.sourceType.typeName like '%课件%'");
			}
			result = sourceService.findByPager(page, new String(sql));
			this.sourceList = result.getContent();
			this.zuixinList = sourceService.getListByLeve(null, LEN);
			this.remenList = sourceService
					.getListByCreateDateOrderResourceDownnum(LEN);
			return LIST;
		} catch (Exception e) {
			this.addActionMessage("分页查询失败！" + e.toString());
			return ERROR;
		}
	}

	/**
	 * 获取资源详情
	 * 
	 * @return
	 */
	public String get() throws Exception {

		// 判断是否是外部资源
		if (date != null && ftpFile != null && !"".equals(ftpFile)) {
			// 对外部资源的处理
			StringBuffer sb = new StringBuffer();
			sb.append("/Date(");
			sb.append(date.getTime());
			sb.append(")/");
			String xmlPath = sn.getBasicHttpBinding_ISonNobleness()
					.getCoursewareXmlAddress(ftpFile, sb.toString());
			System.out.println("---------------------------path=" + xmlPath);
			// 根据url获取数据
			List<CourseXml> list = sourceService.parseXml(xmlPath);
			ActionContext.getContext().put("list", list);
			return "getSuccess";
		}

		try {
			String id = StringUtils.trimToNull(getParameter("id"));
			if (id == null) {
				this.addActionMessage("资源id不存在！");
				return ERROR;
			}
			// 获得资源信息
			CcczgInfoSouce l_source = sourceService.get(id);
			setAttribute("source", l_source);
			this.zuixinList = sourceService.getListByLeve(null, LEN);
			this.remenList = sourceService
					.getListByCreateDateOrderResourceDownnum(LEN);
		} catch (Exception e) {
			this.addActionMessage("查询详细资源失败！");
			return ERROR;
		}
		return "detail";
	}

	/**
	 * 下载资源
	 * 
	 * @return
	 */
	public String downLoad() {
		try {
			String ssid = StringUtils.trimToNull(getParameter("id"));
			if (ssid == null) {
				this.addActionMessage("资源id不存在！");
				return ERROR;
			}

			UserBase user = getCurrLoginUser();
			if (user == null) {
				this.addActionMessage("您还没有登录，请先登录！");
				return "login";
			}

			// 获得资源信息
			CcczgInfoSouce l_resource = sourceService.get(ssid);

			l_resource.getMember().getPoints();
			l_resource.getNeedpoint();

			Long pintout = Long.valueOf(getCurrLoginUser().getPoints())
					- l_resource.getNeedpoint();
			if (pintout < 0) {
				this.addActionMessage("您的积分不够");
				return "nopoint";
			}

			if (null == l_resource.getResourcePath()) {
				FileOperate.downFile("none/", "none.file");
			} else {
				int mark = l_resource.getResourcePath().lastIndexOf("/") + 1;
				String filepath = l_resource.getResourcePath().substring(0,
						mark);
				String filename = l_resource.getResourcePath().substring(mark,
						l_resource.getResourcePath().length());
				String userfilename = l_resource.getTitle() + "."
						+ l_resource.getSourcefileType().toLowerCase();
				FileOperate.downFile(filepath, filename, userfilename);
				l_resource
						.setResourceDownnum(l_resource.getResourceDownnum() == null ? new Long(
								1) : l_resource.getResourceDownnum()
								+ Long.valueOf(1));
				l_resource.setModifyDate(new Date());
				sourceService.update(l_resource);

				CcczgInfoSousown sousown = new CcczgInfoSousown();

				UserBase downUser = userService.getUserBaseById(user.getId());

				sousown.setUser(downUser);
				downUser.getCcczgInfoSousown().add(sousown);
				downUser.setPoints(pintout.intValue());
				userService.saveOrUpdate(downUser);

				l_resource.getCcczgInfoSousown().add(sousown);
				sousown.setCcczgInfoSouce(l_resource);

				UserBase uploadUser = userService.getUserBaseById(l_resource
						.getMember().getId());
				uploadUser.setPoints(uploadUser.getPoints()
						+ l_resource.getNeedpoint().intValue());

				// 增加积分历史记录
				PointsHistory downPoint = new PointsHistory();
				downPoint.setUser(downUser);
				downPoint.setType(Constant.DOWN_RESOURCE);
				downPoint.setState(1);
				downPoint.setPointsChange(l_resource.getNeedpoint().intValue());
				downPoint.setFlag(-1);

				PointsHistory uploadPoint = new PointsHistory();
				uploadPoint.setUser(uploadUser);
				uploadPoint.setType(Constant.UPLOAD_RESOURCE);
				uploadPoint.setState(1);
				uploadPoint.setPointsChange(l_resource.getNeedpoint()
						.intValue());
				uploadPoint.setFlag(1);

				pointHistoryService.save(downPoint);
				pointHistoryService.save(uploadPoint);
				userService.saveOrUpdate(uploadUser);

				sousownService.save(sousown);

			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("下载失败！");
			return ERROR;
		}
	}

	public String goUpdate() {
		String id = null;
		if (tid != null) {
			id = tid;
		} else {
			id = StringUtils.trimToNull(getParameter("id"));
		}
		typeList = typeService.getList(" from SourceType where state=" + 1);
		gradeList = gradeService
				.getList(" from SouceStugrade where njZtai=" + 51);
		subjectList = souceSubjectService
				.getList(" from SouceSubject where isCommonUse=" + 1);
		versionList = versionService
				.getList(" from SouceVersion where state=" + 1);

		setAttribute("articleList", gradeList);
		setAttribute("subjectList", subjectList);
		setAttribute("versionList", versionList);
		setAttribute("typeList", typeList);
		if (id == null) {
			this.addActionMessage("资源id不存在！");
			return ERROR;
		}
		this.source = sourceService.get(id);
		return SUCCESS;

	}

	public String update() {
		try {
			if (tid == null) {
				this.addActionMessage("资源id不存在！");
				return ERROR;
			}
			CcczgInfoSouce l_resource = sourceService.get(tid);
			String filepath = null;
			String filename = null;
			String[] net = getParameterValues("net");
			if (net != null && net.length > 0) {
				String netPath = StringUtils
						.trimToNull(getParameter("netPath"));
				if (netPath.length() > 7
						&& !"http://".equals(netPath.substring(0, 7))) {
					netPath = "http://" + netPath;
				}
				if (!URLAvailability.isConnect(netPath)) {
					this.addActionError("这是个错误的链接");
					list();
					return INPUT;
				}
				l_resource.setNetPath(netPath);
			} else {
				// 上传视频
				if (null != sourcefile) {
					// 处理原有资源
					if (l_resource.getResourcePath() != null
							&& l_resource.getResourcePath().length() > 0) {
						String resourcePath = ConfigUtils
								.getProperty(RESOURCE_SAVED_DIR);
						resourcePath = resourcePath.substring(0,
								resourcePath.lastIndexOf("/") + 1)
								+ l_resource.getResourcePath();
						FileOperate.deleteFile(resourcePath);
						// 处理pdf
						int begin = 0, end = 0;
						end = resourcePath.lastIndexOf(".");
						FileOperate.deleteFile(resourcePath.substring(begin,
								end) + ".pdf");

						// 处理swf
						FileOperate.deleteFile(resourcePath.substring(begin,
								end) + ".swf");

					}
					filepath = ConfigUtils.getProperty(RESOURCE_SAVED_DIR);
					filename = FileOperate.generateFileName(sourcefileFileName);

					// 上传资源,返回名称
					l_resource.setResourcePath(FileOperate.fileUpload(
							sourcefile, null, null, filepath, filename));
					try {
						if (!source.getSourcefileType().equals("FLV")
								&& !source.getSourcefileType().equals("RAR")
								&& !source.getSourcefileType().equals("EXE")
								&& !source.getSourcefileType().equals("CHM")) {

							// 得到上传资源的绝对路径
							String realfilename = filepath.substring(0,
									filepath.lastIndexOf("/") + 1)
									+ l_resource.getResourcePath();
							// 去掉后缀
							realfilename = realfilename.substring(0,
									realfilename.lastIndexOf("."));

							String pdfpath = realfilename + ".pdf";
							String swfpath = realfilename + ".swf";
							ConvertToSWF conswf = new ConvertToSWF();
							boolean issuc = conswf.convertFile(
									l_resource.getResourcePath(), pdfpath,
									swfpath);
							if (!issuc) {
								this.addActionMessage("文件转换失败，请重新上传文件");
							}
							l_resource.setResourceSwfpath(swfpath);

						}
						l_resource
								.setSourcefileType(source.getSourcefileType());
						l_resource.setResourceSize(source.getResourceSize());
					} catch (Exception e) {
						e.printStackTrace();
						this.addActionMessage("文件转换出现错误，资源上传失败！");
						return ERROR;
					}
				}
			}
			l_resource.setTitle(HtmlUtil.splitAndFilterString(title, -1));
			l_resource.setResourceNotice(HtmlUtil.splitAndFilterString(
					resourceNotice, -1));
			l_resource.setModifyDate(new Date());
			// 与sourceType 建立双向关联关系
			SourceType sourceType = new SourceType();
			sourceType.setTypeId(sourcetype);
			sourceType.getCcczgInfoSouces().add(l_resource);
			l_resource.setSourceType(sourceType);

			// 与sourceType 建立双向关联关系
			SouceVersion souceVersion = new SouceVersion();
			souceVersion.setVersionId(sourceversion);
			souceVersion.getCcczgInfoSouces().add(l_resource);
			l_resource.setSouceVersion(souceVersion);

			// 与subject 建立双向关联关系
			SouceSubject subject = new SouceSubject();
			subject.setSubjectId(sourcesubject);
			subject.getCcczgInfoSouces().add(l_resource);
			l_resource.setSouceSubject(subject);

			// 与stugrade 建立双向关联关系
			SouceStugrade stugrade = new SouceStugrade();
			stugrade.setNjId(sourcegrade);
			stugrade.getCcczgInfoSouces().add(l_resource);
			l_resource.setSouceStugrade(stugrade);
			l_resource.setNeedpoint(needpoint);
			l_resource.setKeys(HtmlUtil.splitAndFilterString(keys, -1));
			l_resource.setCheckSign(Long.parseLong("0"));
			// l_resource.setSourceType(sourcetype);
			// l_resource.setSouceVersion(sourceversion);
			// l_resource.setSouceSubject(sourcesubject);

			// l_resource.setSouceStugrade(sourcegrade);
			sourceService.update(l_resource);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("资源修改失败！" + e.toString());
			setAttribute("id", StringUtils.trimToNull(getParameter("id")));
			return ERROR;
		}
		this.addActionMessage("修改成功！");
		return SUCCESS;
	}

	public void validateAdd() {
		// try {
		String[] net = getParameterValues("net");
		if (net != null && net.length > 0) {
			String netPath = StringUtils.trimToNull(getParameter("netPath"));
			if (netPath == null) {
				this.addActionError("外部链接不能为空!");
			}
			if (title == null || "".equals(title)) {
				this.addActionError("标题不能为空!");
			} else if (title.length() > 0 && title.getBytes().length > 256) {

				this.addActionError("标题长度不能超过256个字符!");
			}
		} else {
			if (this.source == null) {
				source = new CcczgInfoSouce();
			}
			if (title == null || "".equals(title)) {

				this.addActionError("标题不能为空!");
			} else if (title.length() > 0 && title.getBytes().length > 256) {
				this.addActionError("标题长度不能超过256个字符!");
			}
			if (null == this.sourcefile) {
				this.addActionError("资源不能为空!");
			}
			if (this.getCurrLoginUser() == null) {
				this.addActionError("您还没有登录，请先登录！");
			}

			if (null != this.sourcefile) {
				// 获得文件后缀名
				String extensionphoto = sourcefileFileName.substring(
						sourcefileFileName.lastIndexOf(".") + 1).trim();
				extensionphoto = extensionphoto.toUpperCase();
				if (!"FLV".equals(extensionphoto)
						&& !"PPT".equals(extensionphoto)
						&& !"TXT".equals(extensionphoto)
						&& !"DOC".equals(extensionphoto)
						&& !"XLS".equals(extensionphoto)
						&& !"PPTX".equals(extensionphoto)
						&& !"DOCX".equals(extensionphoto)
						&& !"XLSX".equals(extensionphoto)
						&& !"PDF".equals(extensionphoto)
						&& !"RAR".equals(extensionphoto)
						&& !"CHM".equals(extensionphoto)) {
					this.addActionError("资源类型错误！");

				}

				// 获得文件大小 KB
				Long len = sourcefile.length() / 1024;
				String unit = "KB";
				if (len / 1024 / 1024 >= 1) {
					this.addActionError("资源最大不能超过1G!");
				}

				// 文件后缀
				source.setSourcefileType(extensionphoto.trim());
				// 文件大小
				if (len / 1024 >= 1) {
					len = len / 1024;
					unit = "MB";
				}
				source.setResourceSize(len.toString() + unit);
			}

		}
		if (this.sourcetype == null) {
			this.addActionError("请选择资源类型!");
		}

		if (this.sourceversion == null) {
			this.addActionError("请选择资源版本!");
		}

		/*
		 * if(fla==1){ sendRedirect("source/addsourceGet!addget.action"); } }
		 * catch (IOException e) { e.printStackTrace(); }
		 */

	}

	public void validateUpdate() {
		String[] net = getParameterValues("net");
		if (net != null && net.length > 0) {
			String netPath = StringUtils.trimToNull(getParameter("netPath"));
			if (netPath == null) {
				this.addActionError("外部链接不能为空!");
				if (title == null || "".equals(title)) {
					this.addActionError("标题不能为空!");
				} else if (title.length() > 0 && title.getBytes().length > 256) {
					this.addActionError("标题长度不能超过256个字符!");
				}
			}
		} else {
			if (this.source == null) {
				// this.addActionError("资源对象不能为空!");
				source = new CcczgInfoSouce();
			}

			if (title == null || "".equals(title)) {

				this.addActionError("标题不能为空!");
			} else if (title.length() > 0 && title.getBytes().length > 256) {

				this.addActionError("标题长度不能超过256个字符!");
			}

			if (null != this.sourcefile) {
				// 获得文件后缀名
				String extensionphoto = sourcefileFileName.substring(
						sourcefileFileName.lastIndexOf(".") + 1).trim();
				extensionphoto = extensionphoto.toUpperCase();
				if (!"FLV".equals(extensionphoto)
						&& !"PPT".equals(extensionphoto)
						&& !"TXT".equals(extensionphoto)
						&& !"DOC".equals(extensionphoto)
						&& !"XLS".equals(extensionphoto)
						&& !"PPTX".equals(extensionphoto)
						&& !"DOCX".equals(extensionphoto)
						&& !"XLSX".equals(extensionphoto)
						&& !"RAR".equals(extensionphoto)
						&& !"PDF".equals(extensionphoto)
						&& !"CHM".equals(extensionphoto)) {
					this.addActionError("资源类型错误！");
				}
				// 文件后缀
				source.setSourcefileType(extensionphoto.trim());
				// 获得文件大小 KB
				Long len = sourcefile.length() / 1024;
				String unit = "KB";
				if (len / 1024 / 1024 >= 1) {
					this.addActionError("资源最大不能超过1G!");
				}

				// 文件大小
				if (len / 1024 >= 1) {
					len = len / 1024;
					unit = "MB";
				}

				source.setResourceSize(len.toString() + unit);
			}
		}
		if (this.sourcetype == null) {
			this.addActionError("请选择资源类型!");
		}

		if (this.sourceversion == null) {
			this.addActionError("请选择资源版本!");
		}
	}

	public String getSelstr() {
		return selstr;
	}

	public void setSelstr(String selstr) {
		this.selstr = selstr;
	}

	public int getSeltj() {
		return seltj;
	}

	public void setSeltj(int seltj) {
		this.seltj = seltj;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public List<CcczgInfoSouce> getZuixinList() {
		return zuixinList;
	}

	public void setZuixinList(List<CcczgInfoSouce> zuixinList) {
		this.zuixinList = zuixinList;
	}

	public List<CcczgInfoSouce> getRemenList() {
		return remenList;
	}

	public void setRemenList(List<CcczgInfoSouce> remenList) {
		this.remenList = remenList;
	}

	public SourceService getSourceService() {
		return sourceService;
	}

	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public VersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	public SouceSubjectService getSouceSubjectService() {
		return souceSubjectService;
	}

	public void setSouceSubjectService(SouceSubjectService souceSubjectService) {
		this.souceSubjectService = souceSubjectService;
	}

	public SousownService getSousownService() {
		return sousownService;
	}

	public void setSousownService(SousownService sousownService) {
		this.sousownService = sousownService;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFtpFile() {
		return ftpFile;
	}

	public void setFtpFile(String ftpFile) {
		this.ftpFile = ftpFile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getSourcegrade() {
		return sourcegrade;
	}

	public void setSourcegrade(String sourcegrade) {
		this.sourcegrade = sourcegrade;
	}

	public String getSourceversion() {
		return sourceversion;
	}

	public void setSourceversion(String sourceversion) {
		this.sourceversion = sourceversion;
	}

	public String getSourcesubject() {
		return sourcesubject;
	}

	public void setSourcesubject(String sourcesubject) {
		this.sourcesubject = sourcesubject;
	}

	public String getResourceNotice() {
		return resourceNotice;
	}

	public void setResourceNotice(String resourceNotice) {
		this.resourceNotice = resourceNotice;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public long getNeedpoint() {
		return needpoint;
	}

	public void setNeedpoint(long needpoint) {
		this.needpoint = needpoint;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public File getSourcefile() {
		return sourcefile;
	}

	public void setSourcefile(File sourcefile) {
		this.sourcefile = sourcefile;
	}

	public String getSourcefileContentType() {
		return sourcefileContentType;
	}

	public void setSourcefileContentType(String sourcefileContentType) {
		this.sourcefileContentType = sourcefileContentType;
	}

	public String getSourcefileFileName() {
		return sourcefileFileName;
	}

	public void setSourcefileFileName(String sourcefileFileName) {
		this.sourcefileFileName = sourcefileFileName;
	}

	public Long getResourceNum() {
		return resourceNum;
	}

	public void setResourceNum(Long resourceNum) {
		this.resourceNum = resourceNum;
	}

	public long getOrderList() {
		return orderList;
	}

	public void setOrderList(long orderList) {
		this.orderList = orderList;
	}

	public CcczgInfoSouce getSource() {
		return source;
	}

	public void setSource(CcczgInfoSouce source) {
		this.source = source;
	}

	public List<SouceStugrade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<SouceStugrade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<SouceSubject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SouceSubject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<SouceVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<SouceVersion> versionList) {
		this.versionList = versionList;
	}

	public List<SourceType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<SourceType> typeList) {
		this.typeList = typeList;
	}

	public List<CcczgInfoSouce> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<CcczgInfoSouce> sourceList) {
		this.sourceList = sourceList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public PointHistoryService getPointHistoryService() {
		return pointHistoryService;
	}

	public void setPointHistoryService(PointHistoryService pointHistoryService) {
		this.pointHistoryService = pointHistoryService;
	}

}
