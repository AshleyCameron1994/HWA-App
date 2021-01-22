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

import com.qa.persistence.domain.MSealDomain;
import com.qa.persistence.dto.MSealDTO;
import com.qa.services.MSealService;

@RestController
@RequestMapping("/mseal")
public class MSealController {
	
	private MSealService service;
	
	@Autowired
	public MSealController(MSealService service) {
		super();
		this.service = service;
	}
	
	//CRUD functionality or GET, PUT, POST AND DELETE
	
	@PostMapping("/create")
	public ResponseEntity<MSealDTO> create(@RequestBody MSealDomain mseal){
		return new ResponseEntity<MSealDTO>(this.service.create(mseal), HttpStatus.CREATED);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<MSealDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<MSealDTO> readMSeal(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MSealDTO> update(@PathVariable Long id, @RequestBody MSealDomain mseal){
		
		return new ResponseEntity<>(this.service.update(id, mseal), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MSealDTO> deleteMSeal(@PathVariable("id") Long id){
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
