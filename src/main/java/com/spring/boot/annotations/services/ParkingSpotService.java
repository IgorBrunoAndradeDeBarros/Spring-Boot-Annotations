package com.spring.boot.annotations.services;

import com.spring.boot.annotations.models.ParkingSpotModel;
import com.spring.boot.annotations.repositories.ParkingSpotRepository;
import com.spring.boot.annotations.utils.ParkingSpotFormatter;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;
    final ParkingSpotFormatter parkingSpotFormatter;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository,
                              ParkingSpotFormatter parkingSpotFormatter) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingSpotFormatter = parkingSpotFormatter;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        String formattedNumber = parkingSpotFormatter.formatCode(parkingSpotModel.getParkingSpotNumber());
        parkingSpotModel.setParkingSpotNumber(formattedNumber);
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }
}