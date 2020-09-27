package com.bhanu.java;

public class ElevatorController implements IElevatorController, ElevatorEventListener {
    @Override
    public void onStopped(Object sender) {
        
    }

    @Override
    public void status() {

    }

    @Override
    public Elevator getElevator(int elevatorId) {
        return null;
    }

    @Override
    public void update(int elevatorId, int floor) {

    }

    @Override
    public void pickUp(int floor, boolean direction) {

    }

    @Override
    public void reset(int elevatorId, int floor) {

    }

    @Override
    public void step() {

    }
}
