import java.io.File;

public class Semester {
    public String semester;
    public Semester(String semester){
        this.semester = semester;
    }
    public boolean addSemister(){
        //In case data folder does not exists it will create one.
        File dataFolder = new File("data/");
        if(!dataFolder.mkdir()  && !dataFolder.exists())
            return false;
        File semesterFolder = new File("data/"+semester);
        if(semesterFolder.mkdir())
            return true;
        else
            return false;
    }

    public boolean isSemisterAvailable(){
        File semesterFolder = new File("data/"+semester);
        if(semesterFolder.exists() && semesterFolder.isDirectory())
            return true;
        else
            return false;
    }

    public boolean removeSemister(){
        File semesterFolder = new File("data/"+semester);
        if(deleteDirectory(semesterFolder))
            return true;
        else
            return false;
    }
    //a method to 
    public static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            // get all the directories in the current file
            File[] files = directory.listFiles();
            if (files != null) {
                // recursivly delete each folder and file
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }

        // Delete the directory or file
        return directory.delete();
    }

}
