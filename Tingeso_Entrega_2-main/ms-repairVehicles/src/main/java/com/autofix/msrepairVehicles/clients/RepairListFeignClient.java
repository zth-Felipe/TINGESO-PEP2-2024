package com.autofix.msrepairVehicles.clients;

import com.autofix.msrepairVehicles.models.RepairsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-repairList", path = "/api/v1/repairList")
public interface RepairListFeignClient {

    @GetMapping("/")
    List<RepairsEntity> listRepairs();

    @PostMapping("/")
    RepairsEntity saveRepair(@RequestBody RepairsEntity repair);

    @GetMapping("/types")
    String[] getTypes();

    @GetMapping("/{id}")
    RepairsEntity getRepairById(@PathVariable Long id);

    @PutMapping("/")
    RepairsEntity updateRepair(@RequestBody RepairsEntity repair);
}
