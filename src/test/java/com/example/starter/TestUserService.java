package com.example.starter;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.starter.entity.User;
import com.example.starter.exception.BaseExeption;
import com.example.starter.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseExeption {
		User user = userService.create(TestCreateData.email, TestCreateData.password, TestCreateData.name);

		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		// Equals
		Assertions.assertEquals(TestCreateData.email, user.getEmail());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);

		Assertions.assertEquals(TestCreateData.name, user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseExeption {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updatedUser = userService.userUpdateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.delete(user.getId());

		Optional<User> optCheckDeleted = userService.findByEmail(TestCreateData.email);

		Assertions.assertTrue(optCheckDeleted.isEmpty());
	}

	interface TestCreateData {

		String email = "chawalit@test.com";

		String password = "test1234";

		String name = "Chawalit Developer";

	}

	interface TestUpdateData {

		String name = "Chawalit Updated";

	}
}
