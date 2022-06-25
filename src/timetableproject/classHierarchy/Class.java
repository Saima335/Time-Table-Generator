
package timetableproject.classHierarchy;

//**********IMPORTS**********//
import java.io.Serializable;
import java.util.Arrays;
import timetableproject.roomHierarchy.RoomSlot;

//======================
//|   CREATING CLASS   |
//======================
public class Class implements Serializable {
    
    //********************************************DATA MEMBERS********************************************//
    private String name;
    private int strength;
    private boolean active;
    private Course[] courses = new Course[10];
    private int courseCount;
    public static int classCount = 0;
    private RoomSlot[] slots;
    private ClassSlot[] classSlots = new ClassSlot[100];
    private int classSlotCount = 0;

    //********************************************CONSTRUCTORS********************************************//
    //--------DEFAULT CONSTRUCTOR--------//
    public Class() {
        classCount++;
    }

    //--------ARGUMENT CONSTRUCTORS--------//
    public Class(String name, int strength) {
        this.name = name;
        this.strength = strength;
        classCount++;
        this.active = false;
    }

    public Class(String name, int strength, boolean active, Course[] courses, int courseCount) {
        this.name = name;
        this.strength = strength;
        this.active = active;
        this.courses = courses.clone();
        this.courseCount = courseCount;
        classCount++;
    }
    //*****************************************************************************//
    
    //******************************SETTERS AND GETTERS******************************//
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public ClassSlot[] getClassSlots() {
        return classSlots;
    }

    public void setClassSlots(ClassSlot[] classSlots) {
        this.classSlots = classSlots;
    }

    public int getClassSlotCount() {
        return classSlotCount;
    }

    public void setClassSlotCount(int classSlotCount) {
        this.classSlotCount = classSlotCount;
    }

    public RoomSlot[] getSlots() {
        return slots;
    }

    public void setSlots(RoomSlot[] slots) {
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses.clone();
    }

    public int getCourseCount() {
        return courseCount;
    }
    //*****************************************************************************//


    //**********To STRING FUNCTION**********//
    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", active=" + active +
                ", courses=" + Arrays.toString(courses) +
                ", courseCount=" + courseCount +
                ", classCount=" + classCount +
                '}';
    }
    //*****************************************************************************//

    //*******Return true if class is added and false otherwise*******//
    public static boolean addClass(String name,int strength,Class[] arr){
        //-----If all classes are added-----
        if(arr.length == Class.classCount){
            return false;
        }
        boolean nameValid = true;
        //-----Check the uniqueness of name-----
        for(int i = 0;i<classCount;i++){
            if(arr[i].name.equals(name)){
                nameValid = false;
                break;
            }
        }
        //-----Validating length of name-----
        if(name.length()<2 || name.length()>20){
            nameValid = false;
        }
        //-----Add class if name of class is valid-----
        if(nameValid){
            arr[Class.classCount] = new Class(name,strength);
        }
        return nameValid;
    }
    //*****************************************************************************//
    
    //*******Return true if class is removed successfully*******//
    public static boolean removeClass(String name,Class[] arr){
        boolean removed = false;
        //-----If only one class is present-----
        if(classCount == 1){
            arr[0] = null;
            classCount--;
            return true;
        }
        //-----Removing Class by Name-----
        for(int i = 0;i<classCount;i++){
            //If class is not removed yet and class name is not found
            if(!removed && arr[i].getName().equals(name)){
                removed = true;
            }
            //Remove the class by shifting left
            else if(removed){
                arr[i-1] = arr[i];
            }
        }
        //-----Decrement classes if class is removed-----
        if(removed){
            classCount--;
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Return true if class is edited successfully*******//
    public boolean editClass(String newName, int newStrength, Class[] arr){
        boolean valid = true;
        //-----Validate length of name-----
        if(newName.length()>20||newName.length()<2){
            valid = false;
        }
        if (!(newName.equals(this.getName()))){
            //-----Checking Uniqueness of Class Name -----
            for(int i = 0;i<classCount;i++){
                if(arr[i].getName().equals(newName)){
                    valid = false;
                    break;
                }
            }
        }
        //-----If Class Name is Valid Edit Class Name and Strength-----
        if(valid){
            this.setName(newName);
            this.setStrength(newStrength);
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Return true if course is added successfully*******//
    public boolean addCourse(String name,String teacher,int weeklyClassCount){
        boolean valid = true;
        //-----Checking Validity of Course Name-----
        if(name.length()>20 || name.length()<2){
            valid = false;
        }
        //-----Checking Uniqueness of Course Name-----
        for(int i = 0;i<courseCount;i++){
            if(courses[i].getName().equals(name)){
                valid = false;
                break;
            }
        }
        //-----In a Class Maximum number of Courses is 10-----
        if(courseCount>=10){
            valid = false;
        }
        //-----If Course is valid and Number of Courses is greater than 0 add Course-----
        if(valid&&weeklyClassCount>0){
            courses[courseCount] = new Course(name,teacher,weeklyClassCount);
            courseCount++;
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Return true if course is removed successfully*******//
    public boolean removeCourse(String name){
        boolean removed = false;
        //-----If only one course is present-----
        if(courseCount == 1){
            courses[0] = null;
            courseCount--;
            return true;
        }
        //-----Removing Course by Name-----
        for(int i = 0;i<courseCount;i++){
            //If course is not removed and course name is not found
            if(!removed && courses[i].getName().equals(name)){
                removed = true;
            }
            //Remove the course by shifting left
            else if (removed){
                courses[i-1] = courses[i];
            }
        }
        //-----Decrement courses if course removed-----
        if(removed){
            courseCount--;
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Making Slots For Classes*******//
    public void makeSlots(){
        //-----Making Slots for All Courses-----
        for(int i = 0;i<this.courseCount;i++){
            //-----Making Slots for Weekly Classes of Each Course-----
            for(int j = 0;j<this.courses[i].getWeeklyClassCount();j++){
                //-----Increasing length of Class Slots Array-----
                if(this.classSlots.length==this.classSlotCount){
                    ClassSlot[] temp = new ClassSlot[classSlotCount];
                    for(int k = 0;k<classSlotCount;k++){
                        temp[k] = classSlots[k];
                    }
                    classSlots = new ClassSlot[classSlotCount*2];
                    for(int k = 0;k<classSlotCount;k++){
                        classSlots[k] = temp[k];
                    }
                }
                //-----Adding Class, Course and Class number in Class Slot Array-----
                classSlots[classSlotCount] = new ClassSlot(this,this.courses[i],j);
                classSlotCount++;
            }
        }
    }
    //*****************************************************************************//
    
    //*******Resetting Class Slots*******//
    public void resetSlots(){
        classSlots = new ClassSlot[100];
        classSlotCount = 0;
    }
    //*****************************************************************************//
}

