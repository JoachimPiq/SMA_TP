package com.company;

import java.util.Random;
import java.util.Stack;

public class Agent {
    public char value;
    public Environement env;

    public Agent(char value,Environement env){
        this.value=value;
        this.env = env;
    }



    public boolean moveOrPush(int x){
        // Tente un déplacement, si pas possible parce que agent au dessus, transmet le déplacement et return false
        if (getAgentSuivant()==null) {
            moveBlocTo(x);
            return true;
        }

        else{
            getAgentSuivant().moveOrPush(x);
            return false;

        }
    }

    public Agent getAgentPrecedent(){
        //Retourne l'agent qui est en dessous de l'agent
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

    public boolean moveBlocTo( int x)
    {
        for (Stack pile : env.table){
            if (pile.size()>0 && pile.peek()==this){
                env.table.get(x).add((Agent)pile.pop());
                return true;
            }
        }

        System.out.println("Pas normal");
        return false;
    }

    public boolean action(){
        int pileToGo;
        //On définit une pile ou aller pour l'agentn, qui ne soit pas celle sur lequel il est déjà.
        Random rand = new Random();
        do{
            pileToGo =  rand.nextInt(env.table.size());
        }while(env.table.get(pileToGo).contains(this));

        //Si la piece n'est pas au bonne endroit cad
        //(La bloc est A est n'as pas de bloc en dessous
        //OU le bloc n'est pas A et le bloc en dessous de lui est pas null et c'est la lettre précédente)
        //ET
        //(Le bloc est E est n'a pas de bloc au dessus
        //OU le bloc n'est pas E et le bloc au dessus est pas null et c'est la lettre suivante')
        if(!(((value=='A' && getAgentPrecedent() == null) || (value!='A' && getAgentPrecedent()!= null && getAgentPrecedent().value == value-1))&&((value=='E' && getAgentSuivant()==null)||(value!='E' && getAgentSuivant()!= null && getAgentSuivant().value== value+1))))
        {
            moveOrPush(pileToGo);
            return true;
        }

        return false;


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
