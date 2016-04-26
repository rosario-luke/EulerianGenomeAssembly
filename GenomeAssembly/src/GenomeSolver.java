import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucasrosario on 4/7/16.
 */
public class GenomeSolver {

    private HashMap<String, List<String>> nodeMap;
    private HashMap<String, Counter> degreeIn;
    private HashMap<String, Counter> degreeOut;
    private boolean foundEnd;
    private boolean foundStart;
    private List<String> allNodes;
    private String[] lmers;

    public GenomeSolver(String[] lmers){
        this.lmers = lmers;
        nodeMap = new HashMap<String, List<String>>();
        degreeIn = new HashMap<String, Counter>();
        degreeOut = new HashMap<String, Counter>();
        allNodes = new LinkedList<String>();
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
            if (!allNodes.contains(front)){
                allNodes.add(front);
            }
            if (!allNodes.contains(back)){
                allNodes.add(back);
            }
            if (!degreeOut.containsKey(front)){
                degreeOut.put(front, new Counter());
            }
            if (!degreeIn.containsKey(back)){
                degreeIn.put(back, new Counter());
            }
            nodeMap.get(front).add(back);
            degreeOut.get(front).increment();
            degreeIn.get(back).increment();
        }
    }

    public String solve(){
        if (!canSolve()){
            return null;
        }

        LinkedList<String> queue = new LinkedList<String>();
        String current = findStart();
        queue.add(current);

        queue = findPath(queue, current);
        int pos = findEmptyPos(queue);
        while (edgesLeft() && pos != -1){
            LinkedList<String> mid = findPath(new LinkedList<String>(), queue.get(pos));
            LinkedList<String> nQueue = new LinkedList<String>();
            for (int i = 0; i < pos; i ++){
                nQueue.add(queue.get(i));
            }
            for (int i = 0; i < mid.size(); i ++){
                nQueue.add(mid.get(i));
            }
            for (int i = pos +1; i < queue.size(); i ++){
                nQueue.add(queue.get(i));
            }
            queue = nQueue;
            pos = findEmptyPos(queue);
        }
        return createStringFromPath(queue);
    }


    public LinkedList<String> findPath(LinkedList<String> path, String start){
        while (!nodeMap.get(start).isEmpty()){
            String newEdge = nodeMap.get(start).get(0);
            nodeMap.get(start).remove(newEdge);
            path.add(start);
            start = newEdge;
        }
        path.add(start);
        return path;
    }


    public boolean canSolve(){
        for (String node : allNodes){
            int in = degreeIn.containsKey(node) ? degreeIn.get(node).getCount() : 0;
            int out = degreeOut.containsKey(node) ? degreeOut.get(node).getCount() : 0;
            if (in != out){
                if (out == (in + 1) && !foundEnd){
                    foundEnd = true;
                } else if ((out + 1) == in && !foundStart){
                    foundStart = true;
                } else {
                    System.out.println("Found imbalanced node");
                    return false;
                }
            }
        }
        /*if (foundStart && foundEnd){
            System.out.println("Can solve");
            return true;
        }*/
        /*System.out.println("Couldn't find start and end");
        return false;*/
        return true;
    }


    public String findStart(){
        if (!foundStart){
            return allNodes.get(0);
        }
        for (String node : allNodes){
            int in = degreeIn.containsKey(node) ? degreeIn.get(node).getCount() : 0;
            int out = degreeOut.containsKey(node) ? degreeOut.get(node).getCount() : 0;
            if (out == (in + 1)){
                return node;
            }
        }
        return null;
    }


    public String findEnd(){
        for (String node : allNodes){
            int in = degreeIn.containsKey(node) ? degreeIn.get(node).getCount() : 0;
            int out = degreeOut.containsKey(node) ? degreeOut.get(node).getCount() : 0;
            if ((out + 1) == in){
                return node;
            }
        }
        return null;
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

    private boolean edgesLeft(){
        for (String node : nodeMap.keySet()){
            if (!nodeMap.get(node).isEmpty()){
                return true;
            }
        }
        return false;
    }


    private String createStringFromPath(LinkedList<String> path){
        StringBuilder sb = new StringBuilder("");
        sb.append(path.get(0).substring(0, path.get(0).length()-1));
        for (int i = 1; i < path.size(); i ++){
            sb.append(path.get(i).charAt(path.get(i).length()-1));
        }

        return sb.toString();
    }


    public class Counter{
        private Integer count;
        public Counter(){
            count = 0;
        }

        public int getCount(){
            return count;
        }

        public void increment(){
            count ++;
        }
    }

}
