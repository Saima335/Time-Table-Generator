
package timetableproject.classHierarchy;

//**********IMPORTS**********//
import java.io.Serializable;

//==================================
//|   CREATING SLOTS FOR CLASSES   |
//==================================
public class ClassSlot implements Serializable {
    
    //********************************************DATA MEMBERS********************************************//
    Class sClass;
    Course course;
    int classNo;
    boolean used = false;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTOR--------//
    public ClassSlot(Class sClass, Course course, int classNo) {
        this.sClass = sClass;
        this.course = course;
        this.classNo = classNo;
    }
    //*****************************************************************************//

    //******************************SETTERS AND GETTERS******************************//
    public Class getsClass() {
        return sClass;
    }

    public void setsClass(Class sClass) {
        this.sClass = sClass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    //*****************************************************************************//

}

