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
    String filePath = null;
    //A prevous saved material - with location
    public Material(Semester semester, Course course, String filePath) {
        this.semester = semester;
        this.course = course;
        this.filePath = filePath;
    }

    //A new material - without location
    public Material(Semester semester, Course course) {
        this.semester = semester;
        this.course = course;
    }


    public void addMaterial(String fileName ,String filePath){
        this.filePath = filePath;
        File file = new File(filePath);
        
    }

    public void relocateMaterial(String fileName, String fileSourcePath){

    }
}