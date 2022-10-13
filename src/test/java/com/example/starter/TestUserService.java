package com.example.starter;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.starter.entity.Address;
import com.example.starter.entity.Social;
import com.example.starter.entity.User;
import com.example.starter.exception.BaseException;
import com.example.starter.service.AddressService;
import com.example.starter.service.SocialService;
import com.example.starter.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
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
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updatedUser = userService.userUpdateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testCreateSocail() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(
				user,
				SocialTestCreateData.facebook,
				SocialTestCreateData.line,
				SocialTestCreateData.instragram,
				SocialTestCreateData.tiktok);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
	}

	@Order(4)
	@Test
	void testCreateAddress() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		List<Address> address = user.getAddresses();
		Assertions.assertTrue(address.isEmpty());

		createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
		createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);
	}

	private void createAddress(User user, String line1, String line2, String zipcode) {
		Address addresses = addressService.create(
				user,
				line1,
				line2,
				zipcode);

		Assertions.assertNotNull(addresses);
		Assertions.assertEquals(line1, addresses.getLine1());
		Assertions.assertEquals(line2, addresses.getLine2());
		Assertions.assertEquals(zipcode, addresses.getZipcode());
	}

	@Order(5)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		// Check social
		Social social = user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

		// Check address
		List<Address> addresses = user.getAddresses();
		Assertions.assertNotNull(addresses);
		Assertions.assertFalse(addresses.isEmpty());
		Assertions.assertEquals(2, addresses.size());

		userService.delete(user.getId());

		Optional<User> optCheckDeleted = userService.findByEmail(TestCreateData.email);

		Assertions.assertTrue(optCheckDeleted.isEmpty());
	}

	interface TestCreateData {

		String email = "chawalit@test.com";

		String password = "test1234";

		String name = "Chawalit Developer";

	}

	interface SocialTestCreateData {
		String facebook = "Chawalit Dev";

		String line = "";

		String instragram = "";

		String tiktok = "";
	}

	interface AddressTestCreateData {

		String line1 = "123/1";

		String line2 = "Moung";

		String zipcode = "10240";

	}

	interface AddressTestCreateData2 {

		String line1 = "456/7";

		String line2 = "Moung";

		String zipcode = "37001";

	}

	interface TestUpdateData {

		String name = "Chawalit Updated";

	}
}
