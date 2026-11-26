import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    private final JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        main.setBackground(new Color(238, 243, 250));

        JLabel heading = new JLabel("SIMPLE CALCULATOR", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setForeground(new Color(35, 62, 105));
        main.add(heading, BorderLayout.NORTH);

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 170, 200), 2),
            BorderFactory.createEmptyBorder(12, 10, 12, 10)));
        main.add(display, BorderLayout.CENTER);

        JPanel keys = new JPanel(new GridLayout(5, 4, 8, 8));
        keys.setOpaque(false);
        String[] labels = {
            "C", "⌫", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "−",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };
        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBackground(isOperator(label) ? new Color(67, 112, 180) :
                                 (label.equals("=") ? new Color(34, 145, 105) : Color.WHITE));
            button.setForeground((isOperator(label) || label.equals("=")) ? Color.WHITE :
                                 new Color(35, 45, 60));
            button.setBorder(BorderFactory.createLineBorder(new Color(190, 202, 218)));
            button.addActionListener(this);
            keys.add(button);
        }
        main.add(keys, BorderLayout.SOUTH);
        setContentPane(main);
    }

    private boolean isOperator(String s) {
        return s.equals("÷") || s.equals("×") || s.equals("−") || s.equals("+");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = ((JButton)e.getSource()).getText();
        String current = display.getText();

        if (key.matches("[0-9]")) {
            if (startNewNumber || current.equals("0") || current.equals("Error")) {
                display.setText(key);
            } else {
                display.setText(current + key);
            }
            startNewNumber = false;
        } else if (key.equals(".")) {
            if (startNewNumber || current.equals("Error")) {
                display.setText("0.");
                startNewNumber = false;
            } else if (!current.contains(".")) {
                display.setText(current + ".");
            }
        } else if (key.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
        } else if (key.equals("⌫")) {
            if (!startNewNumber && !current.equals("Error")) {
                display.setText(current.length() > 1 ? current.substring(0, current.length()-1) : "0");
            }
        } else if (key.equals("±")) {
            if (!current.equals("Error") && !current.equals("0")) {
                display.setText(current.startsWith("-") ? current.substring(1) : "-" + current);
            }
        } else if (key.equals("%")) {
            if (!current.equals("Error")) {
                display.setText(format(Double.parseDouble(current) / 100));
            }
        } else if (isOperator(key)) {
            if (!current.equals("Error")) {
                firstNumber = Double.parseDouble(current);
                operator = key;
                startNewNumber = true;
            }
        } else if (key.equals("=")) {
            if (!operator.isEmpty() && !current.equals("Error")) {
                double secondNumber = Double.parseDouble(current);
                double result;
                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "−": result = firstNumber - secondNumber; break;
                    case "×": result = firstNumber * secondNumber; break;
                    case "÷":
                        if (secondNumber == 0) {
                            display.setText("Cannot divide by zero");
                            operator = "";
                            startNewNumber = true;
                            return;
                        }
                        result = firstNumber / secondNumber;
                        break;
                    default: return;
                }
                display.setText(format(result));
                operator = "";
                startNewNumber = true;
            }
        }
    }

    private String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) return "Error";
        if (value == (long)value) return String.valueOf((long)value);
        return String.format(java.util.Locale.US, "%.8f", value)
                     .replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator().setVisible(true));
    }
}
