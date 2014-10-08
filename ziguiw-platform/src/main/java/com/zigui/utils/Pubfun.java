package com.zigui.utils;

import java.io.File;
import java.lang.Double;
import java.lang.Integer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.lang.Math;

/**
 * @description:公用函数集
 * @author 湖南爱瑞杰科技发展股份有限公司技术部
 * @version 1.0 History: // 历史修改记录 <author> <time> <version > <desc> Hedan
 *          11/04/18 1.0 build this moudle
 * 
 */
public class Pubfun {
	/*
	 * 小写数字转换为大写数字
	 */
	public static String tocapitalize(String arg) {
		String[] dx = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		int len = arg.length();
		String rtn = new String("");
		int x = 0;
		double dcur = Double.parseDouble(arg);
		if (dcur == 0) {
			for (int i = 0; i < len; i++) {
				rtn = rtn + "零";
			}
			return rtn;
		}
		if (arg.substring(len - 3, len - 2).equals(".")) {
		} else {
			System.out.println(arg.substring(len - 3, len - 2));
			return "格式错误";
		}

		if (dcur < 0) {
			for (int i = 1; i < len; i++) {
				if (i != len - 3) {
					x = Integer.valueOf(arg.substring(i, 1)).intValue();
					rtn = rtn + dx[x];
				}
			}
			return "负" + rtn;
		} else {
			for (int i = 0; i < len; i++) {
				if (i != len - 3) {
					x = Integer.valueOf(arg.substring(i, i + 1)).intValue();
					rtn = rtn + dx[x];
				}
			}
			return rtn;
		}
	}

	/*
	 * 小写金额转换为大写金额
	 */
	public static String toCapitalize(double arg) {
		String[] dx = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String rtn = new String("");
		String s = "";
		int ti = 0;
		double j = 0, n_bak = 0, k = 1;
		if (arg == 0) {
			rtn = "零圆整";
			return rtn;
		}
		if (arg < 0) {
			arg = Math.abs(arg);
			rtn = " (负)";
		}
		Double my;
		my = Double.valueOf(new DecimalFormat("##0.00").format(arg));
		arg = my.doubleValue();
		n_bak = arg;
		double a, b;
		a = 10;
		for (int i = 10; i >= 0; i--) {
			b = (double) i;
			j = n_bak / (Math.pow(a, b));

			if ((int) j < 0 || (int) j > 9) {
				return rtn + String.valueOf(arg) + "圆整";
			}
			n_bak = arg % (Math.pow(a, b));
			// System.out.println(n_bak);
			if ((int) j == 0 && s.equals("")) {
				continue;
			}
			if ((int) j == 0 && (int) k == 0 && i != 8 && i != 4) {
				continue;
			}
			if ((int) j == 0) {
				if (i != 8 && i != 4 && (int) k != 0) {
					s += dx[(int) j];
				}
				k = j;
				if (i == 8 || i == 4) {
					if (s.substring(s.length() - 1).equals("零")) {
						s = s.substring(0, s.length() - 1);
					}
					if (i == 8) {
						s += "亿";
						k = 1;
					} else {
						if (i == 4) {
							s += "万";
							k = 1;
						}
					}
				}
				continue;
			}
			k = j;
			s += dx[(int) j];
			if (i == 7 || i == 3) {
				s += "仟";
			} else {
				if (i == 6 || i == 2 || i == 10) {
					s += "佰";
				} else {
					if (i == 5 || i == 1 || i == 9) {
						s += "拾";
					}
				}
			}

			if (i == 8 || i == 4) {
				if (s.length() < 1)
					continue;
				if (s.substring(s.length() - 1).equals("零")) {
					s = s.substring(0, s.length() - 1);
				}
				if (i == 8) {
					s += "亿";
					k = 1;
				} else {
					if (i == 4) {
						s += "万";
						k = 1;
					}
				}
			}
		}
		// 小数部分
		n_bak = n_bak % 10;
		if (s.substring(s.length() - 1) == "零") {
			s = s.substring(0, s.length() - 1);
			ti = 1;
		}
		if (s != "")
			s += "圆";

		String s1 = "";
		if (n_bak != 0) {
			n_bak *= 10;
			j = Math.round(n_bak);
			if ((int) j != 0)
				s1 += dx[(int) j] + "角";

			n_bak *= 10;
			n_bak = n_bak % 10;
			j = Math.round(n_bak);
			if ((int) j != 0) {
				if ((int) j > 9)
					j = 0;
				s1 += dx[(int) j] + "分";
			}
		}
		if (s1 != "" && s != "") {
			if (ti == 1)
				s += "零" + s1;
			else
				s += s1;
		} else {
			if (s == "")
				s = s1;
		}
		rtn = rtn + s + "整";
		return rtn;
	}

	/*
	 * 自动加1函数 例Y0001通过此函数后变为Y0002
	 */
	public String autoadd(String arg) {
		String out = "", temp = "", rtn = "";
		int add;
		arg = arg.trim();
		int i = 1, len;
		if (arg.equals("") || arg == null) {
			return "";
		}
		len = arg.length();
		do {
			out = temp + out;
			temp = arg.substring(len - i, len - i + 1);
			i++;
			if (isnumber(temp)) {
			} else {
				break;
			}
		} while (i < len);
		i--;
		add = (int) Double.parseDouble(out) + 1;
		out = "0000000000000" + String.valueOf(add);
		out = out.substring(out.length() - i + 1, out.length());
		if (out.length() > len) {
			rtn = out;
		} else
			rtn = arg.substring(0, len - i + 1) + out;
		return rtn;
	}

