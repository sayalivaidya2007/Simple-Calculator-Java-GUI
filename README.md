# Simple Calculator – Java GUI (Swing)

## Project overview
A beginner-friendly desktop calculator developed individually using Java Swing and object-oriented programming concepts.

## Features
- Addition, subtraction, multiplication and division
- Decimal numbers and percentage
- Positive/negative toggle
- Clear and backspace
- Division-by-zero handling

## Requirements
- JDK 8 or later
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or a terminal

## Run using a terminal
1. Extract the ZIP file.
2. Open a terminal in the `src` folder.
3. Compile:
   ```bash
   javac SimpleCalculator.java
   ```
4. Run:
   ```bash
   java SimpleCalculator
   ```

## GUI components used
- `JFrame` – main application window
- `JPanel` – groups the interface components
- `JTextField` – displays numbers and results
- `JButton` – calculator keys
- `GridLayout` and `BorderLayout` – arrange components
- `ActionListener` – handles button clicks

## OOP concepts
- Class and object: `SimpleCalculator` is a class; the application window is its object.
- Encapsulation: calculator state is stored in private fields.
- Event handling: button actions are handled by `actionPerformed()`.

## Test examples
| Operation | Keys | Expected result |
|---|---|---:|
| Addition | 12 + 8 = | 20 |
| Subtraction | 15 − 6 = | 9 |
| Multiplication | 7 × 5 = | 35 |
| Division | 20 ÷ 4 = | 5 |
| Percentage | 50 % | 0.5 |

## Repository contents
- `src/SimpleCalculator.java` – complete Java source code
- `Output_Screenshot.pdf` – sample GUI output preview
- `README.md` – project instructions

## GitHub
Create a new public repository named `Simple-Calculator-Java-GUI`, upload the contents of this folder, and copy the repository URL into your submission form.
