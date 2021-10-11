package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Environement env = new Environement(4);
        System.out.println(env);
        Random rand = new Random();
        int count = 0;
        while (!env.succes()){
            count ++;
            List<Character> listAgent = new ArrayList<Character>();
            listAgent.add("A".charAt(0));
            listAgent.add("B".charAt(0));
            listAgent.add("C".charAt(0));
            listAgent.add("D".charAt(0));

            int nextAgent  = rand.nextInt(4);

            System.out.println(nextAgent);
            Agent agent = env.getAgent(listAgent.get(nextAgent));
            agent.action();
            System.out.println(env);

        }
        System.out.println("Fini en "+count);


    }
}
