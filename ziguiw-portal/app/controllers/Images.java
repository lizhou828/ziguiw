package controllers;

import com.arj.ziguiw.common.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-5
 * Time: 下午4:47
 */
public class Images extends Application {

    public static void upload(File file) {
        String imgUrl = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir,new String[]{"500X400"});
        Map<String, String> map = new HashMap();
        map.put("imgUrl", String.format("%s/%s", ctx, imgUrl));
        renderJSON(map);
    }
}
