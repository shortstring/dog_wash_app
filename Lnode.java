package Program4;

import java.util.Scanner;

//This class we implemented for Program 5
public class Lnode {
    private Lnode next;
    protected Service service;


    //constructor
    public Lnode(){
        service = null;
    }



    //copy constructor
    public Lnode(Lnode source){
        if(source.service != null) {
            int type = source.service.type();
            if (type == 1) {
                service = new Home((Home) source.service);
            } else if (type == 2) {
                service = new Delivery((Delivery) source.service);
            } else {
                service = new Pet((Pet) source.service);
            }
        }

    }

    public void search_list(int to_find, Lnode head){
        if(head == null)
            return;
        if(head.service.type() == to_find)
            head.display();
        search_list(to_find, head.go_next());
    }


    //returns the next pointer
    public Lnode go_next(){
        return this.next;
    }


    //Connects the argument to the argument
    public Lnode connect_next(Lnode to_connect){
        next = to_connect;
        return next;
    }



    public Lnode load(Scanner scan){
        int type = scan.nextInt();
        scan.nextLine();
        if (type == 1) {
            this.service = new Home();
        } else if (type == 2) {
            this.service = new Delivery();
        } else
            this.service = new Pet();

        this.service.load(scan);
        return this;
    }


    //Used to display all of a services information
    public void long_display(){
        service.display();
    }



    //Used to display only the title of the service
    public void display(){
        service.short_display();
    }
}
