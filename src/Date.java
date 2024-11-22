public class Date {
    private int _day;
    private int _month;
    private int _year;

    private static final int minimumDaysPerMonth = 1;
    private static final int lowestYear = 0;

    private static final Date newDate = new Date(1, 1, 2024);

    //Get int and after validation enter it into new Date object:
    public Date(int day, int month, int year) {
        if (checkIfDateIsReal(day, month, year) == "invalidDate") {
            this._day = 1;
            this._month = 1;
            this._year = 2024;

        } else {
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }


    //returns FirstJanuary2024 object:
    public Date() {
        this._day = 1;
        this._month = 1;
        this._year = 2024;
    }

    //Copies Date into new object:
    public Date(Date other) {
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }

    //Getter for day:
    public int getDay() {
        return _day;
    }

    //Setter for day, with validation
    public void setDay(int dayToSet) {
        if (checkIfDateIsReal(dayToSet, this._month, this._year).equals("realDate")) {
            this._day = dayToSet;
        }
        ;
    }

    //Getter for month:
    public int getMonth() {
        return _month;
    }

    //Setter for month, with validation
    public void setMonth(int monthToSet) {
        if (monthToSet > 1 && monthToSet <= 12) {
            this._month = monthToSet;
        }
    }

    //Getter for year:
    public int getYear() {
        return _year;
    }

    //Setter for year, with validation
    public void setYear(int yearToSet) {
        if (yearToSet >= 0) {
            this._year = yearToSet;
        }
    }

    /*Checks if the date is a real date.
     * Returns a string value:
     * invalidDate or realDate
     */

    private String checkIfDateIsReal(int enterdDay, int enterdMonth, int enterdYear) {
        switch (enterdMonth) {
            case 1, 3, 5, 7, 8, 10, 12:
                if (enterdDay < minimumDaysPerMonth || enterdDay > 31 || enterdYear < lowestYear) {
                    return "invalidDate";
                } else return "realDate";

            case 2:
                if (isLeapYear(enterdYear)) {
                    if (enterdDay < minimumDaysPerMonth || enterdDay > 29 || enterdYear < lowestYear) {
                        return "invalidDate";
                    } else return "realDate";
                } else {
                    if (enterdDay < minimumDaysPerMonth || enterdDay > 28 || enterdYear < lowestYear) {
                        return "invalidDate";
                    } else return "realDate";
                }

            case 4, 6, 9, 11:
                if (enterdDay < minimumDaysPerMonth || enterdDay > 30 || enterdYear < lowestYear) {
                    return "invalidDate";
                } else return "realDate";

            default:
                return "invalidDate";
        }

    }

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    //Compares an entered date to the date in the object

    public boolean equals(Date other) {
        if (
                other.getDay() == this._day &&
                        other.getMonth() == this._month &&
                        other.getYear() == this._year
        ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean before(Date other) {
        if (calculateDate(other._day, other._month, other._year) < calculateDate(this._day, this._month, this._year)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean after(Date other) {
        return this.before(other);
    }

    public int difference (Date other) {
        return calculateDate(other.getDay(), other.getMonth(), other.getYear()) - calculateDate(_day, _month, other.getYear());
    }

    public String toString() {
        return (_day + "/" + _month + "/" + _year);
    }

    public Date tomorrow() {
        if (_month == 4 || _month == 6 || _month == 9 || _month == 11) {
            if (_day < 30) {
                return new Date(_day + 1, _month, _year);
            } else {
                return new Date(1, _month + 1, _year);
            }
        }

        if (_month == 1 || _month == 3 || _month == 5 || _month == 7 || _month == 8 || _month == 10) {
            if (_day < 31) {
                return new Date(_day + 1, _month, _year);
            } else {
                return new Date(1, _month + 1, _year);
            }
        }

        if (_month == 12) {
            if (_day < 31) {
                return new Date(_day + 1, _month, _year);
            } else {
                return new Date(1, 1, _year + 1);
            }
        }

        if (_month == 2) {
            if (isLeapYear(_year)) {
                if (_day < 29) {
                    return new Date(_day + 1, _month, _year);
                } else {
                    return new Date(1, _month + 1, _year);
                }
            } else {
                if (_day < 28) {
                    return new Date(_day + 1, _month, _year);
                } else {
                    return new Date(1, _month + 1, _year);
                }
            }
        }
        return null;
    }


    private boolean isLeapYear (int y)
    {
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    }

}
