package com.arj.ziguiw.common;

public class ImageSize {
	
	
	 private final static int[][] sizes = {
         {201, 151}, //    0
         {136, 102}, //    1
         {313, 265}, //    2
         {120,155}, //      3
         {155,116}, //       4
         {693,521}, //      5
         {50, 50}, //X      6
         {95,70}, //             7
         {120,120}, //IS        8
             {160,160},
             {65,65},
             {305,235},
             {500,1500}            //12
 };

	 public final static String DEFAULT_IMAGE = "/images/newImages/default.jpg";
	
	 /* 学校快乐校园列表页图 */
	 public final static String SKL = sizes[0][0] + "X" + sizes[0][1];

	 /* 学校首页小图 */
	 public final static String SSX = sizes[1][0] + "X" + sizes[1][1];

	 /* 学校首页轮播图 */
	 public final static String SSL = sizes[2][0]+ "X" + sizes[2][1];

    /* 师资力量图及视屏页小图 */
    public final static String TC = sizes[3][0]+ "X" + sizes[3][1];

    /* 学校照片页小图及视屏页小图 */
    public final static String SZX = sizes[4][0]+ "X" + sizes[4][1];

    /* 学校照片页大图 */
    public final static String SZD = sizes[5][0]+ "X" + sizes[5][1];

    /* 缩略图 */
    public final static String X = sizes[6][0] + "X" + sizes[6][1];

    /* 班级站左侧菜单小图 */
    public final static String CZX= sizes[7][0]+ "X" + sizes[7][1];

    /* 我的小家头像*/
    public final static String HOMEPHOTO= sizes[8][0]+ "X" + sizes[8][1];

    /*相册封面图*/
    public final static String XINGCEFENGMIAN = sizes[9][0] + "X" + sizes[9][1];

    /*照片列表图*/
    public final static String PHOTOLIST = sizes[10][0] + "X" + sizes[10][1];

    /*照片详细图*/
    public final static String PHOTOSHOW = sizes[10][0] + "X" + sizes[10][1];

    /*资源轮播图*/
    public final static String RESOURCELUNBO = sizes[11][0] + "X" + sizes[11][1];

    /*新闻里面图片尺寸*/
    public final static String NEWSPHOTO = sizes[12][0] + "X" + sizes[12][1];


    /**
     * get special size for compressed image
     * <pre>
     *     String xUrl = IMAGE_SIZE.getImage("upload/2013/0121/123456.jpg", IMAGE_SIZE.X)
     *     the xUrl is "upload/2013/0121/123456_50X50.jpg"
     * </pre>
     *
     * @param url the origin image url
     * @param size image size, for example : IMAGE_SIZE.X or IMAGE_SIZE.S
     * @return the compressed image url
     */
    public static String getImage(String url, String size) {
        int idx = url.lastIndexOf(".");
        return String.format("%s_%s%s", url.substring(0, idx), size, url.substring(idx));
    }
}
