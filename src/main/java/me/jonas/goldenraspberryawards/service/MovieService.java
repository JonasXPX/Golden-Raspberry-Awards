package me.jonas.goldenraspberryawards.service;

import me.jonas.goldenraspberryawards.persistence.entities.Movie;

import java.util.Collection;

public interface MovieService {

    void saveAll(final Collection<Movie> movies);
}
