package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table STU_ENCOURAGE_PUNISH(
 * ENCOURAGE_PUNISH_ID		integer,
 * Xs_id		integer,
 * ENC_PUNI_TYPE			integer,
 * ENC_PUNI_NAME			varchar2(100),
 * ENC_PUNI_DATE			varchar2(40),
 * REMARK			varchar2(200)
);
 * @author bertyang
 *
 */

@Entity
@Table(name="STU_ENCOURAGE_PUNISH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RwdPblshInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int ENCOURAGE_PUNISH_ID;
	
	private int Xs_id;
	
	private int ENC_PUNI_TYPE;
	
	private String ENC_PUNI_NAME;
	
	private String ENC_PUNI_DATE;
	
	private String remark;

}
