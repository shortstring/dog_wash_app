//CS202 Program 4 Ashton Smith
//Home.java
package Program4;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Home extends Service{
    protected String details;


    //Constructor
    public Home()
    {
        super();
        details = null;
    }



    //Copy constructor
    public Home(Home source){
        super(source);
        this.details = source.details;
    }



    //Allows the user to input all data for a home service.
    public int add(String title){
        String days[];
        days = new String[7];
        days[0] = "Monday";
        days[1] = "Tuesday";
        days[2] = "Wednesday";
        days[3] = "Thursday";
        days[4] = "Friday";
        days[5] = "Saturday";
        days[6] = "Sunday";
        int option = 0;
        Scanner input = new Scanner(System.in);
        this.title = title;
        System.out.println("What days is this service available?");
        do{
        for(int i = 0; i < 7; ++i) {
            if(days_open[i] == false)
            System.out.println(i + "." + days[i] + " closed.");
            else
                System.out.println(i + "." + days[i] + " open.");
        }
            System.out.println("Enter the number next to the day to change it. Or enter 8 to continue.");
            option = input.nextInt();
            if (option < 0 || option > 6)
                System.out.println("Continuing");
            else if(!days_open[option])
                days_open[option] = true;
            else
                days_open[option] = false;
        }while(option >= 0 && option < 8);

        System.out.println("What are the time does the service open?");
        System.out.println("Enter the hour?");
        hours[0][0] = input.nextInt();
        System.out.println("Enter the minute");
        hours[0][1] = input.nextInt();
        System.out.println("What are the time does the service close?");
        System.out.println("Enter the hours?");
        hours[1][0] = input.nextInt();
        System.out.println("Enter the minute");
        hours[1][1] = input.nextInt();
        System.out.println("Enter any specific details related to your home service");
        input.nextLine();
        this.details = input.nextLine();
        System.out.println("What is the price for this service?");
        price = input.nextDouble();

        return 0;
    }



    //This function displays the title only
    int short_display() {
        System.out.println("Home Service:");
        System.out.println(title);

        System.out.println("\n");
        return 1;
    }



    //This function displays all information for the service
    int display() {
        String days[];
        days = new String[7];
        days[0] = "Monday";
        days[1] = "Tuesday";
        days[2] = "Wednesday";
        days[3] = "Thursday";
        days[4] = "Friday";
        days[5] = "Saturday";
        days[6] = "Sunday";
        System.out.println("Home Service");
        System.out.println(title);
        System.out.println("Days open: ");
        for(int i = 0; i < 7; ++i) {
            System.out.print(days[i] + " ");
            if (days_open[i])
                System.out.println(" open. ");
            else
                System.out.println(" closed. ");
        }
        System.out.println("Hours: " + hours[0][0] + ":" + hours[0][1] + " to " + hours[1][0] + ":" + hours[1][1]);
        System.out.println("Price: $" + price);
        System.out.println("Details " + details + ". \n\n");
        return 0;
    }



    //This function saves data from a home object to an external data file.
    int save(FileWriter Wout) {
        try {
            Wout.write("1\n");
            Wout.write(title + "\n");
            for(int i = 0; i < 7; ++i) {
                if(days_open[i])
                    Wout.write("true\n");
                else
                    Wout.write("false\n");
            }
            Wout.write(hours[0][0] + "\n");
            Wout.write(hours[0][1] + "\n");
            Wout.write(hours[1][0] + "\n");
            Wout.write(hours[1][1] + "\n");
            Wout.write(details + "\n");
            Wout.write(String.valueOf(price + "\n"));
            Wout.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return 0;
    }



    //returns a number to indicate the type of service
    int type(){
        return 1;
    }



    //This function loads data from an external data file.
    public int load(Scanner scan) {
        title = scan.nextLine();
        for(int i = 0; i < 7; ++i)
            days_open[i] = scan.nextBoolean();

        hours[0][0] = scan.nextInt();
        hours[0][1] = scan.nextInt();
        hours[1][0] = scan.nextInt();
        hours[1][1] = scan.nextInt();
        scan.nextLine();
        this.details = scan.nextLine();
        price = scan.nextDouble();
        return 0;
    }
}
