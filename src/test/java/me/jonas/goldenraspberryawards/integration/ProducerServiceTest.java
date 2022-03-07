package me.jonas.goldenraspberryawards.integration;

import me.jonas.goldenraspberryawards.service.ProducerService;
import me.jonas.goldenraspberryawards.service.dto.PrizeRange;
import me.jonas.goldenraspberryawards.service.dto.ProducerInterval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ProducerServiceTest {

    @Autowired
    ProducerService service;

    @Test
    void shouldReturnMovies() {
        PrizeRange moviesIntervals = service.getIntervals();
        assertNotNull(moviesIntervals);
    }

    @Test
    void shouldReturnMaxProducers() {
        PrizeRange moviesIntervals = service.getIntervals();
        assertEquals(1, moviesIntervals.getMax().size());
        ProducerInterval max = moviesIntervals.getMax().iterator().next();
        assertEquals(13, max.getInterval());
        assertEquals(2002, max.getPreviousWin());
        assertEquals(2015, max.getFollowingWin());
        assertEquals("Matthew Vaughn", max.getProducer());
    }

    @Test
    void shouldReturnMinProducers() {
        PrizeRange moviesIntervals = service.getIntervals();

        Collection<ProducerInterval> min = moviesIntervals.getMin();
        min.forEach(producerInterval -> assertEquals(1, producerInterval.getInterval()));
    }
}
