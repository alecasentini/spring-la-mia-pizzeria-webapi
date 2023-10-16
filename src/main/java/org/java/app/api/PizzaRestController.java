package org.java.app.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PizzaDTO;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PizzaRestController {
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<Pizza>> getAll(@RequestParam(required = false) String nome) {

		List<Pizza> pizzas;

		if (nome != null) {
			pizzas = pizzaService.findByNomeContaining(nome);
		} else {
			pizzas = pizzaService.findAll();
		}

		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Integer> save(@RequestBody PizzaDTO pizzaDto) {
	    Pizza pizza = new Pizza();
	    pizza.fillFromPizzaDto(pizzaDto);
	    pizza = pizzaService.save(pizza);
	    return new ResponseEntity<>(pizza.getId(), HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(
			@PathVariable int id
		) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(
			@PathVariable int id,
			@RequestBody PizzaDTO pizzaDto
		) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Pizza pizza = optPizza.get();

		pizza.fillFromPizzaDto(pizzaDto);

		try {

			pizza = pizzaService.save(pizza);

			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(
			@PathVariable int id
		) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Pizza pizza = optPizza.get();
		
		pizzaService.deleteById(id);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
