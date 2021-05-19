package Program4;

import java.util.Scanner;

//This class we implemented for Program 5
public class ArrLLL {
    Lnode[] head;



    //Constructor - sets each index to null
    public ArrLLL(){
        head = new Lnode[98];
        int i = 0;
        set_null(head, i );
    }


    //Helper function to set each index to null in the ArrLLL
    void set_null(Lnode[] to_set, int i){
        to_set[i] = new Lnode();
        to_set[i] = null;
        ++i;
        if(i < 98)
            set_null(to_set, i);
    }



    //Copy constructor
    public ArrLLL(ArrLLL source){
        if(source == null)
            return;
        head = new Lnode[98];
        int i = 0;
        copy_all(source.head, head, i);
    }



    //This function copies each index then calls the function to copy the list in the index
    void copy_all(Lnode[] source, Lnode[] to_set, int i){
        if(source[i] != null) {
            to_set[i] = copy_list(source[i], to_set[i]);
        }
        else {
            to_set[i] = new Lnode();
            to_set[i] = null;
        }

        ++i;
        if(i < 98)
            copy_all(source, to_set, i);
    }



    //This function copies a linear linked list
    Lnode copy_list(Lnode source, Lnode dest){
        if(source == null) {
            dest = null;
            return dest;
        }
        dest = new Lnode(source);
        dest.connect_next(copy_list(source.go_next(), dest.go_next()));
        return dest;
    }

    //This function prompts the user for a name to search for. Then it creates a hash value and goes to
    //the matching index and searches for that string. (Each index is a hash value)
    public void search(){
        String to_search;
        int hash_value;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name to search for:");
        to_search = input.nextLine();
        hash_value = hash(to_search);
        if(head[hash_value] == null)
            System.out.println("No Matches found");
        search(head[hash_value], to_search);
    }



    public void search_type(){
        Scanner input = new Scanner(System.in);
        System.out.println("What type of service would you like to search for?\n1. Home\n2. Delivery\n3. Pet");
        int option = input.nextInt();
        int index = 0;
        search_type(option, index);
    }

    public void search_type(int to_find, int index){
        if(index > 97)
            return;
        if(head[index] != null){
            head[index].search_list(to_find, head[index]);
        }
        ++index;
        search_type(to_find, index);

    }




    //Recursive function to search for a title in a linear linked list.
    public Lnode search(Lnode head, String to_search){
        if(head == null)
            return head;
        if(to_search.length() == head.service.title.length())
        {
            if(to_search.equals(head.service.title)) {
                head.service.display();
                System.out.println();
            }
        }
        search(head.go_next(), to_search);
        return head;
    }





    //This function creates a key given a string.
    public int hash(String to_hash){
        int key = 0;
        char[] temp = new char[to_hash.length() +1];
        for(int i = 0; i < to_hash.length(); ++i){
            temp[i] = to_hash.charAt(i);
        }
        for(int i = 0; i < to_hash.length(); ++i){
            key += temp[i];
        }
        return (key * 907) % 97;
    }



    //This function is a wrapper function that calls a recursive function to display each index
    public int display_all(){
        if(this.head == null) {
            System.out.println("No services to display");
            return 0;
        }
        for(int i = 0; i < 98; ++i) {
            display_all(this.head[i]);
        }
        return 0;
    }



    //Displays all nodes in the current list
    public int display_all(Lnode head){
        if(head == null)
            return 0;
        head.display();
        display_all(head.go_next());
        return 0;
    }




    //Sets all indexes to null
    public void remove_all(){
        for(int i = 0; i < 97; ++i)
        head[i] = null;
        head = null;
    }



    public int Save(){
        return 0;
    }




    //Returns the tail node of the argument
    public Lnode go_tail(Lnode head){
        if(head.go_next() == null)
            return head;
        else
            return go_tail(head.go_next());
    }



    //This function loads services from external data files into an ArrLLL
    public int Load(Scanner scan){
        int value = scan.nextInt();
        scan.nextLine();
        Lnode temp;
        if(head[value] == null) {
            head[value] = new Lnode();
            head[value].load(scan);
        }
        else {
            Lnode pre = go_tail(head[value]);
            temp = pre.go_next();
            temp = new Lnode();
            temp.load(scan);
            pre.connect_next(temp);
        }
        return 0;
    }
}
