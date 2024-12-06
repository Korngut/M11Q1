/**
 This class represents a Baby object.

 @version 2025a
 @author Ori Korngut
 */

public class Date {
    private int _day;
    private int _month;
    private int _year;

    private static final int MINIMUM_DAYS_PER_MONTH = 1;
    private static final int LOWEST_YEAR = 0;

    private static final int DEFAULT_DAY_IN_MONTH = 1;
    private static final int DEFAULT_MONTH_IN_YEAR = 1;
    private static final int DEFAULT_YEAR = 1;

    private static final int MONTH_LENGTH_28 = 28;
    private static final int MONTH_LENGTH_29 = 29;
    private static final int MONTH_LENGTH_30 = 30;
    private static final int MONTH_LENGTH_31 = 31;


    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    /**
     Date constructor - If the given date is valid - creates a new Date object, otherwise creates the date 01/01/2000.

     @param day the day in the month (1-31)
     @param month the month in the year (1-12)
     @param year the year (4 digits)
     */
    public Date(int day, int month, int year) {
        if (checkIfDateIsReal(day, month, year).equals("invalidDate")) { //Checks if the date is correct.
            this._day = DEFAULT_DAY_IN_MONTH;
            this._month = DEFAULT_MONTH_IN_YEAR;
            this._year = DEFAULT_YEAR;

        } else { //Initializes to entered values
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }


    /**
     Default constructor
     */
    public Date() {
        this._day = 1;
        this._month = 1;
        this._year = 2024;
    }

    /**
     Copy constructor

     @param other the date to be copied
     */
    public Date(Date other) {
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }

    /**
     Gets the day

     @return the day of this date
     */
    public int getDay() {
        return _day;
    }

    /**
     Sets the day (only if date remains valid)

     @param dayToSet the new day value
     */
    public void setDay(int dayToSet) {
        if (checkIfDateIsReal(dayToSet, this._month, this._year).equals("realDate")) { //Checks if the date is correct.
            this._day = dayToSet;
        }
    }

    /**
     Gets the month

     @return the month of this date
     */
    public int getMonth() {
        return _month;
    }

    /**
     Sets the month (only if date remains valid)

     @param monthToSet the new month value
     */
    public void setMonth(int monthToSet) {
        if (checkIfDateIsReal(this._day, monthToSet, this._year).equals("realDate")){ //Checks if the date is correct.
            this._month = monthToSet;
        }
    }

    /**
     Gets the year

     @return the year of this date
     */
    public int getYear() {
        return _year;
    }

    /**
     Sets the year (only if date remains valid)

     @param yearToSet the new year value
     */
    public void setYear(int yearToSet) {
        if (checkIfDateIsReal(this._day, this._month, yearToSet).equals("realDate")){ //Checks if the date is correct.
            this._year = yearToSet;
        }
    }

    //The function checks for each month whether the number of days in it is correct, and thus returns whether the date is a real date.
    private String checkIfDateIsReal(int enteredDay, int enteredMonth, int enteredYear) {
        switch (enteredMonth) {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER:
                if (enteredDay < MINIMUM_DAYS_PER_MONTH || enteredDay > MONTH_LENGTH_31 || enteredYear < LOWEST_YEAR) {
                    return "invalidDate";
                } else return "realDate";

            case FEBRUARY:
                if (isLeapYear(enteredYear)) {
                    if (enteredDay < MINIMUM_DAYS_PER_MONTH || enteredDay > MONTH_LENGTH_29 || enteredYear < LOWEST_YEAR) {
                        return "invalidDate";
                    } else return "realDate";
                } else {
                    if (enteredDay < MINIMUM_DAYS_PER_MONTH || enteredDay > MONTH_LENGTH_28 || enteredYear < LOWEST_YEAR) {
                        return "invalidDate";
                    } else return "realDate";
                }

            case APRIL, JUNE, SEPTEMBER, NOVEMBER:
                if (enteredDay < MINIMUM_DAYS_PER_MONTH || enteredDay > MONTH_LENGTH_30 || enteredYear < LOWEST_YEAR) {
                    return "invalidDate";
                } else return "realDate";

            default:
                return "invalidDate";
        }

    }

    /**
     Checks if two dates are the same

     @param other the date to compare this date to
     @return true if the dates are the same
     */
    public boolean equals(Date other) { //Compares each of the equals parameters.
        return other.getDay() == this._day &&
                other.getMonth() == this._month &&
                other.getYear() == this._year;
    }

    /**
     Checks if this date comes before another date

     @param other date to compare this date to
     @return true if this date is before the other date
     */
    public boolean before(Date other) { //Checks whether each of the parameters is greater than the other, depending on their size (year, month, and day)
        int thisDays = this.calculateDate(this._day, this._month, this._year);
        int otherDays = other.calculateDate(other._day, other._month, other._year);

        return (thisDays < otherDays);
    }

    /**
     Checks if this date comes after another date

     @param other date to compare this date to
     @return true if this date is before the other date
     */
    public boolean after(Date other) {
        return (other.before(this) && !before (other));
    }

    /**
     Calculates the difference in days between two dates

     @param other the date to calculate the difference between
     @return the number of days between the dates (non negative value)
     */
    public int difference(Date other) {
        int thisDays = this.calculateDate(this._day, this._month, this._year);
        int otherDays = other.calculateDate(other._day, other._month, other._year);

        System.out.println(thisDays);
        return Math.abs(thisDays - otherDays); //Subtracts the difference between two dates, in absolute value.
    }

    /**
     Returns a String that represents this date

     @return a String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString() {
        String formattedDay = "";
        if (_day < 10) {
            formattedDay = "0" + _day;
        } else {
            formattedDay = "" + _day;
        }

        String formattedMonth = "";
        if (_month < 10) {
            formattedMonth = "0" + _month;
        } else {
            formattedMonth = "" + _month;
        }

        String formattedYear = "";
        if (_year < 1000) {
            formattedYear = "0" + _year;
        } else {
            formattedYear = "" + _year;
        }

        return formattedDay + "/" + formattedMonth + "/" + formattedYear;
    }



    /**
     Calculate the date of tomorrow

     @return the date of tomorrow
     */
    public Date tomorrow() {
        // Handling for months with 31 days
        if (_month == JANUARY || _month == MARCH || _month == MAY || _month == JULY || _month == AUGUST || _month == OCTOBER || _month == DECEMBER) {
            if (_day < MONTH_LENGTH_31) {
                return new Date(_day + 1, _month, _year);
            } else {
                if (_month == DECEMBER) {
                    return new Date(1, 1, _year + 1);
                } else {
                    return new Date(1, _month + 1, _year);
                }
            }
        }

        // Handling for months with 30 days
        if (_month == APRIL || _month == JUNE || _month == SEPTEMBER || _month == NOVEMBER) {
            if (_day < MONTH_LENGTH_30) {
                return new Date(_day + 1, _month, _year);
            } else {
                return new Date(1, _month + 1, _year);
            }
        }

        // Handling for February (leap year and regular year)
        if (_month == FEBRUARY) {
            if (isLeapYear(_year)) {
                if (_day < MONTH_LENGTH_29) {
                    return new Date(_day + 1, _month, _year);
                } else {
                    return new Date(1, MARCH, _year);
                }
            } else {
                if (_day < MONTH_LENGTH_28) {
                    return new Date(_day + 1, _month, _year);
                } else {
                    return new Date(1, MARCH, _year);
                }
            }
        }

        return null;  // This should never happen with proper date logic, but just in case
    }



    private boolean isLeapYear (int y)
    {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
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