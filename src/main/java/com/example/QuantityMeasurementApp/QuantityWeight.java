package com.example.QuantityMeasurementApp;
public class QuantityWeight{
    private final double value;
    private final WeightUnit unit;
    public QuantityWeight(double value, WeightUnit unit){
        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }
    // ---------- INTERNAL ----------
    private double toBase() {
        return unit.convertToBaseUnit(value); // convert to kilograms
    }    // ---------- CONVERSION ----------
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit== null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base= toBase();
        double converted= targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(converted, targetUnit);
    }    // ---------- ADDITION (result in this.unit) ----------
    public QuantityWeight add(QuantityWeight other){
        if (other== null)
            throw new IllegalArgumentException("Other weight cannot be null");

        double sumBase= this.toBase() + other.toBase();
        double result= this.unit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(result, this.unit);
    }    // ADDITION WITH TARGET UNIT -
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if(other== null)
            throw new IllegalArgumentException("Other weight cannot be null");

        if(targetUnit== null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumBase= this.toBase() + other.toBase();
        double result= targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(result, targetUnit);
    }

    // ---------- STATIC CONVERT ----------
    public static double convert(double value,
                                 WeightUnit source,
                                 WeightUnit target) {

        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }   // ---------- EQUALITY ----------
    @Override
    public boolean equals(Object obj){

        if (this== obj) return true;
        if (obj== null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }
    @Override
    public String toString() {
        return value + " " + unit;
    }
}