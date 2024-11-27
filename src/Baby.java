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

    //TODO: ID check
    public Baby (String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams){
        this._firstName = fName;
        this._lastName = lName;
        this._id = id;
        this._dateOfBirth = new Date(day, month, year);
        this._birthWeight = new Weight(birthWeightInGrams);
        this._currentWeight = new Weight(birthWeightInGrams);
    }

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

    public boolean areTwins (Baby other) {
        return other._lastName.equals(this._lastName) &&
                !other._firstName.equals(this._firstName) &&
                !other._id.equals(this._id) &&
                (other._dateOfBirth == this._dateOfBirth || other._dateOfBirth == this._dateOfBirth.tomorrow() || other._dateOfBirth.tomorrow() == this._dateOfBirth);
    }

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
        if (numOfDays >= 1 && numOfDays <= 7) {
            double expectedWeight = turnWeightToGrams(this._birthWeight) * (1 - (0.1 * numOfDays / 7));
            if (Math.abs(expectedWeight - turnWeightToGrams(this._currentWeight)) < 0.01) { // Allowing minor rounding errors
                return 3;
            } else {
                return 2;
            }
        }

        else if (numOfDays >= 8 && numOfDays <= 60) {
            if ((numOfDays - 7) * (30) + turnWeightToGrams(this._birthWeight) * (1 - 0.1)
                    == turnWeightToGrams(this._currentWeight)) {
                return 3;
            } else {
                return 2;
            }
        }

        else if (numOfDays >= 61 && numOfDays <= 120) {
            if ((numOfDays - 60) * (25) + 52 * (30) + turnWeightToGrams(this._birthWeight) * (1 - 0.1)
                    == turnWeightToGrams(this._currentWeight)) {
                return 3;
            } else {
                return 2;
            }
        }

        else if (numOfDays >= 121 && numOfDays <= 240) {
            if ((numOfDays - 121) + 59 * (25) + 52 * (30) + turnWeightToGrams(this._birthWeight) * (1 - 0.1)
                    == turnWeightToGrams(this._currentWeight)) {
                return 3;
            } else {
                return 2;
            }
        }

        else if (numOfDays >= 241 && numOfDays <= 365) {
            if ((numOfDays - 241) * (8) + 119 * (16) + 59 * (25) + 52 * (30)
                    + turnWeightToGrams(this._birthWeight) * (1 - 0.1)
                    == turnWeightToGrams(this._currentWeight)) {
                return 3;
            } else {
                return 2;
            }
        }

        else {
            return 1;
        }
    }


    private int turnWeightToGrams(Weight weight) {
        return weight.getGrams() + (weight.getKilos() * 1000);
    }

}
