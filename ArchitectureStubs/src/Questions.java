import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public abstract class Questions {
    protected HashMap<String, String> questionMap;

    public Questions() {
        questionMap = new HashMap<>();
    }

    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);
    }

    public String getAnswer(String question) {
        return questionMap.get(question);
    }

    // Method to safely retrieve any question
    public String getAnyQuestion() {
        Iterator<String> it = questionMap.keySet().iterator();
        return it.hasNext() ? it.next() : null;
    }
}
