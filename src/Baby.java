public class Baby {
    private String _firstName;
    private String _lastName;
    private String _id;
    private Date _dateOfBirth;
    private Weight _birthWeight;
    private Weight _currentWeight;

    private static final int MIN_WEIGHT_IN_GRAMS = 1000;

    public Baby (String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams){
        this._firstName = fName;
        this._lastName = lName;
        this._id = id;
        this._dateOfBirth = new Date(day, month, year);
        this._birthWeight = new Weight(birthWeightInGrams);
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
        if (_currentWeight.getGrams() > MIN_WEIGHT_IN_GRAMS) {
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

    public String toString(){
        return (
                ("Name:" + this._firstName + " " + this._lastName + "\n") +
                ("Id:" + this._id + "\n") +
                ("Date of Birth:" + this._dateOfBirth.toString() + "\n") +
                ("Birth Weight:" + this._birthWeight.toString() + "\n") +
                ("Birth Weight:" + this._currentWeight.toString() + "\n")
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


}
