package com.company;

import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class Agent {
    public char value;
    public Environement env;

    public Agent(char value,Environement env){
        this.value=value;
        this.env = env;
    }

    public boolean seDeplacer(int X){
       // Si le bloc suivant n'est pas le bon, ou que le bloc précédent n'est pas le bon, et que l'on peut bouger
        if ((getAgentPrecedent() == null || getAgentPrecedent().value!=value-1)){
            moveBlocTo(X);
            return true;
        }
        else if ((getAgentSuivant() ==null || getAgentSuivant().value==value+1)
                && (getAgentPrecedent().value==value-1 || getAgentPrecedent() == null))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void pousser(){

            if (getAgentSuivant()!=null){
                 getAgentSuivant().pousser();
            }
            else {
                Random rand = new Random();
                int pileToGo = rand.nextInt(env.table.size());
                do{
                    pileToGo =  rand.nextInt(env.table.size());
                }while(env.table.get(pileToGo).size() != 0 && env.table.get(pileToGo).peek()!=this);
                moveBlocTo(pileToGo);
            }

    }


    public Agent getAgentPrecedent(){
        for (Stack pile : env.getTable()){
            if (pile.indexOf(this)!=-1){
                try{
                    Agent agentPrec = (Agent) pile.get(pile.indexOf(this)-1);
                    return agentPrec;
                }
                catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public Agent getAgentSuivant(){
        for (Stack pile : env.getTable()){
            if (pile.indexOf(this)!=-1){
                try{
                    Agent agentSuivant = (Agent) pile.get(pile.indexOf(this)+1);
                    return agentSuivant;

                }catch (IndexOutOfBoundsException e)
                {
                    return null;
                }
            }
        }
        return null;
    }



    public void moveBlocTo( int x)
    {
        for (Stack pile : env.table){
            if (pile.size()>0 && pile.peek()==this){
                env.table.get(x).add((Agent)pile.pop());
            }
        }
    }

    public void action(){
        Random rand = new Random();
        if (getAgentSuivant() ==null)
            seDeplacer(rand.nextInt(3));
        else
            pousser();



    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object a){

        if (a instanceof Agent && this.value==((Agent) a).getValue())
            return true;
        else
            return false;
    }
}
