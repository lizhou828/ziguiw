package com.zigui.service.impl;

import com.zigui.dao.SourceDao;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.CourseXml;
import com.zigui.service.SourceService;
import com.zigui.utils.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("sourceService")
public class SourceServiceImpl extends BaseServiceImpl<CcczgInfoSouce, String> implements SourceService{
	
	@Autowired
	private SourceDao sourceDao;

	public SourceDao getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
		super.setBaseDao(sourceDao);
	}




	public List<CcczgInfoSouce> getListBySubjectIdAndLeve(String subjectId,
			Integer leve, Integer len) throws BusinessException {
		try {
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
			String l_subjectId = StringUtils.trimToNull(subjectId);
			if (l_subjectId != null) {
				hql.append(" and t.souceSubject.subjectId='" + l_subjectId
						+ "' ");
			}
			if (leve != null) {
				hql.append(" and t.souceStugrade.leve=" + leve + " ");
			}
			hql.append(" order by t.createDate desc ");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	public List<CcczgInfoSouce> getListByTypeName(String typeName, Integer len)
			throws BusinessException {
		try {
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
			String l_typeName = StringUtils.trimToNull(typeName);
			if (l_typeName != null) {
				hql.append(" and t.sourceType.typeName='" + l_typeName + "' ");
			}
			hql.append(" order by t.createDate desc ");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	@SuppressWarnings("deprecation")
	public List<CcczgInfoSouce> getListByCreateDateOrderRecommend(Integer len)
			throws BusinessException {
		try {
			Date date = new Date();
			date.setMonth(new Date().getMonth() + 1);
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM ");
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
//			hql.append(" and createDate>to_date('" + sim.format(new Date())
//					+ "-01','yyyy-mm-dd')");
//			hql.append(" and createDate<to_date('" + sim.format(date)
//					+ "-01','yyyy-mm-dd')");
			hql.append(" order by t.recommend desc ");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	@SuppressWarnings("deprecation")
	public List<CcczgInfoSouce> getListByCreateDateOrderResourceDownnum(
			Integer len) throws BusinessException {
		try {
			Date date = new Date();
			date.setMonth(new Date().getMonth() + 1);
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM ");
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
//			hql.append(" and createDate>to_date('" + sim.format(new Date())
//					+ "-01','yyyy-mm-dd')");
//			hql.append(" and createDate<to_date('" + sim.format(date)
//					+ "-01','yyyy-mm-dd')");
			hql.append(" order by t.resourceDownnum desc ");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	public List<CcczgInfoSouce> getListByLeve(Integer leve, Integer len)
			throws BusinessException {
		try {
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
			if (leve != null) {
				hql.append(" and t.souceStugrade.leve=" + leve);
			}
			hql.append(" order by t.createDate desc");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	public List<CcczgInfoSouce> getListOrderByResourceDownnum(Integer leve,
			Integer len) throws BusinessException {
		try {
			StringBuffer hql = new StringBuffer(
					" from CcczgInfoSouce t where delsign = 0 and checkSign = 1 ");
			if (leve != null) {
				hql.append(" and t.souceStugrade.leve=" + leve);
			}
			hql.append(" order by t.resourceDownnum desc ");
			return sourceDao.getListBetMinAndMax(hql.toString(),len);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错!");
		}
	}

	public List<CcczgInfoSouce> getListByFlag() throws BusinessException {
		StringBuffer hql = new StringBuffer(
				" from CcczgInfoSouce t where flag=-1");

		hql.append(" order by t.createDate desc ");
		return this.getList(hql.toString());
	}

    @Override
    public List<CourseXml> parseXml(String xmlPath) throws Exception {
        StringBuffer sb = new StringBuffer();
        List<CourseXml> list =null;
        CourseXml cx = null;
        XmlPullParserFactory pullParserFactory=XmlPullParserFactory.newInstance();
        //获取XmlPullParser的实例
        XmlPullParser xmlPullParser=pullParserFactory.newPullParser();

        URL url = new URL(xmlPath);
        HttpURLConnection uc =  (HttpURLConnection)url.openConnection();
        uc.setRequestMethod("GET");
        uc.setConnectTimeout(7000);
        InputStream ips = null;
        if(uc.getResponseCode()==200){
            ips = uc.getInputStream();
        }

        xmlPullParser.setInput(ips,"UTF-8");
        //开始
        int eventType=xmlPullParser.getEventType();
        while(eventType!=XmlPullParser.END_DOCUMENT){
            String nodeName=xmlPullParser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    list = new ArrayList<CourseXml>();
                    break;
                case XmlPullParser.START_TAG:
                    if("exercise".equals(nodeName)){
                        cx = new CourseXml();
                        cx.setType(Integer.parseInt(xmlPullParser.getAttributeValue(6)));
                    }else if("txt".equals(nodeName)){
                        String scriptType = null;
                        if(xmlPullParser.getAttributeCount()>=6){
                            scriptType = xmlPullParser.getAttributeValue(5);
                        }


                        if("1".equals(scriptType)){
                            sb.append("<span style="+"'line-height: 10px; vertical-align:top'"+">"+xmlPullParser.nextText()+"</span>");
                            //cx.getList().add("<span style="+"'vertical-align:top'"+">"+xmlPullParser.nextText()+"</span>");
                        }else if("2".equals(scriptType)){
                            sb.append("<span style="+"'line-height: 10px; vertical-align:bottom'"+">"+xmlPullParser.nextText()+"</span>");
                            //cx.getList().add("<span style="+"'vertical-align:bottom'"+">"+xmlPullParser.nextText()+"</span>");
                        }
                        else{
                            sb.append(xmlPullParser.nextText());
                            //cx.getList().add(xmlPullParser.nextText());
                        }
                    }else if("enter".equals(nodeName)){
                        cx.getList().add(sb.toString());
                        cx.getList().add(xmlPullParser.nextText());
                        sb.delete(0, sb.length());
                    }else if("sound".equals(nodeName)){
                        String soundPath = xmlPullParser.nextText();
                        soundPath = xmlPath.substring(0, xmlPath.lastIndexOf("/")+1) + soundPath;
                        sb.append(" <a href='"+soundPath+"'>sound</a>");
                        //cx.getList().add(soundPath);
                    }else if("img".equals(nodeName)){
                        int width = Integer.parseInt(xmlPullParser.getAttributeValue(0));
                        int height = Integer.parseInt(xmlPullParser.getAttributeValue(1));
                        String imagePath = xmlPullParser.nextText();
                        imagePath = xmlPath.substring(0, xmlPath.lastIndexOf("/")+1) + imagePath;
                        sb.append("<image src='"+imagePath+"' width='"+width+"' height='"+height+"'/>");
                        //cx.getList().add("<image href='"+imagePath+"' width='"+width+"' height='"+height+"'/>");
                    }else if("FillBlank".equals(nodeName)){
                        sb.append("( )");
                        //cx.getList().add("( )");
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("exercise".equals(nodeName)){
                        list.add(cx);
                        cx = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = xmlPullParser.next();
        }



        /*while(eventType!=XmlPullParser.END_DOCUMENT){
            String nodeName=xmlPullParser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    list = new ArrayList<CourseXml>();
                    break;
                case XmlPullParser.START_TAG:
                    if("exercise".equals(nodeName)){
                        cx = new CourseXml();
                        cx.setType(Integer.parseInt(xmlPullParser.getAttributeValue(6)));
                    }else if("txt".equals(nodeName)){
                       String scriptType = null;
                        if(xmlPullParser.getAttributeCount()>=6){
                            scriptType = xmlPullParser.getAttributeValue(5);
                        }
                        if("1".equals(scriptType)){
                            cx.getList().add("<span style="+"'line-height: 10px; vertical-align:top'"+">"+xmlPullParser.nextText()+"</span>");
                        }else if("2".equals(scriptType)){
                            cx.getList().add("<span style="+"'line-height: 10px; vertical-align:bottom'"+">"+xmlPullParser.nextText()+"</span>");
                        }
                        else{
                            cx.getList().add(xmlPullParser.nextText());
                        }
                    }else if("enter".equals(nodeName)){
                        cx.getList().add(xmlPullParser.nextText());
                    }else if("sound".equals(nodeName)){
                        String soundPath = xmlPullParser.nextText();
                        soundPath = xmlPath.substring(0, xmlPath.lastIndexOf("/")+1) + soundPath;

                        cx.getList().add(soundPath);
                    }else if("img".equals(nodeName)){
                        int width = Integer.parseInt(xmlPullParser.getAttributeValue(0));
                        int height = Integer.parseInt(xmlPullParser.getAttributeValue(1));
                        String imagePath = xmlPullParser.nextText();
                        imagePath = xmlPath.substring(0, xmlPath.lastIndexOf("/")+1) + imagePath;
                        cx.getList().add("<image src='"+imagePath+"' width='"+width+"' height='"+height+"'/>");
                    }else if("FillBlank".equals(nodeName)){
                        cx.getList().add("( )");
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("exercise".equals(nodeName)){
                        list.add(cx);
                        cx = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = xmlPullParser.next();
        }*/

        return list;


    }



}