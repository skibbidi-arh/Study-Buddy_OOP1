package Middleware;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
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
    
}