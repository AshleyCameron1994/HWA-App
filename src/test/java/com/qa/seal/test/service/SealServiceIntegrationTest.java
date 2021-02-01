package com.qa.seal.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.dto.FSealDTO;
import com.qa.persistence.repos.FSealRepo;
import com.qa.services.FSealService;


@SpringBootTest
class SealServiceIntegrationTest {

	@Autowired
	private FSealService service;

	@Autowired
	private FSealRepo repo;

	private FSealDomain testFSeal;

	private FSealDomain testFSealWithID;

	@Autowired
	private ModelMapper mapper;

	private FSealDTO mapToDTO(FSealDomain FSeal) {
		return this.mapper.map(FSeal, FSealDTO.class);
	}

	@BeforeEach
	void init() {
		this.testFSeal = new FSealDomain("Savvy", 230D);

		this.repo.deleteAll();
		// getting around auto-generated id's
		this.testFSealWithID = this.repo.save(this.testFSeal);
	}

//	@Test
//	void testCreateFSeal() {
//		assertThat(this.mapToDTO(this.testFSealWithID)).isEqualTo(this.service.create(mapToDTO(testFSeal)));
//	}

	@Test
	void testDeleteFSeal() {
		assertThat(this.service.delete(this.testFSealWithID.getId())).isTrue();
	}

	@Test
	void testFindFSealByID() {
		assertThat(this.service.readOne(this.testFSealWithID.getId()))
				.isEqualTo(this.mapToDTO(this.testFSealWithID));
	}

	@Test
	void testReadFSeals() {
		assertThat(this.service.readAll())
				.isEqualTo(Stream.of(this.mapToDTO(testFSealWithID)).collect(Collectors.toList()));
	}

//	@Test
//	void testUpdateFSeal() {
//		FSealDTO newFSeal = new FSealDTO(null, "Sushi", 200D);
//		FSealDTO updatedFSeal = new FSealDTO(this.testFSealWithID.getId(), newFSeal.getName(), newFSeal.getWeight());
//
//		assertThat(this.service.update(newFSeal, this.testFSealWithID.getId())).isEqualTo(updatedFSeal);
//	}

}
