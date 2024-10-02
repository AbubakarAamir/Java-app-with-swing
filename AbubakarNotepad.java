import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AbubakarNotepad extends JFrame implements ActionListener {
    // Declare components
    private JTextArea textArea;
    private JMenuItem openItem, saveItem, exitItem, boldItem, italicItem, plainItem;
    private JMenuItem cutItem, copyItem, pasteItem, findItem, wordWrapItem, fontSizeItem, fontFamilyItem, textColorItem;

    // Constructor to set up the GUI
    public AbubakarNotepad() {
        // Set up the frame (window)
        setTitle("Enhanced Abubakar Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a text area for writing
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Default font size and style
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Scrollable text area

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a "File" menu
        JMenu fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Create an "Edit" menu
        JMenu editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        findItem = new JMenuItem("Find");
        wordWrapItem = new JMenuItem("Toggle Word Wrap");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(findItem);
        editMenu.add(wordWrapItem);

        // Create a "Format" menu
        JMenu formatMenu = new JMenu("Format");
        boldItem = new JMenuItem("Bold");
        italicItem = new JMenuItem("Italic");
        plainItem = new JMenuItem("Plain");
        fontSizeItem = new JMenuItem("Change Font Size");
        fontFamilyItem = new JMenuItem("Change Font Family");
        textColorItem = new JMenuItem("Text Color");
        formatMenu.add(boldItem);
        formatMenu.add(italicItem);
        formatMenu.add(plainItem);
        formatMenu.addSeparator();
        formatMenu.add(fontSizeItem);
        formatMenu.add(fontFamilyItem);
        formatMenu.add(textColorItem);

        // Add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        setJMenuBar(menuBar);

        // Add action listeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        findItem.addActionListener(this);
        wordWrapItem.addActionListener(this);
        boldItem.addActionListener(this);
        italicItem.addActionListener(this);
        plainItem.addActionListener(this);
        fontSizeItem.addActionListener(this);
        fontFamilyItem.addActionListener(this);
        textColorItem.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Handle menu item actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == exitItem) {
            System.exit(0); // Exit the application
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        } else if (e.getSource() == findItem) {
            findText();
        } else if (e.getSource() == wordWrapItem) {
            toggleWordWrap();
        } else if (e.getSource() == boldItem) {
            textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
        } else if (e.getSource() == italicItem) {
            textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
        } else if (e.getSource() == plainItem) {
            textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
        } else if (e.getSource() == fontSizeItem) {
            changeFontSize();
        } else if (e.getSource() == fontFamilyItem) {
            changeFontFamily();
        } else if (e.getSource() == textColorItem) {
            changeTextColor();
        }
    }

    // Method to open a file
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to save a file
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to toggle word wrap
    private void toggleWordWrap() {
        textArea.setLineWrap(!textArea.getLineWrap());
    }

    // Method to change the font size
    private void changeFontSize() {
        String sizeStr = JOptionPane.showInputDialog(this, "Enter font size:");
        if (sizeStr != null) {
            int size = Integer.parseInt(sizeStr);
            textArea.setFont(textArea.getFont().deriveFont((float) size));
        }
    }

    // Method to change the font family
    private void changeFontFamily() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String selectedFont = (String) JOptionPane.showInputDialog(this, "Choose a font family:", "Font Family",
                JOptionPane.PLAIN_MESSAGE, null, fonts, textArea.getFont().getFamily());
        if (selectedFont != null) {
            textArea.setFont(new Font(selectedFont, textArea.getFont().getStyle(), textArea.getFont().getSize()));
        }
    }

    // Method to change text color
    private void changeTextColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose Text Color", textArea.getForeground());
        if (newColor != null) {
            textArea.setForeground(newColor);
        }
    }

    // Method to find text in the document
    private void findText() {
        String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (searchText != null) {
            String content = textArea.getText();
            int index = content.indexOf(searchText);
            if (index != -1) {
                textArea.select(index, index + searchText.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found.");
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new AbubakarNotepad(); // Create an instance of Enhanced Abubakar Notepad
    }
}
