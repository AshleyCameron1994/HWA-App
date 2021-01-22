package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.sun.istack.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class MSealDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	private String name;
	
	private Double weight;

	@OneToMany(mappedBy = "male", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FSealDomain> fsealList;

	public MSealDomain() {
		super();
		
	}

	public MSealDomain(Long id, String name, List<FSealDomain> fsealList) {
		super();
		Id = id;
		this.name = name;
		this.fsealList = fsealList;
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

	public List<FSealDomain> getFsealList() {
		return fsealList;
	}

	public void setFsealList(List<FSealDomain> fsealList) {
		this.fsealList = fsealList;
	}
	
		
}
