package com.project.scheduler.controller;


import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import com.project.scheduler.dto.result.ResultDTO;
import com.project.scheduler.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @PostMapping
    public ResponseEntity<ResultDTO<ReportResponse>> createReport(@RequestBody @Valid ReportRequest request) {
        ResultDTO<ReportResponse> response = service.createReport(request);
        if(response.getCode() == 200) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllConfigs() {
        return null;
    }
}
