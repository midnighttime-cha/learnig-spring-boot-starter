package com.example.starter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.starter.business.EmailBusiness;
import com.example.starter.exception.BaseException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEmailBusiness {

	@Autowired
	private EmailBusiness emailBusiness;

	@Order(1)
	@Test
	void testSendAcriveteEmail() throws BaseException {
		emailBusiness.sendActivateUserEmail(TestData.email, TestData.name, TestData.token);
	}

	interface TestData {
		String email = "khem.k18@gmail.com";
		String name = "Chawalit Sengsang";
		String token = "LOnasmdfoasdnqw90-123u483masd";
	}
}
