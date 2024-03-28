// HistoryModel.java
package weather;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistoryModel {
    private Map<String, LocalDateTime> history;

    public HistoryModel() {
        this.history = new LinkedHashMap<>();
    }

    public void addSearch(String search) {
        this.history.put(search, LocalDateTime.now());
    }

    public Map<String, LocalDateTime> getHistory() {
        return history;
    }
}