package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Environement {
    public List<Stack<Agent>> table;
    public int nbBloc;

    public Environement (int nbBloc)
    {
        this.nbBloc = nbBloc;
        List<Agent> agents = new ArrayList<Agent>();
        agents.add(new Agent('A',this));
        char currentChar = 'B';
        for (int i =1;i<nbBloc;i++) {
            agents.add(new Agent(currentChar,this));
            currentChar++;
        }

        table = new ArrayList<Stack<Agent>>();
        Stack<Agent> stack = new Stack();
        Random rand = new Random();
        List<Integer> alreadyPut = new ArrayList();
        for (int i =0;i<nbBloc;i++){
            int rdmI =-1;
            do {
                rdmI = rand.nextInt(agents.size());
            }while (alreadyPut.contains(rdmI));
            stack.add(agents.get(rdmI));
            alreadyPut.add(rdmI);
        }
        table.add(stack);
        table.add(new Stack());
        table.add(new Stack());

    }




    public List<Stack<Agent>> getTable() {
        return table;
    }

    public boolean succes(){
        for (Stack pile : table){
            if (pile.size()==nbBloc){
                for (int i =1;i<nbBloc-1;i++) {
                    if (((Agent)pile.get(i)).getAgentSuivant().value!=((Agent)pile.get(i)).value+1 ){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Agent getAgent(char A){
        for (Stack pile : table){
            for (Object agent : pile)
            {
                if (((Agent) agent).getValue() == A){
                    return (Agent) agent;
                }
            }

        }
        return null;
    }


    @Override
    public String toString() {
        return "Environement{" +
                "table=" + table +
                '}';
    }
}
