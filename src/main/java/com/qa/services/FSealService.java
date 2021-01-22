package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.dto.FSealDTO;
import com.qa.persistence.repos.FSealRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class FSealService {
	
	
	private FSealRepo repo;
	private ModelMapper mapper;
	
	
	@Autowired
	public FSealService(FSealRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private FSealDTO maptoDTO(FSealDomain model) {
		return this.mapper.map(model, FSealDTO.class);
	}
	//create
	public FSealDTO create(FSealDomain model) {
		return maptoDTO(this.repo.save(model));
		
	}
	//read all
	public List<FSealDTO> readAll() {
		
		List<FSealDomain> fsealList = repo.findAll();
		List<FSealDTO> fsealListDTO = fsealList.stream().map(this::maptoDTO).collect(Collectors.toList());
		
		return fsealListDTO;
	}
	//read one fseal from list
	public FSealDTO readOne(Long id) {
		return this.maptoDTO(this.repo.findById(id).orElseThrow());
	}
	//update
	public FSealDTO update(Long id, FSealDomain fseal) {
		FSealDomain updatedFSeal = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(fseal, updatedFSeal);
		
		return this.maptoDTO(this.repo.save(updatedFSeal));
	}
	
	//delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
	}
}