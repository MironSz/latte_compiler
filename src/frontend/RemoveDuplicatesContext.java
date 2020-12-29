package frontend;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesContext {
    RemoveDuplicatesContext prevContext=null;
    static Map<String,Integer> identCounter = new HashMap<>();
    Map<String,String> newNames = new HashMap<>();

    public void createNewVar(String name){
        int count = identCounter.getOrDefault(name,0);
        identCounter.put(name,count+1);
        newNames.put(name,name+"_"+count);
    }
    public String getName(String name){
        if(newNames.containsKey(name)){
            return newNames.get(name);
        } else{
            return prevContext.getName(name);
        }
    }
    public RemoveDuplicatesContext  newScope(){
        RemoveDuplicatesContext removeDuplicatesContext = new RemoveDuplicatesContext();
        removeDuplicatesContext.prevContext=this;
        return removeDuplicatesContext;
    }

}
