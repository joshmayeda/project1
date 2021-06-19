/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Ian Hoole & Josh Mayeda
 */
class Project1{
    
    public static void main(String[] args){
        
        
        Scanner console = new Scanner(System.in);
        String input = "";
        System.out.println("Created by Ian Hoole & Josh Mayeda");
        while (true){
            System.out.println("Would you like Problem 1, 2 or to quit");
            input = console.nextLine();
            if (input.contains("1")) {
                System.out.println("what length of strings would you like?");
                int n = console.nextInt();
                System.out.println(count(n).toString());
            } else if (input.contains("2")) {
                System.out.println("what would you like k to be?");
                int k = console.nextInt();
                System.out.println("Enter permitted digits");
                input = console.nextLine();
                while(input.isEmpty()){
                    input = console.nextLine();
                }
                String subStrings[] = input.trim().split(" ");
                int acceptedDigits[] = new int[subStrings.length];
                for(int i =0; i < subStrings.length; i++){
                    acceptedDigits[i] = Integer.parseInt(subStrings[i]);
                }
                System.out.println(smallestMultiple(k, acceptedDigits));
            } else if (input.contains("quit")) {
                break;
            }
        }
        System.out.print("Goodbye");
        console.close();
    }
    public static String smallestMultiple(int k, int[] arr){
        Problem2DFA M = new Problem2DFA();
        return M.FindString(k, arr);
    }
    public static BigInteger count(int n){
        Problem1DFA M = new Problem1DFA();
        return M.count(n);
    }
}
