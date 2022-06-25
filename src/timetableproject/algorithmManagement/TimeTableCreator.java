
package timetableproject.algorithmManagement;

//**********IMPORTS**********//
import java.sql.Time;
import timetableproject.classHierarchy.ClassSlot;
import timetableproject.classHierarchy.Class;
import timetableproject.dataManagement.TimeTableSpace;
import timetableproject.roomHierarchy.Room;
import timetableproject.roomHierarchy.RoomSlot;
import timetableproject.runner.GuiMain;

//==========================
//|   CREATING TIME TABLE  |
//==========================
public class TimeTableCreator {
    
    //********************************************DATA MEMBERS********************************************//
    RoomSlot[] roomSlots = new RoomSlot[100];
    RoomSlot[] remainingRoomSlots = new RoomSlot[100];
    ClassSlot[] classSlots = new ClassSlot[100];
    ClassSlot[] remainingClassSlots = new ClassSlot[100];
    int roomSlotCount;
    int classSlotCount;
    int roomCount;
    int dailyClassesPerRoom;

    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTOR--------//
    public TimeTableCreator(TimeTableSpace space, GuiMain gui) {
        //-----Getting number of Rooms-----
        roomCount = Room.roomCount;
        //-----Get start, end, duration entered in text fields in float-----
        float start = Integer.parseInt(gui.startTimeHourField.getText())+(Integer.parseInt(gui.startTimeMinField.getText())/60.0f);
        float end = Integer.parseInt(gui.endTimeHourField.getText())+(Integer.parseInt(gui.endTimeMinField.getText())/60.0f);
        float duration = Integer.parseInt(gui.durationHourField.getText())+(Integer.parseInt(gui.durationMinField.getText())/60.0f);
        //-----Get Number of Classes in Room-----
        dailyClassesPerRoom = (int)((end - start)/duration);
        //-----Room and Class Slots are Initialized 0-----
        roomSlotCount = 0;
        classSlotCount = 0;
        //-----Read all Classes from file-----
        Class[] classes = space.getClasses();
        //-----Loop till Number of classes-----
        for(int i = 0;i<Class.classCount;i++){
            //-----Make Slots of Classes-----
            classes[i].makeSlots();
            //-----Assign all class slots to class slots and remaining class slots at start-----
            for(int j = 0;j<classes[i].getClassSlotCount();j++){
                //-----Increasing Class Slot Count array length if filled-----
                if(classSlotCount==classSlots.length){
                    ClassSlot[] temp = new ClassSlot[classSlotCount];
                    for(int k = 0;k<classSlotCount;k++){
                        temp[k] = classSlots[k];
                    }
                    classSlots = new ClassSlot[classSlotCount*2];
                    remainingClassSlots = new ClassSlot[classSlotCount*2];
                    for(int k = 0;k<classSlotCount;k++){
                        classSlots[k] = temp[k];
                        remainingClassSlots[k] = temp[k];
                    }
                }
                classSlots[classSlotCount] = classes[i].getClassSlots()[j];
                remainingClassSlots[classSlotCount] = classes[i].getClassSlots()[j];
                classSlotCount++;
            }
        }
        //-----Read all Rooms from file-----
        Room[] rooms = space.getRooms();
        //-----Geeting time fo reach slot by reading text field of duration-----
        float slotTime = Integer.parseInt(gui.durationHourField.getText())+(Integer.parseInt(gui.durationMinField.getText())/60.0f);
        //-----Loop till Number of Rooms-----
        for(int i = 0;i<Room.roomCount;i++){
            //-----Get start and end time entered in text fields and make slots using duration, day text fields-----
            rooms[i].setStartTime(new Time(Integer.parseInt(gui.startTimeHourField.getText()),Integer.parseInt(gui.startTimeMinField.getText()),0));
            rooms[i].setEndTime(new Time(Integer.parseInt(gui.endTimeHourField.getText()),Integer.parseInt(gui.endTimeMinField.getText()),0));
            rooms[i].makeSlots(slotTime,Integer.parseInt(gui.daysField.getText()),0);
            //-----Assign all room slots to room slots and remaining room slots at start-----
            for(int j = 0;j<rooms[i].getSlotCount();j++){
                //-----Increasing Room Slot Count array length if filled-----
                if(roomSlots.length==roomSlotCount){
                    RoomSlot[] temp = new RoomSlot[roomSlotCount];
                    for(int k = 0;k<roomSlotCount;k++){
                        temp[k] = roomSlots[k];
                    }
                    roomSlots = new RoomSlot[roomSlotCount*2];
                    remainingRoomSlots = new RoomSlot[roomSlotCount*2];
                    for(int k = 0;k<roomSlotCount;k++){
                        roomSlots[k] = temp[k];
                        remainingRoomSlots[k] = temp[k];
                    }
                }
                roomSlots[roomSlotCount] = rooms[i].getSlots()[j];
                remainingRoomSlots[roomSlotCount] = rooms[i].getSlots()[j];
                roomSlotCount++;
            }
        }
    }
    //*****************************************************************************//
    
