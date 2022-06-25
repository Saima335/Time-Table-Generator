
package timetableproject.runner;

//**********IMPORTS**********//
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import timetableproject.algorithmManagement.TimeTableCreator;
import timetableproject.dataManagement.TimeTableSpace;

//===========================================
//|   CREATING GUI FOR SHOWING TIME TABLE   |
//===========================================
public class GuiShowTimeTable extends JFrame {
    
    //********************************************DATA MEMBERS********************************************//
    JScrollPane timeTableScrollPane;
    JTable timeTableToShow;
    JPanel topPanel;
    ButtonGroup buttonGroup;
    JRadioButton day1;
    JRadioButton day2;
    JRadioButton day3;
    JRadioButton day4;
    JRadioButton day5;
    JRadioButton day6;
    JRadioButton day7;
    JButton back;
    JPanel belowPanel;
    JLabel remainingClasses;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTOR--------//
    public GuiShowTimeTable(TimeTableCreator timeTableCreator, TimeTableSpace space, GuiMain gui) throws HeadlessException {
        //-----Set Size Of Frame-----
        setSize(800,600);
        //-----Frame Open in Maximized State-----
        setExtendedState(MAXIMIZED_BOTH);
        //-----Set Main Layout Of Frame As Border Layout-----
        BorderLayout layout = new BorderLayout();
        //-----Set Horizontal Gap Between Components-----
        layout.setHgap(10);
        //-----Set Vertical Gap Between Components-----
        layout.setVgap(10);
        setLayout(layout);
        //-----Remove the frame's title bar-----
        setUndecorated(true);

        //-----Create Table to Show Time Table-----
        timeTableToShow = new JTable();
        //-----Provide Scrollable view of Time Table-----
        timeTableScrollPane = new JScrollPane(timeTableToShow);
        //-----Create Back Button to return to previous Frame-----
        back = new JButton("Back");
        //-----Create Top Panel To Add Days Button Group and set it's layout to 1 row and columns equal to no of days-----
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,Integer.parseInt(gui.daysField.getText())));
        //-----Add Days to Button Group-----
        buttonGroup = new ButtonGroup();
        day1 = new JRadioButton("Monday");
        day2 = new JRadioButton("Tuesday");
        day3 = new JRadioButton("Wednesday");
        day4 = new JRadioButton("Thursday");
        day5 = new JRadioButton("Friday");
        day6 = new JRadioButton("Saturday");
        day7 = new JRadioButton("Sunday");
        buttonGroup.add(day1);
        buttonGroup.add(day2);
        buttonGroup.add(day3);
        buttonGroup.add(day4);
        buttonGroup.add(day5);
        buttonGroup.add(day6);
        buttonGroup.add(day7);
        //-----Create Below Panel to print unallocated classes of grid layout with one row and one column-----
        belowPanel = new JPanel();
        belowPanel.setLayout(new GridLayout(1,1));
        //-----Get Number Of unallocated Classes from Class Slots That are not allocated in Time table-----
        int unallocatedClasses = 0;
        for(int i = 0;i<timeTableCreator.getClassSlotCount();i++){
            if(timeTableCreator.getRemainingClassSlots()[i]!=null){
                unallocatedClasses++;
            }
        }
        //-----Create label to put Unallocated Classes-----
        remainingClasses = new JLabel("Unallocated Classes: "+unallocatedClasses);
        //-----If there are some unallocated Classes avaiable set below panel colour as red-----
        if(unallocatedClasses>0){
            belowPanel.setBackground(Color.red);
        }
        //-----If all Classes are allocated set below panel colour as green
        else{
            belowPanel.setBackground(Color.green);
        }
        //-----Start From Monday-----
        day1.setSelected(true);
        //-----Createe time table of Monday-----
        changeTimeTableDay(1,timeTableCreator,space);

        //*******When Back Button is Clicked go Back to Previous GUI Frame (Main Frame)******* 
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMain();
                dispose();
            }
        });
        //*****************************************************************************//
        
        //*******Changing Time Table According to the day Clicked******* 
        ActionListener daysListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton)e.getSource();
                if(source == day1){
                    changeTimeTableDay(1,timeTableCreator,space);
                }
                else if(source == day2){
                    changeTimeTableDay(2,timeTableCreator,space);
                }
                else if(source == day3){
                    changeTimeTableDay(3,timeTableCreator,space);
                }
                else if(source == day4){
                    changeTimeTableDay(4,timeTableCreator,space);
                }
                else if(source == day5){
                    changeTimeTableDay(5,timeTableCreator,space);
                }
                else if(source == day6){
                    changeTimeTableDay(6,timeTableCreator,space);
                }
                else{
                    changeTimeTableDay(7,timeTableCreator,space);
                }
            }
        };
        //*****************************************************************************//
        
        //-----Linking Butttons to Action Listener-----
        day1.addActionListener(daysListener);
        day2.addActionListener(daysListener);
        day3.addActionListener(daysListener);
        day4.addActionListener(daysListener);
        day5.addActionListener(daysListener);
        day6.addActionListener(daysListener);
        day7.addActionListener(daysListener);

        //-----Get Number of Days From Day Field-----
        int days = Integer.parseInt(gui.daysField.getText());
        //-----Add Time Table in the Center of Frame-----
        add(timeTableScrollPane,BorderLayout.CENTER);
        //-----Add Back Button To the West Of Frame-----
        add(back,BorderLayout.WEST);
        //-----Add Top Panel Containing Days Buttons to North of Frame 
        add(topPanel,BorderLayout.NORTH);
        //-----Add Buttons Depending On Number of Days-----
        if(days>=1){
            topPanel.add(day1);
        }
        if(days>=2){
            topPanel.add(day2);
        }
        if(days>=3){
            topPanel.add(day3);
        }
        if(days>=4){
            topPanel.add(day4);
        }
        if(days>=5){
            topPanel.add(day5);
        }
        if(days>=6){
            topPanel.add(day6);
        }
        if(days>=7){
            topPanel.add(day7);
        }
        //-----Add Below panel displaying Unallocated Classes To the South Of Plane
        add(belowPanel,BorderLayout.SOUTH);
        //-----Add Reamining Classes to below plane
        belowPanel.add(remainingClasses);

        //-----Set Frame To Visible-----
        setVisible(true);
    }
    //*****************************************************************************//
    
    //*******Changing Time Table For Each Day******* 
    public void changeTimeTableDay(int day,TimeTableCreator timeTableCreator,TimeTableSpace space){
        //-----Columns of Time Table Will Represent Rooms-----
        String[] columns = new String[timeTableCreator.getRoomCount()+1];
        //-----Add Time in first Column-----
        columns[0] = "Time";
        //-----Add Rooms Name to Columns-----
        for(int i = 1;i<timeTableCreator.getRoomCount()+1;i++){
            columns[i] = space.getRooms()[i-1].getName();
        }
        //-----Add data to Time Table-----
        String[][] data = new String[timeTableCreator.getDailyClassesPerRoom()][timeTableCreator.getRoomCount()+1];
        //-----Get Start Time for Classes-----
        for(int i = 0;i<timeTableCreator.getDailyClassesPerRoom();i++){
            data[i][0] = timeTableCreator.getRoomSlots()[i].getStartTime()+"";
        }
        //-----Alloat Classes to all Slots Available in Room-----
        for(int i = 0;i<timeTableCreator.getRoomSlotCount();i++){
            //-----If day matches the day of room slot-----
            if(timeTableCreator.getRoomSlots()[i].getDay()==day){
                //-----Set row where data to be added at -1-----
                int horizontal = -1;
                //-----Set row where Room name is equal to any of the room name present in record-----
                for(int j = 0;j<timeTableCreator.getRoomCount()+1;j++){
                    if(timeTableCreator.getRoomSlots()[i].getName().equals(columns[j])){
                        horizontal = j;
                    }
                }
                //-----Set column where data to be added at -1-----
                int vertical = -1;
                //-----Set coloumn where start time matches with start time of room slot present in record-----
                for(int j = 0;j<timeTableCreator.getDailyClassesPerRoom();j++){
                    if(data[j][0].equals(timeTableCreator.getRoomSlots()[i].getStartTime().toString())){
                        vertical = j;
                    }
                }
                //-----If specific row, column is not get start the loop again to find correct place for data entry-----
                if(vertical==-1||horizontal==-1){
                    continue;
                }
                //-----If Class and Room is present add data at specific location in table-----
                if(timeTableCreator.getRoomSlots()[i].getClass()!=null && timeTableCreator.getRoomSlots()[i].getCourse()!=null){
                    data[vertical][horizontal] = "Class: "+timeTableCreator.getRoomSlots()[i].getsClass().getName()+" , Course: "+timeTableCreator.getRoomSlots()[i].getCourse().getName()+" , Teacher: "+timeTableCreator.getRoomSlots()[i].getCourse().getTeacher();
                }
                //-----If Class or room is not present leave that cell as empty-----
                else{
                    data[vertical][horizontal] = "";
                }
            }
        }
        //-----Set Time Table Model to not editable-----
        DefaultTableModel tableModel = new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        timeTableToShow.setModel(tableModel);
    }
    //*****************************************************************************//
}
