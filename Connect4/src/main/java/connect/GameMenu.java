package connect;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * A játékban szereplő menüt megjelenítő objektum, mely az escape gomb lenyomása után jelenik meg.
 */
public class GameMenu {
    private float width;
    private float height;
    private float border;
    private float top;

    /**
     * Az objektum konstruktora, mely lementi a képernyő alap adatait.
     * @param width A képernyő szélessége.
     * @param height A képernyő magasséga.
     * @param border A képernyő kerete.
     */
    public GameMenu(float width, float height, float border){
        setWidth(width);
        setHeight(height);
        setBorder(border);
    }

    /**
     * Létrehozza a játékban szereplő menü színjét, mely az escape gomb lenyomása után jelenik meg.
     * @param buttons A menüben szereplő gombok.
     * @return A játék menü színét tériti vissza.
     */
    public Scene MenuView(Button[] buttons){
        Rectangle rectangle = new Rectangle(border,border+top,width-2*border,height-2*border-top);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Group MainGroup = new Group(rectangle);

        int menuwidth=100;
        int menuheight=30;

        Rectangle rectangle1 = new Rectangle(width/2-menuwidth/2-10,90,menuwidth+20,3*menuheight+40);
        rectangle1.setFill(Color.WHITE);
        rectangle1.setStroke(Color.BLACK);
        MainGroup.getChildren().add(rectangle1);

        buttons[0]=new Button();
        buttons[0].setText("Resume game");
        buttons[0].setLayoutX(width/2-menuwidth/2);
        buttons[0].setLayoutY(100);
        buttons[0].setMinWidth(menuwidth);
        buttons[0].setMinHeight(menuheight);
        buttons[0].setId(Integer.toString(0));
        MainGroup.getChildren().add(buttons[0]);

        buttons[1]=new Button();
        buttons[1].setText("Save");
        buttons[1].setLayoutX(width/2-menuwidth/2);
        buttons[1].setLayoutY(140);
        buttons[1].setMinWidth(menuwidth);
        buttons[1].setMinHeight(menuheight);
        buttons[1].setId(Integer.toString(1));
        MainGroup.getChildren().add(buttons[1]);

        buttons[2]=new Button();
        buttons[2].setText("Exit to menu");
        buttons[2].setLayoutX(width/2-menuwidth/2);
        buttons[2].setLayoutY(180);
        buttons[2].setMinWidth(menuwidth);
        buttons[2].setMinHeight(menuheight);
        buttons[2].setId(Integer.toString(2));
        MainGroup.getChildren().add(buttons[2]);



        Scene scene = new Scene(MainGroup, width,height);
        return scene;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
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

    public void setTop(float top) {
        this.top = top;
    }
}
