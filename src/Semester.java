import java.util.*;
public class Semester extends Main {
    int semester;
   ArrayList <Course> cou ;
    ArrayList <Course> getCou()
    {
        return cou;
    }

    public Semester(int semester)
    {
     this.semester=semester;
       this.cou=new ArrayList<>();
    }
    public void addcourse(ArrayList<Course> course)
    {


        System.out.println("Write course name");
        Scanner scan = new Scanner(System.in);
        String nm = scan.nextLine();
        course.add(new Course(nm));
        System.out.println("0 for go back \n1 for adding another course");
        int as=scan.nextInt();
        if(as==0)showsem(sem);
        else if (as==1)addcourse(cou);
        else System.out.println("invalid input , try again");

    }
    public void showcourse(ArrayList<Course> course)
    {
        System.out.println("ok");
        for(Course cr: course)
        {
            System.out.println(cr.name+"\n");
        }
        System.out.println("1 - add a course");
        System.out.println("0- go back");
        System.out.println("2- access a course");
        Scanner scan = new Scanner(System.in);
        int as = scan.nextInt();
        if(as==0)showsem(sem);
        else if (as==1)addcourse(cou);
        else if (as==2) {

            System.out.println("Enter course name");
           String sc= scan.nextLine();
            String cname = scan.nextLine();
            int ind;
            for(Course cr: course )
            {
                if(cname==cr.name) { ind= course.indexOf(cr);break;}
            }
            System.out.println("1-add a material\n2");

        }
        else System.out.println("invalid input");

    }




}
