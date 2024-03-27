// History.java
package weather;

import java.util.ArrayList;
import java.util.List;

public class HistoryModel {
    private List<String> history;

    public HistoryModel() {
        this.history = new ArrayList<>();
    }

    public void addSearch(String search) {
        this.history.add(search);
    }

    public List<String> getHistory() {
        return history;
    }
}