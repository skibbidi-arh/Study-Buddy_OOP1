import java.io.File;

public class Semester {
    public boolean addSemister(String semester){
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

    public boolean isSemisterAvailable(String semester){
        File semesterFolder = new File("data/"+semester);
        if(semesterFolder.exists() && semesterFolder.isDirectory())
            return true;
        else
            return false;
    }

    public boolean removeSemister(String semester){
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
