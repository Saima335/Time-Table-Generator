
package timetableproject.roomHierarchy;

//**********IMPORTS**********//
import java.io.Serializable;
import java.sql.Time;

//=====================
//|   CREATING ROOM   |
//===================== 
public class Room implements Serializable {
    
    //********************************************DATA MEMBERS********************************************//
    private String name;
    public static int roomCount = 0;
    private int capacity;
    private Time startTime;
    private Time endTime;
    private RoomSlot[] roomSlots = new RoomSlot[100];
    private int slotCount = 0;

    //********************************************CONSTRUCTORS********************************************//
    //--------DEFAULT CONSTRUCTOR--------//
    public Room() {
        roomCount++;
    }

    //--------ARGUMENT CONSTRUCTORS--------//
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        roomCount++;
    }

    public Room(String name, int capacity, Time startTime, Time endTime) {
        this.name = name;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        roomCount++;
    }

    public Room(String name, int capacity, Time startTime, Time endTime, RoomSlot[] roomSlots, int slotCount) {
        this.name = name;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomSlots = roomSlots;
        this.slotCount = slotCount;
        roomCount++;
    }
    //*****************************************************************************//
    
    //******************************SETTERS AND GETTERS******************************//
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

    public RoomSlot[] getSlots() {
        return roomSlots;
    }

    public void setSlots(RoomSlot[] roomSlots) {
        this.roomSlots = roomSlots;
    }

    public int getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    public boolean hasSlots(){
        if(slotCount>0){
            return true;
        }
        return false;
    }
    //*****************************************************************************//

    //*******Return true if room is added and false otherwise*******//
    public static boolean addRoom(String name,int capacity,float startTime,float endTime,Room[] arr){
         //-----If all rooms are filled-----
        if(arr.length==Room.roomCount){
            return false;
        }
        boolean valid = true;
        //-----Validating length of room name-----
        if(name.length()>20||name.length()<2){
            valid = false;
        }
        //-----Check the uniqueness of room name-----
        for(int i = 0;i<roomCount;i++){
            if(arr[i].getName().equals(name)){
                valid = false;
                break;
            }
        }
        //-----Add data to room if name is valid-----
        if (valid){
            int startHour = (int) Math.floor(startTime);
            int endHour = (int) Math.floor(endTime);
            int startMin = (int) (startTime - startHour)*60;
            int endMin = (int) (endTime - endHour)*60;
            arr[Room.roomCount] = new Room(name,capacity,new Time(startHour,startMin,0),new Time(endHour,endMin,0));
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//
    
    //*******Return true if room is removed successfully*******//
    public static boolean removeRoom(String name,Room[] arr){
        boolean removed = false;
        //-----If only one room is present-----
        if(roomCount == 1){
            arr[0] = null;
            roomCount--;
            return true;
        }
        //-----Removing Room by Name-----
        for(int i = 0;i<roomCount;i++){
            //If room is not removed yet and room name is not found
            if(!removed && arr[i].getName().equals(name)){
                removed = true;
            }
            //Remove the room by shifting left
            else if(removed){
                arr[i-1] = arr[i];
            }
        }
        //-----Decrement rooms if room is removed-----
        if(removed){
            roomCount--;
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Return true if room is edited successfully*******//
    public boolean editRoom(String newName,int newCapacity,Room[] arr){
        boolean valid = true;
        //-----Validate length of room name-----
        if(newName.length()>20||newName.length()<2){
            valid = false;
        }
        if (!(newName.equals(this.getName()))){
            //-----Checking Uniqueness of Room Name -----
            for(int i = 0;i<roomCount;i++){
                if(arr[i].getName().equals(newName)){
                    valid = false;
                    break;
                }
            }
        }
        //-----If Room Name is Valid Edit Room Name and Capacity-----
        if(valid){
            this.setName(newName);
            this.setCapacity(newCapacity);
            return true;
        }
        else{
            return false;
        }
    }
    //*****************************************************************************//

    //*******Increasing Slots for Room*******//
    public void extendSlots(){
        RoomSlot[] temp = new RoomSlot[slotCount];
        for(int i =0 ;i<slotCount;i++){
            temp[i] = roomSlots[i];
        }
        roomSlots = new RoomSlot[slotCount*2];
        for(int i =0 ;i<slotCount;i++){
            roomSlots[i] = temp[i];
        }
    }
    //*****************************************************************************//

    //*******Making Slots for Room*******// 
    public void makeSlots(float slotTime,int studyDays,int breakMins){
        //-----Making Room Slots for All Study Days-----
        for(int i = 1;i<=studyDays;i++){
            //-----Initialize Time with Start Time in Float-----
            float time = this.startTime.getHours()+((float)this.startTime.getMinutes()/60.0f);
            //-----Get End Time in Float-----
            float endTime = this.endTime.getHours()+((float)this.endTime.getMinutes()/60.0f);
            //-----Make Slots till end time has reached-----
            while(time+slotTime<=endTime){
                //-----Extend Slots if Room Slots Array is filled-----
                if(slotCount==roomSlots.length){
                    extendSlots();
                }
                //-----Get Start Hour and Minute in Integer-----
                int startHour = (int) Math.floor(time);
                int startMin = (int) ((time-startHour)*60.0f);
                //-----Add Slot Time to Time to get One Slot End Time in Float-----
                time = time + slotTime;
                //-----Get End Hour and Minute in Integer-----
                int endHour = (int) Math.floor(time);
                int endMin = (int) (time-endHour)*60;
                //-----Add Slot to Room Slot Array-----
                this.roomSlots[this.slotCount] = new RoomSlot(this.name,this.capacity,i,new Time(startHour,startMin,0),new Time(endHour,endMin,0));
                //Increment Time by Adding Break Minutes in it
                time = time + ((float)breakMins/60.0f);
                slotCount++;
            }
        }
    }
    //*****************************************************************************//

    //*******Resetting Room Slots*******//
    public void resetSlots(){
        roomSlots = new RoomSlot[100];
        slotCount = 0;
    }

}

