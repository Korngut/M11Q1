public class Date {
    private int _day;
    private int _month;
    private int _year;

    public Date(int _day, int _month, int _year) {
        this._day = _day;
        this._month = _month;
        this._year = _year;

        switch (_month){
            case 1:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 2:
                if (_year % 400 == 0 || _year % 4 == 0 && _year % 100 != 0){
                    if(_day < 1 || _day > 29){
                        Date FirstJanuary2024 = new Date(1, 1, 2024);
                    }
                }
                else {
                    if(_day < 1 || _day > 28){
                        Date FirstJanuary2024 = new Date(1, 1, 2024);
                    }
                }

            case 3:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 4:
                if(_day < 1 || _day > 30){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 5:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 6:
                if(_day < 1 || _day > 30){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 7:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 8:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 9:
                if(_day < 1 || _day > 30){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 10:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 11:
                if(_day < 1 || _day > 30){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }

            case 12:
                if(_day < 1 || _day > 31){
                    Date FirstJanuary2024 = new Date(1, 1, 2024);
                }
        }
    }

    public Date(){
        Date FirstJanuary2024 = new Date(1, 1, 2024);
    }

    public Date(Date other){

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
}
