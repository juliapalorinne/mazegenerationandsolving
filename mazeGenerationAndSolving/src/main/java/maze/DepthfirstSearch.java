package maze;

import java.util.Random;
import java.util.Stack;


/** Randomized depth-first search for creating a maze.
 *
 * @author julia
 */
public final class DepthfirstSearch {  
    
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
        
        this.visited = 0;        
        this.x = 0;
        this.y = 0;
        this.stack = new Stack();
    }
    
    /** Run the depth-first search algorithm.
     * Then choose first and last cells.
     */
    public void run() {
        generateRoutes();
        maze.getCell(0, 0).removeUpperWall();
        maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1).removeLowerWall();
        // maze.printMaze();
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
    
    
    public void generateRoutes() {
        maze.getCell(x, y).visit();
        visited++;
        
        chooseDirection();
        
        while (visited < cells) {
            search();
        }
    }
    
    
    public void search() {
        // System.out.println(x + ", " + y + " , visited: " + maze.getCell(x, y).numberOfVisits());
        
        if (maze.getCell(x, y).numberOfVisits() == 0) {
            visited++;
        }
        maze.getCell(x, y).visit();
        
        if (visited < cells) {
            if (checkIfAllVisited() == true) {
                Cell c = (Cell) stack.pop();
                x = c.getX();
                y = c.getY();
            } else {
                chooseDirection();
                search();
            }
        }
    }
    
    
    
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
    
    public int getRandomNumber() {
        Random r = new Random();
        int number = r.nextInt(4);
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
