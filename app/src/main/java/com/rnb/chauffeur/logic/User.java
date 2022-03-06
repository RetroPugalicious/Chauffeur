package com.rnb.chauffeur.logic;

public class User {

    private int index;
    private final String roomNumber;

    public User(int index, String roomNumber){
        this.index = index;
        this.roomNumber = roomNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

}
