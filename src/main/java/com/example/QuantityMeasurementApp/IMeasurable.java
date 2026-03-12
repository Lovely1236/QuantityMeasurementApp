package com.example.QuantityMeasurementApp;
/*
 * Functional Interface to indicate whether a measurable unit
 * supports arithmetic operations.
 */
@FunctionalInterface
interface SupportsArithmetic{
    boolean isSupported();
}
/*
 * Base interface for all measurable units.
 * Provides conversion methods and optional arithmetic support validation.
 */
public interface IMeasurable{

    // Default lambda -> all units support arithmetic by default
    SupportsArithmetic supportsArithmetic = () -> true;

    // ---------- Mandatory conversion methods ----------

    String getUnitName();
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    // ---------- Optional arithmetic capability ----------
    //Indicates whether arithmetic operations are supported.
     
    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }
    /**
     * Validates if an arithmetic operation is supported.
     * Default implementation allows all operations.
     * Units like TemperatureUnit can override this.
     */
    default void validateOperationSupport(String operation){
        // default: allow operations
    }
}