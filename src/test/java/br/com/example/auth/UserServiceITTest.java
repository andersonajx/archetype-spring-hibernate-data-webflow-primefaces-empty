package br.com.example.auth;

import br.com.example.configuration.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfiguration.class)
public class UserServiceITTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	@Transactional
	public void loadUserByName() {
		User user = User.newInstance("fulano", "123456");

		userRepository.save(user);

		assertNotNull(user.getId());

		UserDetails userDetails = userService.loadUserByUsername("fulano");

		assertNotNull(userDetails);
		assertTrue(user.equals(userDetails));

		userDetails = userService.loadUserByUsername("sicrano");

		assertNull(userDetails);
	}

}
