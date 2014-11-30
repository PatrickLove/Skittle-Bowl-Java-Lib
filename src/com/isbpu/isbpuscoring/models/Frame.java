package com.isbpu.isbpuscoring.models;

/**
 * Created by Patrick Love on 9/25/2014.
 */
public class Frame {

    private Throw t1;
    private Throw t2;
    private Throw t3;
    boolean isTenth;

    public Frame(int number){
        isTenth = number == 10;
    }

    public Frame(Throw th1, Throw th2){
        isTenth = false;
        t1 = th1;
        t2 = th2;
    }

    public Frame(Throw th1, Throw th2, Throw th3){
        isTenth = true;
        t1 = th1;
        t2 = th2;
        t3 = th3;
    }

    /**
     * Adds the given throw to the frame
     * @param t Throw to add to this frame
     * @return Is the frame complete
     */
    public boolean makeThrow(Throw t){
        if(isComplete()){
            return false;
        }
        if(t1 == null){
            t1 = t;
        }
        else if(t2 == null){
            t2 = t;
        }
        else{
            t3 = t;
        }
        return isComplete();
    }

    public void editThrow(Throw t, int tNum){
        switch (tNum){
            case 1:
                t1 = t;
                return;
            case 2:
                t2 = t;
                return;
            case 3:
                t3 = t;
                return;
        }
    }

    public boolean isComplete(){
        if(!isTenth){
            return t1 != null && (t1.isStrike() || t2 != null);
        }
        else{
            return t1 != null && t2 != null && (!t2.isSpare(t1) || t3 != null);
        }
    }

    public Score score(){
        if(isComplete()) {
            if (isTenth) {
                if(t3 == null) {
                    return new Score(t1.getDownedPins() + t2.getDownedPins());
                }
                else{
                    return new Score(t1.getDownedPins() + t2.getDownedPins() + t3.getDownedPins());
                }

            } else {
                if(t1.isStrike()){
                    return Score.SCORE_STRIKE;
                }
                else if(t2.isSpare(t1)){
                    return Score.SCORE_SPARE;
                }
                else{
                    return new Score(t1.getDownedPins() + t2.getDownedPins());
                }
            }
        }
        else{
            return Score.NO_SCORE;
        }
    }

    public Score addToLastFrame(Score lastScore){
        if(lastScore.hasExtraThrows() && t1 != null) {
            lastScore.addExtraThrow(t1);
            if(lastScore.hasExtraThrows() && t2 != null){
                lastScore.addExtraThrow(t2);
            }
        }
        return lastScore;
    }
}
