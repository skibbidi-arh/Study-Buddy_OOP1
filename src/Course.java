import java.util.*;
public class Course  extends Semester{
    String name;
    ArrayList<Material> mat;
public Course(String name)
{
    super(2);
    this.name=name;
    this.mat= new ArrayList<>();

}


public void addmtr(ArrayList<Material> mat)
{
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter material name");
    String nm = scan.nextLine();
    System.out.println("Enter material type");
    String tp = scan.nextLine();
    System.out.println("Enter material location");
    String lc = scan.nextLine();
    mat.add(new Material(nm,tp,lc));
    System.out.println("material added");
    System.out.println("1- add another material\n2-view material list\n3-view semester list\n0-main menue");
    int as =scan.nextInt();
    switch (as)
    {

        case 1:
            addmtr(mat);
            break;
        case 2:
            showmtr(mat);
    }


}


public void showmtr(ArrayList<Material> mat)
{
    Scanner scan = new Scanner(System.in);
    for(Material mt: mat)
    {
        System.out.println(mt.name+" "+mt.type);

    }
    System.out.println("1 - add a material\n2-back to main option");

    int as =scan.nextInt();
    switch (as)
    {

        case 1:
            addmtr(mat);
            break;
        case 2:
            options(sem);
    }

}


}
