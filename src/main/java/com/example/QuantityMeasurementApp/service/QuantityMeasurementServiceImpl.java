package com.example.QuantityMeasurementApp.service;

import com.example.QuantityMeasurementApp.dto.QuantityDTO;
import com.example.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurement().equals(q2.getMeasurement())) {
            throw new RuntimeException("Cross category comparison not allowed");
        }

        return q1.getValue() == q2.getValue();
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit) {

        return new QuantityDTO(source.getValue(), targetUnit,
                source.getMeasurement());
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurement().equals(q2.getMeasurement())) {
            throw new RuntimeException("Cross category addition not allowed");
        }

        double result = q1.getValue() + q2.getValue();

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurement());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() - q2.getValue();

        return new QuantityDTO(result, q1.getUnit(), q1.getMeasurement());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if (q2.getValue() == 0) {
            throw new RuntimeException("Division by zero");
        }

        return q1.getValue() / q2.getValue();
    }
}