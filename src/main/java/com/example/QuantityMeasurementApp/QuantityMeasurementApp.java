package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp{ //enum
    public enum LengthUnit{
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);
        private final double toFeetFactor;
        LengthUnit(double toFeetFactor){
            this.toFeetFactor = toFeetFactor;
        }
        double toFeet(double value){
            return value * toFeetFactor;
        }
        double fromFeet(double feetValue){
            return feetValue / toFeetFactor;
        }
    }
    public static class QuantityLength{ // Value Object
        private final double value;
        private final LengthUnit unit;
        public QuantityLength(double value, LengthUnit unit){
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }
        private double toFeet(){
            return unit.toFeet(value);
        }
        public QuantityLength convertTo(LengthUnit target){ // UC5 conversion
            if (target == null)
                throw new IllegalArgumentException("Target unit cannot be null");
            double feet = toFeet();
            double converted = target.fromFeet(feet);
            
            return new QuantityLength(converted, target);
        }
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target){
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (source==null || target==null)
                throw new IllegalArgumentException("Units cannot be null");

            double feet= source.toFeet(value);
            return target.fromFeet(feet);
        } //  UC6 ADDITION (result in first operand unit)
        public QuantityLength add(QuantityLength other){
            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");
            double sumFeet = this.toFeet() + other.toFeet();
            double result = this.unit.fromFeet(sumFeet);

            return new QuantityLength(result, this.unit);
        }
        //UC7 ADDITION WITH TARGET UNIT
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit){
            if (other==null)
                throw new IllegalArgumentException("Other length cannot be null");
            if (targetUnit==null)
                throw new IllegalArgumentException("Target unit cannot be null");
            double sumFeet = this.toFeet() + other.toFeet();
            double result = targetUnit.fromFeet(sumFeet);
            
            return new QuantityLength(result, targetUnit);
        }// optional static overloads
        public static QuantityLength add(QuantityLength a,
                                         QuantityLength b,
                                         LengthUnit targetUnit){
            if(a==null || b==null)
                throw new IllegalArgumentException("Lengths cannot be null");
            return a.add(b, targetUnit);
        }
        //EQUALITY 
        @Override
        public boolean equals(Object obj){
            if (this==obj) return true;
            if (obj==null || getClass() != obj.getClass()) return false;

            QuantityLength other= (QuantityLength)obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
        @Override
        public int hashCode(){
            return Double.hashCode(toFeet());
        }
        @Override
        public String toString(){
            return value + " " + unit;
        }
    }
                       // DEMO 
    public static QuantityLength demonstrateLengthAddition(
            QuantityLength a, QuantityLength b, LengthUnit target){
        QuantityLength result = a.add(b, target);
        System.out.println(a + " + " + b + " -> " + result);
        return result;
    }
    public static void main(String[] args){
        QuantityLength f= new QuantityLength(1, LengthUnit.FEET);
        QuantityLength i= new QuantityLength(12, LengthUnit.INCH);

        demonstrateLengthAddition(f, i, LengthUnit.FEET);
        demonstrateLengthAddition(f, i, LengthUnit.INCH);
        demonstrateLengthAddition(f, i, LengthUnit.YARDS);
    }
}