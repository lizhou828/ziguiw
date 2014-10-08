package com.zigui.dao;

import com.zigui.domain.Exam;
import com.zigui.service.impl.DsisExamServiceImpl;
import com.zigui.utils.Page;
import org.junit.Test;

public class DsisExamServiceImplTest {

//    public List findAllBySQL(String schoolId){
//        return dsisExamDao.findAllBySQL(schoolId);
//    }

        @Test
        public static  void main(String [] args){
            DsisExamServiceImpl dsisExamService=new DsisExamServiceImpl();
            Page<Exam> examPage=dsisExamService.queryExam(1,10,2011001L,1872);
            System.out.println("该学校所有考试记录");
            for(int i=0;i<examPage.getList().size();i++){
                System.out.print("="+(examPage.getList().get(i)).getEXAM_NAME()+",");
            }

//            该学校所有考试记录
//                    =中考,=小考,=大考,=全校统考,=期末统考,=高一第一次月考,=c1104本周生物测试,=12月月考,=英语第九单元测试成绩,=C1102英语第九单元测试,


//        List list= new ArrayList();
//        list=dsisExamService.findAllBySQL("2011001")  ;
//      报错：  org.hibernate.QueryParameterException: Position beyond number of declared ordinal parameters. Remember that ordinal parameters are 1-based! Position: 1

        }




}
