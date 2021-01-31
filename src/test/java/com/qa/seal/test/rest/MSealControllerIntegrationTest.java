package com.qa.seal.test.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.MSealDomain;
import com.qa.persistence.dto.MSealDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class MSealControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper mapper;

	private MSealDTO mapToDTO(MSealDomain MSeal) {
		return this.modelMapper.map(MSeal, MSealDTO.class);
	}

	private long TEST_ID= 1L;

	final private MSealDomain TEST_MSEAL_FROM_DB = new MSealDomain(1L, "Smashley", 300.5D);
	

	@BeforeEach
	void init() {
	}

	@Test
	void testCreateMSeal() throws Exception {
		MSealDomain newMSeal = new MSealDomain("Robert", 250D);
		MSealDomain savedMSeal = new MSealDomain(newMSeal.getName(), newMSeal.getWeight());
		savedMSeal.setId(TEST_ID + 2);
		MSealDTO savedMSealDTO = this.mapToDTO(savedMSeal);
		this.mock
				.perform(request(HttpMethod.POST, "/mseal/create").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(newMSeal)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(this.mapper.writeValueAsString(savedMSealDTO)));
	}

	@Test
	void testDeleteMSeal() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/MSeal/deleteMSeal/" + TEST_ID)).andExpect(status().isNoContent());
	}

	@Test
	void testGetMSeal() throws Exception {
		MSealDTO expectedResult = this.mapToDTO(TEST_MSEAL_FROM_DB);
		this.mock.perform(request(HttpMethod.GET, "/mseal/get/" + TEST_ID).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(expectedResult)));
	}

	@Test
	void testReadAllMSeals() throws Exception {
		List<MSealDTO> MSealList = new ArrayList<>();
		MSealList.add(this.mapToDTO(TEST_MSEAL_FROM_DB));

		this.mock.perform(request(HttpMethod.GET, "/mseal/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(MSealList)));
	}

	@Test
	void testUpdateMSeal() throws Exception {
		MSealDomain newMSeal = new MSealDomain("Bashley", 350D);
		MSealDomain updatedMSeal = new MSealDomain(TEST_ID, newMSeal.getName(), newMSeal.getWeight());
		MSealDTO updatedMSealDTO = this.mapToDTO(updatedMSeal);
		this.mock
				.perform(request(HttpMethod.PUT, "/MSeal/updatemseal/" + TEST_ID).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newMSeal)))
				.andExpect(status().isAccepted())
				.andExpect(content().json(this.mapper.writeValueAsString(updatedMSealDTO)));
	}

}
