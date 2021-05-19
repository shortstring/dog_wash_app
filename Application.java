//Program4.Application.java
//CS202 Program 4 Ashton Smith

package Program4;
import java.util.Scanner;
import java.io.*;

//This class is used to allow the user to interact with data and supports a menu along with utility.
public class Application {
    protected Scanner input = null;



    //Class Constructor
    public Application(){

    }



    //This function provides a menu to select between the customer or service provider menu.
    public void Menu() {
    Scanner input = new Scanner(System.in);
    int option = 3;
    do {
        System.out.println("Are you a customer or service provider?");
        System.out.println("1. Customer");
        System.out.println("2. Service Provider");
        System.out.println("3. Exit");
        option = input.nextInt();

        if(option == 1)
            customerMenu();
        else if(option == 2)
            providerMenu();

    }while(option != 3);
    }



    //This function a menu for customers
    public void customerMenu(){
        Scanner input = new Scanner(System.in);
        ArrLLL a = new ArrLLL();

        ArrLLL a2;
        int option = 0;

        c_load(a);
        boolean loop = true;
        do{
            System.out.println("What would you like to do?");
            System.out.println("1. Display all services. \n" +
                    "2. Search by name and displays all details for matches.\n" +
                    "3. Search by type\n" +
                    "4. Exit");
            option = input.nextInt();
            switch (option) {
                case 1:
                    a.display_all();
                    break;
                case 2:
                    a.search();
                    break;
                case 3:
                    a.search_type();
                    break;
                default:
                    loop = false;
                    break;
            }
        }while(loop);
    }



    //This function is used as a menu interface for the user.
    public void providerMenu(){
        Tree t = new Tree();

        int option = 0;
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        Load(t);
        do{
            System.out.println("What would you like to do?");
            System.out.println("1. Display all service. \n" +
                    "2. Add a service\n" +
                    "3. Load Services From File. \n" +
                    "4. Search by name \n" +
                    "5. Save\n" +
                    "6. Exit");

            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Display all.");
                    t.Display_all();
                    break;
                case 2:
                    System.out.println("Add a service.");
                    t.Insert();
                    break;
                case 3:
                    System.out.println("Load Services from File.");
                    Load(t);
                    break;
                case 4:
                    System.out.println("Search by name.");
                    t.search();
                    break;
                case 5:
                    Save(t);
                    break;
                default:
                    loop = false;
                    break;
            }
        }while(loop);
    }



    //This function is used for the customer part of the program and loads data into an Array of LLLs
    public void c_load(ArrLLL t){
        int type;
        File fileName = new File("src/Program4/test.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(fileName);
            while(scan.hasNextLine()){
                //ignore blank space
                String s = scan.nextLine();
                t.Load(scan);
                //ignore blank space
                String x = scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    //This function is used to load data from an external data file to a 2-3 tree.
    public void Load(Tree t){
        int type;
        File fileName = new File("src/Program4/test.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(fileName);
            while(scan.hasNextLine()){
                    //ignore blank space
                    String s = scan.nextLine();
                    int value = scan.nextInt();
                    Tnode temp = new Tnode();
                    temp = temp.load(scan, value);
                    t.load_Insert(value, temp);
                    //ignore blank space
                    String x = scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    //This function is used to save data to an external data file from a 2-3 tree.
    public void Save(Tree t){
        File my_file = new File("src/Program4/test.text");
            FileWriter out = null;
            try {
                out = new FileWriter("src/Program4/test.txt");
                t.save(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
