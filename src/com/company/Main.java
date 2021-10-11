package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Environement env = new Environement(4);
        System.out.println(env);
        Random rand = new Random();
        while (!env.succes()){
            char nextAgent  = ("A" + rand.nextInt(3)).charAt(0);
            System.out.println(nextAgent);
            Agent agent = env.getAgent(nextAgent);
            agent.action();
            System.out.println(env);
        }


    }
}
