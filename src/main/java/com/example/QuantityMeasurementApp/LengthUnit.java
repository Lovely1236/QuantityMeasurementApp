package com.example.QuantityMeasurementApp;
public enum LengthUnit{
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.393701 / 12.0);
    private final double toFeetFactor;
    LengthUnit(double toFeetFactor){
        this.toFeetFactor=toFeetFactor;
    }
    public double convertToBaseUnit(double value){  
    	// convert this unit → base unit (feet)
        return value*toFeetFactor;
    }
    public double convertFromBaseUnit(double baseValue){
    	// convert base unit (feet) → this unit    
    	return baseValue / toFeetFactor;
    }
    public double getConversionFactor(){
        return toFeetFactor;
    }
}