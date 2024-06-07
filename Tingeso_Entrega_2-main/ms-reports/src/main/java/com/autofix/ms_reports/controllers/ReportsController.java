package com.autofix.ms_reports.controllers;

import com.autofix.ms_reports.Services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @GetMapping("/Report1")
    public List<Object[]> getReport1() {
        return reportsService.getReport1();
    }

    @GetMapping("/Report2")
    public List<Object[]> getReport2(@RequestParam int year, @RequestParam int month ) {
        return reportsService.getReport2(year, month);
    }
}
