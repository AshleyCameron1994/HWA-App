package com.qa.seal.test.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.repos.FSealRepo;
import com.qa.services.FSealService;
import com.qa.persistence.dto.FSealDTO;

@SpringBootTest
class FSealServiceUnitTest {

	@Autowired
	private FSealService service;

	@MockBean
	private FSealRepo repo;

	@MockBean
	private ModelMapper mapper;

	private List<FSealDomain> FSealList;

	private FSealDomain testFSeal;

	private FSealDomain testFSealWithID;

	private FSealDTO FSealDTO;

	private final long ID = 1L;

	@BeforeEach
	void init() {
		this.FSealList = new ArrayList<>();
		this.FSealList.add(testFSeal);
		this.testFSeal = new FSealDomain("Grey", 345D);
		this.testFSealWithID = new FSealDomain(testFSeal.getName(), testFSeal.getWeight());
		this.testFSealWithID.setId(ID);
		this.FSealDTO = new ModelMapper().map(testFSealWithID, FSealDTO.class);
	}

//	@Test
//	void createFSealTest() {
//		FSealDTO newFSealDTO = new FSealDTO(null, "Grey", 345D);
//
//		when(this.mapper.map(newFSealDTO, FSealDomain.class)).thenReturn(testFSeal);
//		when(this.repo.save(testFSeal)).thenReturn(testFSealWithID);
//		when(this.mapper.map(testFSealWithID, FSealDTO.class)).thenReturn(FSealDTO);
//
//		assertThat(this.FSealDTO).isEqualTo(this.service.create(newFSealDTO));
//
//		verify(this.repo, times(1)).save(this.testFSeal);
//	}

	@Test
	void deleteFSealTest() {
		when(this.repo.existsById(ID)).thenReturn(true, false);

		assertThat(this.service.delete(ID)).isTrue();

		verify(this.repo, times(1)).deleteById(ID);
		verify(this.repo, times(0)).existsById(ID);
	}

	@Test
	void findFSealByIDTest() {
		when(this.repo.findById(this.ID)).thenReturn(Optional.of(this.testFSealWithID));
		when(this.mapper.map(testFSealWithID, FSealDTO.class)).thenReturn(FSealDTO);

		assertThat(this.FSealDTO).isEqualTo(this.service.readOne(this.ID));

		verify(this.repo, times(1)).findById(this.ID);
	}

	@Test
	void readFSealsTest() {

		when(repo.findAll()).thenReturn(this.FSealList);
		when(this.mapper.map(testFSealWithID, FSealDTO.class)).thenReturn(FSealDTO);

		assertThat(this.service.readAll().isEmpty()).isFalse();

		verify(repo, times(1)).findAll();
	}

	@Test
	void updateFSealsTest() {
		// given
		final long ID = 1L;
		FSealDTO newFSeal = new FSealDTO(null, "Squidia", 100D);
		FSealDomain FSeal = new FSealDomain("Angharad", 200D);
		FSeal.setId(ID);
		FSealDomain updatedFSeal = new FSealDomain(newFSeal.getName(), newFSeal.getWeight());
		updatedFSeal.setId(ID);
		FSealDTO updatedDTO = new FSealDTO(ID, updatedFSeal.getName(), updatedFSeal.getWeight());

		when(this.repo.findById(this.ID)).thenReturn(Optional.of(FSeal));
		// You NEED to configure a .equals() method in FSealDomain.java for this to work
		when(this.repo.save(updatedFSeal)).thenReturn(updatedFSeal);
		when(this.mapper.map(updatedFSeal, FSealDTO.class)).thenReturn(updatedDTO);

		assertThat(updatedFSeal).isEqualTo(this.service.update(this.ID, FSeal));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedFSeal);
	}
}
