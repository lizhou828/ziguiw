package models;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-6
 * Time: 下午4:04
 */
public class Page<T> {

    public int page;

    public int pageSize;

    public long totalCount;

    public long totalPage;

    public List<T> data;

    public Page() {
    }

    public Page(int page, int pageSize, long totalCount, List data) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) +1;
    }
}
