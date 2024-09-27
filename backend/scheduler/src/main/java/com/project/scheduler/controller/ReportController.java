package com.project.scheduler.controller;


import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import com.project.scheduler.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportRequest settings) {
        ReportResponse response = null;
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllConfigs() {
        return null;
    }
}
