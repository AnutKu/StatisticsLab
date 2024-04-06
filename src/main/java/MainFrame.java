
import  calculatestatistics.*;
import read.ExcelReader;
import write.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainFrame {
    private static File selectedFile;

    public static void showFrame() {
        JFrame frame = new JFrame();
        JButton chooseButton = new JButton("Выбрать файл");
        JButton readButton = new JButton("Cчитать выбранный вариант");
        JButton calculateButton = new JButton("Произвести статистические расчёты");
        JComboBox<Integer> numberComboBox = new JComboBox<>();
        JButton exportButton = new JButton("Экспортировать результаты");
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
                Mean.calculateMean();
                StandardDeviation.calculateStandardDeviation();
                Range.calculateRange();
                QuantityElem.calculateRange();
                CoefficientVariation.calculateRange();
                Variance.calculateVariance();
                Minimum.calculateMinimum();
                Maximum.calculateMaximum();
                ConfidenceInterval.calculateInterval();
                JOptionPane.showMessageDialog(frame, "Расчёты готовы", "Статус", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExcelWriter.writeExcelData("OutputStatistics");
            }

        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(numberComboBox);
        panel.add(chooseButton);
        panel.add(readButton);
        panel.add(calculateButton);
        panel.add(exportButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
