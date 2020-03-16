package com.w2w.model;

public enum Gender {
    Female(1),
    Male(2);

    private int numvale;

    Gender(int value){
        numvale=value;
    }

    public int getNumvale(){
        return  numvale;
    }
}
