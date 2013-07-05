package br.com.example.auth;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;

public class UserTest {

	@Test
	public void instance() {
		User user1 = User.newInstance("fulano", "123456");
		User user2 = User.newInstance("fulano", "123456");

		assertTrue(user1.equals(user2));

		User user3 = User.newInstance("sicrano", "123456");

		assertFalse(user1.equals(user3));
	}

	@Test
	public void showToString() {
		User user = User.newInstance("sicrano", "123456");
		assertEquals("User{sicrano}", user.toString());
	}

	@Test(expected = NullPointerException.class)
	public void userWithNullLogin() {
		User.newInstance(null, "1234554");
	}

	@Test(expected = IllegalArgumentException.class)
	public void userWithEmptyLogin() {
		User.newInstance("", "2q34r34");
	}

	@Test(expected = NullPointerException.class)
	public void userWithNullPassword() {
		User.newInstance("fulano", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void userWithEmptyPassword() {
		User.newInstance("fulano", "");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getAuthoritiesUnmutable() {
		User user = User.newInstance("fulano", "12345");
		((List<Role>)user.getAuthorities()).add(Role.ROLE_ADMIN);
	}

}
