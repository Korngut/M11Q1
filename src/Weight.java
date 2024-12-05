/*
This class represents a Weight object

@version 2025a
@author Ori Korngut
 */

public class Weight {
    private int _kilos;
    private int _grams;

    private static final int MINIMUM_WEIGHT_GRAMS = 0;
    private static final int MINIMUM_WEIGHT_KILOS = 1;
    private static final int MAXIMUM_GRAMS = 999;
    private static final int AMOUNT_OF_GRAMS_IN_KILO = 1000;

    /*
    Weight constructor - If the given weight is valid - creates a new Weight object, otherwise if one of the parameters in not valid initialize it to 1.

    @param kilos - the number of kilos in Weight (greater or equal to 1)
    @param grams - the number of grams in Weight (0-999)
     */
    public Weight(int kilos, int grams) {
        if (kilos >= MINIMUM_WEIGHT_KILOS && grams >= MINIMUM_WEIGHT_GRAMS && grams <= MAXIMUM_GRAMS) {
            this._kilos = kilos;
            this._grams = grams;
        }
        if (grams > MAXIMUM_GRAMS || grams < MINIMUM_WEIGHT_GRAMS || kilos < MINIMUM_WEIGHT_KILOS){
            this._grams = MINIMUM_WEIGHT_GRAMS;
            this._kilos = MINIMUM_WEIGHT_KILOS;
        }
    }

    /*
    Copy constructor

    @param other - the weight to be copied
     */
    public Weight(Weight other) {
        this._kilos = other._kilos;
        this._grams = other._grams;
    }

    /*
    Constructor with only one parameter

    @param totalGrams - the total number of grams
     */
    public Weight(int totalGrams) {
        this._kilos = totalGrams / AMOUNT_OF_GRAMS_IN_KILO;
        this._grams = totalGrams % AMOUNT_OF_GRAMS_IN_KILO;
    }

    /*
    Gets the kilos

    @return the number of kilos for this weight
     */
    public int getKilos() {
        return _kilos;
    }


    /*
    Gets the grams

    @return the number of grams for this weight
     */
    public int getGrams() {
        return _grams;
    }


    /*
    Checks if two weights are the same

    @param other - the weight to compare this weight to
    @return true if the weights are the same
     */
    public boolean equals(Weight other) {
        return this._grams == other.getGrams() && this._kilos == other.getKilos();
    }

    /*
    Checks if this weight is lighter than another weight

    @param other - weight to compare this weight to.
    @return true if this weight is lighter than the other weight.
     */
    public boolean lighter(Weight other) {
        return turnWeightToGrams(other) > turnWeightToGrams(this); // `other` is heavier
    }

    /*
    Checks if this weight is heavier than another weight

    @param other - weight to compare this weight to.
    @return true if this weight is heavier than the other weight.
     */
    public boolean heavier(Weight other) {
        return turnWeightToGrams(this) > turnWeightToGrams(other); // `this` is heavier
    }

    /*
    Returns a String that represents this weight

    @return a String that represents this weight in the following format: kiols.grmas(3 digits) for example: 4.07 or 3.055 or 4.005.
     */
    public String toString() {
        double numOfGramsIn = turnWeightToGrams(this);
        numOfGramsIn = numOfGramsIn/1000;
        return "" + numOfGramsIn;
    }

    /*
    Return a new weight with the additional grams given as parameter

    @param grams - the additional grams to add to the new returned weight.
    @return a new weight with the additional grams given as parameter.
     */
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
