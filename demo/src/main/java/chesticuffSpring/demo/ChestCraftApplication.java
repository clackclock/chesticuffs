package chesticuffSpring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Controller;

@SpringBootApplication
@RestController
@Controller
public class ChestCraftApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChestCraftApplication.class, args);
	}
	//http://localhost:8080/

//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return String.format("Hello %s!", name);
//	}

}
