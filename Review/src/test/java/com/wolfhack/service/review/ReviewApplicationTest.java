package com.wolfhack.service.review;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ReviewApplicationTest {

	@Test
	void contextLoads() {

		ApplicationModules modules = ApplicationModules.of(ReviewApplication.class);

		new Documenter(modules)
			.writeIndividualModulesAsPlantUml()
			.writeModulesAsPlantUml();

	}

}