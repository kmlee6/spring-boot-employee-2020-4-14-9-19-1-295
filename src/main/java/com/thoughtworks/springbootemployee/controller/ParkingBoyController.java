package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-boys")
public class ParkingBoyController {
    private ParkingBoyService parkingBoyService;

    @Autowired
    public ParkingBoyController(ParkingBoyService parkingBoyService){
        this.parkingBoyService = parkingBoyService;
    }

    @PostMapping
    public ParkingBoy addParkingBoy(@RequestBody ParkingBoy newParkingBoy) {
        return parkingBoyService.addParkingBoy(newParkingBoy);
    }

    @GetMapping
    public List<ParkingBoy> getAll() {
        return parkingBoyService.getAll();
    }
}
