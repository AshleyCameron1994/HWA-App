package com.qa.seal.test.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.dto.FSealDTO;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
class FSealControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper mapper;

	//private final FSealDomain TEST_FSEAL_FROM_DB = new FSealDomain(1L, "Salty", 245D);

	private FSealDTO mapToDTO(FSealDomain FSeal) {
		return this.modelMapper.map(FSeal, FSealDTO.class);
	}
	
	private final int TEST_ID = 1;
	private final FSealDomain TEST_FSEAL_FROM_DB = new FSealDomain(1L, "Salty", 245D);

//	@Test
//	void testReadOneFSeal() throws Exception {
//		
//		FSealDTO TEST_FSEAL = new FSealDTO(1L, "Salty", 245D);
//		
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/FSeal/read/" + TEST_ID);
//		mockRequest.contentType(MediaType.APPLICATION_JSON);
//		mockRequest.accept(MediaType.APPLICATION_JSON);
//
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_FSEAL));
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
//
//		this.mock.perform(mockRequest)
//		.andExpect(matchStatus)
//		.andExpect(matchContent);
//		
//	}
	
	@Test
	void testCreateFSeal() throws Exception {
		
		final FSealDomain NEW_FSeal = new FSealDomain("Savage", 300D);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/FSeal/createFSeal");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(this.mapper.writeValueAsString(NEW_FSeal));
		mockRequest.accept(MediaType.APPLICATION_JSON);

		final FSealDomain SAVED_FSeal = new FSealDomain(3L, NEW_FSeal.getName(), NEW_FSeal.getWeight());

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.mapper.writeValueAsString(this.mapToDTO(SAVED_FSeal)));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	void testDeleteFSeal() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/FSeal/delete/" + this.TEST_FSEAL_FROM_DB.getId()))
				.andExpect(status().isNoContent());
	}

	@Test
	void testReadAllFSeals() throws Exception {
		List<FSealDTO> FSealList = new ArrayList<>();
		FSealList.add(this.mapToDTO(TEST_FSEAL_FROM_DB));

		String content = this.mock.perform(request(HttpMethod.GET, "/fseal/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(FSealList), content);
	}

	@Test
	void testUpdateFSeal() throws Exception {
		FSealDTO newFSeal = new FSealDTO(null, "Sauvage", 250D);
		FSealDomain updatedFSeal = new FSealDomain(this.TEST_FSEAL_FROM_DB.getId(), newFSeal.getName(), newFSeal.getWeight());

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/fseal/update/" + this.TEST_FSEAL_FROM_DB.getId())
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(newFSeal)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.mapToDTO(updatedFSeal)), result);
	}

}