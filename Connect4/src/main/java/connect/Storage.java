package connect;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Az osztály tartalmazza a játék adatait
 */
public class Storage {
    /**
     * Az aktuális játékos az első játékos szerint.
     */
    private boolean player;
    /**
     * A képernyő szélessége.
     */
    private float width;
    /**
     * A képernyő magassága.
     */
    private float height;
    /**
     * A képernyő szélének vastagsága.
     */
    private float border;
    /**
     * A képernyő tetején kihagyott hely.
     */
    private float top;
    /**
     * A fő játékban szereplő körök színei.
     */
    private Color[][] CirclesColor;
    /**
     * A főmenü színe.
     */
    private Scene menuScene;
    /**
     * A játék színe.
     */
    private Scene gameScene;
    /**
     * A játékban az escape karakter lenyomása után megjelenő szín.
     */
    private Scene gameMenuScene;
    /**
     * A játék fő {@code stage}-e.
     */
    private Stage stage;
    /**
     * A játék elindításakor a képernyő tetején szereplő gombok.
     */
    private Button[] buttons;
    /**
     * A főmenőben szereplő gombok.
     */
    private Button[] menubuttons;
    /**
     * A játékban az escape karakter lenyomása után megjelenő menüben szereplő gombok.
     */
    private Button[] gamemenubuttons;
    /**
     * A játékban szereplő körök.
     */
    private Circle[][] circles;

    /**
     * Az osztály konstruktora.
     */
    public Storage(){

    }

    /**
     * Leellenőrzi, hogy véget ért e a játék.
     * @param x az utoljára lehelyezett elem x pozíciója.
     * @param y az utoljára lehelyezett elem y pozíciója.
     * @return visszaküldi, hogy véget ért e a játék, és ha igen, akkor ki nyert.
     */
    public String endGame(int x, int y, Circle[][] circles){
        String s="no";
        Color color=Color.WHITE;
        for (int i = 0; i <2 ; i++) {
            if(i==0){
                color=Color.RED;
            }else if(i==1){
                color=Color.YELLOW;
            }

            if (Nyert(Fuggoleges(x,y,color,circles))){
                return KiNyert(color);
            }else if (Nyert(Vizszintesen(x,y,color,circles))){
                return KiNyert(color);
            }else if (Nyert(Foatlo(x,y,color,circles))){
                return KiNyert(color);
            }else if (Nyert(Mellekatlo(x,y,color,circles))){
                return KiNyert(color);
            }

        }

        return s;
    }

