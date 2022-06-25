
package timetableproject.classHierarchy;

//**********IMPORTS**********//
import java.io.Serializable;

//=======================
//|   CREATING COURSE   |
//=======================
public class Course implements Serializable {
    
    //********************************************DATA MEMBERS********************************************//
    private String name;
    public boolean active;
    private String teacher;
    private int weeklyClassCount;

    //********************************************CONSTRUCTORS********************************************//
    //--------DEFAULT CONSTRUCTOR--------//
    public Course() {

    }
    
    //--------ARGUMENT CONSTRUCTORS--------//
    public Course(String name, int weeklyClassCount) {
        this.name = name;
        this.weeklyClassCount = weeklyClassCount;
    }

    public Course(String name,String teacher, int weeklyClassCount) {
        this.name = name;
        this.teacher = teacher;
        this.weeklyClassCount = weeklyClassCount;
    }
    //*****************************************************************************//

    //******************************SETTERS AND GETTERS******************************//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeeklyClassCount() {
        return weeklyClassCount;
    }

    public void setWeeklyClassCount(int weeklyClassCount) {
        this.weeklyClassCount = weeklyClassCount;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    //*****************************************************************************//

}