    //******************************SETTERS AND GETTERS******************************//
    public RoomSlot[] getRemainingRoomSlots() {
        return remainingRoomSlots;
    }

    public void setRemainingRoomSlots(RoomSlot[] remainingRoomSlots) {
        this.remainingRoomSlots = remainingRoomSlots;
    }

    public ClassSlot[] getRemainingClassSlots() {
        return remainingClassSlots;
    }

    public void setRemainingClassSlots(ClassSlot[] remainingClassSlots) {
        this.remainingClassSlots = remainingClassSlots;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getDailyClassesPerRoom() {
        return dailyClassesPerRoom;
    }

    public void setDailyClassesPerRoom(int dailyClassesPerRoom) {
        this.dailyClassesPerRoom = dailyClassesPerRoom;
    }

    public RoomSlot[] getRoomSlots() {
        return roomSlots;
    }

    public void setRoomSlots(RoomSlot[] roomSlots) {
        this.roomSlots = roomSlots;
    }

    public ClassSlot[] getClassSlots() {
        return classSlots;
    }

    public void setClassSlots(ClassSlot[] classSlots) {
        this.classSlots = classSlots;
    }

    public int getRoomSlotCount() {
        return roomSlotCount;
    }

    public void setRoomSlotCount(int roomSlotCount) {
        this.roomSlotCount = roomSlotCount;
    }

    public int getClassSlotCount() {
        return classSlotCount;
    }

    public void setClassSlotCount(int classSlotCount) {
        this.classSlotCount = classSlotCount;
    }
    //*****************************************************************************//
    
    //*******MAKING TIME TABLE*******//
    public void makeTimeTable(){
        //-----Variable to determine unchecked slots of classes-----
        int uncheckedClassSlots = this.classSlotCount;
        //-----Loop Till All Slots of Classes are Filled-----
        while (uncheckedClassSlots>0){
            //-----Finding lagrest strength and it's index from all classes-----
            int largestClassStrength = -1;
            int largestClassIndex = -1;
            //-----Loop Till Number of Slots of Classes-----
            for(int i = 0;i<classSlotCount;i++){
                //-----If Class Slot is present and class strength is greater than previous strength and space for that slot is present in room (slot is used) then update largest strength-----
                if(remainingClassSlots[i]!=null&&remainingClassSlots[i].getsClass().getStrength()>largestClassStrength&&!remainingClassSlots[i].isUsed()){
                    largestClassStrength = remainingClassSlots[i].getsClass().getStrength();
                    largestClassIndex = i;
                }
            }
            //-----Finding room capacity which is nearest to largest strength so not to waste room space-----
            int smallestCompatibleRoomCapacity = Integer.MAX_VALUE;
            int smallestCompatibleRoomIndex = Integer.MAX_VALUE;
            //-----If Class Strngth is not -1-----
            if(largestClassStrength != -1){
                //-----Loop Till Number of Slots of Rooms-----
                for(int i = 0;i<roomSlotCount;i++){
                    //-----If Room Slot is present and Room Capacity is greater than Class strength and room capacity is more nearer to previous one-----
                    if(remainingRoomSlots[i]!=null&&remainingRoomSlots[i].getCapacity()>=largestClassStrength&&remainingRoomSlots[i].getCapacity()<smallestCompatibleRoomCapacity){
                        boolean repetition = false;
                        Time timeCompare = remainingRoomSlots[i].getStartTime();
                        int dayCompare = remainingRoomSlots[i].getDay();
                        //-----On same time and day a teacher and class can't have two classes-----
                        for(int j = 0;j<roomSlotCount;j++){
                            if(roomSlots[j].getDay() == dayCompare&&roomSlots[j].getStartTime().equals(timeCompare)){
                                if(roomSlots[j].getsClass()!=null&&(roomSlots[j].getsClass().getName().equals(classSlots[largestClassIndex].getsClass().getName())||roomSlots[j].getCourse().getTeacher().equals(classSlots[largestClassIndex].getCourse().getTeacher()))){
                                    repetition = true;
                                    break;
                                }
                            }
                        }
                        //-----If no repetition in time and day for teacher and class assign the capacity of room to be compatible for class-----
                        if(!repetition){
                            smallestCompatibleRoomCapacity = remainingRoomSlots[i].getCapacity();
                            smallestCompatibleRoomIndex = i;
                        }
                    }
                }
            }
            //-----If class has no strength then break-----
            else{
                break;
            }
            //-----If a room with compatible space is found assign class and course to it and decrement un checked class slots-----
            if(smallestCompatibleRoomCapacity!=Integer.MAX_VALUE){
                roomSlots[smallestCompatibleRoomIndex].addClass(remainingClassSlots[largestClassIndex].getsClass(),remainingClassSlots[largestClassIndex].getCourse());
                remainingRoomSlots[smallestCompatibleRoomIndex] = null;
                remainingClassSlots[largestClassIndex] = null;
                uncheckedClassSlots--;
            }
            //-----If no room with compatible space is available then that slot is unused-----
            else{
                remainingClassSlots[largestClassIndex].setUsed(true);
            }
        }
    }
    //*****************************************************************************// 
}
