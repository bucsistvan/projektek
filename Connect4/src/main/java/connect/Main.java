package connect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;
//import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * A kontroller szerepét betöltő osztály.
 */
@Slf4j
public class Main extends Application {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private Gson gson=new GsonBuilder().setPrettyPrinting().create();
    private Storage storage=new Storage();
    private SavedData savedData=new SavedData();

    @Override
    public void start(Stage primaryStage){

        SetUp();
        MainGame mainGame=new MainGame(storage.getWidth(),storage.getHeight(),storage.getBorder(),storage.getTop());
        Menu menu=new Menu(storage.getWidth(),storage.getHeight(),storage.getBorder(),storage.getTop());
        GameMenu gameMenu=new GameMenu(storage.getWidth(),storage.getHeight(),storage.getBorder());
        storage.setStage(primaryStage);
        storage.setGameScene(mainGame.MainGameView(storage.getButtons(),storage.getCircles()));
        storage.setMenuScene(menu.MenuView(storage.getMenubuttons()));
        storage.setGameMenuScene(gameMenu.MenuView(storage.getGamemenubuttons()));

        primaryStage.setScene(storage.getMenuScene());
        primaryStage.show();
        GameButtonHandling();
        GameKeyPressHandling();
        MenuButtonHandling();
        GameMenuButtonHanedling();
    }

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * A {@code storage} objektum fő változóinak inicializálása.
     */
    public void SetUp(){
        storage.setPlayer(true);
        storage.setBorder(30);
        storage.setTop(40);
        storage.setWidth(500);
        storage.setCirclesColor(new Color[7][6]);
        storage.setButtons(new Button[7]);
        storage.setMenubuttons(new Button[3]);
        storage.setGamemenubuttons(new Button[3]);
        storage.setCircles(new Circle[7][6]);

    }

    /**
     * A fő játékban szereplő körök színeinek lementése.
     */
    public void GetCirclesFill(){
        Color[][] c=new Color[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                c[i][j]= (Color) storage.getCircles()[i][j].getFill();
            }
        }

