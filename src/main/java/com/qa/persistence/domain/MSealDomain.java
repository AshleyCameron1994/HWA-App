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

	@OneToMany(mappedBy = "male", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FSealDomain> dogList;

	public MSealDomain() {
		super();
		
	}

	public MSealDomain(Long id, String name, List<FSealDomain> dogList) {
		super();
		Id = id;
		this.name = name;
		this.dogList = dogList;
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

	public List<FSealDomain> getfsealList() {
		return dogList;
	}

	public void setDogList(List<FSealDomain> dogList) {
		this.fsealList = fsealList;
	}
		
}
