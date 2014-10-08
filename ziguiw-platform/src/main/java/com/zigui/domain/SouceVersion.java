package com.zigui.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="souceVersion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SouceVersion {
	
		@Id 
		@GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid",strategy = "uuid")
		private String versionId;
		private String VName;
		private Long VOder;
		private Long state;
		@OneToMany(mappedBy="souceVersion")
		private Set<CcczgInfoSouce> ccczgInfoSouces = new HashSet<CcczgInfoSouce>();

		// Constructors

		/** default constructor */
		public SouceVersion() {
		}

		/** full constructor */
		public SouceVersion(String VName, Long VOder, Long state,
				Set ccczgInfoSouces) {
			this.VName = VName;
			this.VOder = VOder;
			this.state = state;
			this.ccczgInfoSouces = ccczgInfoSouces;
		}

	

		public String getVersionId() {
			return versionId;
		}

		public void setVersionId(String versionId) {
			this.versionId = versionId;
		}

		public String getVName() {
			return this.VName;
		}

		public void setVName(String VName) {
			this.VName = VName;
		}

		public Long getVOder() {
			return this.VOder;
		}

		public void setVOder(Long VOder) {
			this.VOder = VOder;
		}

		public Long getState() {
			return this.state;
		}

		public void setState(Long state) {
			this.state = state;
		}

		public Set getCcczgInfoSouces() {
			return this.ccczgInfoSouces;
		}

		public void setCcczgInfoSouces(Set<CcczgInfoSouce> ccczgInfoSouces) {
			this.ccczgInfoSouces = ccczgInfoSouces;
		}

		
}
