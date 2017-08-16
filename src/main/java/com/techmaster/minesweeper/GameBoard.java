package com.techmaster.minesweeper;

import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/12/17
 */
public class GameBoard {

    private final MineMap mineMap;

    GridPane grid;

    private Map<String, Image> loadedRes;

    public GameBoard(int nRow, int nCol, float mineProb) {
        mineMap = new MineMap(nRow, nCol, mineProb);
        grid = new GridPane();
        loadedRes = new HashMap<>();

        initGrid(nRow, nCol);
    }

    private void initGrid(int nRow, int nCol) {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++) {

                Image res = getImage(mineMap.getCell(i, j));
                ImageView imv = new ImageView(res);
                Button b = new Button("", imv);
                b.setPadding(new Insets(0, 0, 0, 0));

                GridPane.setRowIndex(b, i);
                GridPane.setColumnIndex(b, j);

                grid.getChildren().add(b);
            }
    }

    private Image getImage(Cell c) {
        String resName = "blank.png";

        if (c instanceof MineCell) {
            resName = "flag.png";
        } else if (c instanceof EmptyCell) {
            resName = "blank.png";
        } else if (c instanceof NumberCell) {
            int nbMines = ((NumberCell) c).getNbMines();
            resName = "number" + nbMines + ".png";
        }

        Image res = loadedRes.get(resName);
        if (res == null) {
            res = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resName));
            loadedRes.put(resName, res);
        }

        return res;
    }
}
