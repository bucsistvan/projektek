package connect;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    void nyert() {
        Storage storage = new Storage();
        assertEquals(true,storage.Nyert(5));
        assertEquals(true,storage.Nyert(4));
        assertEquals(false,storage.Nyert(3));

    }

    @Test
    void kiNyert() {
        Storage storage = new Storage();
        assertEquals("Player 1",storage.KiNyert(Color.RED));
        assertEquals("Player 2",storage.KiNyert(Color.YELLOW));
    }

    @Test
    void fuggoleges() {
        Storage storage = new Storage();
        Circle[][] circles = new Circle[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                circles[i][j]=new Circle(i,j,2);
            }
        }
        circles[2][2].setFill(Color.RED);
        circles[3][2].setFill(Color.RED);
        circles[4][2].setFill(Color.RED);
        circles[5][2].setFill(Color.RED);
        assertEquals(4,storage.Fuggoleges(2,2,Color.RED,circles));
    }

    @Test
    void vizszintesen() {
        Storage storage = new Storage();
        Circle[][] circles = new Circle[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                circles[i][j]=new Circle(i,j,2);
            }
        }
        circles[2][1].setFill(Color.RED);
        circles[2][2].setFill(Color.RED);
        circles[2][3].setFill(Color.RED);
        circles[2][4].setFill(Color.RED);
        circles[2][5].setFill(Color.RED);
        assertEquals(5,storage.Vizszintesen(2,2,Color.RED,circles));
    }

    @Test
    void foatlo() {
        Storage storage = new Storage();
        Circle[][] circles = new Circle[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                circles[i][j]=new Circle(i,j,2);
            }
        }
        circles[2][1].setFill(Color.RED);
        circles[3][2].setFill(Color.RED);
        circles[4][3].setFill(Color.RED);
        assertEquals(3,storage.Foatlo(2,1,Color.RED,circles));
    }

    @Test
    void mellekatlo() {
        Storage storage = new Storage();
        Circle[][] circles = new Circle[7][6];
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6 ; j++) {
                circles[i][j]=new Circle(i,j,2);
            }
        }
        circles[2][1].setFill(Color.RED);
        circles[1][2].setFill(Color.RED);
        assertEquals(2,storage.Mellekatlo(1,2,Color.RED,circles));
    }
}
