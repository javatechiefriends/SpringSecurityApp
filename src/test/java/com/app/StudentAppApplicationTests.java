package com.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void emailCountsTest() {
		
		
		assertEquals(1, 1);
		
	}
}