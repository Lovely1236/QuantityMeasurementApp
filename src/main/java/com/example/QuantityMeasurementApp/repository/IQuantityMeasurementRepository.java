package com.example.QuantityMeasurementApp.repository;

import com.example.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.example.QuantityMeasurementApp.exception.DatabaseException;
import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity) throws DatabaseException;

    List<QuantityMeasurementEntity> findAll() throws DatabaseException;
}