        storage.setCirclesColor(c);
    }

    /**
     * A fő komponansek lementése a {@code sorage} objektumból a {@code savedData} objektumba.
     */
    public void StoragetoSavedData(){
        savedData.setPlayer(storage.isPlayer());
        savedData.setWidth(storage.getWidth());
        savedData.setHeight(storage.getHeight());
        savedData.setBorder(storage.getBorder());
        savedData.setTop(storage.getTop());
        GetCirclesFill();
        savedData.setCirclesColor(ColorToInt(storage.getCirclesColor()));
    }

    /**
     * A színeknek átalakítása {@code Color} típusúról {@code int} típusúra.
     * @param c a körök színei.
     * @return a szinekből létrehozott {@code int} tömb.
     */
    public int[][] ColorToInt(Color[][] c){
        int[][] intColor = new int[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                if (c[i][j].getGreen()==0) {
                    intColor[i][j]=1;
                }else if (c[i][j].getBlue()==0){
                    intColor[i][j]=2;
                }else if (c[i][j].getRed()==0){
                    intColor[i][j]=0;
                }
            }
        }
        return intColor;
    }

    /**
     * A játék lementése.
     */
    public void saveGame(){
        String file="Saved.json";
        StoragetoSavedData();
        try {
            FileWriter fileWriter=new FileWriter(file);
            gson.toJson(savedData, fileWriter);
            fileWriter.close();
            logger.info("Game saved to the {} file",file);
        } catch (IOException e) {
            logger.error("The game could not be saved to the {} file",file);
        }

    }

    /**
     * Egy {@code int} tipusú tömb átalakítása {@code Color} típusú tömbbé.
     * @param c a kapott integer tömb.
     * @return az integer tömbből átalakított {@code Color} tömb.
     */
    public Color[][] IntToColor(int[][] c){
        Color[][] colors=new Color[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                if (c[i][j]==1) {
                    colors[i][j]=Color.RED;
                    storage.getCircles()[i][j].setFill(Color.RED);
                }else if (c[i][j]==2){
                    colors[i][j]=Color.YELLOW;
                    storage.getCircles()[i][j].setFill(Color.YELLOW);
                }
            }
        }

        return colors;
    }

    /**
     * Előző játékállás visszatöltése.
     */
    public void loadGame() {
        String file="Saved.json";
        try {
            FileReader fileReader=new FileReader(file);
            savedData=gson.fromJson(fileReader,SavedData.class);
        } catch (FileNotFoundException e) {
            logger.error("The {} file is not available!",file);
        }

        storage.setPlayer(savedData.isPlayer());
        storage.setWidth(savedData.getWidth());
        storage.setHeight(savedData.getHeight());
        storage.setBorder(savedData.getBorder());
        storage.setTop(savedData.getTop());
        storage.setCirclesColor(IntToColor(savedData.getCirclesColor()));
        logger.info("Game loaded from {} file",file);
    }

    /**
     * A főmenü gombjainak kezelésének elkapása.
     */
    public void MenuButtonHandling(){
        for (int i = 0; i <3 ; i++) {
            final int j=i;
            storage.getMenubuttons()[i].setOnAction(event->MenuHandler(storage.getButtons()[j]));
        }
    }

    /**
     * A főmenű adott gombjának kezelése.
     * @param button A főmenü egyik gombja.
     */
    public void MenuHandler(Button button){
        int id=Integer.parseInt(button.getId());
        if(id==0){
            logger.info("Starting game");
            storage.getStage().setScene(storage.getGameScene());
        }else if (id==1){
            loadGame();
            storage.getStage().setScene(storage.getGameScene());
        }else if(id==2){
            logger.info("Exit the game");
            storage.getStage().close();
        }
    }

    /**
     * A játékban figyeli az escape karakter lenyomását.
     */
    public void GameKeyPressHandling(){
        storage.getGameScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                storage.getStage().setScene(storage.getGameMenuScene());
            }
        });
    }

    /**
     * A játékban szereplő menü gombjainak kezelése.
     */
    public void GameMenuButtonHanedling() {
        for (int i = 0; i <3 ; i++) {
            Button button=storage.getGamemenubuttons()[i];
            button.setOnAction(event->{
                int id=Integer.parseInt(button.getId());
                if(id==0){
                    storage.getStage().setScene(storage.getGameScene());
                }else if (id==1){
                    saveGame();
                }else if (id==2){
                    logger.info("Back to the main menu.");
                    DeleteCircle();
                    storage.setPlayer(true);
                    storage.getStage().setScene(storage.getMenuScene());
                }
            });
        }

    }

    /**
     * A játék tetején szereplő gombok kezelésének elkapása.
     */
    public void GameButtonHandling(){
        for (int i = 0; i <7 ; i++) {
            final int j=i;
            storage.getButtons()[i].setOnAction(event->GameHandler(storage.getButtons()[j]));
        }
    }

    /**
     * Az adott gomb kezelése.
     * @param button Az aktiv gomb.
     */
    public void GameHandler(Button button){
        int id=Integer.parseInt(button.getId());
        int k=0;
        while((k<5)&&(storage.getCircles()[id][5-k].getFill()!=Color.WHITE)){
            k+=1;
        }
        if(storage.getCircles()[id][5-k].getFill()==Color.WHITE) {
            if (storage.isPlayer()) {
                storage.getCircles()[id][5 - k].setFill(Color.RED);
            } else {
                storage.getCircles()[id][5 - k].setFill(Color.YELLOW);
            }
            storage.setPlayer(!storage.isPlayer());
            String m=storage.endGame(id,5-k, storage.getCircles());
            if(!m.equals("no")){
                DeleteCircle();
                storage.setPlayer(true);
                storage.getStage().setScene(storage.getMenuScene());
                logger.info("{} won the game",m);
            }
        }

    }

    /**
     * A körök átszínezése fehérre.
     */
    public void DeleteCircle(){
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                storage.getCircles()[i][j].setFill(Color.WHITE);
            }
        }
    }


}
