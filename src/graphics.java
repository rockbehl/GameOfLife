import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class graphics extends JPanel implements ActionListener {
    int sizeCell = 10;
    int xWidth = 1920/sizeCell;
    int yHeight = 1080/sizeCell;
    int[][] cells = new int[xWidth][yHeight];
    int[][] preCells = new int[xWidth][yHeight];
    boolean starts = true;

    public graphics(){
        setSize(1920,1080);
        setLayout(null);
        setBackground(Color.BLACK);
        new Timer(80, this).start();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        setBackground(Color.BLACK);

        g.setColor(Color.CYAN);
        grid(g);
        spawn(g);
        display(g);

    }

    private void grid(Graphics g){

        for (int i = 0; i < cells.length; i++){
            g.drawLine(0,i*sizeCell,1920,i*sizeCell);
            g.drawLine(i*sizeCell,0,i*sizeCell,1080);

        }

    }

    private void spawn(Graphics g){

        if (starts){

            for (int x=0; x<cells.length; x++){
                for (int y=0; y<cells[0].length; y++){

                    if ((int)(Math.random()*5) == 0){
                        cells[x][y] = 1;
                    }

                }
            }

            starts = false;
        }

    }

    private void display(Graphics g){

        g.setColor(Color.CYAN);

        copyArray();

        for (int x=0; x<cells.length; x++){
            for (int y=0; y<cells[0].length; y++){

                if (cells[x][y] == 1) {
                    g.fillRect(x * sizeCell, y * sizeCell, sizeCell, sizeCell);
                }

            }
        }

    }

    private void copyArray(){
        for (int x=0; x<cells.length; x++){
            for (int y=0; y<cells[0].length; y++){
                preCells[x][y] = cells[x][y];
            }
        }
    }

    private int check(int x, int y){
        int alive = 0;

        alive += cells[(x+xWidth-1)%xWidth][(y+yHeight-1)%yHeight];
        alive += cells[(x+xWidth)%xWidth][(y+yHeight-1)%yHeight];

        alive += cells[(x+xWidth+1)%xWidth][(y+yHeight-1)%yHeight];
        alive += cells[(x+xWidth-1)%xWidth][(y+yHeight)%yHeight];

        alive += cells[(x+xWidth+1)%xWidth][(y+yHeight)%yHeight];
        alive += cells[(x+xWidth-1)%xWidth][(y+yHeight+1)%yHeight];

        alive += cells[(x+xWidth)%xWidth][(y+yHeight+1)%yHeight];
        alive += cells[(x+xWidth+1)%xWidth][(y+yHeight+1)%yHeight];

        return alive;

    }

    public void actionPerformed(ActionEvent e){

            int alive;

            for (int x = 0; x < cells.length; x++) {
                for (int y = 0; y < cells[0].length; y++) {

                    if (cells[x][y] == 1) {
                        alive = check(x, y);

                        if (alive == 3) {
                            preCells[x][y] = 1;
                        } else if (alive == 2 && cells[x][y] == 1) {
                            preCells[x][y] = 1;
                        } else {
                            preCells[x][y] = 0;
                        }
                    }

                }
            }

            repaint();
        }
}
