import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class MainFrame {
    private static File selectedFile; // Переменная для хранения выбранного файла

    public static void showFrame() {
        JFrame frame = new JFrame("File Chooser Example");
        JButton chooseButton = new JButton("Выбрать файл");
        JButton reportButton = new JButton("Получить отчет");
        JTextField textField = new JTextField(20);
        JLabel label = new JLabel("Номер варианта:");

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(frame, "Файл не выбран.");

                } else if (textFieldValue.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Не указан номер варианта.");
                }
                else{System.out.println("Текст из текстового поля: " + textFieldValue);
                    System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
                }


            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(chooseButton);
        panel.add(reportButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
