package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here


        int total = 0;
        for (int i = 0; i<100; i++) {
            Environement env = new Environement(4);
            System.out.println(env);
            Random rand = new Random();
            List<Character> listAgent = new ArrayList<Character>();
            listAgent.add("A".charAt(0));
            listAgent.add("B".charAt(0));
            listAgent.add("C".charAt(0));
            listAgent.add("D".charAt(0));
            int count = 0;

            while (!env.succes()) {
                int nextAgent = rand.nextInt(4);
                Agent agent = env.getAgent(listAgent.get(nextAgent));
                if (agent.action()) {
                    count++;

                }

            }
            System.out.println("Fini en " + count);
            total+=count;
        }

        System.out.println("Moyenne : "+ total/100);
    }
}
