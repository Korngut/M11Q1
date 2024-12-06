/**
This class represents a Baby object.

@version 2025a
@author Ori Korngut
 */

public class Baby {
    private String _firstName;
    private String _lastName;
    private String _id;
    private Date _dateOfBirth;
    private Weight _birthWeight;
    private Weight _currentWeight;

    private static final int MIN_WEIGHT_IN_GRAMS = 1000;
    private static final int AMOUNT_OF_GRAMS_IN_KILO = 1000;


    private static final int ID_LENGTH = 9;
    private static final String DEFAULT_ID = "000000000";


    /**
    Baby constructor - If the given id and birthWeightInGrams are valid - creates a new Baby object with the parameters
    otherwise, if the id is not valid creates the Baby with id = "000000000" and all other parameters.

    @param fName the first name of the baby.
    @param lName the last name of the baby.
    @param id the id of the baby (9 characters).
    @param day the day of the baby's birth.
    @param month the month of the baby's birth.
    @param year the year of the baby's birth.
    @param birthWeightInGrams the weight of the baby at birth in grams (should be minimum 1KG).
     */
    public Baby (String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams){
        this._firstName = fName;
        this._lastName = lName;

        if (id.length()== ID_LENGTH){
            this._id = id;
        }
        else {
            this._id = DEFAULT_ID; //Changes the id to a default value in case of invalid input.
        }

        this._dateOfBirth = new Date(day, month, year);
        this._birthWeight = new Weight(birthWeightInGrams);
        this._currentWeight = new Weight(birthWeightInGrams);
    }

    /**
    Copy constructor:

    @param other the baby to be copied.
     */
    public Baby(Baby other) {
        this._firstName = other.getFirstName();
        this._lastName = other.getLastName();
        this._id = other.getId();
        this._dateOfBirth = other.getDateOfBirth();
        this._birthWeight = other.getBirthWeight();
        this._currentWeight = other.getCurrentWeight();
    }

    /**
    Sets the current weight if the given parameter is valid.

    @param weightToSet the new current weight.
     */
    public void setCurrentWeight(Weight weightToSet) {
        if (turnWeightToGrams(weightToSet) > MIN_WEIGHT_IN_GRAMS) { //Checks if the weight value is correct.
            this._currentWeight = weightToSet;
        }
    }

    /**
    Gets the first name.

    @return the first name of this baby.
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
    Gets the last name.

    @return the last name of this baby.
     */
    public String getLastName() {
        return _lastName;
    }

    /**
    Gets the date of birth.

    @return the date of birth of this baby.
     */
    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    /**
    Gets the id.

    @return the id of this baby.
     */
    public String getId() {
        return _id;
    }

    /**
    Gets the birth weight.

    @return the weight of this baby at birth.
     */
    public Weight getBirthWeight() {
        return _birthWeight;
    }

    /**
    Gets the current weight.

    @return the current weight of this baby.
     */
    public Weight getCurrentWeight() {
        return _currentWeight;
    }

    /**
    Returns a String that represents this baby.

    @return a String that represents this baby.
     */
    public String toString() {
        return (
                ("Name:" + this._firstName + " " + this._lastName + "\n") +
                ("Id:" + this._id + "\n") +
                ("Date of Birth:" + this._dateOfBirth.toString() + "\n") +
                ("Birth Weight:" + this._birthWeight.toString() + "\n") +
                ("Current Weight:" + this._currentWeight.toString() + "\n")
        );
    }

    /**
    Checks if two babies are twins.

    @param other the baby to compare this baby with.
    @return true if the babies are twins.
     */
    public boolean areTwins (Baby other) {
        return other._lastName.equals(this._lastName) &&
                !other._firstName.equals(this._firstName) &&
                !other._id.equals(this._id) &&
                (other._dateOfBirth == this._dateOfBirth || other._dateOfBirth == this._dateOfBirth.tomorrow() || other._dateOfBirth.tomorrow() == this._dateOfBirth); //Checks all three settings (including the next day) to see if they are twins.
    }

    /**
    Checks if two babies are the same. Two babies are consider the same if they have the same first and last name, same ID and similar date of birth.

    @param other - the baby to compare this baby with.
    @return true if the babies are the same.
     */
    public boolean equals (Baby other) { //Compares all parameters
        return other._lastName.equals(this._lastName) &&
                other._firstName.equals(this._firstName) &&
                other._id.equals(this._id) &&
                (other._dateOfBirth == this._dateOfBirth);
    }

    /**
    Updates the baby's current weight by adding the additional grams. If the sum of the current weight and the additional grams is negative, the baby's current weight will remain unchanged.

    @param grams the number of grams to add to he baby's current weight (can be negative).
     */
    public void updateCurrentWeight (int grams) {
        this._currentWeight.add(grams); //Uses the weight method to add the weight
    }

    /**
    Checks if the date of birth of this baby is before than the date of birth of another baby.

    @param other baby to compare this baby's date of birth to.
    @return true if the date of birth of this baby is before the date of birth of the other baby.
     */
    public boolean older (Baby other){
        return (this._dateOfBirth.before(other._dateOfBirth)); //Uses the Date method to check this.
    }

    /**
    Checks if the date of birth of this baby is before than the date of birth of another baby.

    @param other baby to compare this baby's date of birth to.
    @return true if the date of birth of this baby is before the date of birth of the other baby.
     */
    public boolean heavier (Baby other){
        if (turnWeightToGrams(this._currentWeight) > turnWeightToGrams(other._currentWeight)) { //Converts the two objects to grams and checks if one is greater than the other.
            return true;
        }
        else {
            return false;
        }
    }

    /**
    Checks if the current weight of this baby is within the valid range.

    @param numOfDays of days passed since the baby was born.
    @return 1- If the date given as a parameter is less than a week or more than a year. 2- If the progress is not correct according to the rules. 3- If the progress is correct according to the rules.
     */
    public int isWeightInValidRange(int numOfDays) {
        if (numOfDays < 1 || numOfDays > 365) {
            return 1;
        }

        int birthWeightInGrams = turnWeightToGrams(this._birthWeight);
        int currentWeightInGrams = turnWeightToGrams(this._currentWeight);

        double expectedWeightInGrams = birthWeightInGrams;

        if (numOfDays <= 7) {
            double weightLoss = birthWeightInGrams * 0.10 * (numOfDays / 7.0); // Takes up 10 percent of the weight
            expectedWeightInGrams -= weightLoss; }

        else if (numOfDays <= 60) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10; // Summarize the tests performed and perform the next test.
            expectedWeightInGrams += 30 * (numOfDays - 7); }

        else if (numOfDays <= 120) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10; // Summarize the tests performed and perform the next test.
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * (numOfDays - 60); }

        else if (numOfDays <= 240) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10; // Summarize the tests performed and perform the next test.
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * 60;
            expectedWeightInGrams += 16 * (numOfDays - 120); }

        else {
            expectedWeightInGrams -= birthWeightInGrams * 0.10; // Summarize the tests performed and perform the next test.
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * 60;
            expectedWeightInGrams += 16 * 120;
            expectedWeightInGrams += 8 * (numOfDays - 240);
        }

        if (currentWeightInGrams < expectedWeightInGrams) {
            return 2;
        } else {
            return 3;
        }
    }

    //The function converts a weight object to grams only.
    private int turnWeightToGrams(Weight weight) {
        return weight.getGrams() + (weight.getKilos() * AMOUNT_OF_GRAMS_IN_KILO); //Converts kilos to grams and adds to the sum of grams in the object.
    }

}
