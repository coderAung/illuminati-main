package edu.ucsy.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class UserDetailModelTest {

	private static DatabaseInitializer di;
	private static Model<UserDetail> userDetailModel;

	@BeforeAll
	static void init() {
		var connector = CustomConnectorFactory.getConnectorWithPassword("root");
		di = new DatabaseInitializer(connector);
		di.loadUser();
		di.truncate("user_details");
		userDetailModel = ModelFactory.getModel(UserDetail.class);
	}

	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			delimiter = '\t',
			files = {"test-source/user-details.txt"})
	void test_save(long userId, String address, String bio, String phoneNumber, Gender gender, Occupation occupation) {
		var userDetail = new UserDetail(userId, null, address, bio, phoneNumber, gender, null, occupation);
		userDetail = userDetailModel.save(userDetail);
		
		assertNotNull(userDetail);
		assertEquals(userId, userDetail.userId());
		assertEquals(address, userDetail.address());
		assertEquals(bio, userDetail.bio());
		assertEquals(phoneNumber, userDetail.phoneNumber());
		assertEquals(gender, userDetail.gender());
		assertEquals(occupation, userDetail.occupation());
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(
			delimiter = '\t',
			files = {"test-source/user-details.txt"})
	void test_findOne(long userId, String address, String bio, String phoneNumber, Gender gender, Occupation occupation) {
		var userDetail = userDetailModel.findOne(userId);

		assertNotNull(userDetail);
		assertEquals(userId, userDetail.userId());
		assertEquals(address, userDetail.address());
		assertEquals(bio, userDetail.bio());
		assertEquals(phoneNumber, userDetail.phoneNumber());
		assertEquals(gender, userDetail.gender());
		assertEquals(occupation, userDetail.occupation());
	}

	@Order(3)
	@ParameterizedTest
	@CsvFileSource(
			delimiter = '\t',
			files = {"test-source/user-details-update.txt"})
	void test_update(long userId, String address, String bio, String phoneNumber, Gender gender, Occupation occupation) {
		var userDetail = new UserDetail(userId, null, address, bio, phoneNumber, gender, null, occupation);
		userDetail = userDetailModel.update(userDetail);

		assertNotNull(userDetail);
		assertEquals(userId, userDetail.userId());
		assertEquals(address, userDetail.address());
		assertEquals(bio, userDetail.bio());
		assertEquals(phoneNumber, userDetail.phoneNumber());
		assertEquals(gender, userDetail.gender());
		assertEquals(occupation, userDetail.occupation());
	}
	
}
