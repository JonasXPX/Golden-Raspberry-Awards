package me.jonas.goldenraspberryawards;

import lombok.extern.log4j.Log4j2;
import me.jonas.goldenraspberryawards.service.CSVReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Log4j2
@SpringBootApplication
public class GoldenRaspBerryAwardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspBerryAwardsApplication.class, args);
    }


}
