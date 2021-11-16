package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {


    public static void main(String[] args) throws InterruptedException {
       simpleExecution();
    }

    public static void simpleExecution() throws InterruptedException {
        Environement env = new Environement(4);
        Random rand = new Random();

        List<Character> listAgent = new ArrayList<Character>();
        listAgent.add('A');
        listAgent.add('B');
        listAgent.add('C');
        listAgent.add('D');

        int precRandAgent =-1;
        while (!env.succes()){
            int nextRandAgent;
            do nextRandAgent = rand.nextInt(4);
            while (nextRandAgent==precRandAgent);
            env.getAgent(listAgent.get(nextRandAgent)).action();
            precRandAgent = nextRandAgent;

        }

        System.out.println(env);

    }
    public static void moyenneSurX(int x){
        int total = 0;
        for (int i = 0; i<x; i++) {
            Environement env = new Environement(4);
            System.out.println(env);
            Random rand = new Random();
            List<Character> listAgent = new ArrayList<Character>();
            listAgent.add("A".charAt(0));
            listAgent.add("B".charAt(0));
            listAgent.add("C".charAt(0));
            listAgent.add("D".charAt(0));
            int precAgent = -1;


            while (!env.succes()) {
                int nextAgent;
                do {
                    nextAgent = rand.nextInt(4);
                } while (nextAgent == precAgent);
                Agent agent = env.getAgent(listAgent.get(nextAgent));
                agent.action();
                precAgent = nextAgent;

            }
            total+=env.nbIteration;
        }
        System.out.println("Nombre moyen d'itération sur "+x+" execution : " + (total/x));
    }

}
