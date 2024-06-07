package com.autofix.msrepairList.controllers;

import com.autofix.msrepairList.entities.RepairsEntity;
import com.autofix.msrepairList.services.RepairsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repairList")
public class RepairsController {
    @Autowired
    RepairsServices repairsServices;

    @GetMapping("/")
    public ResponseEntity<List<RepairsEntity>> listRepairs() {
        List<RepairsEntity> repairs = repairsServices.getRepairs();
        return ResponseEntity.ok(repairs);
    }

    @PostMapping("/")
    public ResponseEntity<RepairsEntity> saveRepair(@RequestBody RepairsEntity repair) {
        RepairsEntity repairNew = repairsServices.saveRepair(repair);
        return ResponseEntity.ok(repairNew);
    }

    @GetMapping("/types")
    public ResponseEntity<String[]> getTypes() {
        String[] types = repairsServices.getTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairsEntity> getRepairById(@PathVariable Long id) {
        RepairsEntity repair = repairsServices.getRepairById(id);
        if (repair != null) {
            return ResponseEntity.ok(repair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<RepairsEntity> updateRepair( @RequestBody RepairsEntity repair) {
        RepairsEntity repairNew = repairsServices.updateRepair(repair);
        return ResponseEntity.ok(repairNew);
    }


}
