package me.jonas.goldenraspberryawards.service;

import lombok.extern.log4j.Log4j2;
import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.persistence.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void saveAll(final Collection<Movie> movies) {
        movieRepository.saveAll(movies);
    }


}
