import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by employee on 11/2/16.
 */
public class Calendar1 {

        private LocalDate usersdate;
        private LocalDate current;
        private ArrayList<DayOfWeek> week=new ArrayList<>();
        private ArrayList<DayOfWeek> dayOff=new ArrayList<>();
        private String str="";


        public Calendar1(){
            usersdate=LocalDate.now();
            current=usersdate.withDayOfMonth(1);
        }
        public Calendar1(String yourDate){
            this.usersdate=LocalDate.parse(yourDate);
            current=usersdate.withDayOfMonth(1);
        }
        public String setCalendar(int firstDay) {
            setRangeOfDays(firstDay);   //
            while (usersdate.getMonth() == current.getMonth()) {   // if current month
                str = str + "\n";
                for (DayOfWeek d : week) {      // set range of days
                    if (d != current.getDayOfWeek()) {                        // Пропустить дни другого месяца
                        str = str + "  \t";
                        continue;
                    } else {
                        if (usersdate.isEqual(current)) {                          // вывести заданую дату красным
                            str = str + "\033[31m" + current.getDayOfMonth() + "\033[0m" + "\t";
                            current = current.plusDays(1);                         // следующий день
                            if (usersdate.getMonth() != current.getMonth())        // если  месяц не совпадает закончить работу
                                break;
                            else
                                continue;
                        }
                        if(isDayOff()) // выходной
                            str = str + "\033[34m" + current.getDayOfMonth() + "\033[0m" + "\t";
                        else
                            str = str + current.getDayOfMonth() + "\t";  // вывод обычного дня
                    }
                    current = current.plusDays(1);
                    if (usersdate.getMonth() != current.getMonth())
                        break;
                }
            }
            return str;
        }
        public void setRangeOfDays(int firstDay){
            for(DayOfWeek d:DayOfWeek.values())
                if(d.getValue()>=firstDay){
                    week.add(d);
                    str=str+d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+"\t";
            }
            for(DayOfWeek d:DayOfWeek.values())
                if(d.getValue()<firstDay) {
                    week.add(d);
                    str=str+d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+"\t";
            }
        }
        //===========================================
        public void setDayOff(DayOfWeek ... days){
            for(DayOfWeek d: days){
                dayOff.add(d);
            }
        }
        public void setDayOff(int ... days){
            for(DayOfWeek d: DayOfWeek.values())
                for(int value: days)
                    if(d.getValue() == value)
                        dayOff.add(d);
        }
        //==========================================
        public boolean isDayOff(){
            for(DayOfWeek d: dayOff)
                if(d==current.getDayOfWeek())
                    return true;
            return false;
        }
        @Override
        public String toString(){
            return str;
        }
}

