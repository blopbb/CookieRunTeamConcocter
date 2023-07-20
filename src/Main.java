import java.util.*;
import java.io.*;

public class Main {
    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        cookieRunSum();
        sc.close();
    }
    //creates a cookie run team with a certain power
    static void cookieRunSum() {
        //read inputs
        System.out.println("Number of Cookies:"); //n cookies
        int n = sc.nextInt();
        int[] powers = new int[n];
        System.out.println("Cookie Powers:"); //reads n integer inputs of cookie powers
        for (int i = 0; i < n; i++) {
            powers[i] = sc.nextInt();
        }
        Arrays.sort(powers);
        System.out.println("Desired Total:");
        int goal = sc.nextInt();

        //stores the possible power sum values
        boolean[] filler = new boolean[2*goal+1];
        //stores which powers were used
        ArrayList<Integer>[] teams = new ArrayList[2*goal+1];

        filler[0]=true;
        teams[0]=new ArrayList<>();

        //fill the filler array
        //add each power to an existing power value only if theres less than 5 cookies in the team already
        for(int power: powers) { //for each power
            for (int i = filler.length-2; i >= 0; i--) {//for each power value i
                if(teams[i]==null || teams[i].size()>=5) { //make sure team is not too big or small
                    continue;
                }
                if(filler[i] && (i+power)<filler.length) { //if the starting power value exists and the total doesnt exceed the array size
                    //if the new team doesnt exist yet, make a new team
                    int powerNew = i+power;
                    if(!filler[powerNew]) {
                        filler[powerNew] = true;
                        teams[powerNew] = new ArrayList<>();
                        teams[powerNew].addAll(teams[i]);
                        teams[powerNew].add(power);
                    }
                }
            }
        }
        //if the goal is met
        if(filler[goal]) {
            System.out.println("Goal Achieved: "+ goal);
            for(int power:teams[goal]) {
                System.out.println(power);
            }
        }
        else {//find the closest to goal
            for (int i = 0; i < goal; i++) {
                if(filler[goal+i]) {
                    System.out.println("Achieved Sum: "+ (goal+i));
                    for(int power:teams[goal+i]) {
                        System.out.println(power);
                    }
                    break;
                } else if(filler[goal-i]) {
                    System.out.println("Achieved Sum: "+ (goal-i));
                    for(int power:teams[goal-i]) {
                        System.out.println(power);
                    }
                    break;
                }
            }
        }


    }
}