/*
This class represents a Baby object.

@version 2025a
@author Ori Korngut
 */

public class Date {
    private int _day;
    private int _month;
    private int _year;

    private static final int minimumDaysPerMonth = 1;
    private static final int lowestYear = 0;

    private static final Date newDate = new Date(1, 1, 2024);

    /*
    Date constructor - If the given date is valid - creates a new Date object, otherwise creates the date 01/01/2000.

    @param day - the day in the month (1-31)
    @param month - the month in the year (1-12)
    @param year - the year (4 digits)
     */
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


    /*
    Default constructor
     */
    public Date() {
        this._day = 1;
        this._month = 1;
        this._year = 2024;
    }

    /*
    Copy constructor

    @param other - the date to be copied
     */
    public Date(Date other) {
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }

    /*
    Gets the day

    @return the day of this date
     */
    public int getDay() {
        return _day;
    }

    /*
    Sets the day (only if date remains valid)

    @param dayToSet - the new day value
     */
    public void setDay(int dayToSet) {
        if (checkIfDateIsReal(dayToSet, this._month, this._year).equals("realDate")) {
            this._day = dayToSet;
        }
    }

    /*
    Gets the month

    @return the month of this date
     */
    public int getMonth() {
        return _month;
    }

    /*
    Sets the month (only if date remains valid)

    @param monthToSet - the new month value
     */
    public void setMonth(int monthToSet) {
        if (checkIfDateIsReal(this._day, monthToSet, this._year).equals("realDate")){
            this._month = monthToSet;
        }
    }

    /*
    Gets the year

    @return the year of this date
     */
    public int getYear() {
        return _year;
    }

    /*
    Sets the year (only if date remains valid)

    @param yearToSet - the new year value
     */
    public void setYear(int yearToSet) {
        if (checkIfDateIsReal(this._day, this._month, yearToSet).equals("realDate")){
            this._year = yearToSet;
        }
    }


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

    /*
    Checks if two dates are the same

    @param other - the date to compare this date to
    @return true if the dates are the same
     */
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

    /*
    Checks if this date comes before another date

    @param other - date to compare this date to
    @return true if this date is before the other date
     */
    public boolean before(Date other) {
        if (this._year < other._year) {
            return true;
        }
        if (this._year == other._year) {
            if (this._month < other._month) {
                return true;
            } else if (this._month == other._month) {
                return this._day < other._day;
            }
        }
        return false;
    }

    /*
    Checks if this date comes after another date

    @param other - date to compare this date to
    @return true if this date is before the other date
     */
    public boolean after(Date other) {
        return !(this.before(other));
    }

    /*
    Calculates the difference in days between two dates

    @param other - the date to calculate the difference between
    @return the number of days between the dates (non negative value)
     */
    public int difference(Date other) {
        int thisDays = this.calculateDate(this._day, this._month, this._year);
        int otherDays = other.calculateDate(other._day, other._month, other._year);

        return Math.abs(thisDays - otherDays);
    }

    /*
    Returns a String that represents this date

    @return a String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString() {
        if (_day < 10 && _month < 10) {
        return ("0" + _day + "/" + "0" +  _month + "/" + _year);
        }
        else if (_day < 10) {
            return ("0" + _day + "/" +  _month + "/" + _year);
        }
        else if (_month < 10) {
            return ("0" + _day + "/" + _month + "/" + _year);
        }
        else {
            return (_day + "/" + _month + "/" + _year);
        }

    }

    /*
    Calculate the date of tomorrow

    @return the date of tomorrow
     */
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

    //computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);

    }
}
