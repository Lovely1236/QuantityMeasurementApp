package com.example.QuantityMeasurementApp;
public class QuantityMeasurementApp{
    //VALUE OBJECT
    public static class QuantityLength{
        private final double value;
        private final LengthUnit unit;
        public QuantityLength(double value, LengthUnit unit){
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (unit==null)
                throw new IllegalArgumentException("Unit cannot be null");
            this.value=value;
            this.unit=unit;
        }
        private double toBase(){
            return unit.convertToBaseUnit(value);
        }
        //CONVERSION
        public QuantityLength convertTo(LengthUnit target){
            if (target==null)
                throw new IllegalArgumentException("Target unit cannot be null");
            double base=toBase();
            double converted=target.convertFromBaseUnit(base);
            return new QuantityLength(converted, target);
        }

        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            if(!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if(source==null || target==null)
                throw new IllegalArgumentException("Units cannot be null");

            double base= source.convertToBaseUnit(value);
            return target.convertFromBaseUnit(base);
        }
        //ADDITION (UC6) 
        public QuantityLength add(QuantityLength other){
            if (other==null)
                throw new IllegalArgumentException("Other length cannot be null");
            double sumBase= this.toBase() + other.toBase();
            double result= this.unit.convertFromBaseUnit(sumBase);
            return new QuantityLength(result, this.unit);
        }
        //ADDITION WITH TARGET UNIT (UC7)
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit){
            if(other==null)
                throw new IllegalArgumentException("Other length cannot be null");

            if(targetUnit==null)
                throw new IllegalArgumentException("Target unit cannot be null");
            double sumBase= this.toBase() + other.toBase();
            double result= targetUnit.convertFromBaseUnit(sumBase);
            return new QuantityLength(result, targetUnit);
        }
        public static QuantityLength add(QuantityLength a,
                                         QuantityLength b,
                                         LengthUnit targetUnit) {

            if (a==null || b==null)
                throw new IllegalArgumentException("Lengths cannot be null");
            return a.add(b, targetUnit);
        }
        //EQUALITY
        @Override
        public boolean equals(Object obj){

            if (this== obj) return true;
            if (obj== null || getClass() != obj.getClass()) return false;

            QuantityLength other=(QuantityLength) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
        @Override
        public int hashCode(){
            return Double.hashCode(toBase());
        }
        @Override
        public String toString(){
            return value + " " + unit;
        }
    }
    //MAIN (demo)
    public static void main(String[] args){
        QuantityLength f= new QuantityLength(1, LengthUnit.FEET);
        QuantityLength i= new QuantityLength(12, LengthUnit.INCH);
        QuantityLength y=new QuantityLength(1, LengthUnit.YARDS);

        System.out.println(f.convertTo(LengthUnit.INCH));       // 12 INCH
        System.out.println(f.add(i, LengthUnit.FEET));          // 2 FEET
        System.out.println(i.equals(y));                        // false
        System.out.println(new QuantityLength(36, LengthUnit.INCH)
                .equals(new QuantityLength(1, LengthUnit.YARDS))); // true
    }
}