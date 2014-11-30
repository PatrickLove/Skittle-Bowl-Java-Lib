package com.isbpu.isbpuscoring.models;

/**
 * Created by Patrick Love on 9/25/2014.
 */
public class Throw {

    private int downedPins;

    public Throw(int pins) {
        downedPins = pins;
    }

    public boolean isStrike(){
        return downedPins >= 10;
    }

    public boolean isSpare(Throw t1){
        int totalPins = t1.downedPins + this.downedPins;
        return totalPins >= 10;
    }

    public int getDownedPins(){
        return downedPins;
    }
}
