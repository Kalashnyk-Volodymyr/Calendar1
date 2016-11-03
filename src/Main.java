import java.time.DayOfWeek;
import java.util.Scanner;

/**
 * Created by employee on 11/2/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        Calendar1 c;
        if(str.length()==0)
            c=new Calendar1();
        else
            c=new Calendar1(str);

        //c.setDayOff(DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.MONDAY,DayOfWeek.TUESDAY);
        c.setDayOff(1,2,3,4);
        c.setCalendar(6);
        System.out.print(c);
    }
}
