package com.example.QuantityMeasurementApp;

import com.example.QuantityMeasurementApp.controller.*;
import com.example.QuantityMeasurementApp.repository.*;
import com.example.QuantityMeasurementApp.service.*;

public class QuantityMeasurementApp {

    private static QuantityMeasurementApp instance;

    public final QuantityMeasurementController controller;

    private QuantityMeasurementApp() {

        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    public static QuantityMeasurementApp getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }

        return instance;
    }
}