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



    public boolean moveOrPush(){
        // Tente un déplacement, si pas possible parce que agent au dessus, on indique à l'agent suivant qu'il doit faire de même
        if (getAgentSuivant()==null) {
            moveBlocTo();
            return true;
        }
        else{
            getAgentSuivant().moveOrPush();
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
        //Retourne l'agent suivant
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

    public void moveBlocTo()
    {
        //Déplace le bloc de l'agent jusqu'à un nouvelle position, en essayant les deux autre position,
        //Si jamais il tombe sur la bonne positition (atGoodPlace) il n'essaie pas la seconde.
        int nbPileTry = 0;
        do {

            int pileToGo = (env.getPileAgent(this) + 1) % 3; //On sélection la pile suivante.

            for (Stack pile : env.table) {
                if (pile.size() > 0 && pile.peek() == this) {
                    env.table.get(pileToGo).add((Agent) pile.pop());

                }
            }
            env.incMouvementCount();
            System.out.println(env);

            nbPileTry++;
        }while (nbPileTry<2 && !agentAtGoodPlace());
    }

    public boolean action(){
        //Tant que l'agent n'est pas à la bonne place, et qu'il n'a pas bougé on le fait bougé ou pusher les agent du dessus
        boolean hadMoved=false;
        while (!agentAtGoodPlace() && !hadMoved)
            hadMoved=
                    moveOrPush();

        return false;


    }

    public boolean agentAtGoodPlace(){
        return (((value=='A' && getAgentPrecedent() == null) || (value!='A' && getAgentPrecedent()!= null && getAgentPrecedent().value == value-1))&&((getAgentSuivant()==null)||(value!='E' && getAgentSuivant()!= null && getAgentSuivant().value== value+1)));
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
