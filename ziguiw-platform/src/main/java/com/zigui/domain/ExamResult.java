package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table STUDENT_EXAMRESULT(
 * Cj_ID		integer,
 * Nj_id		INTEGER,
 * Bj_id		INTEGER,
 * Ks_id		integer,
 * Xs_id		integer,
 * RESULTS_ONE			NUMBER(9,2),
 * RESULTS_TWO			NUMBER(9,2),
 * RESULTS_THREE			NUMBER(9,2),
 * RESULTS_FOUR			NUMBER(9,2),
 * RESULTS_FIVE			NUMBER(9,2),
 * RESULTS_SIX			NUMBER(9,2),
 * RESULTS_SEVEN			NUMBER(9,2),
 * RESULTS_EIGHT			NUMBER(9,2),
 * RESULTS_NINE			NUMBER(9,2),
 * RESULTS_TEN			NUMBER(9,2),
 * RESULTS_ELEVEN			NUMBER(9,2),
 * RESULTS_TWELVE			NUMBER(9,2),
 * RESULTS_THIRTEEN			NUMBER(9,2),
 * RESULTS_FOURTEEN			NUMBER(9,2),
 * RESULTS_FIFTEEN			NUMBER(9,2),
 * RESULTS_SIXTEEN			NUMBER(9,2),
 * RESULTS_SEVENTEEN			NUMBER(9,2),
 * RESULTS_Eighth			NUMBER(9,2),
 * RESULTS_NINETEEN			NUMBER(9,2),
 * RESULTS_TWENTY			NUMBER(9,2),
 * TOTAL_SCORE			NUMBER(9,2),
 * TOTAL_SCORE_ONE			NUMBER(9,2),
 * TOTAL_SCORE_TWO			NUMBER(9,2),
 * AVERAGE_SCORE			NUMBER(9,2),
 * XxID		varchar2(50)
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="STUDENT_EXAMRESULT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamResult implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int Cj_ID;
    private int Nj_id;
    private int Bj_id;
    private int Ks_id;
    private int Xs_id;
    private float RESULTS_ONE;
    private float RESULTS_TWO;
    private float RESULTS_THREE;
    private float RESULTS_FOUR;
    private float RESULTS_FIVE;
    private float RESULTS_SIX;
    private float RESULTS_SEVEN;
    private float RESULTS_EIGHT;
    private float RESULTS_NINE;
    private float RESULTS_TEN;
    private float RESULTS_ELEVEN;
    private float RESULTS_TWELVE;
    private float RESULTS_THIRTEEN;
    private float RESULTS_FOURTEEN;
    private float RESULTS_FIFTEEN;
    private float RESULTS_SIXTEEN;
    private float RESULTS_SEVENTEEN;
    private float RESULTS_Eighth;
    private float RESULTS_NINETEEN;
    private float RESULTS_TWENTY;
    private float TOTAL_SCORE;
    private float TOTAL_SCORE_ONE;
    private float TOTAL_SCORE_TWO;
    private float AVERAGE_SCORE;
    private String XxID;

    private String EXAM_NAME;    //考试名称
    private String TERM_NAME;    //考试学期
    private String EXAM_DATE;    //考试日期

    public String getEXAM_NAME() {
        return EXAM_NAME;
    }

    public void setEXAM_NAME(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
    }

    public String getTERM_NAME() {
        return TERM_NAME;
    }

    public void setTERM_NAME(String TERM_NAME) {
        this.TERM_NAME = TERM_NAME;
    }

    public String getEXAM_DATE() {
        return EXAM_DATE;
    }

    public void setEXAM_DATE(String EXAM_DATE) {
        this.EXAM_DATE = EXAM_DATE;
    }

    public int getCj_ID() {
        return Cj_ID;
    }
    public void setCj_ID(int cj_ID) {
        Cj_ID = cj_ID;
    }
    public int getNj_id() {
        return Nj_id;
    }
    public void setNj_id(int nj_id) {
        Nj_id = nj_id;
    }
    public int getBj_id() {
        return Bj_id;
    }
    public void setBj_id(int bj_id) {
        Bj_id = bj_id;
    }
    public int getKs_id() {
        return Ks_id;
    }
    public void setKs_id(int ks_id) {
        Ks_id = ks_id;
    }
    public int getXs_id() {
        return Xs_id;
    }
    public void setXs_id(int xs_id) {
        Xs_id = xs_id;
    }
    public float getRESULTS_ONE() {
        return RESULTS_ONE;
    }
    public void setRESULTS_ONE(float rESULTS_ONE) {
        RESULTS_ONE = rESULTS_ONE;
    }
    public float getRESULTS_TWO() {
        return RESULTS_TWO;
    }
    public void setRESULTS_TWO(float rESULTS_TWO) {
        RESULTS_TWO = rESULTS_TWO;
    }
    public float getRESULTS_THREE() {
        return RESULTS_THREE;
    }
    public void setRESULTS_THREE(float rESULTS_THREE) {
        RESULTS_THREE = rESULTS_THREE;
    }
    public float getRESULTS_FOUR() {
        return RESULTS_FOUR;
    }
    public void setRESULTS_FOUR(float rESULTS_FOUR) {
        RESULTS_FOUR = rESULTS_FOUR;
    }
    public float getRESULTS_FIVE() {
        return RESULTS_FIVE;
    }
    public void setRESULTS_FIVE(float rESULTS_FIVE) {
        RESULTS_FIVE = rESULTS_FIVE;
    }
    public float getRESULTS_SIX() {
        return RESULTS_SIX;
    }
    public void setRESULTS_SIX(float rESULTS_SIX) {
        RESULTS_SIX = rESULTS_SIX;
    }
    public float getRESULTS_SEVEN() {
        return RESULTS_SEVEN;
    }
    public void setRESULTS_SEVEN(float rESULTS_SEVEN) {
        RESULTS_SEVEN = rESULTS_SEVEN;
    }
    public float getRESULTS_EIGHT() {
        return RESULTS_EIGHT;
    }
    public void setRESULTS_EIGHT(float rESULTS_EIGHT) {
        RESULTS_EIGHT = rESULTS_EIGHT;
    }
    public float getRESULTS_NINE() {
        return RESULTS_NINE;
    }
    public void setRESULTS_NINE(float rESULTS_NINE) {
        RESULTS_NINE = rESULTS_NINE;
    }
    public float getRESULTS_TEN() {
        return RESULTS_TEN;
    }
    public void setRESULTS_TEN(float rESULTS_TEN) {
        RESULTS_TEN = rESULTS_TEN;
    }
    public float getRESULTS_ELEVEN() {
        return RESULTS_ELEVEN;
    }
    public void setRESULTS_ELEVEN(float rESULTS_ELEVEN) {
        RESULTS_ELEVEN = rESULTS_ELEVEN;
    }
    public float getRESULTS_TWELVE() {
        return RESULTS_TWELVE;
    }
    public void setRESULTS_TWELVE(float rESULTS_TWELVE) {
        RESULTS_TWELVE = rESULTS_TWELVE;
    }
    public float getRESULTS_THIRTEEN() {
        return RESULTS_THIRTEEN;
    }
    public void setRESULTS_THIRTEEN(float rESULTS_THIRTEEN) {
        RESULTS_THIRTEEN = rESULTS_THIRTEEN;
    }
    public float getRESULTS_FOURTEEN() {
        return RESULTS_FOURTEEN;
    }
    public void setRESULTS_FOURTEEN(float rESULTS_FOURTEEN) {
        RESULTS_FOURTEEN = rESULTS_FOURTEEN;
    }
    public float getRESULTS_FIFTEEN() {
        return RESULTS_FIFTEEN;
    }
    public void setRESULTS_FIFTEEN(float rESULTS_FIFTEEN) {
        RESULTS_FIFTEEN = rESULTS_FIFTEEN;
    }
    public float getRESULTS_SIXTEEN() {
        return RESULTS_SIXTEEN;
    }
    public void setRESULTS_SIXTEEN(float rESULTS_SIXTEEN) {
        RESULTS_SIXTEEN = rESULTS_SIXTEEN;
    }
    public float getRESULTS_SEVENTEEN() {
        return RESULTS_SEVENTEEN;
    }
    public void setRESULTS_SEVENTEEN(float rESULTS_SEVENTEEN) {
        RESULTS_SEVENTEEN = rESULTS_SEVENTEEN;
    }
    public float getRESULTS_Eighth() {
        return RESULTS_Eighth;
    }
    public void setRESULTS_Eighth(float rESULTS_Eighth) {
        RESULTS_Eighth = rESULTS_Eighth;
    }
    public float getRESULTS_NINETEEN() {
        return RESULTS_NINETEEN;
    }
    public void setRESULTS_NINETEEN(float rESULTS_NINETEEN) {
        RESULTS_NINETEEN = rESULTS_NINETEEN;
    }
    public float getRESULTS_TWENTY() {
        return RESULTS_TWENTY;
    }
    public void setRESULTS_TWENTY(float rESULTS_TWENTY) {
        RESULTS_TWENTY = rESULTS_TWENTY;
    }
    public float getTOTAL_SCORE() {
        return TOTAL_SCORE;
    }
    public void setTOTAL_SCORE(float tOTAL_SCORE) {
        TOTAL_SCORE = tOTAL_SCORE;
    }
    public float getTOTAL_SCORE_ONE() {
        return TOTAL_SCORE_ONE;
    }
    public void setTOTAL_SCORE_ONE(float tOTAL_SCORE_ONE) {
        TOTAL_SCORE_ONE = tOTAL_SCORE_ONE;
    }
    public float getTOTAL_SCORE_TWO() {
        return TOTAL_SCORE_TWO;
    }
    public void setTOTAL_SCORE_TWO(float tOTAL_SCORE_TWO) {
        TOTAL_SCORE_TWO = tOTAL_SCORE_TWO;
    }
    public float getAVERAGE_SCORE() {
        return AVERAGE_SCORE;
    }
    public void setAVERAGE_SCORE(float aVERAGE_SCORE) {
        AVERAGE_SCORE = aVERAGE_SCORE;
    }
    public String getXxID() {
        return XxID;
    }
    public void setXxID(String xxID) {
        XxID = xxID;
    }



}
