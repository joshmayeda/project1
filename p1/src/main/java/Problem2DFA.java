import java.util.ArrayDeque;
/**
 *
 * @author Ian Hoole
 */
public class Problem2DFA{
    
    //This object is simply used to house the DFA's delta function, as well as FindString
    
    public Problem2DFA()
    {
    }
    public String FindString(int k, int[] d){
        //Arrays.sort(d);
        StringBuilder output = new StringBuilder("No solution");
        ArrayDeque<Integer> queue= new ArrayDeque<>();
        int label[] = new int[k];
        int parent[] = new int[k];
        boolean visited[] = new boolean[k];
        int nextState = -1;
        //the main needed objects and variables are initialized, and output is set to No solution by default
        //the arrays are set to k, one for each state in our dfa
        for(int i = 1; i < k; i++){
            visited[i] = false;
        }
        visited[0] = true;
        //the loop sets all the locations in visited to false, and 0 to true
        for(int s : d){
            if(s != 0){
                nextState = Delta(0, s, k);
                visited[nextState] = true;
                queue.add(nextState);
                parent[nextState] = 0;
                label[nextState] = s;
            }
        }
        //the loop above iterates through all the accepted digits and sets them as possible starting states
        //if an accepted digit is 0, it is ignored because every answer would be 0 if it was a possible digit and was accepted
        queueLoop:{
            while(!queue.isEmpty()){
                int current = queue.poll();
                for(int s : d){
                    nextState = Delta(current, s, k);
                    if(nextState == 0){
                        parent[nextState] = current;
                        label[nextState] = s;
                        break queueLoop;
                    }
                    else if(!visited[nextState]){
                        visited[nextState] = true;
                        parent[nextState] = current;
                        label[nextState] = s;
                        queue.add(nextState);
                    }
                }
            }
        }
        //this loop is set to iterate through while the queue is not empty, adding possible digit combinations to the queue
        //this dfa takes the order of d as alphabetical order 
        //for example, if d = {3, 2} and k = 11, then 33 would be the outputted string, as opposed to 22
        //to disable this feature, uncomment the first line of this method
        if(nextState == 0){
            output = new StringBuilder();
            output.append(label[0]);
            int pathfinder = parent[0];
            int adder = 0;
            while(pathfinder != 0)
            {
                output.append(label[pathfinder]);
                pathfinder = parent[pathfinder];
            }
            output.reverse();
        }
        //this if statement checks that a solution has been found, and then creates a string tracing back up using parent
        //to find the next value, and label to figure out the digit to place
        return output.toString();
    }
    private int Delta(int j, int a, int k){
        return (10 * j + a) % k;
        //this delta function just serves to make the code a bit more readable
    }
    
}


