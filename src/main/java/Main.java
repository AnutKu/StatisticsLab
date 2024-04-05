import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String args[]){
        MainFrame mainFrame = new MainFrame();
        mainFrame.showFrame();
        try {
            Thread.sleep(20000); // Приостановить выполнение на 2000 миллисекунд (2 секунды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Double> columnX = ExcelReader.getColumnX();
        System.out.println(columnX);
        List<Double> columnY = ExcelReader.getColumnY();
        System.out.println(columnY);
        List<Double> columnZ = ExcelReader.getColumnZ();
        System.out.println(columnZ);

}}