package connect;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A játék fő menűét megjelenítő objektum.
 */
public class Menu {
    private float width;
    private float height;
    private float border;
    private float top;
    public Menu(float width, float height, float border, float topv){
        setWidth(width);
        setHeight(height);
        setBorder(border);
        setTop(top);
    }

    /**
     * A főmenő nézetének létrehozésa.
     * @param buttons A főmenüben található gombok.
     * @return A főmenü nézetét adja vissza.
     */
    public Scene MenuView(Button[] buttons){
        Rectangle rectangle = new Rectangle(border,border+top,width-2*border,height-2*border-top);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Group MainGroup = new Group(rectangle);

        int menuwidth=100;
        int menuheight=30;
        buttons[0]=new Button();
        buttons[0].setText("Start game");
        buttons[0].setLayoutX(width/2-menuwidth/2);
        buttons[0].setLayoutY(100);
        buttons[0].setMinWidth(menuwidth);
        buttons[0].setMinHeight(menuheight);
        buttons[0].setId(Integer.toString(0));
        MainGroup.getChildren().add(buttons[0]);

        buttons[1]=new Button();
        buttons[1].setText("Load game");
        buttons[1].setLayoutX(width/2-menuwidth/2);
        buttons[1].setLayoutY(140);
        buttons[1].setMinWidth(menuwidth);
        buttons[1].setMinHeight(menuheight);
        buttons[1].setId(Integer.toString(1));
        MainGroup.getChildren().add(buttons[1]);

        buttons[2]=new Button();
        buttons[2].setText("Exit");
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

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }
}
