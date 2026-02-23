package com.voguevista.controller;

import com.voguevista.entity.Activity;
import com.voguevista.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;


    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        return ResponseEntity.ok(activityRepository.findAll());
    }

    
    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<Activity>> getByLocation(@PathVariable Integer locationId) {
        return ResponseEntity.ok(activityRepository.findByLocationId(locationId));
    }
}