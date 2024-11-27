public class Weight {
    private int _kilos;
    private int _grams;

    private static final int MINIMUM_WEIGHT_GRAMS = 0;
    private static final int MINIMUM_WEIGHT_KILOS = 1;
    private static final int MAXIMUM_GRAMS = 999;
    private static final int AMOUNT_OF_GRAMS_IN_KILO = 1000;

    public Weight(int kilos, int grams) {
        if (kilos >= MINIMUM_WEIGHT_KILOS && grams >= MINIMUM_WEIGHT_GRAMS && grams <= MAXIMUM_GRAMS) {
            this._kilos = kilos;
            this._grams = grams;
        }
        if (grams > MAXIMUM_GRAMS || grams < MINIMUM_WEIGHT_GRAMS){
            this._grams = MINIMUM_WEIGHT_GRAMS;
        }
        if (kilos < MINIMUM_WEIGHT_KILOS){
            this._kilos = MINIMUM_WEIGHT_KILOS;
        }
    }

    public Weight(Weight other) {
        this._kilos = other._kilos;
        this._grams = other._grams;
    }

    public Weight(int totalGrams) {
        this._kilos = totalGrams / AMOUNT_OF_GRAMS_IN_KILO;
        this._grams = totalGrams % AMOUNT_OF_GRAMS_IN_KILO;
    }

    public int getKilos() {
        return _kilos;
    }

    public void setKilos(int _kilos) {
        this._kilos = _kilos;
    }

    public int getGrams() {
        return _grams;
    }

    public void setGrams(int _grams) {
        this._grams = _grams;
    }

    public boolean equals(Weight other) {
        return this._grams == other.getGrams() && this._kilos == other.getKilos();
    }

    public boolean lighter(Weight other) {
        return turnWeightToGrams(other) > turnWeightToGrams(this); // `other` is heavier
    }

    public boolean heavier(Weight other) {
        return turnWeightToGrams(this) > turnWeightToGrams(other); // `this` is heavier
    }

    public String toString() {
        double numOfGramsIn = turnWeightToGrams(this);
        numOfGramsIn = numOfGramsIn/1000;
        return "" + numOfGramsIn;
    }



    public Weight add(int other) {
        if ((turnWeightToGrams(this) + other) / AMOUNT_OF_GRAMS_IN_KILO < MINIMUM_WEIGHT_KILOS) {
            return this;
        } else {
            return new Weight((turnWeightToGrams(this) + other) / AMOUNT_OF_GRAMS_IN_KILO, (turnWeightToGrams(this) + other) % AMOUNT_OF_GRAMS_IN_KILO);
        }
    }

    private int turnWeightToGrams(Weight weight) {
        return weight._grams + (weight._kilos * AMOUNT_OF_GRAMS_IN_KILO);
    }
}
