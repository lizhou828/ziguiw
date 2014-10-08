package com.zigui.utils;

import com.zigui.domain.Page.OrderType;
import com.zigui.domain.Page;

public class PageUtil {
	//静态方法
		public static Page createPage(Page page, int totalRecords) {
			return createPage(page.getEveryPage(), page.getCurrentPage(),
					totalRecords,page.getOrderBy(),page.getOrderType(),page.getProperty(),page.getKeyword());
		}

	    //创建Page对象
		public static Page createPage(int everyPage, int currentPage,
				int totalRecords,String orderBy,OrderType orderType, String property, String keyword) {
			everyPage = getEveryPage(everyPage);
			currentPage = getCurrentPage(currentPage);
			int beginIndex = getBeginIndex(everyPage, currentPage);
			int totalPage = getTotalPage(everyPage, totalRecords);
			boolean hasNextPage = hasNextPage(currentPage, totalPage);
			boolean hasPrePage = hasPrePage(currentPage);

			return new Page(hasPrePage, hasNextPage, everyPage, totalPage,
					currentPage, beginIndex, totalRecords, orderBy,  orderType,  property,  keyword);
			
		}

		//返回每页显示数目
		private static int getEveryPage(int everyPage) {
			return everyPage == 0 ? 10 : everyPage;
		}

		//返回当前页
		private static int getCurrentPage(int currentPage) {
			return currentPage == 0 ? 1 : currentPage;
		}

		//返回开始的索引
		private static int getBeginIndex(int everyPage, int currentPage) {
			return (currentPage - 1) * everyPage;
		}

		//返回总页数
		private static int getTotalPage(int everyPage, int totalRecords) {
			int totalPage = 0;

			if (totalRecords % everyPage == 0)
				totalPage = totalRecords / everyPage;
			else
				totalPage = totalRecords / everyPage + 1;

			return totalPage;
		}

		//返回是否有上一页。false：没有；true：有
		private static boolean hasPrePage(int currentPage) {
			return currentPage == 1 ? false : true;
		}

		//返回是否有下一页。false：没有；true：有
		private static boolean hasNextPage(int currentPage, int totalPage) {
			return currentPage == totalPage || totalPage == 0 ? false : true;
		}

}
