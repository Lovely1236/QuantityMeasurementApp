package com.example.QuantityMeasurementApp;

/**
 * Hello world!
 */
public class QuantityMeasurementApp {

    // Inner immutable class
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference → true
            if (this == obj)
                return true;

            // Null or different type → false
            if (obj == null || getClass() != obj.getClass())
                return false;

            // Safe cast
            Feet other = (Feet) obj;

            // Compare double safely
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method to test manually
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Equal (" + f1.equals(f2) + ")");
    }
}
