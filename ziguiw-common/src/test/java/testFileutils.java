import com.arj.ziguiw.common.utils.FileUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-19
 * Time: 上午11:38
 */
public class testFileutils {

       static String cotent = "<p style=\"margin:0cm 0cm 0pt;\">\n" +
                "\t<font color=\"#000000\"><span style=\"font-family:宋体;\">随着春天的脚步来临，爱心遍校园，温暖遍校园。湘仪学校继教师义务劳动和中学部爱心捐款活动之后，小学部</span><span style=\"font-family:Verdana;\"> “</span><span style=\"font-family:宋体;\">爱心捐赠零花钱</span><span style=\"font-family:Verdana;\"> </span><span style=\"font-family:宋体;\">关爱困难小伙伴</span><span style=\"font-family:Verdana;\">”</span><span style=\"font-family:宋体;\">捐赠活动也在各班级中热烈的开展起来。</span></font><font color=\"#000000\"><span style=\"font-family:Verdana;\"><br />\n" +
                "\t3</span><span style=\"font-family:宋体;\">月</span><span style=\"font-family:Verdana;\">18</span><span style=\"font-family:宋体;\">日升旗仪式上，各班代表将班级募捐所得善款投入的募捐箱，三个教研组组长也将老师们捐得的善款投入了募捐箱。所得善款整理之后将由学校大队部存入长沙市青少年基金会，用来帮助我们身边需要帮助的小伙伴。钱款有限，爱心无限，湘仪学校学雷锋活动将按照教育处计划陆续开展，所有的队员都在争当雷锋式的好少年！</span></font><span style=\"font-family:Verdana;\"></span></p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<img alt=\"\" src='http://static.ziguiw.com/upload/images/2013/0319/1363660053175.jpg' style=\"margin:10px;float:none;\" /></p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<span style=\"font-family:宋体;font-size:10.5pt;\"><font color=\"#000000\">各班代表将班级募捐所得善款投入的募捐箱</font></span></p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<span style=\"font-family:宋体;font-size:10.5pt;\"><font color=\"#000000\"><img alt=\"\" src=\"http://static.ziguiw.com/upload/images/2013/0319/1363660131769.jpg\" style=\"margin:10px;float:none;\" /> </font></span></p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<span style=\"font-family:宋体;font-size:10.5pt;\"><font color=\"#000000\"><span style=\"font-family:宋体;font-size:10.5pt;\"><font color=\"#000000\">三个教研组组长将老师们捐得的善款投入募捐箱</font></span></font></span></p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t&nbsp;</p>";
        public static void main(String [] args){
            System.out.println(FileUtils.replaceImgTag(cotent, "/data/upload", "http://static.ziguiw.com"));
        }


}