	/*
	 * 判断传入字符串是否是数字型数据
	 */
	public boolean isnumber(String arg) {
		String temp;
		if (arg.trim() == "" || arg == null)
			return false;
		for (int i = 1; i <= arg.length(); i++) {
			temp = arg.substring(arg.length() - i, arg.length() - i + 1);
			if (temp.equals("0") || temp.equals("1") || temp.equals("2")
					|| temp.equals("3") || temp.equals("4") || temp.equals("5")
					|| temp.equals("6") || temp.equals("7") || temp.equals("8")
					|| temp.equals("9")) {

			} else {
				return false;
			}
		}
		return true;
	}

	/*
	 * 将字符串中的,替代为','
	 */
	public static String trantostring(String arg) {
		String olds = ",", news = "','", rtn = "";
		if (arg.trim().equals("") || arg == null) {
			return "";
		}
		rtn = arg.replaceAll(olds, news);
		rtn = "'" + rtn + "'";
		return rtn;
	}

	/*
	 * 得到服务器时间
	 */
	public static String getdate() {
		Date rtn = new Date();
		String srtn;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		srtn = format.format(rtn);
		return srtn;
	}

	public static String getdatetime() {
		Date rtn = new Date();
		String srtn;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		srtn = format.format(rtn);
		return srtn;
	}


	public String right(String arg, int num) {
		int spos;
		spos = arg.length() - num;
		return arg.substring(spos, arg.length());
	}

	/**
	 * 将字符串编码格式转换为iso-8859-1
	 * 
	 * @param sourceString
	 * @return
	 */
	public static String convertEncoding(String sourceString) {
		String result = "";
		if (sourceString != null && !"".equals(sourceString)) {
			try {
				result = new String(sourceString.getBytes("iso-8859-1"),
						"utf-8");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}

	/**
	 * 随机生成6位数字
	 * 
	 * @param length
	 *            要生成几位数字
	 * @return
	 */
	public static String getRandomNumber(int length) {
		String randomNumber = "";
		for (int i = 0; i < length; i++) {
			randomNumber += new Random().nextInt(9);
		}
		return randomNumber;
	}

	/**
	 * 内容处理
	 * 
	 * @param content
	 * @return
	 */
	public static String contentHandle(String content) {
		// 去掉制表位和换行符
		String newContent = content.replaceAll("\t", "");
		newContent = newContent.replaceAll("\r\n", "");

		// 统计各浏览器的分页符
		String pageSign = "<div style=\"page-break-after: always;\"><span style=\"display: none;\">&nbsp;</span></div>";
		newContent
				.replaceAll(
						"<div style=\"page-break-after: always\"><span style=\"display: none;\">&nbsp;</span></div>",
						pageSign);
		newContent
				.replaceAll(
						"<div style=\"page-break-after: always\"><span style=\"display: none\">&nbsp;</span></div>",
						pageSign);
		newContent
				.replaceAll(
						"<div style=\"page-break-after: always;\"><span style=\"display: none\">&nbsp;</span></div>",
						pageSign);
		
		//处理文章中的图片
		
		
		String str = HttpUtil.getWebPath()+"img_news/";
//		System.out.println(newContent.indexOf(ss)+ss+newContent);
//		newContent.replaceAll(ss, "img_news/");
//		System.out.println(newContent.indexOf(ss)+ss+newContent);
		
		int temp;
		while(true){
			temp = newContent.indexOf(str);
			if(temp == -1){
				break;
			}
			else{
				newContent = newContent.substring(0,temp)+newContent.substring(temp+str.length()-9,newContent.length());
			}
		}
		
		return newContent;
	}
	
	/**
	 * 内容处理
	 * 文章中带图片的处理，编辑器显示文章时，图片的src加上webpath
	 * @param content
	 * @return
	 */
	public static String contentHandleUpdate(String content){
		
		String str = "src=\"img_news/";
		String newContent = content.replaceAll("src=\"img_news/", "src=\""+HttpUtil.getWebPath()+"img_news/");
		//处理文章中的图片
		return newContent;
	}

	/**
	 * 构建是传路径
	 * 
	 * @return
	 */
	public static String structurePath(String item, Date date, String id)
			throws Exception {
		// 文件保存路径
		StringBuffer sbUploadFilePath = new StringBuffer("uploadfiles");// 上传文件目录
		sbUploadFilePath.append(File.separator);
		sbUploadFilePath.append(item);
		sbUploadFilePath.append(File.separator);
		sbUploadFilePath.append("admin");// 用户（这里是后台）
		sbUploadFilePath.append(File.separator);
		sbUploadFilePath.append(DateUtil.setDateFormat(date, "yyyy-MM-dd"));// 日期（年月日）
		sbUploadFilePath.append(File.separator);
		sbUploadFilePath.append(id);// ID
		sbUploadFilePath.append(File.separator);
		return sbUploadFilePath.toString();
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String specialCharFilter(String str)
			throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static void main(String[] args) throws PatternSyntaxException {
		String str = "*adCVs*34_a _09_b5*[/435^*&城池()^$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？-....zip";
		String substr=str.substring(0,str.lastIndexOf("."));
		String extension=str.substring(str.lastIndexOf("."));
		System.out.println(str);
		System.out.println(specialCharFilter(substr)+extension);
	}
	
}
