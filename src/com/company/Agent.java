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
        //Si le bloc précédent n'est pas le bon.
        if ((getAgentPrecedent()!= null && value=='A') || (value!='A' && (getAgentPrecedent()==null || getAgentPrecedent().value!=value-1 )))
        {
            return tenterDeplacement(X);
        }
        //Si le bloc suivant n'est pas le bon
        if((value=='C' && getAgentSuivant()!= null) || (value!='C' &&( getAgentSuivant()==null || getAgentSuivant().value!= value+1)))
        {
            return tenterDeplacement(X);
        }
        else
        //Sinon l'objet est au bon endroit et on ne bouge pas
        {
            return false;
        }

    }

    public void pousser(int x){

            if (getAgentSuivant()!=null){
                 getAgentSuivant().pousser(x);
            }
            else {
                moveBlocTo(x);
            }

    }

    public boolean tenterDeplacement(int x){
        // Tente un déplacement, si pas possible parce que agent au dessus, transmet le déplacement et return false
        if (getAgentSuivant()==null) {
            moveBlocTo(x);
            return true;
        }

        else{
            pousser(x);
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
        Random rand = new Random();
        do{
            pileToGo =  rand.nextInt(env.table.size());
        }while(env.table.get(pileToGo).contains(this));
        return seDeplacer(pileToGo);


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
