
import  calculatestatistics.*;
import calculator.Calculator;
import read.ExcelReader;
import write.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MainFrame {
    private static File selectedFile;

    public static void showFrame() throws IOException {
        JFrame frame = new JFrame();
        JLabel label = new JLabel("Имя листа:");
        JComboBox sheetNames = new JComboBox<>();
        sheetNames.setPreferredSize(new Dimension(200, 25));
        JButton chooseButton = new JButton("Выбрать файл");
        JComboBox chooseSampleComboBox = new JComboBox<>();
        JButton readButton = new JButton("Cчитать выбранный лист");
        JButton calculateButton = new JButton("Произвести статистические расчёты");
        JButton exportButton = new JButton("Экспортировать результаты");
        JButton exitButton = new JButton("Выйти");
        Calculator calculator = new Calculator();

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = null;
                try {
                    fileChooser = new JFileChooser(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile());
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    List<String> names = null;
                    try {
                        names = ExcelReader.getSheetNames(selectedFile.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    for (String name : names) sheetNames.addItem(name);
                }
            }
        });
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = (String) sheetNames.getSelectedItem();
                try {
                    if (selectedFile == null) {
                        throw new IllegalArgumentException("Файл не выбран.");
                    }
                    calculator.read(selectedFile.getAbsolutePath(), name);
                    JOptionPane.showMessageDialog(frame, "Вариант считан", "Статус", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.calculateAll();
                calculator.getAllResults();
                JOptionPane.showMessageDialog(frame, "Расчёты готовы", "Статус", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    calculator.write();
                    JOptionPane.showMessageDialog(frame, "Файл OutputStatistics.xlsx экспортирован", "Статус", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }

        });



        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Вы уверены, что хотите выйти?", "Подтверждение выхода", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(sheetNames);
        panel.add(chooseButton);
        panel.add(readButton);
        panel.add(calculateButton);
        panel.add(exportButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
