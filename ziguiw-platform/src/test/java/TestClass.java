import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zigui.action.TestAction;
import com.zigui.domain.UserDiary;
import com.zigui.utils.LogTool;
import com.zigui.utils.StrutsPager;

/**
 * Created with IntelliJ IDEA. User: YeQiang Date: 12-12-14 Time: 下午12:56
 */
public class TestClass {

	// @Test
	public void test() {
		/*
		 * ApplicationContext spring = new ClassPathXmlApplicationContext(new
		 * String[]{"classpath*:applicationContext-*.xml"}); ClassDao classDao =
		 * (ClassDao)spring.getBean("classDao");
		 */

		/*
		 * NewsStatus newsStatus = NewsStatus.parse(2);
		 * LogTool.getLog().info(newsStatus.getDisplayName());
		 */

		/*
		 * String str = "fdsfjdslfjdls.action"; LogTool.getLog().info(1 +
		 * str.lastIndexOf(".jsp"));
		 */

		long a = 11;
		long size = 10;

		long total = a / size;
		long ex = a % size;

		LogTool.getLog().info(total + " " + ex);
	}

	// @Test
	public void testJdbc() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@10.0.1.22:1521:dw", "scott", "ziguiw");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select 0 from dual");
		while (resultSet.next()) {
			LogTool.getLog().info(
					String.format("test %s", resultSet.getObject(1)));
		}
	}

	// @Test
	public void systemPathTest() {
		File file = new File("/");
		System.out.println(file.getAbsolutePath());
	}

	// @Test
	public void restrictionsTest() {
		ApplicationContext spring = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:applicationContext-*.xml" });
		HibernateTemplate hibernateTemplate = (HibernateTemplate) spring
				.getBean("hibernateTemplate");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(UserDiary.class);
		// 数据类型测试,尤其date类型

		// criteria.add(Restrictions.like("title", "%教育%"));//like 值必须%%包裹!

		// criteria.add(Restrictions.eq("status", "1")); java.lang.String cannot
		// be cast to java.lang.Integer

		List<UserDiary> list = criteria.list();
		for (UserDiary userDiary : list) {
			System.out.println(userDiary.getTitle());
		}
		System.out.println(list.size());
	}

	@Test
	public void hqlTest() {
		ApplicationContext spring = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:applicationContext-*.xml" });
		HibernateTemplate hibernateTemplate = (HibernateTemplate) spring
				.getBean("hibernateTemplate");
		Session session = hibernateTemplate.getSessionFactory().openSession();
		String status = "1";
		String createTime = "2013-01-06";
		String queryString = "from UserDiary where status = '" + status
				+ "' and createTime < to_date('" + createTime + "', 'yyyy-mm-dd')";
		Query query = session.createQuery(queryString);
		Date date = new Date();
//		query.setParameter(0, date);// ok
		List<UserDiary> list = query.list();
		for (UserDiary userDiary : list) {
			System.out.println(userDiary.getTitle() + "\t-\t"
					+ userDiary.getCreateTime());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void classTest()
	{
		Class<UserDiary> t = UserDiary.class;
		System.out.println(t.getSimpleName());		
	}
	
	@Test
	public void regexTest()
	{
		String query = "query_title_like";
		Pattern pattern = Pattern.compile("query_(\\w+)_(\\w+)");
		Matcher matcher = pattern.matcher(query);
		if(matcher.find())
		{
			System.out.println(matcher.group(1) +"\t" + matcher.group(2));
		}
	}
	
	@Test
	public void testActionTest() throws UnsupportedEncodingException
	{
		String input = "中文";
		String input2 = URLEncoder.encode(input, "utf-8");
		System.out.println(input2);
		System.out.println(URLDecoder.decode(input2, "utf-8"));
		
	}
}
