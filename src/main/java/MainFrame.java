
import calculatestatistics.GeometricMean;
import read.ExcelReader;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainFrame {
    private static File selectedFile;

    public static void showFrame() {
        JFrame frame = new JFrame("File Chooser Example");
        JButton chooseButton = new JButton("Выбрать файл");
        JButton readButton = new JButton("Cчитать выбранный вариант");
        JButton calculateButton = new JButton("Произвести статистические расчёты");
        JComboBox<Integer> numberComboBox = new JComboBox<>();
        JLabel label = new JLabel("Номер варианта:");

        for (int i = 1; i <= 20; i++) {
            numberComboBox.addItem(i);
        }

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    Integer selectedNumber = (Integer) numberComboBox.getSelectedItem();
                    try {
                        if (selectedFile == null) {
                            throw new IllegalArgumentException("Файл не выбран.");
                        }
                        ExcelReader.readFromExcel(selectedFile.getAbsolutePath(), selectedNumber );
                    } catch (IOException | IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer selectedNumber = (Integer) numberComboBox.getSelectedItem();
                try {
                    if (selectedFile == null) {
                        throw new IllegalArgumentException("Файл не выбран.");
                    }
                    ExcelReader.readFromExcel(selectedFile.getAbsolutePath(), selectedNumber );
                } catch (IOException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GeometricMean.calculateGeometricMean();
                List<Double> anotherGeometricMeans = GeometricMean.getGeomtericMean();
                System.out.println(anotherGeometricMeans);
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(numberComboBox);
        panel.add(chooseButton);
        panel.add(readButton);
        panel.add(calculateButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
