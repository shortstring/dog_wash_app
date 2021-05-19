//Ashton Smith CS202
//This class is used as an abstract base class for service classes.
package Program4;

import java.io.FileWriter;
import java.util.Scanner;

abstract public class Service {
    protected String title;
    protected boolean [] days_open;
    protected int [][] hours;
    protected double price;



    //Constructor
    Service(){
        title = null;
        days_open = new boolean[7];
        for(int i = 0; i < 7; ++i)
            days_open[i] = true;
        hours = new int[2][2];
        hours[0][0] = 0;
        hours[0][1] = 0;
        hours[1][0] = 0;
        hours[1][1] = 0;
        price = 0.00;
    }



    //Copy Constructor
    public Service(Service source) {
        this.title = source.title;
        days_open = new boolean[7];
        for(int i = 0; i < 7; ++i)
            this.days_open[i] = source.days_open[i];
        this.hours = new int[2][2];
        this.hours[0][0] = source.hours[0][0];
        this.hours[0][1] = source.hours[0][1];
        this.hours[1][0] = source.hours[1][0];
        this.hours[1][1] = source.hours[1][1];
        this.price = source.price;
    }


    abstract int type();
    abstract int add(String title);
    abstract int display();
    abstract int save(FileWriter out);
    abstract int load(Scanner scan);
    abstract int short_display();
}
