package me.jonas.goldenraspberryawards.service;

import me.jonas.goldenraspberryawards.persistence.ProducerRepository;
import me.jonas.goldenraspberryawards.persistence.entities.Producer;
import me.jonas.goldenraspberryawards.service.dto.PrizeRange;
import me.jonas.goldenraspberryawards.service.dto.ProducerInterval;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequestScope
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository repository;

    public ProducerServiceImpl(final ProducerRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrizeRange getIntervals() {
        final List<Producer> allMovies = repository.getProducerInfo();
        final PrizeRange prizeRange = new PrizeRange();

        prizeRange.setMin(getMinOfProducerRecords(normalizeProducersWithPeriods(allMovies)));
        prizeRange.setMax(getMaxOfProducerRecords(normalizeProducersWithPeriods(allMovies)));

        return prizeRange;
    }

    private List<ProducerInterval> getMaxOfProducerRecords(final Map<String, List<Integer>> producersWithRecords) {
        final Supplier<Stream<ProducerInterval>> streamSupplier = filterProducersAndMapMinAndMax(producersWithRecords);

        final Optional<ProducerInterval> maxInProducers = streamSupplier
                .get()
                .max(Comparator.comparingInt(ProducerInterval::getInterval));

        return streamSupplier
                .get()
                .filter(producerInterval -> producerInterval.getInterval() == maxInProducers.get().getInterval())
                .collect(Collectors.toList());
    }

    private Function<Map.Entry<String, List<Integer>>, ProducerInterval> getMinAndMaxOfPeriod() {
        return entry -> {
            final Optional<Integer> min = entry.getValue().stream().reduce(Math::min);
            final Optional<Integer> max = entry.getValue().stream().reduce(Math::max);
            return new ProducerInterval(entry.getKey(), min, max);
        };
    }

    private List<ProducerInterval> getMinOfProducerRecords(final Map<String, List<Integer>> producersWithRecords) {
        final Supplier<Stream<ProducerInterval>> streamSupplier = filterProducersAndMapMinAndMax(producersWithRecords);


        final Optional<ProducerInterval> minimal = streamSupplier
                .get()
                .min(Comparator.comparingInt(ProducerInterval::getInterval));

        return streamSupplier
                .get()
                .filter(producerInterval -> producerInterval.getInterval() == minimal.get().getInterval())
                .collect(Collectors.toList());
    }

    private Supplier<Stream<ProducerInterval>> filterProducersAndMapMinAndMax(final Map<String, List<Integer>> producersWithRecords) {
        return () ->
                producersWithRecords.entrySet()
                        .stream()
                        .filter(stringListEntry -> stringListEntry.getValue().size() > 1)
                        .map(getMinAndMaxOfPeriod());
    }

    private Map<String, List<Integer>> normalizeProducersWithPeriods(final List<Producer> movies) {
        final Map<String, List<Integer>> auxProducers = new HashMap<>();

        movies.forEach(movie -> {
            final List<Integer> years = auxProducers.getOrDefault(movie.getProducer(), new ArrayList<>());
            years.add(movie.getYear());
            auxProducers.put(movie.getProducer(), years);
        });
        return auxProducers;
    }
}
