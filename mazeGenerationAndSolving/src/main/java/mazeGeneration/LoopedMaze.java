package mazeGeneration;

import java.util.Random;
import maze.Cell;
import maze.Maze;


/** Class for creating a maze with loops.
 *
 * @author julia
 */
public class LoopedMaze {
    public Maze maze;
    public int cells;
    
    public LoopedMaze(int width, int height) {
        this.cells = width * height;
        generateNewMaze(width, height);
    }
    
    
    /** Run the Kruskal's algorithm to find a simple maze.
     * Then remove chosen walls.
     */
    public void run() {
        generateRoutes();
    }
    
    
    public void generateRoutes() {
        int horizontalRoutes = getRandomNumber(maze.getHeight() / 3) + (maze.getHeight() / 5);
        int verticalRoutes = getRandomNumber(maze.getWidth() / 3) + (maze.getWidth() / 5);
        
        for (int i = 0; i < horizontalRoutes; i++) {
            int route = 0;
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
            
            for (int j = 0; j < maze.getWidth() - 1; j++) {
                maze.removeWall(maze.getCell(j, route), 3);
            }
        }
        
        for (int i = 0; i < verticalRoutes; i++) {
            int route = 0;
            int accepted = 0;
            
            while (accepted < 2) {
                route = getRandomNumber(maze.getWidth());
                accepted = 0;
                
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
            
            for (int j = 0; j < maze.getHeight() - 1; j++) {
                maze.removeWall(maze.getCell(route, j), 2);
            }
        }
        
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getLowerWall() == false || maze.getCell(i, 0).getRightWall() == false) {
                maze.getCell(i, 0).removeUpperWall();
                break;
            }
        }
        
        for (int i = maze.getWidth() - 1; i >= 0; i--) {
            if (maze.getCell(i, maze.getHeight() - 1).getUpperWall() == false || maze.getCell(i, 0).getRightWall() == false) {
                maze.getCell(i, maze.getHeight() - 1).removeLowerWall();
                break;
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
