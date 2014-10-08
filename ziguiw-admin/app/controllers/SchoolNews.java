package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.utils.FileUtils;
import models.QueryParams;
import models.Recommend;
import models.SchoolNew;
import play.db.DB;
import play.db.Model;
import play.mvc.Http;
import play.mvc.Util;
import play.templates.TemplateLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 上午10:53
 */
public class SchoolNews extends Application{

    @Util
    public static void beforeSave(Model object) {
        SchoolNew newse = (SchoolNew) object;
        File file = Http.Request.current().params.get("file", File.class);
        if (file != null) {
            newse.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL ,  ImageSize.SKL});
        }
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeCreate(Model object) {
        SchoolNew newse = (SchoolNew) object;
        File file = Http.Request.current().params.get("file", File.class);
        if (file != null) {
            newse.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL ,  ImageSize.SKL});
        }
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                SchoolNew newse = SchoolNew.findById(id);
                if (newse == null) continue;
                    Recommend recommend = new Recommend();
                    recommend.title = newse.title;
                    recommend.area = recommendArea;
                    recommend.description = newse.describe;
                    recommend.url = newse.url;
                    recommend.objectId = id + "";
                    recommend.create();
            }
        }
        renderText("OK");
    }

    public static void deletePhoto(Long id) throws Exception {
        SchoolNew object = null;
        if(id != null){
            object = SchoolNew.findById(id);
            object.url = null;
            object.save();
        }
        render("SchoolNews/show.html",object);
    }

    public static void statistic(String startTime, String endTime, Integer status) throws SQLException, IOException {
        Map<String, Object> args = new HashMap<String, Object>();
        if (startTime != null) args.put("startTime", startTime);
        if (endTime != null) args.put("endTime", endTime);
        if (status == null) status = Status.OK;
        args.put("status", status);
        String sql = TemplateLoader.load("SchoolNews/statistic.sql").render(args);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            ResultSet resultSet = DB.getConnection().prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                String sname = (String) resultSet.getObject(1);
                String zs = resultSet.getObject(2) + "";
                String yc = resultSet.getObject(3)  + "";
                String zz = resultSet.getObject(4)  + "";
                String gg = resultSet.getObject(5)  + "";
                String xxyd = resultSet.getObject(6)  + "";
                Map<String, String> map = new HashMap<String, String>();
                map.put("sname", sname);
                map.put("zs", zs);
                map.put("yc", yc);
                map.put("zz", zz);
                map.put("gg", gg);
                map.put("xxyd", xxyd);
                list.add(map);
            }
        } finally {
            DB.close();
        }
        if ("1".equalsIgnoreCase(params.get("exp"))) {
            String fileName = "exp_" + System.currentTimeMillis() + ".csv";
            File file = new File(System.getProperty("application.path") + "/tmp/" +fileName );
            FileWriter fw = new FileWriter(file);
            String header = "学校,时间,总篇数,原创校园新闻,转载校园新闻,班级新闻/公告,学习园地\r\n";
            byte csvHeader[] = { (byte) 0xEF, (byte) 0xBB,
                    (byte) 0xBF };
            fw.write(new String(csvHeader) + header);
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                if ("0".equals(map.get("zs"))) continue;
                StringBuffer str = new StringBuffer();
                str.append(String.format("%s,%s,%s,%s,%s,%s,%s\r\n", map.get("sname"),
                    String.format("%s - %s", startTime == null ? "" : startTime , endTime == null ? "" : endTime),
                        map.get("zs"),
                        map.get("yc"),
                        map.get("zz"),
                        map.get("gg"),
                        map.get("xxyd")
                ));
                fw.write(str.toString());
                fw.flush();
            }
            fw.close();
            renderBinary(file);
            file.delete();
        } else
        render(list, startTime, endTime, status);
    }
}
