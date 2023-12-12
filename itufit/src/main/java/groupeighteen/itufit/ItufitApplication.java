package groupeighteen.itufit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ItufitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItufitApplication.class, args);
	}

}
