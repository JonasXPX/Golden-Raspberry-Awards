package me.jonas.goldenraspberryawards.config;

import lombok.extern.log4j.Log4j2;
import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.service.CSVReader;
import me.jonas.goldenraspberryawards.service.MovieService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.String.format;

@Profile("!test")
@Configuration
@Log4j2
public class CSVConfigurationImpl implements CSVConfiguration {

    private final CSVReader csvReader;
    private final MovieService movieService;

    public CSVConfigurationImpl(final CSVReader csvReader, final MovieService movieService) {
        this.csvReader = csvReader;
        this.movieService = movieService;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Override
    public void loadCvs() {
        final File csvFolder = new File("csv");
        final File[] files = csvFolder.listFiles();
        Arrays.stream(files)
                .filter(File::isFile)
                .filter(file -> file.getAbsolutePath().endsWith(".csv"))
                .forEach(file -> {
                    try {
                        final Collection<Movie> movies = csvReader.readCSVFromInputStream(new FileInputStream(file.getAbsolutePath()));
                        movieService.saveAll(movies);
                        log.info(format("loaded file %s", file.getAbsolutePath()));
                    } catch (FileNotFoundException e) {
                        log.error(e.getMessage());
                    }
                });

    }


}
