import java.util.*;
public class Main {
   public static ArrayList<Semester> sem = new ArrayList<>();
    public static   ArrayList<Course> crs = new ArrayList<>();
    public static ArrayList <Material> mtl = new  ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scan = new Scanner(System.in);
//
//        ArrayList<Semester> sem = new ArrayList<>();
//        ArrayList<Course> crs = new ArrayList<>();
//        ArrayList <Material> mtl = new  ArrayList<>();


        options(sem);

    }



    //==============================================================================================



    public static void options(ArrayList<Semester> sem)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. add a new semester\n");
        System.out.println("2. Show all the semester list\n");
        System.out.println("3.Return to main menu\n");
        int op = scan.nextInt();
        switch (op)
        {
            case 1:
                addsem(sem);
                break;
            case 2:
                showsem(sem);
                break;
            case 3:
                options(sem);


        }
    }



  //==================================================================================================



    public static void addsem ( ArrayList<Semester> sem)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter semester no:");
        sem.add(new Semester(scan.nextInt()));
        System.out.println("semester added");
        options(sem);

    }


    //===============================================================================================




    public static void showsem( ArrayList<Semester> sem)
    {

        for(Semester cr: sem)
        {
            System.out.println(cr.semester+"st semester\n");
        }

        System.out.println("1. access a semester \n 2.Go back to main menue");


        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();


        if(a==1){

            System.out.println("Chose semester");
            int b = scan.nextInt();
            int ind=0;
            for(Semester cr: sem)
            {
                if(b==cr.semester)
                {
                   ind= sem.indexOf(cr);
                }
            }


            System.out.println("1. Add courses \n2.Show courses");
            int ab = scan.nextInt();
            switch (ab)
            {
                case 1:

                    sem.get(ind).addcourse(sem.get(ind).cou);
                    break;

                case  2:
                    sem.get(ind).showcourse(sem.get(ind).cou);
                    break;

            }

        }
        else if (a==2)options(sem);
        else System.out.println("Invalid input");


    }

}