package me.jonas.goldenraspberryawards.service;

import lombok.extern.log4j.Log4j2;
import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.persistence.entities.Producer;
import me.jonas.goldenraspberryawards.utils.BooleanConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Log4j2
public class CSVReaderGoldenRaspberryImpl implements CSVReader {

    private static final String DELIMITER = ";";

    private static final int YEAR_COLUMN_INDEX = 0;
    private static final int TITLE_COLUMN_INDEX = 1;
    private static final int STUDIOS_COLUMN_INDEX = 2;
    private static final int PRODUCERS_COLUMN_INDEX = 3;
    private static final int WINNER_COLUMN_INDEX = 4;
    private static final String DELIMITER_PRODUCER = ",.|.and.";

    @Override
    public Collection<Movie> readCSVFromInputStream(final InputStream inputStream) {
        final List<Movie> movies = new ArrayList<>();

        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] columns = line.split(DELIMITER);
                final Movie movie = normalizeColumnWithNames(columns);
                if (nonNull(movie))
                    movies.add(movie);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return movies;
    }

    private Movie normalizeColumnWithNames(final String[] columns) {
        try {
            final int year = columns.length > YEAR_COLUMN_INDEX ? Integer.parseInt(columns[YEAR_COLUMN_INDEX]) : null;
            final String title = columns.length > TITLE_COLUMN_INDEX ? columns[TITLE_COLUMN_INDEX] : null;
            final String studio = columns.length > STUDIOS_COLUMN_INDEX ? columns[STUDIOS_COLUMN_INDEX] : null;
            final String producer = columns.length > PRODUCERS_COLUMN_INDEX ? columns[PRODUCERS_COLUMN_INDEX] : null;
            final boolean winner = columns.length > WINNER_COLUMN_INDEX && BooleanConverter.convert(columns[WINNER_COLUMN_INDEX]);
            Movie movie = new Movie(null, year, title, studio, null, winner);
            movie.setProducer(mapProducer(producer, movie));
            return movie;
        } catch (Exception e) {
            log.warn(format("failed to read line with contents: %s", columns));
            log.error(e.getMessage());
        }

        return null;
    }

    private List<Producer> mapProducer(final String producer, final Movie movie) {
        return Arrays.stream(producer.split(DELIMITER_PRODUCER))
                .map(name -> new Producer(null, name, movie))
                .collect(Collectors.toList());
    }
}
