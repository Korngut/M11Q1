public class Date {
    private int _day;
    private int _month;
    private int _year;

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

    public Date(){
        Date FirstJanuary2024 = new Date(1, 1, 2024);
    }

    public Date(Date other){
        Date newDate = new Date(other.get_year(), other.get_month(), other.get_day());
    }

    public int get_day() {
        return _day;
    }

    public void set_day(int dayToSet) {
        this._day = dayToSet;
    }

    public int get_month() {
        return _month;
    }

    public void set_month(int monthToSet) {
        this._month = monthToSet;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int yearToSet) {
        this._year = yearToSet;
    }

    private String checkIfDateIsReal(int enterdDay, int enterdMonth, int enterdYear) {
        switch (enterdMonth) {
            case 1, 3, 5, 7, 8, 10, 12:
                if (enterdDay < 1 || enterdDay > 31) {
                    return "invalidDate";
                } else return "realDate";

            case 2:
                if (enterdYear % 400 == 0 || enterdYear % 4 == 0 && enterdYear % 100 != 0) {
                    if (enterdDay < 1 || enterdDay > 29) {
                        return "invalidDate";
                    } else return "realDate";
                } else {
                    if (enterdDay < 1 || enterdDay > 28) {
                        return "invalidDate";
                    } else return "realDate";
                }

            case 4, 6, 9, 11:
                if (enterdDay < 1 || enterdDay > 30) {
                    return "invalidDate";
                } else return "realDate";

            default:
                return "invalidDate";
        }

}}
