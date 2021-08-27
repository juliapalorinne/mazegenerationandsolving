package mazegeneration;

import java.util.Random;
import java.util.Stack;
import maze.Cell;
import maze.Maze;


/** Randomized depth-first search for creating a maze.
 *
 * @author julia
 */
public final class DepthfirstSearch {  
    
    public Maze maze;
    public Stack<Cell> stack;
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
        
        this.visited = 0;        
        this.x = 0;
        this.y = 0;
        this.stack = new Stack();
    }
    
    
    /** Run the depth-first search algorithm.
     * Then choose first and last cells and reset visits.
     */
    public void run() {
        generateRoutes();
        maze.getCell(0, 0).removeUpperWall();
        maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1).removeLowerWall();
        
        maze.resetVisits();
    }

    
    /** Generate a new maze with given dimensions.
     *
     * @param width number of cells
     * @param height number of cells
     */
    public void generateNewMaze(int width, int height) {
        this.cells = width * height;
        
        Cell[][] cellArray = new Cell[height][width];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cellArray[j][i] = cell;
            }
        }
        this.maze = new Maze(cellArray);
    }
    
    
    /** Generate routes.
     * Continue until all cells visited.
     */
    public void generateRoutes() {
        maze.getCell(x, y).visit();
        visited++;
        
        chooseDirection();
        
        while (visited < cells) {
            search();
        }
    }
    
    
    /** Search for unvisited cell neighbouring current cell.
     * Move to cell and repeat search.
     */
    public void search() {
        if (maze.getCell(x, y).numberOfVisits() == 0) {
            visited++;
        }
        maze.getCell(x, y).visit();
        
        if (visited < cells) {
            if (checkIfAllVisited() == true) {
                Cell c = stack.pop();
                x = c.getX();
                y = c.getY();
            } else {
                chooseDirection();
                search();
            }
        }
    }
    
    
    /** Choose direction to which to move.
     * For direction, get a random number between 0 and 3.
     */
    public void chooseDirection() {
        int number = -1;
        boolean chosen = false;
        
        while (chosen == false) {
            number = getRandomNumber();
            
            if (number == 0) {
                chosen = moveUp();
            } else if (number == 1) {
                chosen = moveLeft();
            } else if (number == 2) {
                chosen = moveDown();
            } else if (number == 3) {
                chosen = moveRight();
            }
        }
    }
    
    
    /** Check the cell upwards and move if possible.
     *
     * @return true if moved, false if not
     */
    public boolean moveUp() {
        if (y > 0) {
            if (!checkIfVisited(0)) {
                maze.removeWall(maze.getCell(x, y), 0);
                stack.add(maze.getCell(x, y));
                y--;
                return true;
            }
        }
        return false;
    }
    
    
    /** Check the cell to left and move if possible.
     *
     * @return true if moved, false if not
     */
    public boolean moveLeft() {
        if (x > 0) {
            if (!checkIfVisited(1)) {
                maze.removeWall(maze.getCell(x, y), 1);
                stack.add(maze.getCell(x, y));
                x--;
                return true;
            }
        }
        return false;
    }
    
    
    /** Check the cell downwards and move if possible.
     *
     * @return true if moved, false if not
     */
    public boolean moveDown() {
        if (y < maze.getHeight() - 1) {
            if (!checkIfVisited(2)) {
                maze.removeWall(maze.getCell(x, y), 2);
                stack.add(maze.getCell(x, y));
                y++;
                return true;
            }
        }
        return false;
    }
    
    
    /** Check the cell to right and move if possible.
     *
     * @return true if moved, false if not
     */
    public boolean moveRight() {
        if (x < maze.getWidth() - 1) {
            if (!checkIfVisited(3)) {
                maze.removeWall(maze.getCell(x, y), 3);
                stack.add(maze.getCell(x, y));
                x++;
                return true;
            }
        }
        return false;
    }
    
    
    /** Get a random number between 0 and 3.
     *
     * @return the number
     */
    public int getRandomNumber() {
        Random r = new Random();
        int number = r.nextInt(4);
        return number;
    }
    
    
    /** Check if the cell to wanted direction has been visited.
     *
     * @param direction number between 0 and 3
     * @return false if not, else true
     */
    public boolean checkIfVisited(int direction) {
        int visits = -1;
        if (direction == 0) {
            visits = maze.getCell(x, y - 1).numberOfVisits();
        } else if (direction == 1) {
            visits = maze.getCell(x - 1, y).numberOfVisits();
        } else if (direction == 2) {
            visits = maze.getCell(x, y + 1).numberOfVisits();
        } else if (direction == 3) {
            visits = maze.getCell(x + 1, y).numberOfVisits();
        }
        
        if (visits == 0) {
            return false;
        }
        return true;
    }
    
    
    /** Check if all neighbouring cells have been visited.
     *
     * @return false if not, else true
     */
    public boolean checkIfAllVisited() {
        boolean upper = false;
        boolean left = false;
        boolean lower = false;
        boolean right = false;
        
        
        if (y == 0 || maze.getCell(x, y - 1).numberOfVisits() > 0) {
            upper = true;
        }
        if (x == 0 || maze.getCell(x - 1, y).numberOfVisits() > 0) {
            left = true;
        }
        if (y == maze.getHeight() - 1 || maze.getCell(x, y + 1).numberOfVisits() > 0) {
            lower = true;
        }
        if (x == maze.getWidth() - 1 || maze.getCell(x + 1, y).numberOfVisits() > 0) {
            right = true;
        }
        
        if (upper == true && left == true && lower == true &&  right ==  true) {
            return true;
        }
        
        return false;
    }
}
