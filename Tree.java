//CS202 Program 4 Ashton Smith
//Tree.java

package Program4;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//This class is used to organize the services data using a 2-3 tree.
public class Tree {
    protected Scanner input = null;
    Tnode root;


    public Tree() {
        root = null;
    }


    //This function displays all items in the tree.
    public int Display_all() {
        if (this.root == null)
            return 0;
        else
            Display_all(this.root);
        return 1;
    }


    //recursive function to display all services in the tree.
    private int Display_all(Tnode root) {
        if (root == null)
            return 0;
        Display_all(root.go_left());
        root.display();//center handled here too
        Display_all(root.go_right());
        return 1;
    }



    //Creates a key given a string.
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



    //This function is used to insert into the tree.(wrapper function)
    public void Insert() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the title");

        String title = input.nextLine();
        int to_add = hash(title);

        if (this.root == null) {
            this.root = new Tnode();
            this.root.add(to_add, title);
        } else {
            Insert(this.root, to_add, title);
        }
    }


    //Insert function - returns a bool to determine if a child was split.
    public boolean Insert(Tnode root, int to_add, String title) {
        //root is a leaf
        boolean child_splitL = false;
        boolean child_splitR = false;
        boolean child_splitC = false;
        if (root.go_left() == null && root.go_right() == null && root.go_center() == null) {
            root.add(to_add, title);
            //if it is now a 3 node - split until its not
            while (root.get_num() > 2) {
                //split it
                Tnode temp = root.split();
                root = null;
                root = new Tnode(temp);
            }
        } else {
            //traverse to find a leaf
            //if there is a center
            if (root.get_num() > 1) {
                if (root.get_value() < to_add && root.get_value2() >= to_add)
                    child_splitC = Insert(root.go_center(), to_add, title);
                else if (root.get_value() > to_add)
                    child_splitL = Insert(root.go_left(), to_add, title);
                else
                    child_splitR = Insert(root.go_right(), to_add, title);
            } else {
                if (root.get_value() > to_add)
                    child_splitL = Insert(root.go_left(), to_add, title);
                else
                    child_splitR = Insert(root.go_right(), to_add, title);
            }
        }

        //A child was split
        if (child_splitL) {
            root = root.merge(root.go_left());
        } else if (child_splitR) {
            root = root.merge(root.go_right());
        } else if (child_splitC) {
            root = root.merge(root.go_center());
        }

        if (root.get_num() > 2) {
            Tnode temp = root.split();
            root = null;
            root = temp;
            return true;
        }

        return false;
    }

    //Wrapper function for inserting into the tree - this is used when loading from a file.
    public boolean load_Insert(int to_add, Tnode node_to_add) {
        if (this.root == null) {
            this.root = new Tnode(node_to_add);
        } else {
            load_Insert(this.root, to_add, node_to_add);
        }
        return true;
    }


    //Recursive function for inserting into the tree - this is used when loading from a file.
    //The main difference between this and the normal insert function is it uses a different add function.
    public boolean load_Insert(Tnode root, int to_add, Tnode node_to_add) {
        //root is a leaf
        boolean child_splitL = false;
        boolean child_splitR = false;
        boolean child_splitC = false;

        if (root.go_left() == null && root.go_right() == null && root.go_center() == null) {
            root.add(node_to_add);
            //if it is now a 3 node - split until its not
            while (root.get_num() > 2) {
                //split it
                Tnode temp = root.split();
                root = null;
                root = new Tnode(temp);
            }
        } else {
            //traverse to find a leaf
            //if there is a center
            if (root.get_num() > 1) {
                if (root.get_value() < to_add && root.get_value2() >= to_add)
                    child_splitC = load_Insert(root.go_center(), to_add, node_to_add);
                else if (root.get_value() > to_add)
                    child_splitL = load_Insert(root.go_left(), to_add, node_to_add);
                else
                    child_splitR = load_Insert(root.go_right(), to_add, node_to_add);
            } else {
                if (root.get_value() > to_add)
                    child_splitL = load_Insert(root.go_left(), to_add, node_to_add);
                else
                    child_splitR = load_Insert(root.go_right(), to_add, node_to_add);
            }
        }

        //A child was split
        if (child_splitL) {
            root = root.merge(root.go_left());
        } else if (child_splitR) {
            root = root.merge(root.go_right());
        } else if (child_splitC) {
            root = root.merge(root.go_center());
        }

        if (root.get_num() > 2) {
            Tnode temp = root.split();
            root = null;
            root = temp;
            return true;
        }

        return false;
    }


    //Wrapper function to save all nodes to an external data file
    public int save(FileWriter out) {
        if(this.root == null)
            return 0;

        return save(out, this.root);
        }




    //Recursive function to save all nodes to an external data file
    public int save(FileWriter out, Tnode root) {
        if(root == null)
            return 0;
        save(out, root.go_left());
        root.save(out);
        save(out, root.go_center());
        save(out, root.go_right());
        return 1;
    }



    //Search the tree for a name
    public int search() {

        Scanner input = new Scanner(System.in);
        if (this.root == null)
            return 0;
        System.out.println("Enter a name to search for");
        String name = input.nextLine();
        int num = hash(name);
            search(this.root, num);
        return 1;
    }


    //Search for a node by name(hash key)
    private int search(Tnode root, int num) {
        if (root == null)
            return 0;
        if(root.get_value() == num)
            root.display1();
        if(root.get_value2() == num)
            root.display2();
        search(root.go_left(),num);
        search(root.go_center(),num);
        search(root.go_right(), num);


        return 1;
    }

}
