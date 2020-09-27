package com.bhanu.java;

public class Elevator {
    public enum DIRECTION {
        NONE, UP, DOWN
    }

    private DIRECTION direction = DIRECTION.NONE;
    private boolean move = false;

    private boolean[] floors;
    private int countUp = 0;
    private int countDown = 0;
    private int currentFloor = 0;
    private int min = Constants.MIN_FLOORS;
    private int max = Constants.MAX_FLOORS;
    private int numFloors;

    private ElevatorEventListener elevatorEventListener;

    public Elevator(int numFloors){
        if (numFloors < 0) throw new IllegalArgumentException("numFloors should be greater than 0");
        this.numFloors = numFloors;
        floors = new boolean[numFloors];
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public int getGoalFloor(){
        if (direction == DIRECTION.UP) return max;
        if (direction == DIRECTION.DOWN) return min;
        return -1;
    }

    public void moveNext() {
        if (!move){
            move = (direction != DIRECTION.NONE);
            return;
        }

        if (direction == DIRECTION.UP){
            if (floors[++currentFloor]){
                floors[currentFloor] = false;
                if (--countUp == 0){
                    direction = (countDown == 0) ? DIRECTION.NONE : DIRECTION.DOWN;
                    max = Constants.MAX_FLOORS;
                }
                move = false;
                if (elevatorEventListener != null) elevatorEventListener.onStopped(this);
            }
        }

        if (direction == DIRECTION.DOWN){
            if (floors[--currentFloor]){
                floors[currentFloor] = false;
                if (++countDown == 0){
                    direction = (countUp == 0) ? DIRECTION.NONE : DIRECTION.UP;
                    min = Constants.MIN_FLOORS;
                }
                move = false;
                if (elevatorEventListener != null) elevatorEventListener.onStopped(this);
            }
        }
    }

    public void setGoalFloor(int floor){
        if (floor < 0 || (floor >= numFloors)) throw new IllegalArgumentException("Invalid floor number");
        if (currentFloor == floor) return;
        if (floors[floor]) return;
        floors[floor] = true;
        if (floor > currentFloor) { countUp++; max = Math.max(floor, max);}
        if (floor < currentFloor) { countDown--; min = Math.min(floor, min); }
        if (direction == DIRECTION.NONE){
            direction = (floor > currentFloor) ? DIRECTION.UP : DIRECTION.DOWN;
        }
    }

    public void reset(){
        currentFloor = countUp = countDown = 0;
        move = false;
        direction = DIRECTION.NONE;
        floors = new boolean[numFloors];
        max = Constants.MAX_FLOORS;
        min = Constants.MIN_FLOORS;
    }

    public void moveToFloor(int floor){
        if (floor < 0 || floor > numFloors) throw new IllegalArgumentException("Invalid floor number");
        reset();
        currentFloor = floor;
    }
    public boolean getMove(){
        return move;
    }
    public DIRECTION getDirection(){
        return direction;
    }
    public void setElevatorEventListener(ElevatorEventListener elevatorEventListener){
        this.elevatorEventListener = elevatorEventListener;
    }
}
