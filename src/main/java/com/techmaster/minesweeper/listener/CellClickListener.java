package com.techmaster.minesweeper.listener;

import com.techmaster.minesweeper.GameBoard;
import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/19/17
 */
public class CellClickListener implements EventHandler<MouseEvent> {

    private Cell cell;

    private ImageView imv;

    private GameBoard gb;

    public CellClickListener(Cell _cell, ImageView _imv, GameBoard _gb) {
        this.cell = _cell;
        this.imv = _imv;
        this.gb = _gb;
    }

    @Override
    public void handle(MouseEvent e) {
        if (cell.isDiscovered()) {
            System.out.println("Cell discovered, do nothing");
        } else {
            MouseButton mb = e.getButton();
            if (mb == MouseButton.PRIMARY) {
                leftMouseClick();
            } else if (mb == MouseButton.SECONDARY) {
                rightMouseClick();
            } else {
                System.out.println("Clicked on " + mb);
            }
        }
    }

    private void leftMouseClick() {
        String imgRes = "";
        if (cell instanceof MineCell) {
            gb.gameOver();
            cell.discover();
            imgRes = "hitmine.png";
        } else if (cell instanceof EmptyCell) {
            cell.discover();
            imgRes = "exposed.png";
        } else if (cell instanceof NumberCell) {
            cell.discover();

            int nb = ((NumberCell) cell).getNbMines();
            imgRes = "number" + nb + ".png";
        }

        imv.setImage(gb.loadImage(imgRes));
    }

    private void rightMouseClick() {
        int hmNb = gb.getHiddenMines();

        if (cell.flagged()) {
            if (cell instanceof MineCell) {
                gb.setHiddenMines(hmNb + 1);
            }
            imv.setImage(gb.loadImage("blank.png"));
        } else {
            if (cell instanceof MineCell) {
                hmNb--;
                gb.setHiddenMines(hmNb);

                if (hmNb == 0) {
                    gb.winGame();
                }
            }
            imv.setImage(gb.loadImage("flag.png"));
        }

        cell.toogleFlag();
        imv.setImage(gb.loadImage(cell.flagged() ? "flag.png" : "blank.png"));
    }
}
