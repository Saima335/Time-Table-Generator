
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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import timetableproject.dataManagement.TimeTableSpace;
import timetableproject.roomHierarchy.Room;

//====================================
//|   CREATING GUI FOR ROOM Editing  |
//====================================
public class GuiRoomEdit extends JFrame {
    
    //********************************************DATA MEMBERS********************************************//
    JLabel nameLabel;
    JLabel capacityLabel;
    JTextField nameField;
    JTextField capacityField;
    JButton update;
    JButton delete;
    
    //********************************************CONSTRUCTORS********************************************//
    //--------ARGUMENT CONSTRUCTOR--------//
    public GuiRoomEdit(int roomToEdit, TimeTableSpace spaceToEdit){
        //-----Set Size Of Frame-----
        setSize(800,600);
        //-----Frame Open in Maximized State-----
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //-----Exit Frame On Close-----
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //-----Removes the Frame Title bar-----
        setUndecorated(true);
        //-----Set Frame Layout to Grid with 3 rows and 2 columns for 2 labels, text fields and 2 buttons update, delete-----
        setLayout(new GridLayout(3,2));
        //-----Make Labels New Name and New Capacity to update room name, capacity and set it's field to Older Name and Capacity-----
        nameLabel = new JLabel("New Name");
        capacityLabel = new JLabel("New Capacity");
        nameField = new JTextField(10);
        nameField.setText(spaceToEdit.getRooms()[roomToEdit].getName());
        capacityField = new JTextField(3);
        capacityField.setText(spaceToEdit.getRooms()[roomToEdit].getCapacity()+"");
        //-----Create button for updation or going back to main menu and set it's background colour (RGB Constructor used)-----
        update = new JButton("Update / Main Menu");
        update.setBackground(new Color(255,128,0));
        //-----Create a button for deletion-----
        delete = new JButton("delete");

        //-----Checking Room name Validness-----
        nameField.addKeyListener(new GuiMain.invalidInputCheckListenerAlpha(10));
        //-----Checking Room Capacity Validness-----
        capacityField.addKeyListener(new GuiMain.invalidInputCheckListenerNumeric(3));
        
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
        
        //*******Updating Room record on clicking update button*******
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(spaceToEdit.getRooms()[roomToEdit].editRoom(nameField.getText(),Integer.parseInt(capacityField.getText()),spaceToEdit.getRooms())==true){
                    spaceToEdit.saveRooms();
                    new GuiMain();
                    dispose();
                }
                else{
                    //JOptionPane.showMessageDialog(null,"Name Not Unique");
                    spaceToEdit.saveRooms();
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
                Room[] room =spaceToEdit.getRooms() ;
                for(int i = 0;i<Room.roomCount;i++){
                    if(room[i].getName().equals(source.getText()) && !(room[i].getName().equals(spaceToEdit.getRooms()[roomToEdit].getName()))){
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
        
        //*******Deleting Room record on clicking delete button*******
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (spaceToEdit.removeRoom(spaceToEdit.getRooms()[roomToEdit].getName())==true){
                    spaceToEdit.saveRooms();
                    new GuiMain();
                    dispose();
                }
                /*else{
                    JOptionPane.showMessageDialog(null,"Room Name Not Found");
                }*/
            }
        });
        //*****************************************************************************//
        
        //-----Add all text fields, labels, buttons in order in frame
        add(nameLabel);
        add(nameField);
        add(capacityLabel);
        add(capacityField);
        add(update);
        add(delete);
        //-----Set Frame To Visible-----
        setVisible(true);
    }
    //*****************************************************************************//
    
}


