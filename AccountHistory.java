package ContaCorrenteRefeita;

import java.util.ArrayList;
import java.util.Date;

public class AccountHistory {

    public AccountHistory() {
    }

    ArrayList<String> history = new ArrayList<String>();

    void addItem(String type, String data) {
        Date date = new Date();

        history.add(type + " : " + data + " " + date);
    }

    void getHistory() {
        String historyValue = "";

        for (int i = history.size() - 1; i >= 0; i--) {
            historyValue += history.get(i);
            historyValue += "\n";
        }
        DataStream.log(historyValue);
    }
}
