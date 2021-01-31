package com.qa.seal.test.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Only needed to test custom repo methods (especially when written with @query)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.qa.persistence.domain.FSealDomain;
import com.qa.persistence.repos.FSealRepo;

@DataJpaTest
class FSealRepoTest {

	@Autowired
	private FSealRepo repo;

	private final Double TEST_WEIGHT = 300D;

	private final FSealDomain TEST_SEAL = new FSealDomain("Bryn", TEST_WEIGHT);

	private FSealDomain testSavedFSeal;

	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.testSavedFSeal = this.repo.save(this.TEST_SEAL);
	}

	@Test
	void testFindByName() {
		assertThat(this.repo.findByWeight(this.TEST_WEIGHT)).containsExactly(this.testSavedFSeal);
	}

}