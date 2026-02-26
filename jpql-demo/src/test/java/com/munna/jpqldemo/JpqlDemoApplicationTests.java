package com.munna.jpqldemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.munna.jpqldemo.entity.Employee;
import com.munna.jpqldemo.repository.EmployeeRepository;

@SpringBootTest
class JpqlDemoApplicationTests {

	 @Autowired
	    private EmployeeRepository repository;

	    @Test
	    void testFindByDepartment() {
	        Employee e1 = new Employee(null, "Test1", "IT", 50000);
	        Employee e2 = new Employee(null, "Test2", "HR", 30000);

	        repository.save(e1);
	        repository.save(e2);

	        List<Employee> result = repository.findByDepartment("IT");

	        assertThat(result).isNotEmpty();
	        assertThat(result.get(0).getDepartment()).isEqualTo("IT");
	    }
}
