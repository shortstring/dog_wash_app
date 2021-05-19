//Program4. Delivery.java
//CS202 Program 4 Ashton Smith

package Program4;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Delivery extends Service {
    protected String type;



    //Constructor
    public Delivery(){
        super();
        type = null;
    }



    //Copy  constructor
    public Delivery(Delivery source){
        super(source);
        this.type = source.type;
    }



    //This function prompts the user to input data for the class information
    public int add(String title){
        String days[];
        days = new String[7];
        days[0] = "Monday";
        days[1] = "Tuesday";
        days[2] = "Wednesday";
        days[3] = "Thursday";
        days[4] = "Friday";
        days[5] = "Saturday";
        days[6] = "sunday";
        int option = 0;

        Scanner input = new Scanner(System.in);
        this.title = title;
        System.out.println("What days is this service available?");
        do{
            for(int i = 0; i < 7; ++i) {
                if(days_open[i] == false)
                    System.out.println(i + "." + days[i] + " closed");
                else
                    System.out.println(i + "." + days[i] + " open");
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
        input.nextLine();
        System.out.println("What types of things does your service deliver");
        this.type = input.nextLine();
        System.out.println("What is the price for this service");
        price = input.nextDouble();
        return 0;
    }



    //returns a number to indicate the type of service
    int type(){
        return 2;
    }



    //This function is used to display all data from the class
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

        System.out.println("Delivery Service");
        System.out.println(title);
        System.out.println("Days open:");
        for(int i = 0; i < 7; ++i) {
            System.out.print(days[i]+ " ");
            if (days_open[i])
                System.out.println("open.");
            else
                System.out.println("closed.");
        }
        System.out.println("Hours: " + hours[0][0] + ":" + hours[0][1] + " to " + hours[1][0] + ":" + hours[1][1]);
        System.out.println("Price: $" + price);
        System.out.println("Type of deliveries " + type + ".\n\n");
        return 0;
    }



    //This function saves all data from the class to an external data file.
    int save(FileWriter Wout) {
        try {
            Wout.write("2\n");
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
            Wout.write(type + "\n");
            Wout.write(String.valueOf(price + "\n"));
            Wout.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return 0;
    }



    //This function is used to load data from an external data file.
    int load(Scanner scan) {

        title = scan.nextLine();
        for(int i = 0; i < 7; ++i)
            days_open[i] = scan.nextBoolean();

        hours[0][0] = scan.nextInt();
        hours[0][1] = scan.nextInt();
        hours[1][0] = scan.nextInt();
        hours[1][1] = scan.nextInt();
        scan.nextLine();
        this.type = scan.nextLine();

        price = scan.nextDouble();
        return 0;
    }



    //This function displays the title only.
    int short_display() {
        System.out.println("Delivery Service:");
        System.out.println(title);

        System.out.println("\n");
        return 1;
    }
}
