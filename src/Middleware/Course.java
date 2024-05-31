package Middleware;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Course{
    Semester semester;
    public String name;
    public String code;

    public Course(Semester semester,String name, String code){
        this.semester = semester;
        this.name = name;
        this.code = code;
    }

    public boolean courseAlreadyExists(){
        boolean isFound = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                // System.out.println(values[0].getClass().getSimpleName()+semester.semester.getClass().getSimpleName());
                // System.out.println(values[1]+name);
                // System.out.println(values[2]+code);
                if(values[0].equals(semester.semester) && (values[1].equals(name) || values[2].equals(code))){
                    isFound = true;
                    return true;
                }
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error reading file");
        }
        if(!isFound) return false;
        else return true;
    }
    
}