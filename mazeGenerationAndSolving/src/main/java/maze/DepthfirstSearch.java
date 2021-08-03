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
        maze.printMaze();
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
        this.x = 0;
        this.y = 0;
        this.stack = new Stack();
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
        System.out.println(x + ", " + y);
        maze.getCell(x, y).visit();
        visited++;
        
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
                maze.getCell(x, y).removeUpperWall();
                maze.removeWall(maze.getCell(x, y));
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
                maze.getCell(x, y).removeLeftWall();
                maze.removeWall(maze.getCell(x, y));
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
                maze.getCell(x, y).removeLowerWall();
                maze.removeWall(maze.getCell(x, y));
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
                maze.getCell(x, y).removeRightWall();
                maze.removeWall(maze.getCell(x, y));
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
