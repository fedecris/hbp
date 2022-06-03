package health.hbp;

import health.hbp.repository.EdibleRepository;
import health.hbp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EdibleRepository.class)
@EnableCaching
public class HBPApplication {

	public static void main(String[] args) {
		SpringApplication.run(HBPApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> userService.register("test", "1234", "1234");
	}
}
