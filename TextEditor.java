import gui.Borderlayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TextEditor extends JFrame {

    private JButton createFontButton(String imagePath) {
        JButton button = new JButton();
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH)));
        button.setMargin(new Insets(1,15,1,15));
        return button;
    }

    TextEditor() {
        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text editor");
        this.setLayout(new BorderLayout(10,0));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //footer
        JLabel footLabel = new JLabel("Word Count:               Character Count:");
        footLabel.setPreferredSize(new Dimension(0,30));
        footLabel.setBackground(Color.gray);
        this.add(footLabel, BorderLayout.SOUTH);

        //menubar
        JMenuBar menu = new JMenuBar();
        menu.setPreferredSize(new Dimension(200,30));
        JMenu File = new JMenu("File");
        JMenuItem open = new JMenuItem("open");
        JMenuItem save = new JMenuItem("save");
        JMenuItem exit = new JMenuItem("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        File.add(open);
        File.add(save);
        File.add(exit);

        JMenu Edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("copy");
        JMenuItem paste = new JMenuItem("paste");
        JMenuItem zoom = new JMenuItem("zoom");
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(zoom);

        JMenu Review = new JMenu("Review");
        JMenuItem wordcount = new JMenuItem("wordcount");
        JMenuItem translate = new JMenuItem("translate");
        JMenuItem readaloud = new JMenuItem("readaloud");
        Review.add(wordcount);
        Review.add(translate);
        Review.add(readaloud);

        JMenu Help = new JMenu("Help");
        JMenuItem support = new JMenuItem("support");
        JMenuItem find = new JMenuItem("find");
        Help.add(support);
        Help.add(find);

        menu.add(File);
        menu.add(Edit);
        menu.add(Review);
        menu.add(Help);

        this.setJMenuBar(menu);

        //WORD PANEL----------------------------------------------------------------------------------------------------------
        JPanel wordPanel = new JPanel();
        wordPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        wordPanel.setPreferredSize(new Dimension(1000,0));
        wordPanel.setLayout(new BorderLayout());

        //text area
        JTextArea textArea = new JTextArea(31,0);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setPreferredSize(new Dimension(0,400));
        wordPanel.add(scrollPane,BorderLayout.SOUTH);

        JPanel topWordPanel = new JPanel();
        topWordPanel.setLayout(new FlowLayout(FlowLayout.CENTER,8,0));
        topWordPanel.setPreferredSize(new Dimension(1000,400));
        //font buttons
        JPanel fontPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JButton bold = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/bold-removebg-preview.png");
        bold.setToolTipText("bold");
        JButton italic = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/italic-removebg-preview.png");
        italic.setToolTipText("italic");
        JButton underline = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/underline.png");
        underline.setToolTipText("underline");
        JButton strikethrough = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/strikethrough.png");
        strikethrough.setToolTipText("strikethrough");
        fontPanel.add(bold);
        fontPanel.add(italic);
        fontPanel.add(underline);
        fontPanel.add(strikethrough);

        topWordPanel.add(fontPanel);

        //alignment buttons
        JPanel alignmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JButton leftAlign = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/leftAlign.png");
        leftAlign.setToolTipText("leftAlign");
        JButton centerAlign = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/centeralign.png");
        centerAlign.setToolTipText("centerAlign");
        JButton rightAlign = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/rightalign.png");
        rightAlign.setToolTipText("rightAlign");
        JButton JustifyAlign = createFontButton("C:/Users/VIPUL CHAUHAN/Pictures/Saved Pictures/justifyalign.png");
        JustifyAlign.setToolTipText(("justify"));
        alignmentPanel.add(leftAlign);
        alignmentPanel.add(centerAlign);
        alignmentPanel.add(rightAlign);
        alignmentPanel.add(JustifyAlign);

        topWordPanel.add(alignmentPanel);

        //fonttype combobox
        String[] fonttypes = {"Serif","Arial","Georgia","Open Sans","Baskerville","Century Gothic","Helvetica","Times New Roman","Verdena","Calibri"};
        JComboBox<String> fonttype = new JComboBox<>(fonttypes);
        fonttype.setPreferredSize(new Dimension(300,35));
        fonttype.setEditable(true);
        fonttype.setFont(new Font("sans-serif",Font.BOLD,13));
        topWordPanel.add(fonttype);

        //fontsize combobox
        Integer[] fontsizes = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        JComboBox<Integer> fontsize = new JComboBox<>(fontsizes);
        fontsize.setPreferredSize(new Dimension(170,35));
        fontsize.setEditable(true);
        fontsize.setFont(new Font("sans-serif",Font.BOLD,13));
        topWordPanel.add(fontsize);

        //find label and text field
        JLabel findlabel = new JLabel("find");
        findlabel.setPreferredSize(new Dimension(980,35));
        JTextField findfield = new JTextField();
        findfield.setPreferredSize(new Dimension(980,35));

        JLabel replacelabel = new JLabel("replace");
        replacelabel.setPreferredSize(new Dimension(980,35));
        JTextField replacefield = new JTextField();
        replacefield.setPreferredSize(new Dimension(980,35));

        topWordPanel.add(findlabel);
        topWordPanel.add(findfield);
        topWordPanel.add(replacelabel);
        topWordPanel.add(replacefield);

        String[] findButtons = {"Find All","Find Next","Replace All","Replace Next"};
        for (String buttonName: findButtons) {
            JButton button = new JButton(buttonName);
            button.setFocusable(false);
            button.setToolTipText(buttonName);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(82,30));
            if (buttonName.equals("Replace Next")) {
                button.setPreferredSize(new Dimension(100,30));
            }
            button.setFont(new Font("sans-serif",Font.BOLD,13));
            button.setMargin(new Insets(0,0,0,0));
            topWordPanel.add(button);
        }

        wordPanel.add(topWordPanel,BorderLayout.NORTH);

        //SKETCH PANEL--------------------------------------------------------------------------------------------------------
        JPanel sketchPanel = new JPanel();
        sketchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        sketchPanel.setPreferredSize(new Dimension(530,0));
        sketchPanel.setLayout(new BorderLayout());

        //sketch label
        JPanel sketchTopPanel = new JPanel();
        sketchTopPanel.setLayout(new BorderLayout());

        JLabel sketchlabel = new JLabel("SketchPad");
        sketchlabel.setVerticalAlignment(JLabel.CENTER);
        sketchlabel.setHorizontalAlignment(JLabel.CENTER);
        sketchlabel.setOpaque(true);
        sketchlabel.setPreferredSize(new Dimension(0,40));
        sketchlabel.setFont(new Font("sans-serif",Font.BOLD,15));
        sketchlabel.setBackground(new Color(0xEAEAEA));

        //buttons for sketchpad
        JPanel sketchButtonPanel = new JPanel();
        sketchButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
        sketchButtonPanel.setLayout(new FlowLayout());
        sketchButtonPanel.setBackground(new Color(0xEAEAEA));
        String[] sketchButtons = {"Rectangle","Circle","Line","Triangle","pentagon","CLEAR"};
        for (String button: sketchButtons) {
            JButton sketchButton = new JButton(button);
            sketchButton.setFocusable(false);
            sketchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sketchButton.setToolTipText(button);
            sketchButton.setToolTipText(button);
            sketchButton.setPreferredSize(new Dimension(82,30));
            sketchButton.setFont(new Font("sans-serif",Font.BOLD,13));
            sketchButton.setMargin(new Insets(0,0,0,0));
            sketchButtonPanel.add(sketchButton);
        }


        //sketch area
        JLabel sketcharea = new JLabel();
        sketcharea.setPreferredSize(new Dimension(0,700));
        sketcharea.setBackground(new Color(0xB7B7B7));
        sketcharea.setOpaque(true);

        sketchTopPanel.add(sketchButtonPanel);
        sketchTopPanel.add(sketchlabel,BorderLayout.NORTH);
        sketchPanel.add(sketchTopPanel,BorderLayout.NORTH);
        sketchPanel.add(sketcharea,BorderLayout.SOUTH);

        this.add(wordPanel,BorderLayout.WEST);
        this.add(sketchPanel,BorderLayout.EAST);
    }

    public static void main(String[] main) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}
