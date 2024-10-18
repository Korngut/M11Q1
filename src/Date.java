public class Date {
    private int _day;
    private int _month;
    private int _year;

    /**Get int and after validation enter it into new Date object:
     */
    public Date(int _day, int _month, int _year) {
        this._day = _day;
        this._month = _month;
        this._year = _year;
        if (checkIfDateIsReal(_day, _month, _year) == "invalidDate"){
            Date newDate = new Date(1, 1, 2024);
        }
        else {
            Date newDate = new Date(_day, _month, _year);
        }
    }

    /**returns FirstJanuary2024 object:
     */
    public Date(){
        Date FirstJanuary2024 = new Date(1, 1, 2024);
    }

    /**Copies Date into new object:
     */
    public Date(Date other){
        Date newDate = new Date(other.get_year(), other.get_month(), other.get_day());
    }

    /**Getter for day:
     */
    public int get_day() {
        return _day;
    }

    /**Setter for day, with validation
     */
    public void set_day(int dayToSet) {
        if(checkIfDateIsReal(dayToSet, this._month, this._year).equals("realDate")){
            this._day = dayToSet;
        };
    }

    /**Getter for month:
     */
    public int get_month() {
        return _month;
    }

    /**Setter for month, with validation
     */
    public void set_month(int monthToSet) {
        if (monthToSet>1 && monthToSet<=12){
            this._month = monthToSet;
        }
    }

    /**Getter for year:
     */
    public int get_year() {
        return _year;
    }

    /**Setter for year, with validation
     */
    public void set_year(int yearToSet) {
        if (yearToSet>=0){
            this._year = yearToSet;
        }
    }

    /**Checks if the date is a real date.
     * Returns a string value:
     * invalidDate or realDate
     */
    private String checkIfDateIsReal(int enterdDay, int enterdMonth, int enterdYear) {
        switch (enterdMonth) {
            case 1, 3, 5, 7, 8, 10, 12:
                if (enterdDay < 1 || enterdDay > 31  || enterdYear < 0) {
                    return "invalidDate";
                } else return "realDate";

            case 2:
                if (enterdYear % 400 == 0 || enterdYear % 4 == 0 && enterdYear % 100 != 0) {
                    if (enterdDay < 1 || enterdDay > 29  || enterdYear < 0) {
                        return "invalidDate";
                    } else return "realDate";
                } else {
                    if (enterdDay < 1 || enterdDay > 28  || enterdYear < 0) {
                        return "invalidDate";
                    } else return "realDate";
                }

            case 4, 6, 9, 11:
                if (enterdDay < 1 || enterdDay > 30 || enterdYear < 0) {
                    return "invalidDate";
                } else return "realDate";

            default:
                return "invalidDate";
        }

    }

    /** computes the day number since the beginning of the Christian counting of years */
    private int calculateDate(int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }

    /** Compares an entered date to the date in the object */

    public boolean equals(Date other){
        if (
                other.get_day()==this._day &&
                other.get_month()==this._month &&
                other.get_year()==this._year
        ){
            return true;
        }
        else {
            return false;
        }
    }
}
