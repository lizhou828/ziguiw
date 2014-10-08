package controllers;

import com.arj.ziguiw.common.PayState;
import models.AnnualFee;
import models.Clazz;
import models.Student;
import play.data.binding.Binder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-26
 * Time: A.M.10:27
 * 学生年费管理
 */
public class AnnualFees extends Application{

    public static void queryList(Integer bjId, Long xsId){
        List<Student> studentList = new ArrayList<Student>();
        if(xsId == 0){
            studentList = Clazz.findStudentByBjId(bjId);
        }
        if(xsId > 0){
            Student student = Student.findById(xsId);
            studentList.add(student);
        }
        List<AnnualFee>  annualFeeList = getAnnualFeeList(studentList);
        render(annualFeeList);
    }

    public static void create(AnnualFee object,Integer bjId,Integer xsId){
        List<AnnualFee> objects = getObjects(object,bjId,xsId);
        for(AnnualFee obj : objects){
            validation.valid(obj);
            obj.create();
        }
        flash.success("创建成功!");
        String url2 =  String.format("/%s", "AnnualFees/list");
        String url3 =  String.format("/%s", "AnnualFees/blank");
        if (params.get("_save") != null) {
            redirect(url2);
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(url3);
        }
    }

    public static void save(Long id ){
        AnnualFee object = AnnualFee.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("AnnualFees/blank.html", object);
        }
        object.save();
        flash.success("保存成功!");
        String saveUrl = String.format("/%s?id=%s", "AnnualFees/show", object.id);
        String url =  String.format("/%s", "AnnualFees/list");
        if (params.get("_save") != null) {
            redirect(url);
        }
        redirect(saveUrl);
    }

    private static List<AnnualFee> getAnnualFeeList(List<Student> studentList) {
        List<AnnualFee> annualFeeList = new ArrayList<AnnualFee>();
        for(Student student : studentList){
            List<AnnualFee> list = AnnualFee.findAllByXsId(student.xsId);
            for(AnnualFee annualFee : list){
                annualFeeList.add(annualFee);
            }
       }
        return annualFeeList;
    }

    private static List<AnnualFee> getObjects(AnnualFee annualFee, Integer bjId, Integer xsId) {
        List<AnnualFee> annualFeeList = new ArrayList<AnnualFee>();
        Date payDate;
        if(xsId == 0){
            List<Student> studentList = Clazz.findStudentByBjId(bjId);
            AnnualFee annualFee1;
            if(studentList.size() != 0){
                for (Student aStudentList : studentList) {
                    payDate = annualFee.payDate == null ? new Date() : annualFee.payDate;
                    annualFee1 = new AnnualFee(createOrderNo(), annualFee.payMethod, annualFee.price, annualFee.state,
                            annualFee.type, payDate, aStudentList);
                    annualFeeList.add(annualFee1);
                }
            }
        }else {
            annualFee.student = Student.findById(xsId.longValue());
            annualFee.orderNo = createOrderNo();
            annualFee.state = PayState.PAYED;
            payDate = annualFee.payDate == null ? new Date() : annualFee.payDate;
            annualFee.payDate = payDate;
            annualFeeList.add(annualFee);
        }
        return annualFeeList;

    }


    private static String createOrderNo() {
        String time = new SimpleDateFormat("yyyyMMdd-hhmmss-sss-").format(new Date());
        String randomNo = "";
        int inValue = (int)(Math.random()*1000000 + 0);
        randomNo += inValue;
        return time+randomNo;
    }
}
