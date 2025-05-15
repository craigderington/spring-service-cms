package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Collection;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}

@Controller
@ResponseBody
class CustomerHttpController {

	private final CustomerRepository repository;

	CustomerHttpController(CustomerRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/customers")
	 Collection<Customer> customers() {
		return this.repository.findAll();
	}
}

// customer repo
interface CustomerRepository extends ListCrudRepository<Customer, Integer> {}


// customer
record Customer(@Id Integer id, String name)  {

}

// loans
sealed interface Loan permits SecuredLoan, UnsecuredLoan {}

final class SecuredLoan implements Loan {}

record UnsecuredLoan(float interest) implements Loan {}