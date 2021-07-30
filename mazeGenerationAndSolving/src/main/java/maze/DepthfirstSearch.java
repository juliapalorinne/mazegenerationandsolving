package maze;

import java.util.Random;
import java.util.Stack;

/** Randomized depth-first search for creating a maze.
 *
 * @author julia
 */
public class DepthfirstSearch {  
    
    public Maze maze;
    public Stack stack;
    public int x;
    public int y;
    public int visited;
    public int cells;

    
    /** Create a new maze with given dimensions.
     *
     * @param width of the maze
     * @param height of the maze
     */
    public DepthfirstSearch(int width, int height) {
        generateNewMaze(width, height);
    }
    
    public void run() {
        generateRoutes();
    }

    public void generateNewMaze(int width, int height) {
        this.cells = width * height;
        this.visited = 0;        
        
        Cell[][] cellArray = new Cell[height][width];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cellArray[j][i] = cell;
            }
        }
        this.maze = new Maze(cellArray);
    }
    
    
    public void generateRoutes() {
        this.x = 0;
        this.y = 0;
        maze.getCell(x, y).visit();
        visited++;
        
        chooseDirection();
        stack.add(maze.getCell(x, y));
                
        
        while (visited < cells) {
            search();
        }
    }
    
    
    public void search() {
        
        while (!stack.isEmpty()) {
            maze.getCell(x, y).visit();
            visited++;
            chooseDirection();
            stack.add(maze.getCell(x, y));
            search();
        }
        
    }
    
    
    
    public void chooseDirection() {
        int number = -1;
        boolean chosen = false;
        
        while (chosen == false) {
            number = getRandomNumber();
            
            if (number == 0) {
                if (y > 0) {
                    if (!checkIfVisited(number)) {
                        y--;
                        chosen = true;
                    }
                }
            } else if (number == 1) {
                if (x > 0) {
                    if (!checkIfVisited(number)) {
                        x--;
                        chosen = true;
                    }
                }
            } else if (number == 2) {
                if (x < maze.getWidth() - 2) {
                    if (!checkIfVisited(number)) {
                        y++;
                        chosen = true;
                    }
                }
            } else if (number == 3) {
                if (y < maze.getHeight() - 2) {
                    if (!checkIfVisited(number)) {
                        x++;
                        chosen = true;
                    }
                }
            }
        }
    }
    
    public int getRandomNumber() {
        Random r = new Random();
        int number = r.nextInt(3);
        return number;
    }
    
    public boolean checkIfVisited(int i) {
        int visits = -1;
        if (i == 0) {
            visits = maze.getCell(x, y - 1).numberOfVisits();
        } else if (i == 1) {
            visits = maze.getCell(x - 1, y).numberOfVisits();
        } else if (i == 2) {
            visits = maze.getCell(x, y + 1).numberOfVisits();
        } else if (i == 3) {
            visits = maze.getCell(x + 1, y).numberOfVisits();
        }
        
        if (visits == 0) {
            return false;
        }
        return true;
    }
}
