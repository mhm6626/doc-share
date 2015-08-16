import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;

public class PersianCalendar
{
    final static int       MAX_YEAR   = 35000000;
    final static short     C_YEARS    = 4166;
    final static short     C_DAYS     = 1009;
    final static int       C801_DAYS  = 801 * 365 + 5 * 31 + 39;
    final static int       C673_DAYS  = 673 * 365 + 4 * 31 + 39;
    final static int       C4166_DAYS = C_YEARS * 365 + C_DAYS;
    final static short[]   C128       = {29, 33, 33, 33};
    final static short[]   C161       = {29, 33, 33, 33, 33};
    final static short[][] C4166      = {C128, C128, C128, C128, C128, C161, C128, C128, C128, C128, C161, C128,
            C128, C128, C128, C161, C128, C128, C128, C128, C161, C128, C128, C128, C128, C161, C128, C128, C128,
            C128, C161};

    protected static short[] LeapYears;
    private int year  = 1;
    private int month = 1;
    private int day   = 1;

    public static short[] getLeapYears()
    {
        short   y     = 0;
        short[] years = new short[C_DAYS];
        int     index = 0;

        for (int i = 0; i < C4166.length; i++)
        {
            for (int j = 0; j < C4166[i].length; j++)
            {
                for (int k = 1; k < C4166[i][j]; k++)
                {
                    if (k % 4 == 0)
                    {
                        if (k == C4166[i][j] - 1)
                        {
                            y += 5;
                        }
                        else
                        {
                            y += 4;
                        }
                        years[index++] = y;
                    }
                }
            }
        }
        return years;
    }

    public PersianCalendar(int y, int m, int d)
    {
        this.setDate(y, m, d);
    }

    public PersianCalendar()
    {
        this(1, 1, 1);
    }

    public boolean isLeap()
    {
        return isLeap(this.year);
    }

    public static boolean isLeap(int y)
    {
        LeapYears = getLeapYears();
        y %= C_YEARS;
        return Arrays.binarySearch(LeapYears, (short) y) >= 0 || y == 0;
    }

    public int[] daysInMonths()
    {
        return daysInMonths(this.year);
    }

    public static int[] daysInMonths(int y)
    {
        int[] months = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        if (isLeap(y))
        {
            months[11] = 30;
        }
        return months;
    }

    public void setYear(int y)
    {
        if (y >= 1 && y <= MAX_YEAR)
        {
            this.year = y;
        }
        else
        {
            this.year = 1;
        }
    }

    public void setMonth(int m)
    {
        if (m >= 1 && m <= 12)
        {
            this.month = m;
        }
        else
        {
            this.month = 1;
        }
    }

    public void setDay(int d)
    {
        if (d >= 1 && d <= this.daysInMonths()[month - 1])
        {
            this.day = d;
        }
        else
        {
            this.day = 1;
        }
    }

    public void setDate(int y, int m, int d)
    {
        this.setYear(y);
        this.setMonth(m);
        this.setDay(d);
    }


    public int daysFromYear()
    {
        int   days   = 0;
        int[] months = this.daysInMonths();

        for (int i = 0; i < this.month - 1; i++)
        {
            days += months[i];
        }
        days += this.day - 1;
        return days;
    }

    public int daysFromOrigin()
    {
        int days = 0;
        for (int i = 1; i < this.year; i++)
        {
            if (isLeap(i))
            {
                days += 366;
            }
            else
            {
                days += 365;
            }
        }
        days += this.daysFromYear();
        return days;
    }

    public int diffDays(PersianCalendar that)
    {
        return this.daysFromOrigin() - that.daysFromOrigin();
    }

