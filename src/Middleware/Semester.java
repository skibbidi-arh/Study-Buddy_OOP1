package Middleware;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Semester {
    public String semester;
    public Semester(String semester){
        this.semester = semester;
    }
    public ArrayList<String> getAllCourseName(){
        ArrayList<String> courseList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if(values[0].equals(semester)) courseList.add(values[1]);
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error reading file");
        }
        return courseList;
    }

    public ArrayList<String> getAllCourseCode(){
        ArrayList<String> courseList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if(values[0].equals(semester)) courseList.add(values[2]);
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error reading file");
        }
        return courseList;
    }
}
