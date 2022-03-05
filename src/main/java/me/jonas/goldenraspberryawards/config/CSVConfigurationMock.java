package me.jonas.goldenraspberryawards.config;

import lombok.extern.log4j.Log4j2;
import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.service.CSVReader;
import me.jonas.goldenraspberryawards.service.MovieService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Collection;

import static java.lang.String.format;

@Profile("test")
@Configuration
@Log4j2
public class CSVConfigurationMock implements CSVConfiguration {

    private static final File TEST_FILE_CSV = Path.of("csv/movielist.csv.test").toFile();
    private final CSVReader csvReader;
    private final MovieService movieService;

    public CSVConfigurationMock(CSVReader csvReader, MovieService movieService) {
        this.csvReader = csvReader;
        this.movieService = movieService;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Override
    public void loadCvs() throws FileNotFoundException {
        final Collection<Movie> movies = csvReader.readCSVFromInputStream(new FileInputStream(TEST_FILE_CSV.getAbsolutePath()));
        movieService.saveAll(movies);
        log.info(format("loaded file %s", TEST_FILE_CSV.getAbsolutePath()));
    }
}
