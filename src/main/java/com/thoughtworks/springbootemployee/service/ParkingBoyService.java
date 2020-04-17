package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingBoyService {
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    public ParkingBoyService(ParkingBoyRepository parkingBoyRepository){
        this.parkingBoyRepository = parkingBoyRepository;
    }

    public List<ParkingBoy> getAll() {
        return parkingBoyRepository.findAll();
    }

    public ParkingBoy addParkingBoy(ParkingBoy newParkingBoy) {
        return parkingBoyRepository.save(newParkingBoy);
    }
}
