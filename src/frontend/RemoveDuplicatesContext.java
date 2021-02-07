package frontend;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesContext {
    RemoveDuplicatesContext prevContext = null;
    static Map<String, Integer> identCounter = new HashMap<>();
    Map<String, String> newNames = new HashMap<>();

    public RemoveDuplicatesContext() {
        newNames.put("printInt" ,"printInt");
        newNames.put("printString" ,"printString");
        newNames.put("readString" ,"readString");
        newNames.put("readInt" ,"readInt");
        newNames.put("error" ,"error");
    }

    public void createNewVar(String name) {
        if (name.equals("this"))
            return;
        int count = identCounter.getOrDefault(name, 0);
        identCounter.put(name, count + 1);
        newNames.put(name, name + "_" + count);
    }

    public String getName(String name) {
        if (name.equals("this"))
            return name;
        if (newNames.containsKey(name)) {
            return newNames.get(name);
        } else {
            return prevContext.getName(name);
        }
    }

    public RemoveDuplicatesContext newScope() {
        RemoveDuplicatesContext removeDuplicatesContext = new RemoveDuplicatesContext();
        removeDuplicatesContext.prevContext = this;
        return removeDuplicatesContext;
    }

}
