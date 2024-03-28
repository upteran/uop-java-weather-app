// HistoryModel.java
package weather;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HistoryModel is a class that maintains the search history for the Weather App.
 * It stores each search input along with its timestamp in a LinkedHashMap.
 *
 * The class provides a method to add a new search to the history and a method to retrieve the current history.
 */
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