package com.voguevista.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Package ID is required")
    private Integer packageId;

    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;

    @Min(value = 1, message = "At least 1 traveler required")
    private Integer numTravelers;
}