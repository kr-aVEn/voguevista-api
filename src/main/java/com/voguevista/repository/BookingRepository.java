package com.voguevista.repository;

import com.voguevista.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {  

    List<Booking> findByUserId(Integer userId);
    List<Booking> findByPackageId(Integer packageId);
}