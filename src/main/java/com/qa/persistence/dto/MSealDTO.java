package com.qa.persistence.dto;


import java.util.ArrayList;
import java.util.List;

public class MSealDTO {
	
	private Long Id;
	
	private String name;
	
	private Double weight;
	
	private List<FSealDTO> fsealList = new ArrayList<>();

	public MSealDTO(Long id, String name, Double weight, List<FSealDTO> fsealList) {
		super();
		Id = id;
		this.name = name;
		this.weight = weight;
		this.fsealList = fsealList;
	}

	public MSealDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MSealDTO(Long id, String name, Double weight) {
		super();
		Id = id;
		this.name = name;
		this.weight = weight;
	}
	

	public MSealDTO(String name, Double weight) {
		super();
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

	public List<FSealDTO> getFsealList() {
		return fsealList;
	}

	public void setFsealList(List<FSealDTO> fsealList) {
		this.fsealList = fsealList;
	}
	
}
