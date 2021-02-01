package com.qa.persistence.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FSealDTO {
	
	private Long Id;
	
	private String name;
	
	private Double weight;

	
	public FSealDTO() {
		super();
	}

	public FSealDTO(Long id, String name, Double weight) {
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
	
	

}
