package Middleware;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Material{
    Semester semester;
    Course course;
    String fileTitle = null;
    String fileLink = null;
    
    public Material(Semester semester, Course course, String fileTitle,String fileLink) {
        this.semester = semester;
        this.course = course;
        this.fileTitle = fileTitle;
        this.fileLink = fileLink;
    }

    public Semester getSemester(){
        return semester;
    }
    public String getCourseName(){
        return course.name;
    }
    public String getCourseCode(){
        return course.code;
    }
    public String getMaterialLink(){
        return fileLink;
    }
    public String getMaterialTitle(){
        return fileTitle;
    }

    public boolean materialAlreadyExists(){
        boolean isFound = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if( values.length == 5 && values[0].equals(semester.semester) && (values[1].equals(course.name) || values[2].equals(course.code)) && (values[3].equals(fileTitle) || values[4].equals(fileLink))){
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