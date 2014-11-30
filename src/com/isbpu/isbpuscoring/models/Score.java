package com.isbpu.isbpuscoring.models;

/**
 * Created by Patrick Love on 9/25/2014.
 */
public class Score {

    public static final Score NO_SCORE = new Score(-1, -1);
    public static final Score SCORE_SPARE = new Score(10, 1);
    public static final Score SCORE_STRIKE = new Score(10, 2);

    private int value;
    private int extraThrows;

    public Score(int val, int scoreType){
        value = val;
        extraThrows = scoreType;
    }

    public Score(int val){
        value = val;
        extraThrows = 0;
    }

    public void addExtraThrow(Throw t){
        this.value += t.getDownedPins();
        this.extraThrows--;
    }

    public boolean hasExtraThrows(){
        return extraThrows > 0;
    }

    public int getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Score){
            Score score2 = (Score) o;
            return  this.extraThrows == score2.extraThrows &&
                    this.value == score2.value;
        }
        return false;
    }
}
