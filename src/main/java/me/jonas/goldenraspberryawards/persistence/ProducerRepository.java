package me.jonas.goldenraspberryawards.persistence;

import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.persistence.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m.year, m.producer from movie m", nativeQuery = true)
    List<Producer> getProducerInfo();
}
