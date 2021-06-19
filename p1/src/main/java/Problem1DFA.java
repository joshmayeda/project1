import java.math.BigInteger;
import java.lang.Math;
/**
 *
 * @authors Ian Hoole & Josh Mayeda
 */
public class Problem1DFA {
    
    
    final int numOfStates = 1024;
    int deltaFunc[][];
    BigInteger currentVals[];
    BigInteger nextVals[];
    
    public Problem1DFA(){
        currentVals = new BigInteger[numOfStates];
        deltaFunc = new int[numOfStates][];
        for(int i = 0; i < numOfStates; i++){
            currentVals[i] = InitializeDFAVal(i);
        }
    }
    public BigInteger count(int n){
        if(n <= 5){
            int a = (int) (Math.pow(4, n));
            return new BigInteger(a + "");
        }
        n -=5;
        for(int i = 0; i < n; i++){
            nextVals = new BigInteger[numOfStates];    
            for(int j = 0; j < numOfStates;j++){
                nextVals[j] = BigInteger.ZERO;
                for(int k = 0; k < deltaFunc[j].length; k++){
                    nextVals[j] = new BigInteger(nextVals[j].add(currentVals[deltaFunc[j][k]]).toByteArray());
                }
            }
            for(int k = 0; k < numOfStates; k++){
                currentVals[k] = new BigInteger(nextVals[k].toByteArray());
            }
        }
        BigInteger output = BigInteger.ZERO;
        for(BigInteger value: currentVals){
            output = new BigInteger(output.add(value).toByteArray());
        }
        return output;
    }
    private boolean isAcceptState(String state){
        if(state.contains("a")){
            if(state.contains("b")){
                if(state.contains("c")){
                    if(state.contains("d")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private BigInteger InitializeDFAVal(int state){
        boolean hasA = false;
        boolean hasB = false;
        boolean hasC = false;
        boolean hasD = false;
        int counter = 0;
        if(isAcceptState(Encode(state) + "a")){
            hasA = true;
            counter++;
        }
        if(isAcceptState(Encode(state) + "b")){
            hasB = true;
            counter++;
        }
        if(isAcceptState(Encode(state) + "c")){
            hasC = true;
            counter++;
        }
        if(isAcceptState(Encode(state) + "d")){
            hasD = true;
            counter++;
        }
        deltaFunc[state] = new int[counter];
        int newState = state <<2;
        newState = newState % numOfStates;
        for(int i = 0; i < counter; i++){
            if(hasA){
                deltaFunc[state][i] = newState;
                hasA = false;
            }else if(hasB){
                deltaFunc[state][i] = newState + 1;
                hasB = false;
            }else if(hasC){
                deltaFunc[state][i] = newState + 2;
                hasC = false;
            }else if(hasD){
                deltaFunc[state][i] = newState + 3;
            }
        }
        return BigInteger.ONE;
    }
    private String Encode(int state){
        StringBuilder stateAsString = new StringBuilder("");
        for(int i = 0; i < 5; i++){
            if((state&1) == 0){
                state >>= 1;
                if((state&1) == 0){
                    stateAsString.append("a");
                }
                else{
                    stateAsString.append("c");
                }
            }
            else{
                state >>= 1;
                if((state&1) == 0){
                    stateAsString.append("b");
                }
                else{
                    stateAsString.append("d");
                }
            }
            
            state >>= 1;
        }
        return stateAsString.reverse().toString();
    }
    private int Decode(String stateAsString){
        int state = 0;
        for(int i = 0; i < stateAsString.length(); i++){
            state <<=2;
            state += (int) (stateAsString.charAt(i) - 97);
        }
        return state;
    }
}
