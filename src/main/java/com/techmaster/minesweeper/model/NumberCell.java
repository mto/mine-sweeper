package com.techmaster.minesweeper.model;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/11/17
 */
public class NumberCell extends Cell {

    private int nb;

    public NumberCell(int nb) {
        this.nb = nb;
    }

    public int getNbMines() {
        return nb;
    }

    @Override
    public String toString() {
        return "" + nb;
    }
}
