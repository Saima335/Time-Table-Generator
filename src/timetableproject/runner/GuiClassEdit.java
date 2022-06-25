
package timetableproject.runner;

//**********IMPORTS**********//
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
import javax.swing.table.DefaultTableModel;
import timetableproject.dataManagement.TimeTableSpace;
import timetableproject.classHierarchy.Class;

//==============================
//|   CREATING GUI FOR CLASS   |
//==============================
public class GuiClassEdit extends JFrame {
    
    //********************************************DATA MEMBERS********************************************//
    JPanel classEdit;
    JPanel courseEdit;
    JPanel courseAddPanel;
    JPanel courseShowPanel;
    JLabel nameLabel;
    JLabel strengthLabel;
    JTextField nameField;
    JTextField strengthField;
    JButton update;
    JButton delete;
    JLabel courseNameLabel;
    JLabel courseTeacherLabel;
    JLabel courseWeeklyClassCountLabel;
    JTextField courseNameField;
    JTextField courseTeacherField;
    JTextField courseWeeklyClassCountField;
    JButton addCourse;
    JButton removeCourse;
    JTable showCourseTable;
    JScrollPane showCourseScrollPane;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTOR--------//
    public GuiClassEdit(int classToEdit, TimeTableSpace spaceToEdit){
        //-----Set Size Of Frame-----
        setSize(800,600);
        //-----Frame Open in Maximized State-----
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //-----Removes the Frame Title bar-----
        setUndecorated(true);
        //-----Set Frame Layout to Grid with one row and two columns (one for class and course and other for course table)-----
        setLayout(new GridLayout(1,2));
        //-----Make Panel Class Edit To Edit Class Name, Strngth and set its layout to grid with 3 rows and 2 columns so to add 3 labels, textfield and 2 buttons update, remove-----
        classEdit = new JPanel();
        classEdit.setLayout(new GridLayout(3,2));
        //-----Make Panel Course Edit To Enter and view Courses of grid layout with one row and 2 columns (to add course add and view panels)-----
        courseEdit = new JPanel();
        courseEdit.setLayout(new GridLayout(1,2));
        //-----Make Labels New Name and New Capacity to update class name, capacity and set it's field to Older Name and Capacity-----
        nameLabel = new JLabel("New Name");
        strengthLabel = new JLabel("New Capacity");
        nameField = new JTextField(10);
        nameField.setText(spaceToEdit.getClasses()[classToEdit].getName());
        strengthField = new JTextField(3);
        strengthField.setText(spaceToEdit.getClasses()[classToEdit].getStrength()+"");
        //-----Create button for updation or going back to main menu and set it's background colour (RGB Constructor used)-----
        update = new JButton("Update / Main Menu");
        update.setBackground(new Color(255,128,0));
        //-----Create a button for deletion-----
        delete = new JButton("delete");
        //-----Make Panel Course Add To Add Course Name, Teacher, Weekly Classes of grid layout with 4 rows and 2 colums to add (3 text fields, labels and 2 add, remove button-----
        courseAddPanel = new JPanel();
        courseAddPanel.setLayout(new GridLayout(4,2));
        //-----Make Panel Course Show To show the table for course and set it's layout to grid with one row and one column-----
        courseShowPanel = new JPanel();
        courseShowPanel.setLayout(new GridLayout(1,1));
        //-----Create labels, text field for Course Name, Teacher Name, Weekly Classes to get Course data-----
        courseNameLabel = new JLabel("Course Name");
        courseTeacherLabel = new JLabel("Teacher Name");
        courseWeeklyClassCountLabel = new JLabel("Weekly Classes");
        courseNameField = new JTextField(10);
        courseTeacherField = new JTextField(10);
        courseWeeklyClassCountField = new JTextField(1);
        //-----Create Button to add and remove course-----
        addCourse = new JButton("Add Course");
        removeCourse = new JButton("Remove Course");
        //-----Make a Table to Show Course Data-----
        showCourseTable = new JTable();
        //-----Provide Scrollable view of Course table-----
        showCourseScrollPane = new JScrollPane(showCourseTable);
        //-----Update Courses Tabel Of Class-----
        updateTable(classToEdit,spaceToEdit);
        //-----Check Class Name validness-----
        nameField.addKeyListener(new GuiMain.invalidInputCheckListenerAlpha(10));
        //-----Checing Class Strength validness-----
        strengthField.addKeyListener(new GuiMain.invalidInputCheckListenerNumeric(3));
        
        //*******Changing Update button Colour on Entering and Leaving of Mouse******* 
        update.addMouseListener(new MouseAdapter() {
            @Override
            //-----Changing Update button Colour to yellow on Entering of Mouse----- 
            public void mouseEntered(MouseEvent e) {
                update.setBackground(new Color(255,80,0));
            }
            //-----Changing Update button Colour to orange on Exiting of Mouse----- 
            @Override
            public void mouseExited(MouseEvent e) {
                update.setBackground(new Color(255,128,0));
            }
        });
        //*****************************************************************************//
        
        //*******Updating Class record on clicking update button*******
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(spaceToEdit.getClasses()[classToEdit].editClass(nameField.getText(),Integer.parseInt(strengthField.getText()),spaceToEdit.getClasses())==true){
                    spaceToEdit.saveClasses();
                    new GuiMain();
                    dispose();
                }
                else{
                    //JOptionPane.showMessageDialog(null,"Name Not Unique");
                    spaceToEdit.saveClasses();
                    new GuiMain();
                    dispose();
                }
            }
        });
        //*****************************************************************************//
        
        //*******Checking Uniqueness of Name*******
        nameField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                boolean valid=true;
                JTextField source = (JTextField)e.getSource();
                Class[] classes =spaceToEdit.getClasses() ;
                for(int i = 0;i<Class.classCount;i++){
                    if(classes[i].getName().equals(source.getText()) && !(classes[i].getName().equals(spaceToEdit.getClasses()[classToEdit].getName()))){
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
        
        //*******Deleting Class record on clicking delete button*******
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (spaceToEdit.removeClass(spaceToEdit.getClasses()[classToEdit].getName())){
                    spaceToEdit.saveClasses();
                    new GuiMain();
                    dispose();
                }
                /*else{
                    JOptionPane.showMessageDialog(null,"Class Name Not Found");
                }*/
            }
        });
        //*****************************************************************************//
        
        //-----Checking Course name Validness-----
        courseNameField.addKeyListener(new GuiMain.invalidInputCheckListenerAlpha(10));
        //-----Checking Course Teacher Validness-----
        courseTeacherField.addKeyListener(new GuiMain.invalidInputCheckListenerAlpha(10));
        //-----Checking Course weekly Class Count Validness-----
        courseWeeklyClassCountField.addKeyListener(new GuiMain.invalidInputCheckListenerNumeric(1));
        
        //*******Adding Course record on clicking add course button*******
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-----Take Weekly Class Count 0 as default if no value entered-----
                if(courseWeeklyClassCountField.getText().equals("")){
                    courseWeeklyClassCountField.setText("0");
                }
                //-----Add and Save Course data-----
                if (spaceToEdit.getClasses()[classToEdit].addCourse(courseNameField.getText(),courseTeacherField.getText(),Integer.parseInt(courseWeeklyClassCountField.getText()))){
                    spaceToEdit.saveClasses();
                    //-----Update Courses Tabel Of Class-----
                    updateTable(classToEdit,spaceToEdit);
                    //-----Reset Course name, teacher and weekly class count to empty to add another course-----
                    courseNameField.setText("");
                    courseNameField.setEditable(true);
                    courseTeacherField.setText("");
                    courseTeacherField.setEditable(true);
                    courseWeeklyClassCountField.setText("");
                    courseWeeklyClassCountField.setEditable(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Name Not Unique");
                }
            }
        });
        //*****************************************************************************//
        
        //*******Checking Uniqueness of Name*******
        /*courseNameField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                boolean valid=true;
                JTextField source = (JTextField)e.getSource();
                Class[] classes =spaceToEdit.getClasses() ;
                for(int i = 0;i<Class.classCount;i++){
                    //if(classes[i].getName().equals(spaceToEdit.getClasses()[classToEdit].getName())){
                    for (int j=0;j<classes[i].getCourseCount();j++){
                        if(classes[i].getCourses()[j].getName().equals(source.getText())){
                            valid = false;
                            break;
                        }
                    }
                    //}
                }
                if (!valid){
                    source.setText("");
                    source.setEditable(true);
                }
            }
        });*/
        //*****************************************************************************//
        
        //*******Remove Course record on clicking remove course button*******
        removeCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-----Remove and Save Course data-----
                if (spaceToEdit.getClasses()[classToEdit].removeCourse(courseNameField.getText())){
                    spaceToEdit.saveClasses();
                    //-----Update Courses Tabel Of Class-----
                    updateTable(classToEdit,spaceToEdit);
                    //-----Reset Course name, teacher and weekly class count to empty to add another course-----
                    courseNameField.setText("");
                    courseNameField.setEditable(true);
                    courseTeacherField.setText("");
                    courseTeacherField.setEditable(true);
                    courseWeeklyClassCountField.setText("");
                    courseWeeklyClassCountField.setEditable(true);
                }
                /*else{
                    JOptionPane.showMessageDialog(null,"Course Name not Found");
                }*/
            }
        });
        //*****************************************************************************//

        //-----Add Class Edit and Course Edit panel-----
        add(classEdit);
        add(courseEdit);
        //-----Add Class name, strength label and text field, update and delete button in Class Edit panel-----
        classEdit.add(nameLabel);
        classEdit.add(nameField);
        classEdit.add(strengthLabel);
        classEdit.add(strengthField);
        classEdit.add(update);
        classEdit.add(delete);
        //-----Add Course Add Panel in Course Edit Panel-----
        courseEdit.add(courseAddPanel);
        //-----Add Course Name, Teacher, Weekly Classes labels and text fields, add and remove course buttons to Course Add Panel-----
        courseAddPanel.add(courseNameLabel);
        courseAddPanel.add(courseNameField);
        courseAddPanel.add(courseTeacherLabel);
        courseAddPanel.add(courseTeacherField);
        courseAddPanel.add(courseWeeklyClassCountLabel);
        courseAddPanel.add(courseWeeklyClassCountField);
        courseAddPanel.add(addCourse);
        courseAddPanel.add(removeCourse);
        //-----Add Course Show Panel to Course Edit Panel-----
        courseEdit.add(courseShowPanel);
        //-----Add Show Course Scroll Pane to Course Show Panel-----
        courseShowPanel.add(showCourseScrollPane);
        //-----Set Frame To Visible-----
        setVisible(true);
    }
    //*****************************************************************************//
    
    //*******Updating Course Table******* 
    public void updateTable(int classToEdit,TimeTableSpace spaceToEdit){
        //-----Columns For Course Table Made-----
        String[] columns = {"Name","Teacher","Weekly Classes"};
        //-----Add All Courses Data to Tabel-----
        String[][] data = new String[spaceToEdit.getClasses()[classToEdit].getCourseCount()][3];
        for(int i = 0;i<spaceToEdit.getClasses()[classToEdit].getCourseCount();i++){
            data[i][0] = spaceToEdit.getClasses()[classToEdit].getCourses()[i].getName();
            data[i][1] = spaceToEdit.getClasses()[classToEdit].getCourses()[i].getTeacher();
            data[i][2] = spaceToEdit.getClasses()[classToEdit].getCourses()[i].getWeeklyClassCount()+"";
        }
        //-----Set Course Table Model to not editable-----
        DefaultTableModel tableModel = new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        showCourseTable.setModel(tableModel);
    }
    //*****************************************************************************//
}
