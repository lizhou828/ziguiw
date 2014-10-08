<%@ page import="com.zigui.dao.ClassDao" %>
<%@ page import="com.zigui.dao.ParentDao" %>
<%@ page import="com.zigui.dao.UserBaseDao" %>
<%@ page import="com.zigui.domain.*" %>
<%@ page import="com.zigui.service.impl.ParentServiceImpl" %>
<%@ page import="com.zigui.service.impl.SchoolServiceImpl" %>
<%@ page import="com.zigui.service.impl.StudentServiceImpl" %>
<%@ page import="com.zigui.service.impl.TeacherServiceImpl" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="java.util.List" %>
<%
    String userName = request.getParameter("username");
    UserBaseDao userBaseDao = (UserBaseDao) BeanFactoryUtils.getBean("userBaseDao");
    UserBase userBase = userBaseDao.findUniqueBy(UserBase.class, "nickName", userName);

    ParentDao parentDao = (ParentDao) BeanFactoryUtils.getBean("parentDao");
    ClassDao classDao = (ClassDao) BeanFactoryUtils.getBean("classDao");
    ParentServiceImpl parentService =(ParentServiceImpl)BeanFactoryUtils.getBean("parentService");
    StudentServiceImpl studentService = (StudentServiceImpl)BeanFactoryUtils.getBean("studentService");
    TeacherServiceImpl teacherService = (TeacherServiceImpl)BeanFactoryUtils.getBean("teacherService");
    SchoolServiceImpl schoolService = (SchoolServiceImpl)BeanFactoryUtils.getBean("schoolService");

    if (userBase == null || userBase.getNickName() == null) {
        out.print("-1");
        return;
    }
    String password = request.getParameter("password");
    if (!userBase.getPassword().equals(DigestUtils.md5Hex(password))) {
        out.print("0");

    } else  {
        if(userBase.getType() == 1){
            School school = schoolService.getById(userBase.getForeignKey());
            out.print("sign=1"+"-userType="+userBase.getType()+"-name="+school.getSch_name()+"-xxId="+school.getXxID()+
            "-schoolName="+school.getSch_name());
        }
        if(userBase.getType() == 2){
            Teacher teacher = teacherService.getById(userBase.getForeignKey()) ;
            List<Clasz> classList = classDao.findAllClassByTeacherId(userBase.getForeignKey());
            StringBuffer classInfo = new StringBuffer();
            for(Clasz clasz : classList){
                classInfo.append("&bjId="+clasz.getBj_id()+"-className="+clasz.getBj_mcheng());
            }
            out.print("sign=1"+"-userType="+userBase.getType()+"-name="+teacher.getName()+
                    "-xxId="+teacher.getSchool().getXxID()+"-schoolName="+teacher.getSchool().getSch_name()+classInfo);
        }
        if(userBase.getType() == 3){
            Parent parent = parentService.getById(userBase.getForeignKey().intValue());
            List<Student> studentList = parentDao.findChildren(userBase.getNickName());
            StringBuffer studentInfo = new StringBuffer() ;
            for(Student stu:studentList){
                studentInfo.append("&studentName="+stu.getXs_xming()+"-xxId="+stu.getSchool().getXxID()+
                        "-schoolName="+stu.getSchool().getSch_name()+
                        "-bjId="+stu.getClasz().getBj_id()+"-className="+stu.getClasz().getBj_mcheng());
            }
            out.print("sign=1"+"-userType="+userBase.getType()+"-name="+parent.getPARENTS_NAME()+studentInfo);
        }
        if(userBase.getType() == 4){
            Student student = studentService.getById(userBase.getForeignKey().intValue());
            out.print("sign=1"+"-userType="+userBase.getType()+"-name="+student.getXs_xming()+
                    "-xxId="+student.getSchool().getXxID()+"-schoolName="+student.getSchool().getSch_name()+
                    "-bjId="+student.getClasz().getBj_id()+"-className="+student.getClasz().getBj_mcheng());
        }
        if(userBase.getType() == 0) {
           out.print("sign=1"+"-userType="+userBase.getType());
        }

    }
    return;
%>