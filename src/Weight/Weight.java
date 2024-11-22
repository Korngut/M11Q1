package Weight;

public class Weight {
    private int _kilos;
    private int _grams;

    private static final int minimumWeight = 1;
    private static final int amountOfGramsInKilo = 1000;

    public Weight(int kilos, int grams) {
        if (grams >= 0) {  // Just ensure grams is non-negative
            this._kilos = kilos + grams / amountOfGramsInKilo;  // Convert excess grams into kilos
            this._grams = grams % amountOfGramsInKilo;  // Remainder grams after converting excess to kilos
        } else {
            // Handle invalid grams by setting them to zero or other appropriate value
            this._kilos = 0;
            this._grams = 0;
        }
    }

    public Weight(Weight other) {
        this._kilos = other._kilos;
        this._grams = other._grams;
    }

    public Weight(int totalGrams) {
        this._kilos = totalGrams / amountOfGramsInKilo;
        this._grams = totalGrams % amountOfGramsInKilo;
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
        return turnWeightToGrams(other) > turnWeightToGrams(this);
    }

    public boolean heavier(Weight other) {
        return turnWeightToGrams(other) > turnWeightToGrams(this);
    }

    public String toString() {
        return _kilos + "." + (_grams < 10 ? "0" + _grams : _grams);
    }

    public Weight add(int other) {
        if ((turnWeightToGrams(this) + other) / amountOfGramsInKilo < minimumWeight) {
            return this;
        } else {
            return new Weight((turnWeightToGrams(this) + other) / amountOfGramsInKilo, (turnWeightToGrams(this) + other) % amountOfGramsInKilo);
        }
    }

    private int turnWeightToGrams(Weight weight) {
        return this._grams + (this._kilos * amountOfGramsInKilo);
    }
}
