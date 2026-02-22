package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp {   // LENGTH CLASS  (UC1–UC8)
    // =====================================================
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;
     public QuantityLength(double value, LengthUnit unit) {
            if(!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if(unit== null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value= value;
            this.unit= unit;
        }
        private double toBase() {
            return unit.convertToBaseUnit(value);   // convert to feet
        }

        // ---------- CONVERSION ----------
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base = toBase();
            double converted = targetUnit.convertFromBaseUnit(base);

            return new QuantityLength(converted, targetUnit);
        }

        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            double base = source.convertToBaseUnit(value);
            return target.convertFromBaseUnit(base);
        }

        // ---------- ADDITION (implicit unit = this.unit) ----------
        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            double sumBase = this.toBase() + other.toBase();
            double result = this.unit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, this.unit);
        }

        // ---------- ADDITION WITH TARGET UNIT ----------
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other length cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumBase = this.toBase() + other.toBase();
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new QuantityLength(result, targetUnit);
        }

        // ---------- EQUALITY ----------
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

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
    }// MAIN METHOD (Optional demo for UC8 + UC9)
    // =====================================================
    public static void main(String[] args) {
        // ---------- LENGTH DEMO ----------
        QuantityLength f= new QuantityLength(1, LengthUnit.FEET);
        QuantityLength i=new QuantityLength(12, LengthUnit.INCH);

        System.out.println("Length equality: " + f.equals(i));      // true
        System.out.println("Length add FEET: " + f.add(i));         // 2 FEET
        System.out.println("Length add YARDS: " + f.add(i, LengthUnit.YARDS));

        // ---------- WEIGHT DEMO ----------
        QuantityWeight kg = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight g=new QuantityWeight(1000, WeightUnit.GRAM);
        QuantityWeight lb= new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println("Weight equality kg=g: " + kg.equals(g));   // true
        System.out.println("Weight equality kg=lb: " + kg.equals(lb)); // true
        System.out.println("Weight to grams: " + kg.convertTo(WeightUnit.GRAM));
        System.out.println("Weight add kg+g: " + kg.add(g));
        System.out.println("Weight add -> grams: " + kg.add(g, WeightUnit.GRAM));
    }
}