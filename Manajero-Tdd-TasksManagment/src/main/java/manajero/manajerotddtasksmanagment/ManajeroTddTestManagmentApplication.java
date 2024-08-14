package manajero.manajerotddtasksmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class ManajeroTddTestManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManajeroTddTestManagmentApplication.class, args);
    }

}
