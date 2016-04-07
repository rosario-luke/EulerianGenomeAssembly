import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucasrosario on 4/7/16.
 */
public class GenomeSolver {

    private HashMap<String, List<String>> nodeMap;
    private String[] lmers;

    public GenomeSolver(String[] lmers){
        this.lmers = lmers;
        nodeMap = new HashMap<String, List<String>>();
        int len = lmers[0].length();
        for (int i = 0; i < lmers.length; i ++){
            String front = lmers[i].substring(0, len - 1);
            String back = lmers[i].substring(1);
            if (!nodeMap.containsKey(front)){
                nodeMap.put(front, new ArrayList<String>());
            }
            if (!nodeMap.containsKey(back)){
                nodeMap.put(back, new ArrayList<String>());
            }
            nodeMap.get(front).add(back);
        }
        System.out.println(nodeMap);
    }

    public String solve(){
        LinkedList<String> queue = new LinkedList<String>();
        String current = nodeMap.keySet().iterator().next();
        queue.add(current);
    }


    // Find node in the queue with unvisited edges
    private int findEmptyPos(LinkedList<String> queue){
        for (int i = 0; i < queue.size(); i ++){
            if (!nodeMap.get(queue.get(i)).isEmpty()){
                return i;
            }
        }
        return -1;
    }

}
