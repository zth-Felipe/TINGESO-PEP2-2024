package com.autofix.msrepairVehicles.controllers;

import com.autofix.msrepairVehicles.entities.GenerateRepairsEntity;
import com.autofix.msrepairVehicles.models.RepairsEntity;
import com.autofix.msrepairVehicles.models.vehiclesEntity;
import com.autofix.msrepairVehicles.services.GenerateRepairsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/repairs/repairVehicles")
public class GenerateRepairsController {

    @Autowired
    GenerateRepairsServices generateRepairsServices;

    @GetMapping("/PruebaVehicles")
    public ResponseEntity<Iterable<vehiclesEntity>> listVehicles() {
        return ResponseEntity.ok(generateRepairsServices.listVehicles());
    }

    @GetMapping("/PruebaRepairs")
    public ResponseEntity<List<RepairsEntity>> getGenerateRepairs(){
        return ResponseEntity.ok(generateRepairsServices.listRepairs());
    }

    @GetMapping("/")
    public ResponseEntity<List<GenerateRepairsEntity>> listGenerateRepairs() {
        List<GenerateRepairsEntity> generateRepairs = generateRepairsServices.getGenerateRepairs();
        return ResponseEntity.ok(generateRepairs);
    }

    @PostMapping("/{uso_bono}")
    public ResponseEntity<Map<String, Object>> saveGenerateRepair(@RequestBody GenerateRepairsEntity generateRepair, @PathVariable boolean uso_bono) {
        Map<String, Object> response = generateRepairsServices.saveGenerateRepairs(generateRepair, uso_bono);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idReparacion}")
    public ResponseEntity<GenerateRepairsEntity> getGenerateRepair(@PathVariable int idReparacion) {
        GenerateRepairsEntity generateRepair = generateRepairsServices.getGenerateRepairsById(idReparacion);
        return ResponseEntity.ok(generateRepair);
    }

    @GetMapping("/Report1")
    public ResponseEntity<List<Object[]>> getReport1() {
        List<Object[]> report = generateRepairsServices.generarReporte();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/Report2")
    public ResponseEntity<List<Object[]>> getReport2(@RequestParam int year, @RequestParam int month) {
        List<Object[]> report = generateRepairsServices.GenerarReporte2(year, month);
        return ResponseEntity.ok(report);
    }
}
