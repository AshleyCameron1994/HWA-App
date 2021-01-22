package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.MSealDomain;
import com.qa.persistence.dto.MSealDTO;
import com.qa.persistence.repos.MSealRepo;
import com.qa.utils.MyBeanUtils;




@Service
public class MSealService {
	
	
	private MSealRepo repo;
	private ModelMapper mapper;
	
	
	@Autowired
	public MSealService(MSealRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private MSealDTO maptoDTO(MSealDomain model) {
		return this.mapper.map(model, MSealDTO.class);
	}
	//create
	public MSealDTO create(MSealDomain model) {
		return maptoDTO(this.repo.save(model));
		
	}
	//read all
	public List<MSealDTO> readAll() {
		
		List<MSealDomain> houseList = repo.findAll();
		List<MSealDTO> houseListDTO = houseList.stream().map(this::maptoDTO).collect(Collectors.toList());
		
		return houseListDTO;
	}
	//read one dog from list
	public MSealDTO readOne(Long id) {
		return this.maptoDTO(this.repo.findById(id).orElseThrow());
	}
	//update
	public MSealDTO update(Long id, MSealDomain house) {
		MSealDomain updatedMSeal = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(house, updatedMSeal);
		
		return this.maptoDTO(this.repo.save(updatedMSeal));
	}
	
	//delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
	}
}
