package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="grow_archives")
@SequenceGenerator(
    name="GROW_ARCHIVES_SEQ",
    sequenceName="grow_archives_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GrowArchives implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8159645726498678328L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROW_ARCHIVES_SEQ")
	private long id;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="hobby")
	private String hobby;       //爱好
	
	@Column(name="specialty")
	private String specialty;        //特长
	
	@Column(name="ideal")
	private String ideal;             //理想
	
	@Column(name="famous")
	private String famous;            //兴趣貌似不是这么写的吧，应该是interests
	
	@Column(name="parents_send_word")
	private String parentsSendWord;     //家长寄语
	
	@Column(name="teacher_send_word")
	private String teacherSendWord;     //老师寄语
	
	@Column(name="honor_and_works")
	private String honorAndWorks;       //荣誉、成就
	
	@Column(name="favourite_work")
	private String favouriteWork;       //最喜爱的工作
	
	@Column(name="reading_ability_teacher")
	private String readingAbilityTeacher;    //阅读能力-师评
	
	@Column(name="reading_ability_self")
	private String readingAbilitySelf;      //阅读能力-自评
	
	@Column(name="reading_ability_parents")
	private String readingAbilityParents;    //阅读能力-家长评价
	
	@Column(name="expression_ability_teacher")
	private String expressionAbilityTeacher;  //表达能力
	
	@Column(name="expression_ability_self")
	private String expressionAbilitySelf;
	
	@Column(name="expression_ability_parents")
	private String expressionAbilityParents;
	
	@Column(name="writing_ability_teacher")
	private String writingAbilityTeacher;       //写作能力
	
	@Column(name="writing_ability_self")
	private String writingAbilitySelf;
	
	@Column(name="writing_ability_parents")
	private String writingAbilityParents;
	
	@Column(name="cooperation_ability_teacher")
	private String cooperationAbilityTeacher;     //团队协作能力
	
	@Column(name="cooperation_ability_self")
	private String cooperationAbilitySelf;
	
	@Column(name="cooperation_ability_parents")
	private String cooperationAbilityParents;
	
	@Column(name="art_ability_teacher")
	private String artAbilityTeacher;         //艺术能力
	
	@Column(name="art_ability_self")
	private String artAbilitySelf;
	
	@Column(name="art_ability_parents")
	private String artAbilityParents;
	
	@Column(name="sport_ability_teacher")
	private String sportAbilityTeacher;       //运动能力
	
	@Column(name="sport_ability_self")
	private String sportAbilitySelf;
	
	@Column(name="sport_ability_parents")
	private String sportAbilityParents;
	
	@Column(name="daily_ability_teacher")
	private String dailyAbilityTeacher;       //日常表现
	
	@Column(name="daily_ability_self")
	private String dailyAbilitySelf;
	
	@Column(name="daily_ability_parents")
	private String dailyAbilityParents;
	
	@Column(name="reserve1")
	private String reserve1;
	
	@Column(name="reserve2")
	private String reserve2;
	
	@Column(name="state")
	private int state =1;
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="creator_nick")
	private String creatorNick;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="last_modify_id")
	private long lastModifyId;
	
	@Column(name="last_modify_nick")
	private String lastModifyNick;
	
	@Column(name="last_modify_time")
	private Date lastModifyTime;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="student_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Student student;

    @Column(name="blood_type")
    private Integer bloodType;           //血型

    @Column(name="allergic_history")
    private String allergicHistory;     //过敏史

    @Column(name="past_medical_history")
    private String pastMedicalHistory;  //既往史

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getIdeal() {
		return ideal;
	}

	public void setIdeal(String ideal) {
		this.ideal = ideal;
	}

	public String getFamous() {
		return famous;
	}

	public void setFamous(String famous) {
		this.famous = famous;
	}

	public String getParentsSendWord() {
		return parentsSendWord;
	}

	public void setParentsSendWord(String parentsSendWord) {
		this.parentsSendWord = parentsSendWord;
	}

	public String getTeacherSendWord() {
		return teacherSendWord;
	}

	public void setTeacherSendWord(String teacherSendWord) {
		this.teacherSendWord = teacherSendWord;
	}

	public String getHonorAndWorks() {
		return honorAndWorks;
	}

	public void setHonorAndWorks(String honorAndWorks) {
		this.honorAndWorks = honorAndWorks;
	}

	public String getFavouriteWork() {
		return favouriteWork;
	}

	public void setFavouriteWork(String favouriteWork) {
		this.favouriteWork = favouriteWork;
	}

	public String getReadingAbilityTeacher() {
		return readingAbilityTeacher;
	}

	public void setReadingAbilityTeacher(String readingAbilityTeacher) {
		this.readingAbilityTeacher = readingAbilityTeacher;
	}

	public String getReadingAbilitySelf() {
		return readingAbilitySelf;
	}

	public void setReadingAbilitySelf(String readingAbilitySelf) {
		this.readingAbilitySelf = readingAbilitySelf;
	}

	public String getReadingAbilityParents() {
		return readingAbilityParents;
	}

	public void setReadingAbilityParents(String readingAbilityParents) {
		this.readingAbilityParents = readingAbilityParents;
	}

	public String getExpressionAbilityTeacher() {
		return expressionAbilityTeacher;
	}

	public void setExpressionAbilityTeacher(String expressionAbilityTeacher) {
		this.expressionAbilityTeacher = expressionAbilityTeacher;
	}

	public String getExpressionAbilitySelf() {
		return expressionAbilitySelf;
	}

	public void setExpressionAbilitySelf(String expressionAbilitySelf) {
		this.expressionAbilitySelf = expressionAbilitySelf;
	}

	public String getExpressionAbilityParents() {
		return expressionAbilityParents;
	}

	public void setExpressionAbilityParents(String expressionAbilityParents) {
		this.expressionAbilityParents = expressionAbilityParents;
	}

	public String getWritingAbilityTeacher() {
		return writingAbilityTeacher;
	}

	public void setWritingAbilityTeacher(String writingAbilityTeacher) {
		this.writingAbilityTeacher = writingAbilityTeacher;
	}

	public String getWritingAbilitySelf() {
		return writingAbilitySelf;
	}

	public void setWritingAbilitySelf(String writingAbilitySelf) {
		this.writingAbilitySelf = writingAbilitySelf;
	}

	public String getWritingAbilityParents() {
		return writingAbilityParents;
	}

	public void setWritingAbilityParents(String writingAbilityParents) {
		this.writingAbilityParents = writingAbilityParents;
	}

	public String getCooperationAbilityTeacher() {
		return cooperationAbilityTeacher;
	}

	public void setCooperationAbilityTeacher(String cooperationAbilityTeacher) {
		this.cooperationAbilityTeacher = cooperationAbilityTeacher;
	}

	public String getCooperationAbilitySelf() {
		return cooperationAbilitySelf;
	}

	public void setCooperationAbilitySelf(String cooperationAbilitySelf) {
		this.cooperationAbilitySelf = cooperationAbilitySelf;
	}

	public String getCooperationAbilityParents() {
		return cooperationAbilityParents;
	}

	public void setCooperationAbilityParents(String cooperationAbilityParents) {
		this.cooperationAbilityParents = cooperationAbilityParents;
	}

	public String getArtAbilityTeacher() {
		return artAbilityTeacher;
	}

	public void setArtAbilityTeacher(String artAbilityTeacher) {
		this.artAbilityTeacher = artAbilityTeacher;
	}

	public String getArtAbilitySelf() {
		return artAbilitySelf;
	}

	public void setArtAbilitySelf(String artAbilitySelf) {
		this.artAbilitySelf = artAbilitySelf;
	}

	public String getArtAbilityParents() {
		return artAbilityParents;
	}

	public void setArtAbilityParents(String artAbilityParents) {
		this.artAbilityParents = artAbilityParents;
	}

	public String getSportAbilityTeacher() {
		return sportAbilityTeacher;
	}

	public void setSportAbilityTeacher(String sportAbilityTeacher) {
		this.sportAbilityTeacher = sportAbilityTeacher;
	}

	public String getSportAbilitySelf() {
		return sportAbilitySelf;
	}

	public void setSportAbilitySelf(String sportAbilitySelf) {
		this.sportAbilitySelf = sportAbilitySelf;
	}

	public String getSportAbilityParents() {
		return sportAbilityParents;
	}

	public void setSportAbilityParents(String sportAbilityParents) {
		this.sportAbilityParents = sportAbilityParents;
	}

	public String getDailyAbilityTeacher() {
		return dailyAbilityTeacher;
	}

	public void setDailyAbilityTeacher(String dailyAbilityTeacher) {
		this.dailyAbilityTeacher = dailyAbilityTeacher;
	}

	public String getDailyAbilitySelf() {
		return dailyAbilitySelf;
	}

	public void setDailyAbilitySelf(String dailyAbilitySelf) {
		this.dailyAbilitySelf = dailyAbilitySelf;
	}

	public String getDailyAbilityParents() {
		return dailyAbilityParents;
	}

	public void setDailyAbilityParents(String dailyAbilityParents) {
		this.dailyAbilityParents = dailyAbilityParents;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorNick() {
		return creatorNick;
	}

	public void setCreatorNick(String creatorNick) {
		this.creatorNick = creatorNick;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getLastModifyId() {
		return lastModifyId;
	}

	public void setLastModifyId(long lastModifyId) {
		this.lastModifyId = lastModifyId;
	}

	public String getLastModifyNick() {
		return lastModifyNick;
	}

	public void setLastModifyNick(String lastModifyNick) {
		this.lastModifyNick = lastModifyNick;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory;
    }

    public String getPastMedicalHistory() {
        return pastMedicalHistory;
    }

    public void setPastMedicalHistory(String pastMedicalHistory) {
        this.pastMedicalHistory = pastMedicalHistory;
    }
}
