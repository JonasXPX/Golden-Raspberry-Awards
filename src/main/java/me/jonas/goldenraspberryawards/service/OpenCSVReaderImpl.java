package me.jonas.goldenraspberryawards.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.service.cvsbean.MovieBean;
import me.jonas.goldenraspberryawards.utils.BooleanConverter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OpenCSVReaderImpl implements CSVReader {

    @Override
    public Collection<Movie> readCSVFromInputStream(final InputStream inputStream) {
        final HeaderColumnNameMappingStrategy<MovieBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(MovieBean.class);

        final CsvToBean<MovieBean> movieBeans = new CsvToBeanBuilder<MovieBean>(new InputStreamReader(inputStream))
                .withMappingStrategy(strategy)
                .withIgnoreEmptyLine(true)
                .withSeparator(';')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .build();

        return movieBeans.parse()
                .stream()
                .map(movieBean ->
                        new Movie(
                                null,
                                movieBean.getYear(),
                                movieBean.getTitle(),
                                movieBean.getStudios(),
                                movieBean.getProducers(),
                                BooleanConverter.convert(movieBean.getWinner())))
                .collect(Collectors.toList());
    }
}
