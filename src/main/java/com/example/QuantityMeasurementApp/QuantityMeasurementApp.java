package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp {
    public enum LengthUnit { // enum
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);
        private final double toFeetFactor;
        LengthUnit(double toFeetFactor){
            this.toFeetFactor = toFeetFactor;
        }
        double toFeet(double value) {
            return value * toFeetFactor;
        }
        double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }
    public static class QuantityLength{ // Value Object
        private final double value;
        private final LengthUnit unit;
        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }
        private double toFeet() {
            return unit.toFeet(value);
        }
        public QuantityLength convertTo(LengthUnit target){
            if (target== null)
                throw new IllegalArgumentException("Target unit cannot be null");
            double feet= toFeet();
            double converted= target.fromFeet(feet);
            return new QuantityLength(converted, target);
        }
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target){
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");
            double feet = source.toFeet(value);
            return target.fromFeet(feet);
        }
        //UC6 ADDITION
        public QuantityLength add(QuantityLength other) {
            if (other== null)
                throw new IllegalArgumentException("Other length cannot be null");
            double sumFeet= this.toFeet() + other.toFeet();
            double result= this.unit.fromFeet(sumFeet);

            return new QuantityLength(result, this.unit);
        }
        public static QuantityLength add(QuantityLength a, QuantityLength b) {

            if (a== null || b== null)
                throw new IllegalArgumentException("Lengths cannot be null");
            return a.add(b);
        }
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2){
            return new QuantityLength(v1, u1)
                    .add(new QuantityLength(v2, u2));
        }
        //EQUALITY
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other= (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
        @Override
        public int hashCode(){
            return Double.hashCode(toFeet());
        }
        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
    //DEMO API 
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {
      double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }
    public static void demonstrateLengthConversion(QuantityLength length,
                                                   LengthUnit to) {
        QuantityLength converted = length.convertTo(to);
        System.out.println(length + " -> " + converted);
    }
    public static boolean demonstrateLengthEquality(QuantityLength a,
                                                    QuantityLength b){
        boolean equal = a.equals(b);
        System.out.println("The two length measurements are "
                + (equal ? "equal." : "not equal."));
        return equal;
    }
    public static void demonstrateLengthComparison(double v1, LengthUnit u1,
                                                   double v2, LengthUnit u2){
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        demonstrateLengthEquality(q1, q2);
    }
    public static QuantityLength demonstrateLengthAddition(
            QuantityLength a, QuantityLength b){
        QuantityLength result = a.add(b);
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }
    // MAIN
    public static void main(String[] args){
        demonstrateLengthConversion(1, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36, LengthUnit.INCH, LengthUnit.YARDS);
        demonstrateLengthConversion(1, LengthUnit.CENTIMETERS, LengthUnit.INCH);
        
        QuantityLength f = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength i = new QuantityLength(12, LengthUnit.INCH);
        QuantityLength y = new QuantityLength(1, LengthUnit.YARDS);
        demonstrateLengthComparison(1, LengthUnit.FEET, 12, LengthUnit.INCH);
        
        demonstrateLengthAddition(f, i);
        demonstrateLengthAddition(i, f);
        demonstrateLengthAddition(y, f);
    }
}