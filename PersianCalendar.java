import java.util.Arrays;

public class PersianCalendar {
    final static short C_YEARS = 4166;
    final static short C_DAYS = 1009;
    final static short[] C128 = {29, 33, 33, 33};
    final static short[] C161 = {29, 33, 33, 33, 33};
    final static short[][] C4166 = {
            C128, C128, C128, C128, C128, C161,
            C128, C128, C128, C128, C161,
            C128, C128, C128, C128, C161,
            C128, C128, C128, C128, C161,
            C128, C128, C128, C128, C161,
            C128, C128, C128, C128, C161
    };

    protected static short[] LeapYears;
    private int year = 1;
    private int month = 1;
    private int day = 1;

    public static short[] getLeapYears() {
        short y = 0;
        short[] years = new short[C_DAYS];
        int index = 0;

        for (int i = 0; i < C4166.length; i++) {
            for (int j = 0; j < C4166[i].length; j++) {
                for (int k = 1; k < C4166[i][j]; k++) {
                    if (k % 4 == 0) {
                        if (k == C4166[i][j] - 1) {
                            y += 5;
                        } else {
                            y += 4;
                        }
                        years[index++] = y;
                    }
                }
            }
        }
        return years;
    }

    public PersianCalendar(int y, int m, int d) {
        LeapYears = getLeapYears();
    }

    public PersianCalendar() {
        this(1, 1, 1);
    }

    public boolean isLeap() {
        int y = this.year % C_YEARS;
        return Arrays.binarySearch(LeapYears, (short)y) >= 0 || y == 0;
    }

    public void setYear(int y) {

    }


}

class RUN {
    public static void main(String[] args) {
        PersianCalendar p = new PersianCalendar(1375,8,20);
        System.out.println(p.isLeap());
    }
}
/*
const CYCLE=4166;
        const C128=[29,33,33,33];
        const C161=[29,33,33,33,33];
        const C4166=[
        C128,C128,C128,C128,C128,C161,
        C128,C128,C128,C128,C161,
        C128,C128,C128,C128,C161,
        C128,C128,C128,C128,C161,
        C128,C128,C128,C128,C161,
        C128,C128,C128,C128,C161
        ];




        var leapYears=getLeapYears();

        function PersianCalender(y,m,d){
        this.year=1;
        this.month=1;
        this.day=1;
        this.leapYears=getLeapYears();
        this.setDate(y,m,d);

        }

        PersianCalender.prototype={

        isLeap:function(){
        var y=this.year%CYCLE;
        return this.leapYears.indexOf(y)>=0||y==0;
        },

        daysInMonths:function(){
        var months=[31,31,31,31,31,31,30,30,30,30,30,29];
        if(this.isLeap()){
        months[11]=30;
        }
        },

        isValid:function(){
        return!(this.year<1||this.month<1||this.month>12||this.day<1||this.day>this.daysInMonths()[this.month-1]);
        },

        setDate:function(y,m,d){
        this.setYear(y);
        this.setMonth(m);
        this.setDay(d);
        },

        setYear:function(y){
        y=parseInt(y);
        this.year=(y>=1&&y<=35000000)?y:1;
        },

        setMonth:function(m){
        m=parseInt(m);
        this.month=(m>=1&&m<=12)?m:1;
        },

        setDay:function(d){
        d=parseInt(d);
        this.day=(d>=1&&d<=this.daysInMonths()[this.month-1])?d:1;
        },

        daysFromYear:function(){
        var days=0;
        var months=this.daysInMonths();

        for(var i=0;i<this.month-1;i++){
        days+=months[i];
        }
        days+=this.day;
        return days;
        },

        daysFromOrigin:function(){
        var days=0;
        for(var i=1;i<this.year;i++){
        if(this.isLeap())
        days+=366;
        else
        days+=365;
        }
        days+=this.daysFromYear();
        return days;
        }
        };
*/