package com.isbpu.isbpuscoring.models;

import java.util.ArrayList;

/**
 * Created by Patrick Love on 9/25/2014.
 */
public class Game {

    private Frame[] frames = new Frame[10];
    int currentFrame = 0;

    public Game(){
    }

    public Game(Frame[] frameArr){
        frames = frameArr;
        currentFrame = findCurrentFrame();
    }

    public void nextFrame(){
        if(!isComplete()) {
            currentFrame++;
        }
    }

    public void addThrow(int pins) {
        if(frames[currentFrame].makeThrow(new Throw(pins))){
            nextFrame();
        }
    }

    private int findCurrentFrame() {
        for(int i = 0; i < frames.length; i++){
            if(frames[i] == null){
                return i;
            }
        }
        return 11;
    }

    public boolean isComplete(){
        return currentFrame == 11;
    }

    public int score(){
        int totalScore = 0;
        ArrayList<Score> runningScores = new ArrayList<Score>();
        for(Frame f : frames){
            if(f != null){
                Score currentScore = f.score();
                for(Score oldScore: runningScores){
                    f.addToLastFrame(oldScore);
                    if(!oldScore.hasExtraThrows()){
                        totalScore += oldScore.getValue();
                        runningScores.remove(oldScore);
                    }
                }
                if(!currentScore.equals(Score.NO_SCORE)){
                    if(currentScore.hasExtraThrows()){
                        runningScores.add(currentScore);
                    }
                    else{
                        totalScore += currentScore.getValue();
                    }
                }
            }
        }
        return  totalScore;
    }
}
