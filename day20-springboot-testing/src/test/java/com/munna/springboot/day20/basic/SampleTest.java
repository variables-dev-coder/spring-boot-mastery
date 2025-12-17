package com.munna.springboot.day20.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {
	
	@Test
    void additionTest() {
        int result = 10 + 20;
        Assertions.assertEquals(30, result);
    }

}
