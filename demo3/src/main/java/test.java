import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


public class test {

    public static void main(String args[]) throws ParseException {
        test t = new test();
        String a = test.getweek("2020-12-07");
        System.out.println(a);
    }

    public static String getweek(String date) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        String smester1 = "2020-06-30";
        String smester2 = "2020-09-01";

        long between_days = 0;

        if(compareDate(sdf.parse(date),sdf.parse(smester2))){
            cal.setTime(sdf.parse(smester2));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24)/7;
            if(((time2-time1)/(1000*3600*24)%7) != 0 ) {
                between_days++;
            }
        }

        else if(compareDate(sdf.parse(smester1),sdf.parse(date))) {
            cal.setTime(sdf.parse("2020-03-01"));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24)/7;
            if(((time2-time1)/(1000*3600*24)%7) != 0 ) {
                between_days++;
            }
        }

        return String.valueOf(between_days);
    }

    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }
}
