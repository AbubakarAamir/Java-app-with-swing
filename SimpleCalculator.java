
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    // Declare variables for the calculator
    private JTextField textField;  // Display area
    private double num1, num2, result;  // Numbers and result
    private char operator;  // Operator (+, -, *, /)

    // Constructor to set up the GUI
    public SimpleCalculator() {
        // Set title, size, and close operation for the window
        setTitle("Orange & Black Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the text field (display) where numbers and results will be shown
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 36)); // Set font size and style
        textField.setHorizontalAlignment(SwingConstants.RIGHT); // Align text to the right
        textField.setEditable(false); // Users can't type directly into it
        textField.setBackground(Color.BLACK); // Set background color to black
        textField.setForeground(Color.WHITE); // Set text color to white

        // Create the panel to hold the buttons in a grid layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10)); // 5 rows, 4 columns with gaps

        // Array to hold the button labels (C, Del, numbers, operators, etc.)
        String[] buttons = {
            "C", "Del", "/", "*",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            "0", ".", "", ""
        };

        // Loop through the button labels and create a JButton for each one
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for buttons
            button.addActionListener(this); // Attach action listener to handle clicks

            // Set specific color for operators and other buttons
            if (text.equals("C") || text.equals("Del") || text.equals("=")) {
                button.setBackground(Color.ORANGE); // Orange for special buttons
            } else if (text.matches("[+\\-*/]")) {
                button.setBackground(Color.ORANGE); // Orange for operators
            } else {
                button.setBackground(Color.BLACK); // Black for number buttons
                button.setForeground(Color.WHITE); // White text for number buttons
            }

            buttonPanel.add(button); // Add the button to the panel
        }

        // Add the text field at the top and the buttons below it
        setLayout(new BorderLayout()); // BorderLayout allows us to place items in specific areas
        add(textField, BorderLayout.NORTH); // Text field at the top
        add(buttonPanel, BorderLayout.CENTER); // Buttons in the center

        setVisible(true); // Make the window visible
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the label of the button that was clicked
        String command = e.getActionCommand();

        // If the user pressed "C", reset everything
        if (command.equals("C")) {
            textField.setText(""); // Clear the display
            num1 = num2 = result = 0; // Reset numbers
        }
        // If the user pressed "Del", remove the last character
        else if (command.equals("Del")) {
            String currentText = textField.getText();
            if (!currentText.isEmpty()) {
                textField.setText(currentText.substring(0, currentText.length() - 1)); // Remove last character
            }
        }
        // If the user pressed a number or decimal point, append it to the display
        else if (command.matches("[0-9]") || command.equals(".")) {
            textField.setText(textField.getText() + command); // Append number/decimal
        }
        // If the user pressed an operator (+, -, *, /), store the first number and operator
        else if (command.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(textField.getText()); // Get first number
            operator = command.charAt(0); // Store the operator (+, -, *, /)
            textField.setText(""); // Clear the display for the second number
        }
        // If the user pressed "=", perform the calculation
        else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText()); // Get second number

            // Perform the operation based on the stored operator
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }

            textField.setText(String.valueOf(result)); // Display the result
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new SimpleCalculator(); // Create an instance of the calculator
    }
}
