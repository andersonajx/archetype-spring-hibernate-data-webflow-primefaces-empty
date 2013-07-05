package br.com.example.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<User, Long>, Serializable {

	@Query("from User u where u.login = :login")
	User findUserByLogin(@Param("login") String login);

}
