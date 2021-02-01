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

import com.qa.persistence.domain.MSealDomain;
import com.qa.persistence.repos.MSealRepo;
import com.qa.services.MSealService;
import com.qa.persistence.dto.MSealDTO;

@SpringBootTest
class MSealServiceUnitTest {

	@Autowired
	private MSealService service;

	@MockBean
	private MSealRepo repo;

	@MockBean
	private ModelMapper mapper;

	private List<MSealDomain> MSealList;

	private MSealDomain testMSeal;

	private MSealDomain testMSealWithID;

	private MSealDTO MSealDTO;

	private final long ID = 1L;

	@BeforeEach
	void init() {
		this.MSealList = new ArrayList<>();
		this.MSealList.add(testMSeal);
		this.testMSeal = new MSealDomain("Grey", 345D);
		this.testMSealWithID = new MSealDomain(testMSeal.getName(), testMSeal.getWeight());
		this.testMSealWithID.setId(ID);
		this.MSealDTO = new ModelMapper().map(testMSealWithID, MSealDTO.class);
	}

//	@Test
//	void createMSealTest() {
//		MSealDTO newMSealDTO = new MSealDTO(null, "Grey", 345D);
//
//		when(this.mapper.map(newMSealDTO, MSealDomain.class)).thenReturn(testMSeal);
//		when(this.repo.save(testMSeal)).thenReturn(testMSealWithID);
//		when(this.mapper.map(testMSealWithID, MSealDTO.class)).thenReturn(MSealDTO);
//
//		assertThat(this.MSealDTO).isEqualTo(this.service.create(newMSealDTO));
//
//		verify(this.repo, times(1)).save(this.testMSeal);
//	}

	@Test
	void deleteMSealTest() {
		when(this.repo.existsById(ID)).thenReturn(true, false);

		assertThat(this.service.delete(ID)).isTrue();

		verify(this.repo, times(1)).deleteById(ID);
		verify(this.repo, times(2)).existsById(ID);
	}

	@Test
	void findMSealByIDTest() {
		when(this.repo.findById(this.ID)).thenReturn(Optional.of(this.testMSealWithID));
		when(this.mapper.map(testMSealWithID, MSealDTO.class)).thenReturn(MSealDTO);

		assertThat(this.MSealDTO).isEqualTo(this.service.readOne(this.ID));

		verify(this.repo, times(1)).findById(this.ID);
	}

	@Test
	void readMSealsTest() {

		when(repo.findAll()).thenReturn(this.MSealList);
		when(this.mapper.map(testMSealWithID, MSealDTO.class)).thenReturn(MSealDTO);

		assertThat(this.service.readAll().isEmpty()).isFalse();

		verify(repo, times(1)).findAll();
	}

	@Test
	void updateMSealsTest() {
		// given
		final long ID = 1L;
		MSealDTO newMSeal = new MSealDTO(null, "Billy", 500D);
		MSealDomain MSeal = new MSealDomain("Steven", 360D);
		MSeal.setId(ID);
		MSealDomain updatedMSeal = new MSealDomain(newMSeal.getName(), newMSeal.getWeight());
		updatedMSeal.setId(ID);
		MSealDTO updatedDTO = new MSealDTO(ID, updatedMSeal.getName(), updatedMSeal.getWeight());

		when(this.repo.findById(this.ID)).thenReturn(Optional.of(MSeal));
		// You NEED to configure a .equals() method in MSealDomain.java for this to work
		when(this.repo.save(updatedMSeal)).thenReturn(updatedMSeal);
		when(this.mapper.map(updatedMSeal, MSealDTO.class)).thenReturn(updatedDTO);

		assertThat(updatedMSeal).isEqualTo(this.service.update(this.ID, MSeal));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedMSeal);
	}
}
