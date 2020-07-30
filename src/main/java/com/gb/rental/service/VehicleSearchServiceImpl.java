package com.gb.rental.service;

import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.vehicle.VehicleType;
import com.gb.rental.repository.VehicleInventoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleSearchServiceImpl implements VehicleSearchService {

    @Override
    public List<HireableVehicle> searchByType(VehicleType vehicleType, String city,
                                              LocalDateTime fromDate, LocalDateTime toDate) {
        List<HireableVehicle> hireableVehicles =
                VehicleInventoryRepository.vehicleInventoryList
                        .stream()
                        .filter(vehicleInventory ->
                                vehicleInventory.getVehicle().getVehicleType() == vehicleType
                                        && vehicleInventory.getVehicle().getParkedLocation().getAddress()
                                        .getCity().equalsIgnoreCase(city)
                                        && !(
                                        fromDate.isBefore(vehicleInventory.getDueDate())
                                                && toDate.isAfter(vehicleInventory.getFromDate()))
                        ).map(vehicleInventory -> vehicleInventory.getVehicle())
                        .collect(Collectors.toList());
        return hireableVehicles;
    }

    @Override
    public List<HireableVehicle> searchByModel(String make, String model, String city,
                                               LocalDateTime fromDate, LocalDateTime toDate) {
        List<HireableVehicle> hireableVehicles =
                VehicleInventoryRepository.vehicleInventoryList
                        .stream()
                        .filter(vehicleInventory ->
                                vehicleInventory.getVehicle().getMake().equalsIgnoreCase(make)
                                        && vehicleInventory.getVehicle().getModel().equalsIgnoreCase(model)
                                        && vehicleInventory.getVehicle().getParkedLocation().getAddress()
                                        .getCity().equalsIgnoreCase(city)
                                        && !(
                                        fromDate.isBefore(vehicleInventory.getDueDate())
                                                && toDate.isAfter(vehicleInventory.getFromDate()))
                        ).map(vehicleInventory -> vehicleInventory.getVehicle())
                        .collect(Collectors.toList());
        return hireableVehicles;
    }

    @Override
    public List<HireableVehicle> searchByType(int seats, String city,
                                              LocalDateTime fromDate, LocalDateTime toDate) {
        List<HireableVehicle> hireableVehicles =
                VehicleInventoryRepository.vehicleInventoryList
                        .stream()
                        .filter(vehicleInventory ->
                                vehicleInventory.getVehicle().getNumberOfSeats() >= (seats)
                                        && vehicleInventory.getVehicle().getParkedLocation().getAddress()
                                        .getCity().equalsIgnoreCase(city)
                                        && !(
                                        fromDate.isBefore(vehicleInventory.getDueDate())
                                                && toDate.isAfter(vehicleInventory.getFromDate()))
                        ).map(vehicleInventory -> vehicleInventory.getVehicle())
                        .collect(Collectors.toList());
        return hireableVehicles;
    }
}