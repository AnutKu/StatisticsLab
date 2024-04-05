import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String args[]){
        MainFrame mainFrame = new MainFrame();
        mainFrame.showFrame();
        List<Double> listX = ExcelReader.getColumnX();
        System.out.println(listX);

}}