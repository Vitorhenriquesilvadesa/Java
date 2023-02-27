package ContaCorrenteRefeita;

import javax.swing.JOptionPane;

public class DataStream {

    public DataStream() {
    }

    public static void log(String x) {
        JOptionPane.showMessageDialog(null, x);
    }

    public static int requestNumber(String placeholder) {

        int number;
        try {
            number = Integer.parseInt(JOptionPane.showInputDialog(null, placeholder));
            return number;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String requestString(String placeholder) {

        String string;
        try {
            string = JOptionPane.showInputDialog(null, placeholder);
            return string;
        } catch (Exception e) {
            return null;
        }
    }
}
