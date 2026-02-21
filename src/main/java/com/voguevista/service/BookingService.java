package com.voguevista.service;

import com.voguevista.dto.BookingRequest;
import com.voguevista.entity.Booking;
import com.voguevista.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // 1. Create booking
    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setPackageId(request.getPackageId());
        booking.setBookingDate(LocalDate.now());
        booking.setTravelDate(request.getTravelDate());
        booking.setNumTravelers(request.getNumTravelers());
        booking.setStatus("pending");
        return bookingRepository.save(booking);
    }

    // 2. Get bookings by user
    public List<Booking> getUserBookings(Integer userId) {
        return bookingRepository.findByUserId(userId);
    }

    // 3. Get ALL bookings ← THIS ONE WAS MISSING
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // 4. Update status
    public Booking updateStatus(Integer bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    // 5. Delete booking
    public void deleteBooking(Integer bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}