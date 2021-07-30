package maze;


/**
 * Main class
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int wildth = 10;
        int height = 5;
        
        Cell[][] cells = new Cell[height][wildth];
        
        for (int i = 0; i < wildth; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cells[j][i] = cell;
            }
        }
        
        Maze maze = new Maze(cells);
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wildth; j++) {
                Cell c = maze.getCell(j, i);
                System.out.print(c.getX() + ", " + c.getY() + "; ");
            }
            System.out.println("");
        }
    }
    
}
