package manajero.manajerotddtasksmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories(basePackages = "manajero.manajerotddtasksmanagment.Repository")
public class ManajeroTddTaskManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManajeroTddTaskManagmentApplication.class, args);
    }

}
