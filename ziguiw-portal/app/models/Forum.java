package models;

import com.arj.ziguiw.common.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Formula;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-4
 * Time: 上午10:00
 */
@Entity
@Table(name = "forum")
@SequenceGenerator(name = "FORUM_SEQ",
        sequenceName = "forum_seq",
        allocationSize = 1)
public class Forum extends JPASupport {
    private static final Log logger = LogFactory.getLog(Forum.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORUM_SEQ")
    public long id;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="boardid")
    public Board board;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="parentid")
    public Forum parentForum;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "creator_id")
    public long creatorId;

    @Column(name = "title")
    @Required
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "state")
    public int state = Status.OK;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "elite")
    public int elite = 0;

    @Column(name = "istop")
    public int istop = 0;

    @Column(name = "auto_description")
    public String autoDescription;

    @Column(name = "last_post_time")
    public Date lastPostTime;

    @Column(name = "last_post_nick")
    public String lastPostNick;

    @Column(name = "click_count")
    public Integer clickCount = 0;

    @Formula("(select count(*) from Forum f where f.parentid = id)")
    public long renum = 0;

    @Formula("(select u.portrait from user_base u where u.id = creator_id)")
    public String creatorPortrait;



    public static Page<Forum> findReplyByFourmId(long id,Integer page,Integer pageSize) {
        String hql = "select f from Forum f where  f.state = ? and f.parentForum.id = ?";
        List<Forum> list = find(hql,Status.OK,id).fetch(page, pageSize);
        hql = "select count(*) from Forum f where  f.state = ? and f.parentForum.id = ?";
        long totalCount = count(hql,Status.OK,id);
        return new Page<Forum>(page,pageSize,totalCount,list);
    }

    public static List<Forum> findNewForums() {
        return find("select f from Forum f where f.state = ? and " +
                "f.parentForum is null order by f.createTime desc",Status.OK).fetch(1,10);
    }

    public static List<Forum> findHotForums() {
        return find("select f from Forum f where f.state = ? and " +
                "f.parentForum is null order by f.clickCount desc",Status.OK).fetch(1, 6);
    }

    public static Page<Forum> findByUserId(Long userId, Integer page, Integer pageSize) {
        String hql = "select f from Forum f where f.state = ? and f.parentForum is null" +
                " and f.creatorId = ? order by f.createTime desc";
        List<Forum> list = find(hql,Status.OK,userId).fetch(page,pageSize);
        hql = "select count(*) from Forum f where f.state = ? and f.parentForum is null and f.creatorId = ?";
        Long totalCount = count(hql,Status.OK,userId);
        return new Page<Forum>(page,pageSize,totalCount,list);
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

    public static String getPureText(String html){
        StringBuffer sb = new StringBuffer();
        try
        {
            Parser parser = Parser.createParser(html, "UTF-8");
            NodeIterator its  = parser.elements();
            while(its.hasMoreNodes())
            {
                Node node = (Node) its.nextNode();
                sb.append(node.toPlainTextString());
            }
        }catch (ParserException e){
            logger.error("get autoDescription fail",e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


}
