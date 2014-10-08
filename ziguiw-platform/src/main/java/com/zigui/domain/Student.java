package com.zigui.domain;

import com.zigui.dao.CommonDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.utils.BeanFactoryUtils;
import com.zigui.utils.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * create table T_student(
 * Xs_id		integer,
 * Xj_bhao			varchar(30),
 * Xs_xming			varchar(30),
 * Birthplace			VARCHAR(50),
 * PoliticalFace			VARCHAR2(20),
 * IDCard			VARCHAR2(18),
 * AccountPlace			VARCHAR2(100),
 * sex			CHAR(2),
 * birthday			DATE,
 * Bj_ID		integer,
 * Accommodation			CHAR(1),
 * DateintegeroSch			DATE,
 * mzhu			varchar2(10),
 * Hobby			varchar2(300),
 * personalphoto			varchar2(50),
 * homephone			varchar2(50),
 * zip			varchar2(6),
 * homeaddress			varchar2(100),
 * otherlinks			varchar2(100),
 * Healthstate			varchar2(2000),
 * XxID		varchar2(50),
 * groupID		integer,
 * banGanBuID			varchar2(10),
 * ybhao			varchar2(50)
 * )
 * @author bertyang
 *
 */

@Entity
@Table(name="T_student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class
        Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Xs_id;
    private String Xs_xming;
    private String Xj_bhao;
    private String Birthplace;
    private String PoliticalFace;
    private String IDCard;  //学生一卡通卡号
    private String AccountPlace;
    private String sex;
    private Date birthday;
    @Column(name="bj_id")
    private String Bj_ID;

    private String Accommodation;
    private Date dateToSchool; //入校日期
    private String personalPhoto;  //个人照片
    private String mzhu;
    private String Hobby;
    private String homephone;
    private String zip;
    private String homeaddress;
    private String otherlinks;
    private String Healthstate; //健康状况


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name = "XxID", nullable = false, referencedColumnName="XxID")
    private School school;

    private Long groupID;
    private String banGanBuID;    //班干部id
    private String ybHAO;   //医保号
    @Column(name="status")
    private String status;  // 学生在校状态，1：正常，2:退学，3：休学，4：转学

    public String getStatus() {
        if("".equals(status) || status == null ){
           return "";
        }else if(Integer.parseInt(status) == Constant.STUDENT_ARCHIVE_NORMAL){
            return "正常";
        }else if(Integer.parseInt(status) == Constant.STUDENT_ARCHIVE_QUIT){
            return "退学";
        }else if(Integer.parseInt(status) == Constant.STUDENT_ARCHIVE_BREAK){
            return "休学";
        }else if(Integer.parseInt(status) == Constant.STUDENT_ARCHIVE_TURN){
            return "转学";
        }else if(Integer.parseInt(status) == Constant.STUDENT_ARCHIVE_RETURN){
            return "复学";
        }
        return "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYbHAO() {
        return ybHAO;
    }

    public void setYbHAO(String ybHAO) {
        this.ybHAO = ybHAO;
    }

    public int getXs_id() {
        return Xs_id;
    }
    public void setXs_id(int xs_id) {
        Xs_id = xs_id;
    }
    public String getXs_xming() {
        return Xs_xming;
    }
    public void setXs_xming(String xs_xming) {
        Xs_xming = xs_xming;
    }
    public String getXj_bhao() {
        return Xj_bhao;
    }
    public void setXj_bhao(String xj_bhao) {
        Xj_bhao = xj_bhao;
    }
    public String getBirthplace() {
        return Birthplace;
    }
    public void setBirthplace(String birthplace) {
        Birthplace = birthplace;
    }
    public String getPoliticalFace() {
        return PoliticalFace;
    }
    public void setPoliticalFace(String politicalFace) {
        PoliticalFace = politicalFace;
    }
    public String getIDCard() {
        return IDCard;
    }
    public void setIDCard(String iDCard) {
        IDCard = iDCard;
    }
    public String getAccountPlace() {
        return AccountPlace;
    }
    public void setAccountPlace(String accountPlace) {
        AccountPlace = accountPlace;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getBj_ID() {
        return Bj_ID;
    }
    public void setBj_ID(String bj_ID) {
        Bj_ID = bj_ID;
    }
    public String getAccommodation() {
        return Accommodation;
    }
    public void setAccommodation(String accommodation) {
        Accommodation = accommodation;
    }
    //	public String getDateIntoSch() {
//		return DateIntoSch;
//	}
//	public void setDateIntoSch(String dateIntoSch) {
//		DateIntoSch = dateIntoSch;
//	}
    public String getMzhu() {
        return mzhu;
    }
    public void setMzhu(String mzhu) {
        this.mzhu = mzhu;
    }
    public String getHobby() {
        return Hobby;
    }
    public void setHobby(String hobby) {
        Hobby = hobby;
    }
    public String getHomephone() {
        return homephone;
    }
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getHomeaddress() {
        return homeaddress;
    }
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }
    public String getOtherlinks() {
        return otherlinks;
    }
    public void setOtherlinks(String otherlinks) {
        this.otherlinks = otherlinks;
    }
    public String getHealthstate() {
        return Healthstate;
    }
    public void setHealthstate(String healthstate) {
        Healthstate = healthstate;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public Long getGroupID() {
        return groupID;
    }
    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }
    public String getBanGanBuID() {
        return banGanBuID;
    }
    public void setBanGanBuID(String banGanBuID) {
        this.banGanBuID = banGanBuID;
    }

    public Date getDateToSchool() {
        return dateToSchool;
    }

    public void setDateToSchool(Date dateToSchool) {
        this.dateToSchool = dateToSchool;
    }

    public String getPersonalPhoto() {
        return personalPhoto;
    }

    public void setPersonalPhoto(String personalPhoto) {
        this.personalPhoto = personalPhoto;
    }

    public UserBase getUser() {
        UserBaseDao userBaseDao = (UserBaseDao) BeanFactoryUtils.getBean("userBaseDao");
        return (UserBase)userBaseDao.find("from UserBase where type = 4 and foreignKey = " + Xs_id, new Object[0]).get(0); // or however you load the Bar
//		return user;
    }

    public Clasz getClasz() {
        CommonDao commonDao = (CommonDao) BeanFactoryUtils.getBean("commonDao");
        System.out.println(commonDao);
        List<Object> tmp = commonDao.find("from Clasz where Bj_id = " + Bj_ID, new Object[0]); // or however you load the Bar
        if(CollectionUtils.isNotEmpty(tmp)){
            return (Clasz)tmp.get(0);
        }else{
            return null;
        }
//		return user;
    }

    @Override
    public String toString() {
        return "Student [Xs_id=" + Xs_id + ", Xs_xming=" + Xs_xming
                + ", Xj_bhao=" + Xj_bhao + ", Birthplace=" + Birthplace
                + ", PoliticalFace=" + PoliticalFace + ", IDCard=" + IDCard
                + ", AccountPlace=" + AccountPlace + ", birthday=" + birthday
                + ", Bj_ID=" + Bj_ID + ", Accommodation=" + Accommodation
                + ", mzhu=" + mzhu + ", Hobby=" + Hobby + ", homephone="
                + homephone + ", zip=" + zip + ", homeaddress=" + homeaddress
                + ", otherlinks=" + otherlinks + ", Healthstate=" + Healthstate
                + ", school=" + school + ", groupID=" + groupID
                + ", banGanBuID=" + banGanBuID +",status="+ status +"]";
    }


}
