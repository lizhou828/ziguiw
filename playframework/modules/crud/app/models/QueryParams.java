package models;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-2-25
 * Time: P.M 2:32
 */
public class QueryParams {

    /* the current page number */
    public int page;

    /* the keyword */
    public String search;

    /* the field for search keyword */
    public String searchFields;

    /* the field for order by */
    public String orderBy;

    /* asc or desc */
    public String order;

    /* the condition, example: "type=1 and status=0" */
    public String where;

    public QueryParams(int page, String search, String searchFields, String orderBy, String order, String where) {
        this.page = page;
        this.search = search;
        this.searchFields = searchFields;
        this.orderBy = orderBy;
        this.order = order;
        this.where = where;
    }
}
