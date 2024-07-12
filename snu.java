import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;

class links extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(links::new);
    }
    //method to create buttons for center panel
    private JButton createButton(String labelText, String imagePath,int Special) {
        JButton button = new JButton();
        button.setToolTipText(labelText);
        button.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));


        JLabel text = new JLabel(labelText);
        text.setFont(new Font("sans-serif",Font.PLAIN,12));

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(32,34,Image.SCALE_SMOOTH)));
        image.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.gray),BorderFactory.createEmptyBorder(0,0,0,5)));
        //BorderFactory.createMatteBorder(0,0,0,1,Color.gray)
        image.setHorizontalAlignment(JLabel.LEFT);

        button.setFocusable(false);
        button.setBackground(Color.white);
        button.setMargin(new Insets(0,0,0,0));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        button.setPreferredSize(new Dimension(200,47));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //the first button needs to be blue
        if (Special==0) {
            text.setForeground(Color.WHITE);
            image.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.white),BorderFactory.createEmptyBorder(0,0,0,5)));
        } else {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(0xF4F6F6));
                    //button.setOpaque(false);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(Color.white);
                }
            });
        }

        button.add(image);
        button.add(text);

        return button;
    }
    //methods to create labels for bottom panel
    private JPanel createImagePanel(String imagePath, String labelText) {
        JPanel panel = new JPanel();
        JLabel text = new JLabel(labelText);
        JLabel image = new JLabel();

        image.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        image.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH)));
        image.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.white));
        image.setPreferredSize(new Dimension(50,50));

        text.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        text.setPreferredSize(new Dimension(110, 100));
        text.setFont(new Font("sans-serif",Font.PLAIN,12));
        text.setForeground(Color.WHITE);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT,15,-20));
        panel.setBackground(new Color(0x17141f));
        panel.add(image);
        panel.add(text);

        return panel;
    }
    //method to create menu for profile
    private JPopupMenu CustomMenu(String[][] str) {
        JPopupMenu profileMenu = new JPopupMenu();
        //menu Items
        for (String[] Item: str) {
            JMenuItem menuItem = new JMenuItem(Item[0]);
            menuItem.setPreferredSize(new Dimension(230,40));
            menuItem.setIcon(new ImageIcon(new ImageIcon(Item[1]).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
            menuItem.setHorizontalTextPosition(JMenuItem.RIGHT);
            menuItem.setVerticalTextPosition(JMenuItem.CENTER);
            menuItem.setFont(new Font("sans-serif",Font.PLAIN,15));
            menuItem.setBackground(Color.white);
            profileMenu.add(menuItem);
            if (Item[0].equals("Change/ Reset Password")) {
                JSeparator separator = new JSeparator();
                profileMenu.add(separator);
            }
        }

        return profileMenu;
    }
    //constructor
    links() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //panels for main frame
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(0x005197));
        panelTop.setPreferredSize(new Dimension(0,100));
        panelTop.setLayout(new GridLayout(1,3));

        JPanel panelBottom = new JPanel();
        panelBottom.setBackground(new Color(0x17141f));
        panelBottom.setPreferredSize(new Dimension(0,180));
        panelBottom.setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel();
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20,80,0,20));
        panelCenter.setBackground(Color.white);
        panelCenter.setLayout(new FlowLayout(FlowLayout.LEFT,24,16));

        //TOP PANEL ------------------------------------------------------------------------------------------------------------------------
        //panels in top panel
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setBackground(new Color(0x005197));
        topLeftPanel.setLayout(new BorderLayout());

        JPanel topRightPanel = new JPanel();
        topRightPanel.setBackground(new Color(0x005197));
        topRightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        topRightPanel.setBorder(BorderFactory.createEmptyBorder(40,150,0,0));

        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setBackground(new Color(0x005197));
        topCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        topCenterPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        //snu logo in leftpanel of top
        JLabel labelSNUlogo = new JLabel();
        ImageIcon SNUicon = new ImageIcon(new ImageIcon("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/snuLogo-OPSE-logo-white.png").getImage().getScaledInstance(200,60,Image.SCALE_SMOOTH));
        labelSNUlogo.setIcon(SNUicon);
        labelSNUlogo.setHorizontalAlignment(JLabel.CENTER);
        labelSNUlogo.setVerticalAlignment(JLabel.CENTER);
        labelSNUlogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topLeftPanel.setBorder(BorderFactory.createEmptyBorder(0,160,0,160));
        topLeftPanel.add(labelSNUlogo, BorderLayout.CENTER);

        //dropdown menu for profile in right top panel
        String[][] menuItems = {{"Logout","C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/logout.jpg"},
                                {"Forgot Password","C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/key.jpg"},
                                {"Change/ Reset Password","C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/key.jpg"},
                                {"IT HelpDesk","C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/message.jpg"},
                                {"How to Login","C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/info.jpg"}};

        JPopupMenu profileMenu = CustomMenu(menuItems);

        JLabel profileLabel = new JLabel("Hi Student");
        ImageIcon downArrowIcon = new ImageIcon(new ImageIcon("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/dropDown.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        profileLabel.setIcon(downArrowIcon);
        profileLabel.setHorizontalTextPosition(JLabel.LEFT);
        profileLabel.setForeground(Color.WHITE);
        profileLabel.setFont(new Font("sans-serif",Font.PLAIN,15));
        profileLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileMenu.show(profileLabel,-130,profileLabel.getHeight()+10);
            }
        });

        topRightPanel.add(profileLabel);

        //search bar in middle of top panel
        JTextField textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(210,36));
        textfield.setFont(new Font("sans-serif",Font.PLAIN,15));
        //TextPrompt textPrompt = new TextPrompt("search",textfield);
        //textfield.add(textPrompt);

        textfield.setBorder(BorderFactory.createEmptyBorder());

        ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/SearchIcon.png").getImage().getScaledInstance(45,37,Image.SCALE_SMOOTH));
        JButton searchButton = new JButton();
        searchButton.setIcon(searchIcon);
        searchButton.setFocusable(false);
        searchButton.setPreferredSize(new Dimension(45,37));

        topCenterPanel.add(searchButton);
        topCenterPanel.add(textfield);

        //adding panels to top panel
        panelTop.add(topLeftPanel);
        panelTop.add(topCenterPanel);
        panelTop.add(topRightPanel);

        //CENTER PANEL---------------------------------------------------------------------------------------------------------------------------------------
        //string array for function button in centre panel
        String[][] buttonData = {
                {"University ERP", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/SNU-ERP.png"},
                {"Assistantship/Award", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/assistantship.png"},
                {"Blackboard", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/blackboard.png"},
                {"CCT", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Student-Co-Curricular.png"},
                {"Certificate Issuance", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/certificate-issuance.png"},
                {"Course Evaluation Survey", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/course-evaluation.png"},
                {"Doctoral Portal", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Doctoral.png"},
                {"Fastrack", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/fastrack.png"},
                {"Hostel Management", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/HMS.png"},
                {"ID Card Management", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/ID-Card.png"},
                {"Mobile App CMS", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Mobile-App.png"},
                {"On Campus Job", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/OCJ.png"},
                {"Student Outbound Mobility", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Student-Outbound01.png"},
                {"<html>Student Attendance<br>Recording</html>", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Student-attendance.png"},
                {"<html>Student Attendance<br>Management<html>", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Student-attendance.png"},
                {"Student Clearance", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/student-clearence.png"},
                {"Student Payment Center", "C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/student-payment-center.png"}
        };
        //creating adding buttons to centre panel
        int i=0;
        for (String[] data : buttonData) {
            JButton button = createButton(data[0], data[1],i);
            if (i==0) {
                button.setBackground(new Color(0x0167bd));
                button.setForeground(Color.white);
                i++;
            }
            panelCenter.add(button);
        }

        //BOTTOM PANEL -------------------------------------------------------------------------------------------------------------------------------------------
        //panels in bottom panel
        JPanel bottomTopPanel = new JPanel();
        bottomTopPanel.setBackground(new Color(0x17141f));
        bottomTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));
        bottomTopPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
        bottomTopPanel.setPreferredSize(new Dimension(0,95));

        JPanel bottomBottomPanel = new JPanel();
        bottomBottomPanel.setBackground(new Color(0x17141f));
        bottomBottomPanel.setLayout(new FlowLayout());

        //bottom labels string
        String[][] labelData = {
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Students-policy.png", "<HTML><U>Student Policy</U></HTML>"},
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Students-handbook.png", "<HTML><U>Student Handbook</U></HTML>"},
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Academic&Research.png", "<HTML><U>Academic Research</U></HTML>"},
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/Library.png", "<HTML><U>University Library</U></HTML>"},
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/mes-menu.png", "<HTML><U>Mess Menu</U></HTML>"},
                {"C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/net-id.png", "<HTML><U>Netid Help</U></HTML>"}
        };
        //addign them to the bottom panel
        for (String[] data : labelData) {
            String imagePath = data[0];
            String labelText = data[1];
            // Create a label with an image and HTML-formatted text
            JPanel panel = createImagePanel(imagePath, labelText);

            bottomTopPanel.add(panel);
        }

        //footnote in the bottom panel
        JLabel shivnadar = new JLabel();
        shivnadar.setText("<HTML><U>©2023 Shiv Nadar (Institution of Eminence Deemed to be University).</U></HTML>");
        shivnadar.setFont(new Font("sans-serif",Font.PLAIN,15));
        shivnadar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        shivnadar.setForeground(Color.WHITE);
        shivnadar.setHorizontalAlignment(JLabel.CENTER);
        shivnadar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://snu.edu.in/home/"));
                } catch (java.io.IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        bottomBottomPanel.add(shivnadar);

        panelBottom.add(bottomBottomPanel);
        panelBottom.add(bottomTopPanel,BorderLayout.NORTH);

        this.add(panelTop,BorderLayout.NORTH);
        this.add(panelBottom,BorderLayout.SOUTH);
        this.add(panelCenter,BorderLayout.CENTER);
        this.setVisible(true);
    }
}