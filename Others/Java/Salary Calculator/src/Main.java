import java.util.Scanner;

public class Main {
    public static double grossYearSalary (double weekHours, double ratePerHour, int vacationDays) {
        if(weekHours < 0) {
            return -1;
        }
        if(ratePerHour < 0) {
            return -1;
        }
        if(vacationDays < 0) {
            return -1;
        }

        int oneVacationDay = 8;
        double yearlyPay;
        double vacationDaysHours = (vacationDays * oneVacationDay);
        double yearWeek = 52;
        double yearSalary;
        yearlyPay = (weekHours * ratePerHour) * yearWeek;
        //System.out.println("The Amount of money made within a month is " + monthSalary);
        yearSalary = yearlyPay - (vacationDaysHours * ratePerHour);
        return yearSalary;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many hours in a week that you employee have worked?");
        double weekHours = input.nextDouble();
        System.out.println("What is the rate per hour?");
        double ratePerHour = input.nextDouble();
        System.out.println("How many days of vacation have been given to your employee?");
        int vacationDays = input.nextInt();
       double Salary = grossYearSalary(weekHours, ratePerHour, vacationDays);
        System.out.println("The gross salary is " + Salary + "$");
    }
}