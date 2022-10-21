package com.example.testcontainers;

import com.example.testcontainers.entity.User;
import com.example.testcontainers.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class TestcontainersApplicationTests {

	@Resource
	UserRepository userRepository;

	@Test
	void init() {
		Assertions.assertEquals(userRepository.count(), 0);
		userRepository.save(new User(UUID.randomUUID(), "test"));
		Assertions.assertEquals(userRepository.count(), 1);
	}

}
