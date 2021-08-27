package mazegeneration;

import java.util.Random;
import maze.Cell;
import maze.Maze;


/** Class for creating a maze with loops.
 * Created for the testing of Trémaux's algorithm.
 *
 * @author julia
 */
public class LoopedMaze {
    public Maze maze;
    public int cells;
    public int firstRow;
    public int lastRow;
    public int firstColumn;
    public int lastColumn;
    
    public LoopedMaze(int width, int height) {
        this.cells = width * height;
        generateNewMaze(width, height);
    }
    
    
    /** Create looped maze.
     *
     */
    public void run() {
        generateRoutes();
    }
    
    
    public void generateRoutes() {
        generateVerticalRoutes();
        generateHorizontalRoutes();
        
        maze.getCell(firstColumn, 0).removeUpperWall();
        maze.getCell(lastColumn, maze.getHeight() - 1).removeLowerWall();
        
        removeEndingsToUp();
        removeEndingsToLeft();
        removeEndingsToDown();
        removeEndingsToRight();
    }
    
    
    public void removeEndingsToLeft() {
        for (int j = 0; j < maze.getHeight(); j++) {
            maze.addWall(maze.getCell(firstColumn, j), 1);
        }
    }
    
    
    public void removeEndingsToRight() {
        for (int j = 0; j < maze.getHeight(); j++) {
            maze.addWall(maze.getCell(lastColumn, j), 3);
        }
    }
    
    public void removeEndingsToDown() {
        for (int j = 0; j < lastColumn; j++) {
            maze.addWall(maze.getCell(j, lastRow), 2);
        }
    }
    
    
    public void removeEndingsToUp() {
        for (int j = maze.getWidth() - 1; j > firstColumn; j--) {
            maze.addWall(maze.getCell(j, firstRow), 0);
        }
    }
    
    
    public void generateVerticalRoutes() {
        int verticalRoutes = getRandomNumber(maze.getWidth() / 3)
                + (maze.getWidth() / 5);
        
        firstColumn = maze.getWidth();
        lastColumn = 0;
            
        for (int i = 0; i < verticalRoutes; i++) {
            int route = 0;
            int accepted = 0;
            
            while (accepted < 2) {
                accepted = 0;
                route = getRandomNumber(maze.getWidth());
                
                if (maze.getCell(route, 0).getLowerWall() == true) {
                    if (route > 0) {
                        if (maze.getCell(route - 1, 0).getLowerWall() == true) {
                            accepted++;
                        }
                    } else {
                        accepted++;
                    }
                    
                    if (route < maze.getWidth() - 1) {
                        if (maze.getCell(route + 1, 0).getLowerWall() == true) {
                            accepted++;
                        }
                    } else {
                        accepted++;
                    }
                }
            }
            
            if (route < firstColumn) {
                firstColumn = route;
            }
            if (route > lastColumn) {
                lastColumn = route;
            }
            
            for (int j = 0; j < maze.getHeight() - 1; j++) {
                maze.removeWall(maze.getCell(route, j), 2);
            }
        }
    }
    
    public void generateHorizontalRoutes() {
        int routes = getRandomNumber(maze.getHeight() / 3)
                + (maze.getHeight() / 5);
        
        firstRow = maze.getHeight();
        lastRow = 0;
        
        for (int i = 0; i < routes; i++) {
            int route = getRandomNumber(maze.getHeight());
            int accepted = 0;
            
            while (accepted < 2) {
                route = getRandomNumber(maze.getHeight());
                accepted = 0;
                
                if (maze.getCell(0, route).getRightWall() == true) {
                    if (route > 0) {
                        if (maze.getCell(0, route - 1).getRightWall() == true) {
                            accepted++;
                        }
                    } else {
                        accepted++;
                    }
                    
                    if (route < maze.getHeight() - 1) {
                        if (maze.getCell(0, route + 1).getRightWall() == true) {
                            accepted++;
                        }
                    } else {
                        accepted++;
                    }
                }
            }
            if (route < firstRow) {
                firstRow = route;
            }
            if (route > lastRow) {
                lastRow = route;
            }
            
            for (int j = 0; j < maze.getWidth() - 1; j++) {
                maze.removeWall(maze.getCell(j, route), 3);
            }
        }
    }
    
    
    /** Generate a new maze with given dimensions.
     *
     * @param width number of cells
     * @param height number of cells
     */
    public void generateNewMaze(int width, int height) {
        int number = 0;
        
        Cell[][] cellArray = new Cell[height][width];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cell.addNumber(number);
                cellArray[j][i] = cell;
                number++;
            }
        }
        this.maze = new Maze(cellArray);
    }
    
    
    /** Get a random number between 0 and given maximum.
     *
     * @param max the maximum number
     * @return the number
     */
    public int getRandomNumber(int max) {
        Random r = new Random();
        int number = r.nextInt(max);
        return number;
    }    
}
