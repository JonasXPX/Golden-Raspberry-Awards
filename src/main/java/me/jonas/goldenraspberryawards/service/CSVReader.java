package me.jonas.goldenraspberryawards.service;

import me.jonas.goldenraspberryawards.persistence.entities.Movie;

import java.io.InputStream;
import java.util.Collection;

public interface CSVReader {

    Collection<Movie> readCSVFromInputStream(final InputStream inputStream);

}
