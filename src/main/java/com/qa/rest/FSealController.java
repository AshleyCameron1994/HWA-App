package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.dto.FSealDTO;
import com.qa.services.FSealService;

@RestController
@RequestMapping("/fseal")
public class FSealController {
	
	private FSealService service;
	
	@Autowired
	public FSealController(FSealService service) {
		super();
		this.service = service;
	}
	
	//CRUD functionality or GET, PUT, POST AND DELETE
	
	@PostMapping("/create")
	public ResponseEntity<FSealDTO> create(@RequestBody FSealDomain fseal){
		return new ResponseEntity<FSealDTO>(this.service.create(fseal), HttpStatus.CREATED);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<FSealDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<FSealDTO> readfseal(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<FSealDTO> update(@PathVariable Long id, @RequestBody FSealDomain fseal){
		
		return new ResponseEntity<>(this.service.update(id, fseal), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<FSealDTO> deleteFSeal(@PathVariable("id") Long id){
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
