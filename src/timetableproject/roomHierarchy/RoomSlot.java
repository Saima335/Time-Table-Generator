
package timetableproject.roomHierarchy;

//**********IMPORTS**********//
import java.io.Serializable;
import java.sql.Time;
import timetableproject.classHierarchy.Course;
import timetableproject.classHierarchy.Class;

//================================
//|   CREATING SLOTS FOR ROOMS   |
//================================
public class RoomSlot implements Serializable {
    
    //********************************************DATA MEMBERS********************************************//
    private String name;
    private int capacity;
    private int day;
    private Time startTime;
    private Time endTime;
    private Course course;
    private Class sClass;

    //********************************************CONSTRUCTORS********************************************//
    //--------DEFAULT CONSTRUCTOR--------//
    public RoomSlot() {

    }

    //--------ARGUMENT CONSTRUCTORS--------//
    public RoomSlot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public RoomSlot(String name, int capacity,int day, Time startTime, Time endTime) {
        this.name = name;
        this.capacity = capacity;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    //*****************************************************************************//
    
    //******************************SETTERS AND GETTERS******************************//
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setsClass(Class sClass) {
        this.sClass = sClass;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Course getCourse() {
        return course;
    }

    public Class getsClass() {
        return sClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    //*****************************************************************************//

    //*******Adding Class and Course to Room Slot*******//
    public void addClass(Class sClass,Course course){
        this.sClass = sClass;
        this.course = course;
    }
    //*****************************************************************************//
}

