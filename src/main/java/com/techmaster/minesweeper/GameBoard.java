package com.techmaster.minesweeper;

import com.techmaster.minesweeper.listener.CellClickListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private int hiddenMines;

    private boolean terminated = false;

    Label gLabel;

    public GameBoard(int nRow, int nCol, float mineProb) {
        mineMap = new MineMap(nRow, nCol, mineProb);
        hiddenMines = mineMap.getNbOfMines();

        grid = new GridPane();
        loadedRes = new HashMap<>();

        initGrid(nRow, nCol);

        gLabel = new Label();
    }

    private void initGrid(int nRow, int nCol) {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++) {

                ImageView imv = new ImageView(loadImage("blank.png"));
                Button b = new Button("", imv);
                b.setPadding(new Insets(0, 0, 0, 0));
                b.setOnMouseClicked(new CellClickListener(mineMap.getCell(i, j), imv, this));

                GridPane.setRowIndex(b, i);
                GridPane.setColumnIndex(b, j);

                grid.getChildren().add(b);
            }
    }

    public Image loadImage(String resName){
        Image res = loadedRes.get(resName);
        if (res == null) {
            res = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resName));
            loadedRes.put(resName, res);
        }

        return res;
    }

    public int getHiddenMines(){
        return hiddenMines;
    }

    public void setHiddenMines(int _hiddenMines){
        hiddenMines = _hiddenMines;
    }

    public void gameOver(){
        terminated = true;
        gLabel.setText("GAME OVER");
    }

    public void winGame(){
        terminated = true;
        gLabel.setText("YOU WIN!");
    }
}
