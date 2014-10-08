package com.zigui.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class StrutsPager {

	private HibernateTemplate hibernateTemplate;

	protected int pageSize = 10;// 定义默认值
	protected int currentPage = 1;// 定义默认值
	private int totalSize;// 总的数据行
	private int totalPagerNumber;

	private final String PAGE_SIZE = "strutsPager.pageSize";// 一页数据量
	private final String CURRENT_PAGE = "strutsPager.currentPage";// 当前页码
	private final String STRUTSPAGER_QUERY = "strutsPager.query.";// 查询字段前缀
	private final int MAX_PAGER_NUMBER = 10;// 分页标签最大数量
	private final String[] unSafeString = new String[] { "'", "--", ";", "(",
			")" };// 麻烦点,需要思考一下.!!

	protected List list;
	private String groupHtmlTag = "div";
	private String groupHtmlTagClass = "fenye a-r";// 后台div 当前的样式,可自定义修改!
	private String prevHtmlString = "上一页";
	private String nextHtmlString = "下一页";
	private String pageNavigation;
	
	// 用于存储用户自定义的查询参数,如标题,日期,时间等.但要注意,如果domainObject里面没有对应字段,肯定要报错的
	//实例化是为了防止spring注入时候,内部有代码遍历引发空值异常
	private Map<String, String> query = new HashMap<String, String>();

	/**
	 * 执行数据查询,初始化list
	 * 
	 * @param <T>
	 * @param class1
	 *            要查询的domain类信息
	 * @param orderBy
	 *            排序字段,如果没有,设置为null
	 * @param isAsc
	 *            是否升序排序,如果配需字段为null,此处设置false即可!
	 */
	public <T> void queryList(Class<T> class1, String orderBy, boolean isAsc) {
		/**
		 * 问题: 1.虽然request中能够获取到用户提交的查询关键字,但是不同的字段会存在不同的查询算法, 如: title等类型的字段
		 * 应该使用的是 like查询 id等类型的字段 应该使用的是 eq 查询 date等类型的字段应该使用的是 gt 或 lt 查询
		 * 咋办.... =========================== 方案: 所有提交需要参与查询的参数强制命名格式
		 * query_操作代码_属性,如 like_title = 查询标题
		 * RestrictionsGenerator负责分析request传入的参数,生成最终的Restictions列表(所有查询判断条件)
		 * java类型咋搞?
		 * 
		 * 缺陷:需要自行解析query_like_String_title这种查询参数,然后生成对应的查询代码,查询方法和数据类型仅支持已经定义过的
		 * ! 最大问题,此类自己完成了业务逻辑操作,这种设计模式合理?
		 * 
		 * 另外:转换vo问题! 防注入安全问题(由于直接生成的是hql脚本,并且参数是直接写入hql字符串,必须自行控制注入安全问题!)
		 */

		String hql = generateHql(class1.getSimpleName(), orderBy, isAsc);
		Session session = getCurrentSesion();
		Query query = session.createQuery(hql);
		totalSize = getTotalSize(hql);
		totalPagerNumber = totalSize / pageSize;
		if (totalSize % pageSize > 0) {
			totalPagerNumber++;
		}

		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		list = query.list();
	}

	private Session getCurrentSesion() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	private int getTotalSize(String hql) {
		Session session = getCurrentSesion();
		hql = "select count(*) " + hql;
		Query query = session.createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	private String generateHql(String simpleName, String orderBy, boolean isAsc) {
		String hql = "from " + simpleName + " where 1=1 ";
		if (query != null) {
			Pattern pattern = Pattern.compile("([^_]+)_(\\w+)");// 操作符_字段名
			for (String key : query.keySet()) {
				Matcher matcher = pattern.matcher(key);
				if (matcher.find()) {
					// 找到指定标签
					String operate = matcher.group(1);
					String field = matcher.group(2);
					String value = query.get(key);
					// 开始组装查询语句
					if (value != null && !value.equals(""))
						hql += " and " + field + " " + getHqlOperator(operate)
								+ " " + getHqlValue(field, operate, value)
								+ " ";
				}
			}
		}
		hql = hql.substring(0, hql.length() - 1);
		if (orderBy != null && !orderBy.equals("")) {
			hql += " order by " + orderBy + " " + (isAsc ? "" : "desc");
		}
		return hql;
	}

	private String getHqlOperator(String operator) {
		// 有空了再搞个好点的工厂,现在搞个简单工厂够用了
		if (operator.equals("eq"))
			return "=";
		if (operator.equals("gt"))
			return ">";
		if (operator.equals("lt"))
			return "<";
		if (operator.equals("like"))
			return "like";
		throw new RuntimeException(
				"OperatorFactoryForStrutsPager cannot understand operator["
						+ operator + "]");
	}

	private String getHqlValue(String field, String operate, String value) {
		value = getUrlDecodeString(value);
		if (field != null && value != null) {
			// 专门为日期类型做转换
			Pattern fieldPattern = Pattern.compile(".*time.*");
			Matcher fieldMatcher = fieldPattern.matcher(field);
			Pattern valuePattern = Pattern.compile("\\d\\d\\d\\d-\\d+-\\d+");
			Matcher valueMatcher = valuePattern.matcher(value);
			if (fieldMatcher.find() && valueMatcher.find()) {
				return "to_date('" + value + "', 'yyyy-mm-dd')";
			}
		}
		if (operate.equals("like")) {
			return "'%" + getSaveHqlValue(value) + "%'";
		} else {
			return "'" + getSaveHqlValue(value) + "'";
		}
	}

	/**
	 * 过滤掉会导致sql注入漏洞产生的关键字
	 * 
	 * @param value
	 * @return
	 */
	private String getSaveHqlValue(String value) {
		// clear unsafe string
		if (value != null && value.length() > 0) {
			for (String unsafe : unSafeString) {
				value.replace(unsafe, "");
			}
		}
		return value;
	}

	/**
	 * 获取替换掉指定参数的url地址字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private String getTargetPageUrlString(int value) {
		String urlString = "?";
		for (String mapKey : query.keySet()) {
			urlString += "&" + STRUTSPAGER_QUERY + mapKey + "="
					+ getUrlEncodeString(query.get(mapKey));
		}
		urlString += "&" + PAGE_SIZE + "=" + pageSize;
		urlString += "&" + CURRENT_PAGE + "=" + value;
		return urlString;
	}

	private int getNavigationEndIndex(int startIndex) {
		/**
		 * |1 2 3 4 5 6 7 8 9 10 | 11 12 13
		 */
		int endIndex = startIndex + MAX_PAGER_NUMBER - 1;
		if (endIndex <= totalPagerNumber) {
			return endIndex;
		} else {
			return totalPagerNumber;
		}
	}

	private int getNavigationStartIndex() {
		/**
		 * |1 2 3 4 5 6 7 8 9 10 | 11 12 13
		 */
		if ((currentPage + MAX_PAGER_NUMBER - 1) <= totalPagerNumber) {
			return currentPage;
		} else {
			int ex = MAX_PAGER_NUMBER - (totalPagerNumber - currentPage + 1);
			return (currentPage - ex) > 0 ? (currentPage - ex) : 1;// 回退ex步
		}
	}

	private String getUrlEncodeString(String input) {
		try {
			return URLEncoder.encode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			LogTool.getLog().error(e);
			return null;
		}
	}

	private String getUrlDecodeString(String input) {
		try {
			return URLDecoder.decode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			LogTool.getLog().error(e);
			return null;
		}
	}

	/**
	 * 获取导航分页标签
	 * 
	 * @return
	 */
	public String getPageNavigation() {
		// 生成包装头部
		pageNavigation = "<"
				+ groupHtmlTag
				+ ((groupHtmlTagClass == null || groupHtmlTagClass.equals("")) ? ""
						: " class=\"" + groupHtmlTagClass + "\"") + ">";

		int startIndex = getNavigationStartIndex();
		int endIndex = getNavigationEndIndex(startIndex);
		int prevIndex = currentPage > 1 ? currentPage - 1 : 1;
		int nextIndex = currentPage < totalPagerNumber ? currentPage + 1
				: totalPagerNumber;
		// 生成 上一页
		if (prevIndex > 0 && prevIndex != currentPage) {
			pageNavigation += "<a href=\"" + getTargetPageUrlString(prevIndex)
					+ "\">" + prevHtmlString + "</a>";

		}
		// 生成 中间主题部分
		for (int i = startIndex; i <= endIndex; i++) {
			pageNavigation += "<a href=\"" + getTargetPageUrlString(i) + "\">"
					+ (i == currentPage ? "[" + i + "]" : i) + "</a>";

		}
		// 生成 下一页
		if (nextIndex != totalPagerNumber + 1
				&& currentPage != totalPagerNumber) {
			pageNavigation += "<a href=\"" + getTargetPageUrlString(nextIndex)
					+ "\">" + nextHtmlString + "</a>";
		}
		// 后续,生成 输入框 及button 调整到指定页
		// 暂时不做

		// 生成总页码

		// 生成包装尾部
		pageNavigation += "</" + groupHtmlTag + ">";
		return pageNavigation;
	}

	public List getList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setGroupHtmlTag(String groupHtmlTag) {
		this.groupHtmlTag = groupHtmlTag;
	}

	public void setPrevHtmlString(String prevHtmlString) {
		this.prevHtmlString = prevHtmlString;
	}

	public void setNextHtmlString(String nextHtmlString) {
		this.nextHtmlString = nextHtmlString;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		LogTool.getLog().info(hibernateTemplate);
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 设置外层包裹div的class属性
	 * 
	 * @param groupHtmlTagClass
	 */
	public void setGroupHtmlTagClass(String groupHtmlTagClass) {
		this.groupHtmlTagClass = groupHtmlTagClass;
	}
}
