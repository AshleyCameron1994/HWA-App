package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class FSealDomain {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long Id;
		
		@NotNull
		private String name;
		
		@NotNull
		private Double weight;
		
		@ManyToOne
		private MSealDomain male;

		public FSealDomain() {
			super();
			// TODO Auto-generated constructor stub
		}

//		public FSealDomain(Long id, String name, Double weight, MSealDomain male) {
//			super();
//			Id = id;
//			this.name = name;
//			this.weight = weight;
//			this.male = male;
//		}
		public FSealDomain(Long id, String name, Double weight) {
			super();
			Id = id;
			this.name = name;
			this.weight = weight;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getWeight() {
			return weight;
		}

		public void setWeight(Double weight) {
			this.weight = weight;
		}

		public MSealDomain getMale() {
			return male;
		}

		public void setMale(MSealDomain male) {
			this.male = male;
		}
		
		
}
