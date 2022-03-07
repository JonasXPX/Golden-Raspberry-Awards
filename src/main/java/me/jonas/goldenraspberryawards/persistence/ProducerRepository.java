package me.jonas.goldenraspberryawards.persistence;

import me.jonas.goldenraspberryawards.persistence.entities.Movie;
import me.jonas.goldenraspberryawards.persistence.entities.ProducerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m.year, p.name from " +
            "movie m " +
            "   join producer p on p.id_movie = m.id " +
            "where m.winner = true " +
            "order by p.name, m.year ", nativeQuery = true)
    List<ProducerView> getProducerInfo();
}
