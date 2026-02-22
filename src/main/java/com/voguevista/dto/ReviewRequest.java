package com.voguevista.dto;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


@Data
public class ReviewRequest {

@NotNull(message = "User ID is required")
private Integer userId;

@NotNull(message = "Package ID is required")
private Integer packageId;

@NotBlank(message = "Review text cant be blank")
private String review;

@NotNull(message = "Rating is required")
@Min(value = 1, message = "Rating must be at least 1")
@Max(value = 5, message = "Rating must be at most 5")
private Integer rating;


}
