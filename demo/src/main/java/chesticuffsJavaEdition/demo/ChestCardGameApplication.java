package chesticuffsJavaEdition.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChestCardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChestCardGameApplication.class, args);
	}
	//http://localhost:8080/hello

}
