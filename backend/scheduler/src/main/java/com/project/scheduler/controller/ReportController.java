package com.project.scheduler.controller;


import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @PostMapping
    public ResponseEntity<ReportResponse> createReportSettings(@RequestBody ReportRequest settings) {
        ReportResponse response = null;
        return ResponseEntity.ok(response);
    }
}
