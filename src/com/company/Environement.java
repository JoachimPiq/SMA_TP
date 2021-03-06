package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Environement {
    public List<Stack<Agent>> table;
    public int nbBloc;
    public int nbIteration;

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

    public int getPileAgent(Agent agent){
        for (int i =0; i<3; i++) if (table.get(i).contains(agent)) return i;
        return -1;

    }


    public List<Stack<Agent>> getTable() {
        return table;
    }

    public boolean succes(){
        for (Stack pile : table){
            if (pile.size()==nbBloc){
                for (int i =0;i<nbBloc-1;i++) {
                    if (((Agent)pile.get(i)).getAgentSuivant().value!=((Agent)pile.get(i)).value+1 ){
                        return false;
                    }
                }
                System.out.println("Nombre de mouvement pour arriver au succes : "+nbIteration);
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

    public void incMouvementCount(){nbIteration++;}

    @Override
    public String toString() {
        String res = "Environement ("+nbIteration+" it??ration):\n";
        for (Stack pile : table){
            for (Object agent : pile){
                res += agent.toString()+" ";
            }
            res +="\n";
        }
        return res;
    }
}
