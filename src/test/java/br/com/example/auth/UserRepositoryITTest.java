package br.com.example.auth;

import br.com.example.configuration.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static junit.framework.Assert.*;

@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfiguration.class)
public class UserRepositoryITTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void save() {
		User user = User.newInstance("fulano", "123456");

		userRepository.save(user);

		assertNotNull(user.getId());

		User userDB = userRepository.findOne(user.getId());

		assertNotNull(userDB);
		assertTrue(user.equals(userDB));
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void saveWithNullLogin() {
		User user = User.newInstance();
		user.setPassword("123455");
		userRepository.save(user);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void saveWithEmptyLogin() {
		User user = User.newInstance();
		user.setLogin("");
		user.setPassword("12345");
		userRepository.save(user);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void saveWithNullPassword() {
		User user = User.newInstance();
		user.setLogin("fulano");
		userRepository.save(user);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void saveWithEmptyPassword() {
		User user = User.newInstance();
		user.setLogin("fulano");
		user.setPassword("");
		userRepository.save(user);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void saveWithNullRole() {
		User user = User.newInstance("fulano", "123456");
		user.setRole(null);
		userRepository.save(user);
	}

	@Test
	@Transactional
	public void findUserByLogin() {
		User user = User.newInstance("fulano", "123456");

		userRepository.save(user);

		assertNotNull(user.getId());

		User userDB = userRepository.findUserByLogin("fulano");

		assertNotNull(userDB);
		assertTrue(user.equals(userDB));

		userDB = userRepository.findUserByLogin("sicrano");

		assertNull(userDB);
	}

}
