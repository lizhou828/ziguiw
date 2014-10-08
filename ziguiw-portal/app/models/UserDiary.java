package models;

import com.arj.ziguiw.common.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-12
 * Time: 上午10:43
 */

@Entity
@Table(name="user_diary")
@SequenceGenerator(name="USER_DIARY_SEQ",
        sequenceName="user_diary_seq",
        allocationSize=1)
public class UserDiary extends JPASupport{

    private static final Log logger = LogFactory.getLog(UserDiary.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DIARY_SEQ")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "keyword")
    public String keyword;

    @Column(name = "type")
    public int type;

    @Column(name = "status")
    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", insertable=true, updatable=true)
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "last_modify_id")
    public int lastModifyId;

    @Column(name = "last_modify_time")
    public Date lastModifyTime;

    @Lob
    @Basic(fetch= FetchType.EAGER,optional=true)
    @Column(name = "text")
    public String text;

    @Column(name = "description")
    public String description;

    @Column(name = "view_count")
    public int viewCount = 0;


    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "click_count")
    public Long clickCount = 0l;


    public static Page<UserDiary> findByUserId(Long userId,Integer page,Integer pageSize) {
        String hql = "select u from UserDiary u where u.user.id = ? " +
                     "and u.status = ? order by u.createTime desc";
        List<UserDiary> list = find(hql,userId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from UserDiary u where u.user.id = ? and u.status = ?";
        Long totalCount = count(hql,userId,Status.OK);
        return new Page<UserDiary>(page,pageSize,totalCount,list);
    }



    public static String getFirstImage(String content){
        String firstImage = "";
        Parser parser = Parser.createParser(content, "UTF-8");
        NodeFilter filter = new NodeClassFilter(ImageTag.class);
        try {
            NodeList images = parser.extractAllNodesThatMatch(filter);
            for (Node node : images.toNodeArray()) {
                ImageTag imageTag = (ImageTag)node;
                firstImage = imageTag.getImageURL();
                break;
            }
        } catch (ParserException e) {
            logger.error("get FirstImage fail",e);
            throw new RuntimeException(e);
        }
        if(!firstImage.equals(""))
        firstImage = firstImage.substring(firstImage.indexOf("upload"));
        return firstImage;
    }
}