    public static PersianCalendar daysConvert(int days)
    {
        if (days < 0)
        {
            return new PersianCalendar();
        }

        int y = 0, m = 1;

        y = (int) (days / C4166_DAYS) * C_YEARS;
        days %= C4166_DAYS;

        int i        = 1;
        int yearDays = isLeap(i) ? 366 : 365;

        while (days >= yearDays)
        {
            y++;
            i++;
            days -= yearDays;
            yearDays = isLeap(i) ? 366 : 365;
        }

        y++;
        int[] months = daysInMonths(y);

        i = 0;
        while (days >= months[i])
        {
            days -= months[i];
            m++;
            i++;
        }

        return new PersianCalendar(y, m, days + 1);
    }

    @Override
    public String toString()
    {
        return this.year + "/" + this.month + "/" + this.day;
    }

    public static PersianCalendar fromGregorianCalendar(GregorianCalendar gc)
    {
        return daysConvert(gc.daysFromOrigin() - 226895);
    }
}

class GregorianCalendar
{
    private int year  = 1;
    private int month = 1;
    private int day   = 1;

    public GregorianCalendar(int y, int m, int d)
    {
        this.setDate(y, m, d);
    }

    public GregorianCalendar()
    {
        this(1, 1, 1);
    }

    public static boolean isLeap(int y)
    {
        return (((y % 4) == 0) && ((y % 100) != 0)) || ((y % 400) == 0);
    }

    public boolean isLeap()
    {
        return isLeap(this.year);
    }

    public int[] daysInMonths()
    {
        return daysInMonths(this.year);
    }

    public static int[] daysInMonths(int y)
    {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeap(y))
        {
            months[1] = 29;
        }
        return months;
    }

    public void setYear(int y)
    {
        if (y >= 1 && y <= 10000)
        {
            this.year = y;
        }
        else
        {
            this.year = 1;
        }
    }

    public void setMonth(int m)
    {
        if (m >= 1 && m <= 12)
        {
            this.month = m;
        }
        else
        {
            this.month = 1;
        }
    }

    public void setDay(int d)
    {
        if (d >= 1 && d <= this.daysInMonths()[month - 1])
        {
            this.day = d;
        }
        else
        {
            this.day = 1;
        }
    }

    public void setDate(int y, int m, int d)
    {
        this.setYear(y);
        this.setMonth(m);
        this.setDay(d);
    }


    public int daysFromYear()
    {
        int   days   = 0;
        int[] months = this.daysInMonths();

        for (int i = 0; i < this.month - 1; i++)
        {
            days += months[i];
        }
        days += this.day - 1;
        return days;
    }

    public int daysFromOrigin()
    {
        int days = 0;
        for (int i = 1; i < this.year; i++)
        {
            if (isLeap(i))
            {
                days += 366;
            }
            else
            {
                days += 365;
            }
        }
        days += this.daysFromYear();
        return days;
    }

    public int diffDays(GregorianCalendar that)
    {
        return this.daysFromOrigin() - that.daysFromOrigin();
    }

    public static GregorianCalendar daysConvert(int days)
    {
        if (days < 0)
        {
            return new GregorianCalendar();
        }

        int y        = 0, m = 1;
        int i        = 1;
        int yearDays = isLeap(i) ? 366 : 365;

        while (days >= yearDays)
        {
            y++;
            i++;
            days -= yearDays;
            yearDays = isLeap(i) ? 366 : 365;
        }

        y++;
        int[] months = daysInMonths(y);

        i = 0;
        while (days >= months[i])
        {
            days -= months[i];
            m++;
            i++;
        }

        return new GregorianCalendar(y, m, days + 1);
    }

    @Override
    public String toString()
    {
        return this.year + "/" + this.month + "/" + this.day;
    }

    public static GregorianCalendar fromPersianCalendar(PersianCalendar pc)
    {
        return daysConvert(pc.daysFromOrigin() + 226895);
    }
}

class RUN
{
    public static void main(String[] args)
    {
        PersianCalendar   p = new PersianCalendar();
        GregorianCalendar g = GregorianCalendar.fromPersianCalendar(p);
        System.out.println(g);
    }
}
