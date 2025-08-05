package com.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.StuffIdModel;
import com.spring.services.StuffIdService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/stuff-ids")
public class StuffIdController {
    private final StuffIdService stuffIdService;

    public StuffIdController(StuffIdService stuffIdService) {
        this.stuffIdService = stuffIdService;
    }

    @GetMapping
    public List<StuffIdModel> getStuffIds() {
        return stuffIdService.getAllStuffIds();
    }

    @PostMapping
    public void createStuffId(
            @RequestBody StuffIdModel stuffId) {
        stuffIdService.createStuffId(stuffId);
    }
}
