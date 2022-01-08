package health.hbp;

import health.hbp.repository.EdibleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EdibleRepository.class)
public class HBPApplication {

	public static void main(String[] args) {
		SpringApplication.run(HBPApplication.class, args);
	}

}
