
package timetableproject.dataManagement;

//**********IMPORTS**********//
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import timetableproject.classHierarchy.Course;
import timetableproject.classHierarchy.Class;
import timetableproject.roomHierarchy.Room;
import timetableproject.roomHierarchy.RoomSlot;

//===============================================
//|   STORING CLASSES AND ROOMS RECORD IN FILE  |
//===============================================
public class TimeTableSpace extends JFrame {
    
    //********************************************DATA MEMBERS********************************************//
    private String name;
    private Class[] classes = new Class[100];
    private Room[] rooms = new Room[100];
    private boolean autoSaveEnabled;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTORS--------//
    public TimeTableSpace(String name) {
        this.name = name;
    }

    public TimeTableSpace(String name, Class[] classes, Course[] courses, Room[] rooms, RoomSlot[] roomSlots) {
        this.name = name;
        this.classes = classes;
        this.rooms = rooms;
    }
    //*****************************************************************************//
    
    //******************************SETTERS AND GETTERS******************************//
    public boolean isAutoSaveEnabled() {
        return autoSaveEnabled;
    }

    public void setAutoSaveEnabled(boolean autoSaveEnabled) {
        this.autoSaveEnabled = autoSaveEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF ROOMS AND CLASSES ARE WRITED TO FILE**********//
    public boolean saveSpace(){
        return saveClasses() & saveRooms();
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF ROOMS AND CLASSES ARE READED FROM FILE**********//
    public boolean loadSpace(){
        return loadClasses() & loadRooms();
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF ALL CLASSES ARE WRITED IN FILE**********//
    public boolean saveClasses(){
        try{
            //-----Writing array of classes and static variable class count to file-----
            ObjectOutputStream writeToFile = new ObjectOutputStream(new FileOutputStream("classes.dat"));
            writeToFile.writeObject(classes);
            writeToFile.writeInt(Class.classCount);
            writeToFile.close();
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF ALL ROOMS ARE WRITED IN FILE**********//
    public boolean saveRooms(){
        //-----Try Writing array of rooms and static variable room count to file-----
        try{
            ObjectOutputStream writeToFile = new ObjectOutputStream(new FileOutputStream("rooms.dat"));
            writeToFile.writeObject(rooms);
            writeToFile.writeInt(Room.roomCount);
            writeToFile.close();
        }
        //-----Catching Exception-----
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF CLASSES DATA IS READED FROM FILE**********//
    public boolean loadClasses(){
        //-----Creating object of file of Classes-----
        File dataFile = new File("classes.dat");
        //-----If file of Classes exists-----
        if(dataFile.exists()){
            //-----Try Reading array of classes and static variable class count from file-----
            try{
                ObjectInputStream loadFromFile = new ObjectInputStream(new FileInputStream("classes.dat"));
                classes = (Class[]) loadFromFile.readObject();
                Class.classCount = loadFromFile.readInt();
                loadFromFile.close();
                return true;
            }
            //-----Catching Exceptions-----
            catch (IOException e){
                e.printStackTrace();
                return false;
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF ROOMS DATA IS READED FROM FILE**********//
    public boolean loadRooms(){
        //-----Make an object of Rooms file-----
        File dataFile = new File("rooms.dat");
        //-----If File of Rooms Exists-----
        if(dataFile.exists()){
            //-----Try Reading array of classes and static variable class count from file-----
            try{
                ObjectInputStream loadFromFile = new ObjectInputStream(new FileInputStream("rooms.dat"));
                rooms = (Room[]) loadFromFile.readObject();
                Room.roomCount = loadFromFile.readInt();
                loadFromFile.close();
                return true;
            }
            //-----Catching Exceptions-----
            catch (IOException e){
                e.printStackTrace();
                return false;
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //**********RETURN TRUE IF CLASS IS ADDED**********//
    public boolean addClass(String name,int capacity){
        //-----Increasing Length of Class Array if full-----
        if(classes.length == Class.classCount){
            Class[] temp = new Class[classes.length];
            for(int i = 0;i<classes.length;i++){
                temp[i] = classes[i];
            }
            classes = new Class[Class.classCount*2];
            for(int i = 0;i<Class.classCount;i++){
                classes[i] = temp[i];
            }
        }
        //-----Adding Class-----
        boolean done = Class.addClass(name,capacity,classes);
        //-----Save Classes in File after adding if Auto Save is Enabled-----
        if(autoSaveEnabled){
            this.saveClasses();
        }
        return done;
    }
    //*****************************************************************************//
    
    //**********RETURN TRUE IF CLASS IS REMOVED**********//
    public boolean removeClass(String name){
        //-----Removing Class by Name-----
        boolean done = Class.removeClass(name,classes);
        //-----Save Classes in File after removing if Auto Save is Enabled-----
        if(autoSaveEnabled){
            this.saveClasses();
        }
        return done;
    }
    //*****************************************************************************//
    
    //**********RETURN TRUE IF ROOM IS ADDED**********//
    public boolean addRoom(String name,int capacity,float startTime,float endTime){
        //-----Increasing Length of Room Array if full-----
        if(rooms.length == Room.roomCount){
            Room[] temp = new Room[rooms.length];
            for(int i = 0;i<rooms.length;i++){
                temp[i] = rooms[i];
            }
            rooms = new Room[Room.roomCount*2];
            for(int i = 0;i<Room.roomCount;i++){
                rooms[i] = temp[i];
            }
        }
        //-----Adding Room-----
        boolean done = Room.addRoom(name,capacity,startTime,endTime,rooms);
        //-----Save Rooms in File after adding if Auto Save is Enabled-----
        if(autoSaveEnabled){
            this.saveRooms();
        }
        return done;
    }
    //*****************************************************************************//
    
    //**********RETURN TRUE IF CLASS IS REMOVED**********//
    public boolean removeRoom(String name){
        //-----Removing Room by Name-----
        boolean done = Room.removeRoom(name,rooms);
        //-----Save Rooms in File after removing if Auto Save is Enabled-----
        if(autoSaveEnabled){
            this.saveRooms();
        }
        return done;
    }
    //*****************************************************************************//
}