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
@Table(name="sourceType")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceType {
		@Id 
		@GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid",strategy = "uuid")
		private String typeId;
		private String typeName;
		private String suffix;
		private Long typeOrder;
		private Long state;
		
		@OneToMany(mappedBy="sourceType")
		private Set<CcczgInfoSouce> ccczgInfoSouces = new HashSet<CcczgInfoSouce>();	


		// Constructors

		/** default constructor */
		public SourceType() {
		}

		/** full constructor */
		public SourceType(String typeName, String suffix, Long typeOrder,
				Long state, Set ccczgInfoSouces) {
			this.typeName = typeName;
			this.suffix = suffix;
			this.typeOrder = typeOrder;
			this.state = state;
			this.ccczgInfoSouces = ccczgInfoSouces;
		}

		// Property accessors
		

		public String getTypeName() {
			return this.typeName;
		}

		public void setCcczgInfoSouces(Set<CcczgInfoSouce> ccczgInfoSouces) {
			this.ccczgInfoSouces = ccczgInfoSouces;
		}

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getSuffix() {
			return this.suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public Long getTypeOrder() {
			return this.typeOrder;
		}

		public void setTypeOrder(Long typeOrder) {
			this.typeOrder = typeOrder;
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

		
}
