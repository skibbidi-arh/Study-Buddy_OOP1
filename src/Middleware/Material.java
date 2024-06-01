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
    String fileLink = null;
    
    public Material(Semester semester, Course course, String fileLink) {
        this.semester = semester;
        this.course = course;
        this.fileLink = fileLink;
    }

    public String getSemester(){
        return semester.semester;
    }
    public String getCourseName(){
        return course.name;
    }
    public String getCourseCode(){
        return course.code;
    }
    public String getFileLink(){
        return getFileLink();
    }
    public String getMaterialLink(){
        return fileLink;
    }
    
}