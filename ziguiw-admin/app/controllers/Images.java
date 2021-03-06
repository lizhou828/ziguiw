package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liumj
 * Date: 13-1-22
 * Time: A.M 10:49
 */
public class Images extends Application {

    public static void upload(File file) {
        String imgUrl = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir , new String[]{ImageSize.NEWSPHOTO});
        Map<String, String> map = new HashMap();
        map.put("imgUrl", String.format("%s/%s", renderArgs.get("ctx"), ImageSize.getImage(imgUrl,ImageSize.NEWSPHOTO)));
        renderJSON(map);
    }

}
