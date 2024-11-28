/*
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
    private static final int MIN_DAY_IN_YEAR = 1;
    private static final int MAX_DAY_IN_YEAR = 365;

    /*
    Baby constructor - If the given id and birthWeightInGrams are valid - creates a new Baby object with the parameters
    otherwise, if the id is not valid creates the Baby with id = "000000000" and all other parameters.

    @param fname - the first name of the baby.
    @param lname - the last name of the baby.
    @param id - the id of the baby (9 characters).
    @param day - the day of the baby's birth.
    @param month - the month of the baby's birth.
    @param year - the year of the baby's birth.
    @param birthWeightInGrams - the weight of the baby at birth in grams (should be minimum 1KG).
     */
    public Baby (String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams){
        this._firstName = fName;
        this._lastName = lName;

        if (id.length()== 9){
            this._id = id;
        }
        else {
            this._id = "000000000";
        }

        this._dateOfBirth = new Date(day, month, year);
        this._birthWeight = new Weight(birthWeightInGrams);
        this._currentWeight = new Weight(birthWeightInGrams);
    }

    /*
    Copy constructor:

    @param other - the baby to be copied.
     */
    public Baby (Baby other){
        this._firstName = other._firstName;
        this._lastName = other._lastName;
        this._id = other._id;
        this._dateOfBirth = other._dateOfBirth;
        this._birthWeight = other._birthWeight;
        this._currentWeight = other._currentWeight;
    }

    public void setCurrentWeight(Weight _currentWeight) {
        if (turnWeightToGrams(_currentWeight) > MIN_WEIGHT_IN_GRAMS) {
            this._currentWeight = _currentWeight;
        }
    }

    /*
    Gets the first name.

    @return the first name of this baby.
     */
    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    public String getId() {
        return _id;
    }

    /*
    Gets the birth weight.
     */
    public Weight getBirthWeight() {
        return _birthWeight;
    }

    public Weight getCurrentWeight() {
        return _currentWeight;
    }

    public String toString() {
        return (
                ("Name:" + this._firstName + " " + this._lastName + "\n") +
                ("Id:" + this._id + "\n") +
                ("Date of Birth:" + this._dateOfBirth.toString() + "\n") +
                ("Birth Weight:" + this._birthWeight.toString() + "\n") +
                ("Current Weight:" + this._currentWeight.toString() + "\n")
        );
    }

    /*
    Checks if two babies are twins.
     */
    public boolean areTwins (Baby other) {
        return other._lastName.equals(this._lastName) &&
                !other._firstName.equals(this._firstName) &&
                !other._id.equals(this._id) &&
                (other._dateOfBirth == this._dateOfBirth || other._dateOfBirth == this._dateOfBirth.tomorrow() || other._dateOfBirth.tomorrow() == this._dateOfBirth);
    }

    /*
    Checks if two babies are the same.
     */
    public boolean equals (Baby other) {
        return other._lastName.equals(this._lastName) &&
                other._firstName.equals(this._firstName) &&
                other._id.equals(this._id) &&
                (other._dateOfBirth == this._dateOfBirth);
    }

    public void updateCurrentWeight (int grams) {
        this._currentWeight.add(grams);
    }

    public boolean older (Baby other){
        return (this._dateOfBirth.before(other._dateOfBirth));
    }

    public boolean heavier (Baby other){
        if (turnWeightToGrams(this._currentWeight) > turnWeightToGrams(other._currentWeight)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int isWeightInValidRange(int numOfDays) {
        if (numOfDays < 1 || numOfDays > 365) {
            return 1;
        }

        int birthWeightInGrams = turnWeightToGrams(this._birthWeight);
        int currentWeightInGrams = turnWeightToGrams(this._currentWeight);

        double expectedWeightInGrams = birthWeightInGrams;

        if (numOfDays <= 7) {
            double weightLoss = birthWeightInGrams * 0.10 * (numOfDays / 7.0);
            expectedWeightInGrams -= weightLoss; }

        else if (numOfDays <= 60) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * (numOfDays - 7); }

        else if (numOfDays <= 120) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * (numOfDays - 60); }

        else if (numOfDays <= 240) {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
            expectedWeightInGrams += 30 * 53;
            expectedWeightInGrams += 25 * 60;
            expectedWeightInGrams += 16 * (numOfDays - 120); }

        else {
            expectedWeightInGrams -= birthWeightInGrams * 0.10;
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


    private int turnWeightToGrams(Weight weight) {
        return weight.getGrams() + (weight.getKilos() * 1000);
    }

}
