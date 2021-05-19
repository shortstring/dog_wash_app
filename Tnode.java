//CS202 Program 4 Ashton Smith
//Tnode.java
//This class is the node class for the 2-3 tree.


package Program4;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Tnode {

    private Tnode left;
    private Tnode right;
    private Tnode center;
    private Service service[];
    private int value[];
    protected Scanner input = null;



    //Class constructor
    public Tnode() {

        value = new int[3];
        service = new Service[3];
        service[0] = null;
        service[1] = null;
        service[2] = null;
        value[0] = 0;
        value[1] = 0;
        value[2] = 0;
        left = null;
        right = null;
        center = null;
    }



    //Copy constructor
    public Tnode(Tnode source){
        value = new int[3];
        service = new Service[3];
        service[0] = source.service[0];
        service[1] = source.service[1];
        service[2] = source.service[2];
        value[0] = source.value[0];
        value[1] = source.value[1];
        value[2] = source.value[2];
        left = null;
        right = null;
        center = null;
    }



    //Save to external data file
    public Tnode save(FileWriter out){

        if(service[0] != null) {
            try {
                out.write( "\n" + (int)value[0] + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            service[0].save(out);
        }
        if(service[1] != null) {
            try {
                out.write("\n" + (int)value[1] + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            service[1].save(out);
        }
        return this;
    }



    //Loads from external data file
    public Tnode load(Scanner scan, int value) {
        this.value[0] = value;
        int type = scan.nextInt();
        scan.nextLine();
        if (type == 1) {
            service[0] = new Home();
        } else if (type == 2) {
            service[0] = new Delivery();
        } else
            service[0] = new Pet();

        service[0].load(scan);
        return this;
    }



    //Display all services in the node
    public void display(){

        if(service[0] != null) {
            service[0].display();
        }

        if(service[1] != null) {
            if(center != null)
                center.display();
            service[1].display();
        }

        if(service[2] != null) {
            service[2].display();
        }
    }



    //Displays first data element
    public void display1(){

        if(service[0] != null) {
            service[0].display();
        }
    }



    //displays second data element.
    public void display2(){
        if(service[1] != null) {
            if(center != null)
                center.display();
            service[1].display();
        }
    }



    //Add a service
    public void add(int to_add, String title) {
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("What type of service is this?\n 1. Home \n 2. Pet Care \n 3. Delivery");
        option = input.nextInt();
        if (service[0] == null) {
            if (option == 1) {
                service[0] = new Home();
            }else if (option == 2) {
                service[0] = new Pet();
            }else
                service[0] = new Delivery();

            service[0].add(title);
            value[0] = to_add;
        } else if (service[1] == null) {
            if (option == 1)
                service[1] = new Home();
            else if (option == 2)
                service[1] = new Pet();
            else
                service[1] = new Delivery();

            value[1] = to_add;
            service[1].add(title);
            if (value[1] < value[0]) {
                Service temp = service[0];
                int temp2 = value[0];
                service[0] = service[1];
                value[0] = value[1];
                service[1] = temp;
                value[1] = temp2;
            }
        } else {
            if (option == 1)
                service[2] = new Home();
            else if (option == 2)
                service[2] = new Pet();
            else
                service[2] = new Delivery();
            service[2].add(title);
            value[2] = to_add;
            //reorganize by value
            if (value[2] < value[1]) {
                Service temp = service[1];
                service[1] = service[2];
                service[2] = temp;
                int temp2 = value[1];
                value[1] = value[2];
                value[2] = temp2;
            }
            if (value[1] < value[0]) {
                Service temp = service[0];
                service[0] = service[1];
                service[1] = temp;
                int temp2 = value[0];
                value[0] = value[1];
                value[1] = temp2;

            }
        }
    }



    //Used to add a node into the tree.
    public void add(Tnode to_add) {
            if(this.service[0] == null) {
                this.value[0] = to_add.value[0];
                this.service[0] = to_add.service[0];
            }
            else if(this.service[1] == null) {
                this.value[1] = to_add.value[0];
                this.service[1] = to_add.service[0];
            }else{
                this.value[2] = to_add.value[0];
                this.service[2] = to_add.service[0];
            }

            if (value[1] < value[0]) {
                Service temp = service[0];
                int temp2 = value[0];
                service[0] = service[1];
                value[0] = value[1];
                service[1] = temp;
                value[1] = temp2;
            }
            if (value[2] < value[1]) {
                Service temp = service[1];
                service[1] = service[2];
                service[2] = temp;
                int temp2 = value[1];
                value[1] = value[2];
                value[2] = temp2;
            }
            if (value[1] < value[0]) {
                Service temp = service[0];
                service[0] = service[1];
                service[1] = temp;
                int temp2 = value[0];
                value[0] = value[1];
                value[1] = temp2;

            }
        }




    //Return the left node.
    public Tnode go_left(){
        return this.left;
    }



    //Return the right node.
    public Tnode go_right(){
        return this.right;
    }



    //Return the center node.
    public Tnode go_center(){
        return this.center;
    }



    //Connect the left
    public void connect_left(Tnode to_connect){
        this.left = to_connect;
    }


    //Connect the left
    public void connect_right(Tnode to_connect){
        this.right = to_connect;
    }



    //Connect the left
    public void connect_center(Tnode to_connect){
        this.center = to_connect;
    }



    //Returns number of services in the node
    public int get_num(){
        int num = 0;
        if(service[0] != null)
            ++num;
        if(service[1] != null)
            ++num;
        if(service[2] != null)
            ++num;
        return num;
    }



    //returns value 1
    public int get_value(){
        return value[0];
    }



    //returns value 2
    public int get_value2(){
        return value[1];
    }



    //Splits a node
    public Tnode split(){
        Tnode tleft = new Tnode();
        Tnode tright = new Tnode();

        tleft.service[0] = this.service[0];
        tleft.value[0] = this.value[0];

        tright.service[0] = this.service[2];
        tright.value[0] = this.value[2];

        this.service[0] = this.service[1];
        this.value[0] = this.value[1];

        this.service[1] = null;
        this.service[2] = null;
        if(this.center != null)
            tleft.connect_right(this.center);
        this.connect_left(tleft);
        this.connect_right(tright);
        return this;
    }



    //Merges 2 nodes
    public Tnode merge(Tnode to_merge){
        boolean cleft = false;

        if (service[1] == null) {
            value[1] = to_merge.value[0];
            service[1] = to_merge.service[0];
            if (value[1] < value[0]) {
                cleft = true;
                Service temp = service[0];
                int temp2 = value[0];
                service[0] = service[1];
                value[0] = value[1];
                service[1] = temp;
                value[1] = temp2;
            }

       } else {
            value[2] = to_merge.value[0];
            service[2] = to_merge.service[0];

            if (value[2] < value[1]) {
                Service temp = service[1];
                service[1] = service[2];
                service[2] = temp;
                int temp2 = value[1];
                value[1] = value[2];
                value[2] = temp2;
            }
            if (value[1] < value[0]) {
                Service temp = service[0];
                service[0] = service[1];
                service[1] = temp;
                int temp2 = value[0];
                value[0] = value[1];
                value[1] = temp2;
            }
            this.split();
        }
        if (cleft) {
            connect_left(to_merge.go_left());
            connect_center(to_merge.go_right());
        } else {
            connect_right(to_merge.go_right());
            connect_center(to_merge.go_left());
        }
        return this;
    }
}

