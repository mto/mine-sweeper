package com.techmaster.minesweeper.model;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/11/17
 */
public class Cell {

    private boolean flagged = false;

    public void toogleFlag(){
        flagged = !flagged;
    }

    public boolean flagged(){
        return flagged;
    }

    @Override
    public String toString() {
        return " ";
    }
}
