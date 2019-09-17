package connect;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * A játék fő színét megjelenítő objektum.
 */
public class MainGame {
    private float width;
    private float height;
    private float border;
    private float top;
    public MainGame(float width, float height, float border, float top){
        setWidth(width);
        setHeight(height);
        setBorder(border);
        setTop(top);
    }

    /**
     * Előaáálítja a játék fő színét.
     * @param buttons A játék tetején megjelenő gombok.
     * @param circles A játékban szereplő körök, melyekből 4 egyszinüt kell egymás mellé elhelyezni.
     * @return A játék színénak visszaadása.
     */
    public Scene MainGameView(Button[] buttons, Circle[][] circles){
        Rectangle rectangle = new Rectangle(border,border+top,width-2*border,height-2*border-top);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Group MainGroup = new Group(rectangle);

        float circleRadius=(width-2*border)/14;
        for (int i = 0; i <7 ; i++) {
            buttons[i]=new Button(Integer.toString(i));
            buttons[i].setLayoutX(border+circleRadius*2*(i));
            buttons[i].setLayoutY(top);
            buttons[i].setMinWidth(circleRadius*2);
            buttons[i].setMinHeight(border);
            buttons[i].setId(Integer.toString(i));
            for (int j = 0; j <6 ; j++) {
                circles[i][j]=new Circle(border+circleRadius+circleRadius*2*i,border+circleRadius+circleRadius*2*j+top,circleRadius);
                circles[i][j].setFill(Color.WHITE);
                MainGroup.getChildren().addAll(circles[i][j]);
            }
        }
        MainGroup.getChildren().addAll(buttons);




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

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }
}
