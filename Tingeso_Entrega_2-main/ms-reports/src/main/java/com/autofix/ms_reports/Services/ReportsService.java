package com.autofix.ms_reports.Services;

import com.autofix.ms_reports.clients.RepairVehiclesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    RepairVehiclesFeignClient repairVehiclesFeignClient;

    public List<Object[]> getReport1() {
        return repairVehiclesFeignClient.getReport1();
    }

    public List<Object[]> getReport2(int year, int month) {
        return repairVehiclesFeignClient.getReport2(year, month);
    }

}
