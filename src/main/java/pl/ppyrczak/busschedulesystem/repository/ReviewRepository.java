package pl.ppyrczak.busschedulesystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ppyrczak.busschedulesystem.model.Passenger;
import pl.ppyrczak.busschedulesystem.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByScheduleIdIn(List<Long> ids);
    List<Review> findAllByScheduleId(Long id, Pageable pageable);
    List<Review> findAllByScheduleId(Long id);
    boolean existsByPassengerId(Long id);

    List<Review> findAllByPassengerIdIn(List<Long> ids);
}
