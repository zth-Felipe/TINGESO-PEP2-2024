package com.autofix.ms_reports.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-repairVehicles", path = "/api/v1/repairs/repairVehicles")
public interface RepairVehiclesFeignClient {

    @GetMapping("/Report1")
    List<Object[]> getReport1();

    @GetMapping("/Report2")
    List<Object[]> getReport2(@RequestParam int year, @RequestParam int month);

}