    /**
     * Megnézi, hogy függőlegesen hány egymás utáni egyforma színű kör van.
     * @param x az utoljára lehelyezett elem x pozíciója.
     * @param y az utoljára lehelyezett elem y pozíciója.
     * @param color az aktuálisan lehelyezett kör színe.
     * @return visszatérírit hogy hány kör van egyforma színnel egymás mellett.
     */
    public int Fuggoleges(int x, int y, Color color, Circle[][] circles){
        int count = 0;
        Boolean mehet = true;
        int pozicion = 0;
        while (x - pozicion >= 0) {
            if (mehet) {
                if (circles[x - pozicion][y].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }
        pozicion = 0;
        mehet = true;
        count -= 1;
        while (x + pozicion < 7) {
            if (mehet) {
                if (circles[x + pozicion][y].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }

        return count;
    }

    /**
     * Megnézi, hogy vízszintesen hány egymás utáni egyforma színű kör van.
     * @param x az utoljára lehelyezett elem x pozíciója.
     * @param y az utoljára lehelyezett elem y pozíciója.
     * @param color az aktuálisan lehelyezett kör színe.
     * @return visszatérírit hogy hány kör van egyforma színnel egymás mellett.
     */
    public int Vizszintesen(int x, int y, Color color, Circle[][] circles){
        int count = 0;
        Boolean mehet = true;
        int pozicion = 0;
        while (y - pozicion >= 0) {
            if (mehet) {
                if (circles[x][y - pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }
        pozicion = 0;
        mehet = true;
        count -= 1;
        while (y + pozicion < 6) {
            if (mehet) {
                if (circles[x][y + pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }

        return count;
    }

    /**
     * Megnézi, hogy a belról-jobbra föntről lefelé hány egymás utáni egyforma színű kör van.
     * @param x az utoljára lehelyezett elem x pozíciója.
     * @param y az utoljára lehelyezett elem y pozíciója.
     * @param color az aktuálisan lehelyezett kör színe.
     * @return visszatérírit hogy hány kör van egyforma színnel egymás mellett.
     */
    public int Foatlo(int x, int y, Color color, Circle[][] circles){
        int count = 0;
        Boolean mehet = true;
        int pozicion = 0;
        while ((y - pozicion >= 0) && (x - pozicion >= 0)) {
            if (mehet) {
                if (circles[x - pozicion][y - pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }
        pozicion = 0;
        mehet = true;
        count -= 1;
        while ((y + pozicion < 6) && (x + pozicion < 7)) {
            if (mehet) {
                if (circles[x + pozicion][y + pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }

        return count;
    }

    /**
     * Megnézi, hogy a jobról-balra föntről lefelé hány egymás utáni egyforma színű kör van.
     * @param x az utoljára lehelyezett elem x pozíciója.
     * @param y az utoljára lehelyezett elem y pozíciója.
     * @param color az aktuálisan lehelyezett kör színe.
     * @return visszatérírit hogy hány kör van egyforma színnel egymás mellett.
     */
    public int Mellekatlo(int x, int y, Color color, Circle[][] circles){
        int count = 0;
        boolean mehet = true;
        int pozicion = 0;
        while ((y - pozicion >= 0) && (x + pozicion < 7)) {
            if (mehet) {
                if (circles[x + pozicion][y - pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }
        pozicion = 0;
        mehet = true;
        count -= 1;
        while ((y + pozicion < 6) && (x - pozicion >= 0)) {
            if (mehet) {
                if (circles[x - pozicion][y + pozicion].getFill() == color) {
                    count += 1;
                } else {
                    mehet = false;
                }
            }
            pozicion += 1;
        }
        return count;
    }

    /**
     * Megvizsgálja hogy nyert-e valaki.
     * @param count Az egymás utáni egyforma színű körök száma.
     * @return Ha nyert valaki, akkor igaz, egyébként hamis értéket térít vissza.
     */
    public Boolean Nyert(int count){
        if (count >= 4) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Megnézi, hogy ki nyerte meg a játékor.
     * @param color Az utoljára lehelyezett kör színe.
     * @return A győztes játékos neve.
     */
    public String KiNyert(Color color){
        String player="Player 2";
        if (color==Color.RED) {
            player= "Player 1";
        }
        return player;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        setHeight((this.width-2*this.border)/7*6+2*this.border+this.top);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBorder() {
        return border;
    }

    public void setBorder(float border) {
        this.border = border;

    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public void setMenuScene(Scene menuScene) {
        this.menuScene = menuScene;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Scene getGameMenuScene() {
        return gameMenuScene;
    }

    public void setGameMenuScene(Scene gameMenuScene) {
        this.gameMenuScene = gameMenuScene;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    public Circle[][] getCircles() {
        return circles;
    }

    public void setCircles(Circle[][] circles) {
        this.circles = circles;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button[] getMenubuttons() {
        return menubuttons;
    }

    public void setMenubuttons(Button[] menubuttons) {
        this.menubuttons = menubuttons;
    }

    public Button[] getGamemenubuttons() {
        return gamemenubuttons;
    }

    public void setGamemenubuttons(Button[] gamemenubuttons) {
        this.gamemenubuttons = gamemenubuttons;
    }


    public Color[][] getCirclesColor() {
        return CirclesColor;
    }

    public void setCirclesColor(Color[][] circlesColor) {
        CirclesColor = circlesColor;
    }
}
