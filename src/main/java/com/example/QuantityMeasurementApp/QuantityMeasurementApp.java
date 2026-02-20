package com.example.QuantityMeasurementApp;

public class QuantityMeasurementApp {
    // *********** ENUM ***********
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);   // convert cm -> inch -> feet
        private final double toFeetFactor;
     
        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }
        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }
    // *********** GENERIC CLASS **************
    public static class QuantityLength {
      private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }
    //  MAIN 
    public static void main(String[] args) {

        System.out.println(
                new QuantityLength(1, LengthUnit.YARDS)
                        .equals(new QuantityLength(3, LengthUnit.FEET))
        );
        System.out.println(
                new QuantityLength(1, LengthUnit.CENTIMETERS)
                        .equals(new QuantityLength(0.393701, LengthUnit.INCH))
        );
    }
}
