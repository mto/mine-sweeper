package com.techmaster.minesweeper;

import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;

import java.util.Random;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/11/17
 */
public class MineMap {

    private Cell[][] cells;

    private int nRow;

    private int nCol;

    private float mineProb;

    private int mineNb;

    public MineMap(int nRow, int nCol, float mineProb) {
        this.nRow = nRow;
        this.nCol = nCol;
        this.mineProb = mineProb;

        cells = new Cell[nRow][nCol];

        setMines();
        initCells();
    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }

    public int getNbOfMines(){
        return mineNb;
    }

    private void setMines() {
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (new Random().nextFloat() < mineProb) {
                    cells[i][j] = new MineCell();
                    mineNb++;
                }
            }
        }
    }

    private void initCells() {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++) {
                initCell(i, j);
            }
    }

    private void initCell(int rowIdx, int colIdx) {
        int minesCount = 0;

        for (int i = Math.max(0, rowIdx - 1); i <= Math.min(nRow - 1, rowIdx + 1); i++)
            for (int j = Math.max(0, colIdx - 1); j <= Math.min(nCol - 1, colIdx + 1); j++) {
                if (i != rowIdx || j != colIdx) {
                    minesCount += (cells[i][j] instanceof MineCell) ? 1 : 0;
                }
            }
        if (cells[rowIdx][colIdx] instanceof MineCell) {
            return;
        }

        if (minesCount == 0) {
            cells[rowIdx][colIdx] = new EmptyCell();
        } else {
            cells[rowIdx][colIdx] = new NumberCell(minesCount);
        }
    }

    private void display() {
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print("|" + cells[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MineMap bg = new MineMap(20, 30, 0.15f);
        System.out.println();
        bg.display();
    }
}
