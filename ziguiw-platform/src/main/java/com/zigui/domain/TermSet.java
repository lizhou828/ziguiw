package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-30
 * Time: P.M 2:04
 */
@Entity
@Table(name="T_TERM_SET")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TermSet implements java.io.Serializable  {
    private static final long serialVersionUID = 1718996073837912000L;

    @Id
    @Column(name="term_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long termId;

    @Column(name="term_name")
    private String termName;

    @Column(name="term_year")
    private String termYear;

    @Column(name="term_type")
    private Long termType;

    @Column(name="is_current_term")
    private String isCurrentTerm;

    @Column(name="xxid")
    private String xxid;

    @Column(name="holiday_begin")
    private Date holidayBegin;

    @Column(name="holiday_end")
    private Date holidayEnd;

//    private Set TExaminations = new HashSet(0);

    public TermSet() {
        super();
    }

    public TermSet(String termName, String termYear, Long termType) {
        this.termName = termName;
        this.termYear = termYear;
        this.termType = termType;
    }
    public TermSet(String termName, String termYear, Long termType,
                    String isCurrentTerm, String xxid, Date holidayBegin,
                    Date holidayEnd, Set TExaminations) {
        this.termName = termName;
        this.termYear = termYear;
        this.termType = termType;
        this.isCurrentTerm = isCurrentTerm;
        this.xxid = xxid;
        this.holidayBegin = holidayBegin;
        this.holidayEnd = holidayEnd;
    }

    public Long getTermId() {
        return this.termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermYear() {
        return this.termYear;
    }

    public void setTermYear(String termYear) {
        this.termYear = termYear;
    }

    public Long getTermType() {
        return this.termType;
    }

    public void setTermType(Long termType) {
        this.termType = termType;
    }

    public String getIsCurrentTerm() {
        return this.isCurrentTerm;
    }

    public void setIsCurrentTerm(String isCurrentTerm) {
        this.isCurrentTerm = isCurrentTerm;
    }

    public String getXxid() {
        return this.xxid;
    }

    public void setXxid(String xxid) {
        this.xxid = xxid;
    }

    public Date getHolidayBegin() {
        return this.holidayBegin;
    }

    public void setHolidayBegin(Date holidayBegin) {
        this.holidayBegin = holidayBegin;
    }

    public Date getHolidayEnd() {
        return this.holidayEnd;
    }

    public void setHolidayEnd(Date holidayEnd) {
        this.holidayEnd = holidayEnd;
    }

//    @SuppressWarnings("unchecked")
//    public Set getTExaminations() {
//        return this.TExaminations;
//    }
//
//    @SuppressWarnings("unchecked")
//    public void setTExaminations(Set TExaminations) {
//        this.TExaminations = TExaminations;
//    }


}
