
package timetableproject.runner;

//**********IMPORTS**********//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import timetableproject.algorithmManagement.TimeTableCreator;
import timetableproject.roomHierarchy.Room;
import timetableproject.classHierarchy.Class;
import timetableproject.dataManagement.TimeTableSpace;

//===================================
//|   CREATING MAIN FRAME FOR GUI   |
//===================================
public class GuiMain extends JFrame {
    
    //********************************************DATA MEMBERS********************************************//
    public TimeTableSpace space = new TimeTableSpace("sp1");
    JButton exit;
    JPanel emptyPanel;
    JPanel classRoomPanel;
    JPanel classPanel; 
    JPanel roomPanel;
    JPanel timeTablePanel;
    JPanel timingDetailsPanel;
    JPanel addClassPanel;
    JPanel showClassPanel;
    JPanel addRoomPanel;
    JPanel showRoomPanel;
    JPanel classFieldPanel;
    JPanel classButtonPanel;
    JPanel roomFieldPanel;
    JPanel roomButtonPanel;
    JButton addClass;
    JTextField classNameField;
    JLabel classNameLabel;
    JTextField classStrengthField;
    JLabel classStrengthLabel;
    JButton addRoom;
    JTextField roomNameField;
    JLabel roomNameLabel;
    JTextField roomCapacityField;
    JLabel roomCapacityLabel;
    JScrollPane showClassScrollPane;
    JTable showClassTable;
    JScrollPane showRoomScrollPane;
    JTable showRoomTable;
    JLabel timingHourLabel;
    JLabel timingMinLabel;
    JLabel classDurationLabel;
    JLabel startTimeLabel;
    JLabel endTimeLabel;
    JLabel daysLabel;
    public JTextField durationHourField;
    public JTextField durationMinField;
    public JTextField startTimeHourField;
    public JTextField startTimeMinField;
    public JTextField endTimeHourField;
    public JTextField endTimeMinField;
    public JTextField daysField;
    JButton checkTimeValidityButton;
    JButton makeTimeTable;
    JLabel makeTimeTableNote;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------DEFAULT CONSTRUCTOR--------//
    public GuiMain(){ 
        //-----Read Room And Class Data From File-----
        space.loadSpace();
        //-----Set Main Layout Of Frame As Border Layout-----
        BorderLayout mainLayout = new BorderLayout();
        //-----Set Horizontal Gap Between Components-----
        mainLayout.setHgap(10);
        //-----Removes the Frame Title bar-----
        setUndecorated(true);
        //-----Set Vertical Gap Between Components-----
        mainLayout.setVgap(10);
        setLayout(mainLayout);
        //-----Set Size Of Frame-----
        setSize(800,600);
        //-----Frame Open in Maximized State-----
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //-----Exit Frame On Close-----
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Definition

        //-----Make a Button Exit to exit from frame-----
        exit = new JButton("Exit");
        //-----Set Colour of Exit Button-----
        exit.setBackground(new Color(255,128,0));
        //------Create a Panel Named Empty Panel         and Set it's Background Colour as Gray-----
        emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.gray);
        //----Create Room Panel to Add and View Room with grid layout of 1 row and 2 columns having 2 panels (1 for adding room and other for viewing room table)-----
        classRoomPanel = new JPanel();
        classRoomPanel.setLayout(new GridLayout(1,2));
        //----Create Class Panel to Add and View Class with grid layout of 1 row and 2 columns having 2 panels (1 for adding class and other for viewing Class table)-----
        classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(1,2));
            //-----Create Class Panel to Add Class Data and buttons, and Set it's layout as Border Layout-----
            addClassPanel = new JPanel();
            addClassPanel.setLayout(new BorderLayout());
                //-----Create Class Field Panel to Add Class Data and Set it's layout as Grid Layout with 2 rows and 2 columns (For 2 text fiels, lables)-----
                classFieldPanel = new JPanel();
                classFieldPanel.setLayout(new GridLayout(2,2));
                    //-----Create Label and Fields for Class Name and Strength-----
                    classNameLabel = new JLabel("Class Name");
                    classNameField = new JTextField(10);
                    classStrengthLabel = new JLabel("Class Strength");
                    classStrengthField = new JTextField(3);
                //-----Create Class Button Panel to Add Add Class Button to Panel-----
                classButtonPanel = new JPanel();
                    //-----Create Add Class Button-----
                    addClass = new JButton("Add Class");
            //-----Create Show Class Panel to Show Class Data in Tabular Form with Grid Layout 1 row and 1 column-----
            showClassPanel = new JPanel();
            showClassPanel.setLayout(new GridLayout(1,1));
                    //********Creating Table For Class********
                    //-----Creating Columns For Class Table-----
                    String[] columnsClass = {"Name","Strength"};
                    String[][] dataClass = new String[Class.classCount][2];
                    //-----Adding Class Data in Table-----
                    for(int i = 0;i<Class.classCount;i++){
                        dataClass[i][0] = space.getClasses()[i].getName();
                        dataClass[i][1] = space.getClasses()[i].getStrength()+"";
                    }
                    //-----Creating Show Class Table and Class Scroll Pane to Provide Scrollable view of Class table-----
                    showClassTable = new JTable(dataClass,columnsClass);
                    showClassScrollPane = new JScrollPane(showClassTable);
        //----Create Room Panel to Add and View Rooms with grid layout of 1 row and 2 columns having 2 panels (1 for adding Room and other for viewing Room table)-----
        roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(1,2));
            //-----Create Room Panel to Add Room Data and buttons, and Set it's layout as Border Layout-----
            addRoomPanel = new JPanel();
            addRoomPanel.setLayout(new BorderLayout());
                //-----Create Room Field Panel to Add Room Data and Set it's layout as Grid Layout with 2 rows and 2 columns (For 2 text fiels, lables)-----
                roomFieldPanel = new JPanel();
                roomFieldPanel.setLayout(new GridLayout(2,2));
                    //-----Create Label and Fields for Room Name and Capacity-----
                    roomNameLabel = new JLabel("Room Name");
                    roomNameField = new JTextField(10);
                    roomCapacityLabel = new JLabel("Room Capacity");
                    roomCapacityField = new JTextField(3);
                //-----Create Room Button Panel to Add Add Room Button to Panel-----
                roomButtonPanel = new JPanel();
                    //-----Create Add Room Button-----
                    addRoom = new JButton("Add Room");
            //-----Create Show Room Panel to Show Room Data in Tabular Form with Grid Layout 1 row and 1 column-----
            showRoomPanel = new JPanel();
            showRoomPanel.setLayout(new GridLayout(1,1));
                //********Creating Table For Room********
                //-----Creating Columns For Room Table-----
                String[] columnsRoom = {"Name","Capacity"};
                String[][] dataRoom = new String[Room.roomCount][2];
                //-----Adding Room Data in Table-----
                for(int i = 0;i< Room.roomCount;i++){
                    dataRoom[i][0] = space.getRooms()[i].getName();
                    dataRoom[i][1] = space.getRooms()[i].getCapacity()+"";
                }
                //-----Creating Show Room Table and Class Scroll Pane to Provide Scrollable view of Room table-----
                showRoomTable = new JTable(dataRoom,columnsRoom);
                showRoomScrollPane = new JScrollPane(showRoomTable);
        //-----Creating timing panel to show details Time, set it's background as gray and layout as grid with 5 rows and 3 columns for adding time data and buttons-----
        timingDetailsPanel = new JPanel();
        timingDetailsPanel.setBackground(Color.gray);
        timingDetailsPanel.setLayout(new GridLayout(5,3));
            //-----Create hour, minute, class duration, Start time, end time, working days labels-----
            timingHourLabel = new JLabel("Hour");
            timingMinLabel = new JLabel("Minute");
            classDurationLabel = new JLabel("Class Duration");
            startTimeLabel = new JLabel("Start Time");
            endTimeLabel = new JLabel("End Time");
            daysLabel = new JLabel("Working Days");
            //-----Create duration hour, minute; start time hour, minute; end time hour, minute; working days text fields-----
            durationHourField = new JTextField(2);
            durationMinField = new JTextField(2);
            startTimeHourField = new JTextField(2);
            startTimeMinField = new JTextField(2);
            endTimeHourField = new JTextField(2);
            endTimeMinField = new JTextField(2);
            daysField = new JTextField(1);
            //-----Checking validity of Time By creating Button Invalid and set it's colour as red-----
            checkTimeValidityButton = new JButton("Invalid");
            checkTimeValidityButton.setBackground(Color.red);
        //-----Create Time Table Panel With grid layout of 2 rows and 1 column for Button Make TimeTable and label for displaying errors-----
        timeTablePanel = new JPanel();
        timeTablePanel.setLayout(new GridLayout(2,1));
            makeTimeTable = new JButton("Make TimeTable");
            makeTimeTableNote = new JLabel("");
        GuiMain guiMain = this;

        //*******Exit from GUI Frame When Exit Button is Clicked*******
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Exit")){
                    System.exit(0);
                }
            }
        });
        //*****************************************************************************//
        
        //*******Changing Exit button Colour on Entering and Leaving of Mouse*******
        exit.addMouseListener(new MouseAdapter() {
            @Override
            //-----Changing Exit button Colour to yellow on Entering of Mouse----- 
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(new Color(255,80,0));
            }
            //-----Changing Exit button Colour to orange on Exiting of Mouse----- 
            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(new Color(255,128,0));
            }
        });
        //*****************************************************************************//
        
        //-----Check Class Name and Strength Validity-----
        classNameField.addKeyListener(new invalidInputCheckListenerAlpha(10));
        classStrengthField.addKeyListener(new invalidInputCheckListenerNumeric(3));
        
        //*******Add Class record on clicking add Class button*******
        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-----If Strength of Class is Present
                if(!classStrengthField.getText().equals("")){
                    //-----Add and Save Class data-----
                    if (space.addClass(classNameField.getText(),Integer.parseInt(classStrengthField.getText()))==true){
                        space.saveClasses();
                        //-----Update Class Table-----
                        updateTable();
                        //-----Reset Class name, and Strength to empty to add another class-----
                        classNameField.setText("");
                        classNameField.setEditable(true);
                        classStrengthField.setText("");
                        classStrengthField.setEditable(true);
                    }
                    /*else{
                        JOptionPane.showMessageDialog(null,"Name Not Unique");
                    }*/
                }
            }
        });
        //*****************************************************************************//
        
        //*******Checking Uniqueness of Name*******
        classNameField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                boolean valid=true;
                JTextField source = (JTextField)e.getSource();
                Class[] classes =space.getClasses() ;
                for(int i = 0;i<Class.classCount;i++){
                    if(classes[i].getName().equals(source.getText())){
                        valid = false;
                        break;
                    }
                }
                if (!valid){
                    source.setText("");
                    source.setEditable(true);
                }
            }
        });
        
        //-----Check Room Name and Capacity Validity-----
        roomNameField.addKeyListener(new invalidInputCheckListenerAlpha(10));
        roomCapacityField.addKeyListener(new invalidInputCheckListenerNumeric(3));
        
        //*******Add Room record on clicking add Room button*******
        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //-----If Capacity of Class is Present
                if(!roomCapacityField.getText().equals("")){
                    //-----Add and Save Room data-----
                    if (space.addRoom(roomNameField.getText(),Integer.parseInt(roomCapacityField.getText()),8.0f,16.0f)){
                        space.saveRooms();
                        //-----Update Room Table-----
                        updateTable();
                        //-----Reset Room name, and Capacity to empty to add another room-----
                        roomNameField.setText("");
                        roomNameField.setEditable(true);
                        roomCapacityField.setText("");
                        roomCapacityField.setEditable(true);
                    }
                    /*else{
                        JOptionPane.showMessageDialog(null,"Name Not Unique");
                    }*/
                }
            }
        });
        //*****************************************************************************//
        
         //*******Checking Uniqueness of Name*******
        roomNameField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                boolean valid=true;
                JTextField source = (JTextField)e.getSource();
                Room[] room =space.getRooms() ;
                for(int i = 0;i<Room.roomCount;i++){
                    if(room[i].getName().equals(source.getText())){
                        valid = false;
                        break;
                    }
                }
                if (!valid){
                    source.setText("");
                    source.setEditable(true);
                }
            }
        });
        //*****************************************************************************//

        //*******Show Class Data in Edit Class Form When Row of Class Table is double Clicked*******
        showClassTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //-----If Double Clicked-----
                if(e.getClickCount()==2){
                    //-----Get Table-----
                    JTable tempTable = (JTable) e.getSource();
                    //-----Get Row Of Table Which is Selected-----
                    int row = tempTable.getSelectedRow();
                    //-----When Name on Tale Row Equals Class Name present in File record the Open Class Edit Frame-----
                    for(int i = 0;i<Class.classCount;i++){
                        if(space.getClasses()[i].getName()==tempTable.getValueAt(row,0)){
                            new GuiClassEdit(i,space);
                            dispose();
                            break;
                        }
                    }
                }
            }
        });
        //*****************************************************************************//
        
        //*******Show Room Data in Edit Room Form When Row of Room Table is double Clicked*******
        showRoomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //-----If Double Clicked-----
                if(e.getClickCount()==2){
                    //-----Get Table-----
                    JTable tempTable = (JTable) e.getSource();
                    //-----Get Row Of Table Which is Selected-----
                    int row = tempTable.getSelectedRow();
                    //-----When Name on Tale Row Equals Room Name present in File record the Open Room Edit Frame-----
                    for(int i = 0;i<Room.roomCount;i++){
                        if(space.getRooms()[i].getName()==tempTable.getValueAt(row,0)){
                            new GuiRoomEdit(i,space);
                            dispose();
                            break;
                        }
                    }
                }
            }
        });
        //*****************************************************************************//
        
        //*******When Check Time Validity Button Is Clicked then Check Validity of Time Entered*******
        checkTimeValidityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-----If any Field is empty set its default value as 0-----
                if(startTimeMinField.getText().equals("")){
                    startTimeMinField.setText("0");
                }
                if(endTimeMinField.getText().equals("")){
                    endTimeMinField.setText("0");
                }
                if(startTimeHourField.getText().equals("")){
                    startTimeHourField.setText("0");
                }
                if(endTimeHourField.getText().equals("")){
                    endTimeHourField.setText("0");
                }
                if(durationMinField.getText().equals("")){
                    durationMinField.setText("0");
                }
                if(durationHourField.getText().equals("")){
                    durationHourField.setText("0");
                }
                if(daysField.getText().equals("")){
                    daysField.setText("5");
                }
                //-----Get Start, End, Duration in Float-----
                float startTime = Integer.parseInt(startTimeHourField.getText())+(Integer.parseInt(startTimeMinField.getText())/60.0f);
                float endTime = Integer.parseInt(endTimeHourField.getText())+(Integer.parseInt(endTimeMinField.getText())/60.0f);
                float duration = Integer.parseInt(durationHourField.getText())+(Integer.parseInt(durationMinField.getText())/60.0f);
                //-----When Time Valid Then Change Button Text to Valid and Colour to Green-----
                if(endTime-startTime>=duration&&endTime-startTime!=0){
                    checkTimeValidityButton.setText("Valid");
                    checkTimeValidityButton.setBackground(Color.green);
                }
                //-----If Time is not Valid then again Change Button Text to Invalud and Colour to Red-----
                else{
                    checkTimeValidityButton.setText("Invalid");
                    checkTimeValidityButton.setBackground(Color.red);
                }
            }
        });
        //*****************************************************************************//
        
        //*******Create Time Table when make time table button is clicked*******
        makeTimeTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                String toFix = "Fix: ";
                //-----If time is Invalid display Fix Timing details-----
                if(checkTimeValidityButton.getText().equals("Invalid")){
                    valid = false;
                    toFix = toFix+"Timing Details, ";
                }
                //-----Get Number Of Courses-----
                int totalCourseCount = 0;
                for(int i = 0;i<Class.classCount;i++){
                    for(int j = 0;j<space.getClasses()[i].getCourseCount();j++){
                        totalCourseCount++;
                    }
                }
                //-----If no course Present display fix classses-----
                if(totalCourseCount == 0){
                    valid = false;
                    toFix = toFix+"Classes, ";
                }
                //-----If no room present display fix rooms-----
                if(Room.roomCount == 0){
                    valid = false;
                    toFix = toFix+"Rooms, ";
                }
                //-----If Eveything is fixed create Time Tabel-----
                if(valid){
                    toFix = "";
                    makeTimeTableNote.setText(toFix);
                    TimeTableCreator timeTableCreator = new TimeTableCreator(space,guiMain);
                    timeTableCreator.makeTimeTable();
                    new GuiShowTimeTable(timeTableCreator,space,guiMain);
                    //-----Reset Room and Class Slots after Time table creation-----
                    for(int i = 0;i<Room.roomCount;i++){
                        space.getRooms()[i].resetSlots();
                    }
                    for(int i = 0;i<Class.classCount;i++){
                        space.getClasses()[i].resetSlots();
                    }
                    dispose();
                }
                //-----Display Fixing Message if anything needs to be fixed-----
                else{
                    toFix = toFix.substring(0,toFix.length()-2);
                    makeTimeTableNote.setText(toFix);
                }
            }
        });
        //*****************************************************************************//
        
        //-----Validate all Time fields-----
        durationHourField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        durationHourField.addKeyListener(new ExceedCheckListener(24));
        durationMinField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        durationMinField.addKeyListener(new ExceedCheckListener(59));
        startTimeHourField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        startTimeHourField.addKeyListener(new ExceedCheckListener(24));
        startTimeMinField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        startTimeMinField.addKeyListener(new ExceedCheckListener(59));
        endTimeHourField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        endTimeHourField.addKeyListener(new ExceedCheckListener(23));
        endTimeMinField.addKeyListener(new invalidInputCheckListenerNumeric(2));
        endTimeMinField.addKeyListener(new ExceedCheckListener(59));
        daysField.addKeyListener(new invalidInputCheckListenerNumeric(1));
        daysField.addKeyListener(new ExceedCheckListener(7));



        //Structure

        //-----Add Exit Button at North Of Frame-----
        add(exit,BorderLayout.NORTH);
        //-----Add Class Room Panel at Center of Frame-----
        add(classRoomPanel,BorderLayout.CENTER);
            //-----Add Class Panel in Class Room Panel-----
            classRoomPanel.add(classPanel);
                //-----Add Add Class Panel in Class Panel-----
                classPanel.add(addClassPanel);
                    //-----Add Class Field Panel in Add Class Panel Center-----
                    addClassPanel.add(classFieldPanel,BorderLayout.CENTER);
                        //-----Add Class Name, Strength fields and labels to Class Field Panel-----
                        classFieldPanel.add(classNameLabel);
                        classFieldPanel.add(classNameField);
                        classFieldPanel.add(classStrengthLabel);
                        classFieldPanel.add(classStrengthField);
                    //-----Add Class Button panel in Add Class Panel South-----
                    addClassPanel.add(classButtonPanel,BorderLayout.SOUTH);
                        //-----Add Add Class Button in Class Button panel-----
                        classButtonPanel.add(addClass);
                //-----Add Show Class Panel in Class Panel-----
                classPanel.add(showClassPanel);
                    //-----Add show Class Scroll Pane in show Class Panel-----
                    showClassPanel.add(showClassScrollPane);
            //-----Add Room Panel to Class Room Panel-----
            classRoomPanel.add(roomPanel);
                //-----Add Add Room Panel to Room Panel-----
                roomPanel.add(addRoomPanel);
                    //-----Add Room Field Panel to add Room Panel at center-----
                    addRoomPanel.add(roomFieldPanel,BorderLayout.CENTER);
                        //-----Add Room Name, Capacity fields and labels to Room Field Panel-----
                        roomFieldPanel.add(roomNameLabel);
                        roomFieldPanel.add(roomNameField);
                        roomFieldPanel.add(roomCapacityLabel);
                        roomFieldPanel.add(roomCapacityField);
                    //-----Add Room Button panel in Add Room Panel South-----
                    addRoomPanel.add(roomButtonPanel,BorderLayout.SOUTH);
                        //-----Add Add Room Button in Room Button panel-----
                        roomButtonPanel.add(addRoom);
                //-----Add Show Room Panel in Room Panel-----
                roomPanel.add(showRoomPanel);
                    //-----Add show Room Scroll Pane in show Room Panel-----
                    showRoomPanel.add(showRoomScrollPane);
        //-----Add Timing detail Panel at West of Frame
        add(timingDetailsPanel,BorderLayout.WEST);
            //-----Add empty Panel in timimg detail panel-----
            timingDetailsPanel.add(emptyPanel);
            //-----Add all timing fields and labels in timing panel-----
            timingDetailsPanel.add(timingHourLabel);
            timingDetailsPanel.add(timingMinLabel);
            timingDetailsPanel.add(classDurationLabel);
            timingDetailsPanel.add(durationHourField);
            timingDetailsPanel.add(durationMinField);
            timingDetailsPanel.add(startTimeLabel);
            timingDetailsPanel.add(startTimeHourField);
            timingDetailsPanel.add(startTimeMinField);
            timingDetailsPanel.add(endTimeLabel);
            timingDetailsPanel.add(endTimeHourField);
            timingDetailsPanel.add(endTimeMinField);
            timingDetailsPanel.add(daysLabel);
            timingDetailsPanel.add(daysField);
            //-----Add Validity Button in timing panel-----
            timingDetailsPanel.add(checkTimeValidityButton);
        //-----Add Time Tabel Panel At South Of Frame-----
        add(timeTablePanel,BorderLayout.SOUTH);
            //-----Add make time table button and make time tabel note label in time tabel panel-----
            timeTablePanel.add(makeTimeTable);
            timeTablePanel.add(makeTimeTableNote);
        //-----Update Class and Room Table-----
        updateTable();
        //-----Set Frame to Visible-----
        setVisible(true);
    }
    //*****************************************************************************//
    
    //*******Updating Table of Room and Class*******
    void updateTable(){
        //-----Add All Class Data to Tabel-----
        String[][] dataClass = new String[Class.classCount][2];
        for(int i = 0;i<Class.classCount;i++){
            dataClass[i][0] = space.getClasses()[i].getName();
            dataClass[i][1] = space.getClasses()[i].getStrength()+"";
        }
        //-----Columns For Class Table Made-----
        String[] columnsClass = {"Name","Strength"};
        //-----Set Class Table Model to not editable-----
        DefaultTableModel tableModelClass = new DefaultTableModel(dataClass,columnsClass) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        showClassTable.setModel(tableModelClass);
        //-----Add All Rooms Data to Tabel-----
        String[][] dataRoom = new String[Room.roomCount][2];
        for(int i = 0;i<Room.roomCount;i++){
            dataRoom[i][0] = space.getRooms()[i].getName();
            dataRoom[i][1] = space.getRooms()[i].getCapacity()+"";
        }
        //-----Columns For Room Table Made-----
        String[] columnsRoom = {"Name","Capacity"};
        //-----Set Room Table Model to not editable-----
        DefaultTableModel tableModelRoom = new DefaultTableModel(dataRoom,columnsRoom) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        showRoomTable.setModel(tableModelRoom);
    }
    //*****************************************************************************//
    
    //*******************************************Checking Validness*******************************************
    //*******Checking Number Range by using key released*******
    class ExceedCheckListener extends KeyAdapter{
        int max = 0;

        public ExceedCheckListener(int max) {
            this.max = max;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField source = (JTextField)e.getSource();
            if(!source.getText().equals("")&&Integer.parseInt(source.getText())>max){
                source.setText("");
                source.setEditable(true);
            }
        }
    }
    //*****************************************************************************//
    
    //*******Checking Invalid Numeric Input by using Key pressed*******
    public static class invalidInputCheckListenerNumeric extends KeyAdapter{
        int maxDigits = 0;

        public invalidInputCheckListenerNumeric(int maxDigits) {
            this.maxDigits = maxDigits;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            JTextField source = (JTextField)e.getSource();
            if((source.getText().length()<maxDigits)&&(e.getKeyChar()>='0'&&e.getKeyChar()<='9')||e.getKeyCode()==8){
                source.setEditable(true);
            }
            else{
                source.setEditable(false);
            }
        }
    }
    //*****************************************************************************//
    
    //*******Checking Invalid text Length by using key pressed*******
    public static class invalidInputCheckListenerAlpha extends KeyAdapter{
        public int maxChars = 0;

        public invalidInputCheckListenerAlpha(int maxChars) {
            this.maxChars = maxChars;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            JTextField source = (JTextField)e.getSource();
            if(source.getText().length()<maxChars||e.getKeyCode()==8){
                source.setEditable(true);
            }
            else{
                source.setEditable(false);
            }
        }
    }
    //*****************************************************************************//

}
