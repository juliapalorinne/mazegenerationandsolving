package mazeSolving;

import java.util.Random;
import java.util.Stack;
import maze.Cell;
import maze.Maze;


/** Class for solving a maze with right Trémaux's algorithm.
 *
 * @author julia
 */
public class TremauxsAlgorithm {
    
    Stack queue;
    int direction;
    Maze maze;
    Cell firstCell;
    Cell lastCell;
    Cell currentCell;
    
    
    /** Start a new Trémaux's algorithm with given maze.
     *
     * @param maze a new maze
     */
    public TremauxsAlgorithm(Maze maze) {
        this.queue = new Stack();        
        this.maze = maze;
    }
    
    
    /** Run the Trémaux's algortihm.
     * Find a route or print error message.
     */
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes");
        } else {
            for (int i = 0; i < maze.getWidth(); i++) {
                for (int j = 0; j < maze.getHeight(); j++) {
                    if (maze.getCell(i, j).numberOfVisits() < 2) {
                        maze.getCell(i, j).addToRoute();
                    }
                }
            }
        }
    }
    
    
    /** Find a route from first to last cell.
     *
     * @return true if route found, false if not
     */
    public boolean findRoute() {
        if (!findFirstCell() || !findLastCell()) {
            return false;
        }
        
        while (currentCell.getX() != lastCell.getX() || currentCell.getY() != lastCell.getY()) {
            if (possibleToMove()) {
                search();
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    
    /** Search for available cell neighbouring current cell.
     * Move to cell and repeat search.
     */
    public void search() {
        if (direction == 0) {
            if (leftAndRightWall()) {
                currentCell.visit();
                if (moveUp()) {
                   search();
                } else {
                    chooseDirection();
                }
            } else {
                chooseDirection();
            }
        } else if (direction == 1) {
            if (upperAndLowerWall()) {
                currentCell.visit();
                if (moveLeft()) {
                    search();
                } else {
                    chooseDirection();
                }
            } else {
                chooseDirection();
            }
        } else if (direction == 2) {
            if (leftAndRightWall()) {
                currentCell.visit();
                if (moveDown()) {
                   search();    
                } else {
                    chooseDirection();
                }
            } else {
                chooseDirection();
            }
        } else if (direction == 3) {
            if (upperAndLowerWall()) {
                currentCell.visit();
                if (moveRight()) {
                    search();
                } else {
                    chooseDirection();
                }
            } else {
                chooseDirection();
            }
        }
        
    }
    
    
    /** Choose randomly the direction to which to move.
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
        if (currentCell.getY() > 0 && currentCell.getUpperWall() == false) {
            if (!visitedTwice(0)) {
                currentCell = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
                direction = 0;
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
        if (currentCell.getX() > 0 && currentCell.getLeftWall() == false) {
            if (!visitedTwice(1)) {
                currentCell = maze.getCell(currentCell.getX() - 1, currentCell.getY());
                direction = 1;
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
        if (currentCell.getY() < maze.getHeight() - 1 && currentCell.getLowerWall() == false) {
            if (!visitedTwice(2)) {
                currentCell = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
                direction = 2;
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
        if (currentCell.getY() < maze.getWidth() - 1 && currentCell.getRightWall() == false) {
            if (!visitedTwice(3)) {
                currentCell = maze.getCell(currentCell.getX() + 1, currentCell.getY());
                direction = 3;
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
    public boolean visitedTwice(int direction) {
        int visits = -1;
        int x = currentCell.getX();
        int y = currentCell.getY();
        
        if (direction == 0) {
            visits = maze.getCell(x, y - 1).numberOfVisits();
        } else if (direction == 1) {
            visits = maze.getCell(x - 1, y).numberOfVisits();
        } else if (direction == 2) {
            visits = maze.getCell(x, y + 1).numberOfVisits();
        } else if (direction == 3) {
            visits = maze.getCell(x + 1, y).numberOfVisits();
        }
        
        if (visits == 0 || visits == 1) {
            return false;
        }
        return true;
    }
    
    
    /** When moving left or right, check if walls on both sides.
     *
     * @return true if yes, false if no
     */
    public boolean upperAndLowerWall() {
        if (currentCell.getLowerWall() == true
                && currentCell.getUpperWall() == true) {
            return true;
        }
        return false;
    }
    
    
    /** When moving up or down, check if walls on both sides.
     *
     * @return true if yes, false if no
     */
    public boolean leftAndRightWall() {
        if (currentCell.getLeftWall() == true
                && currentCell.getRightWall() == true) {
            return true;
        }
        return false;
    }
    
    
    /** Check if possible to move.
     *
     * @return true if yes, false if no
     */
    public boolean possibleToMove() {
        int blockedDirections = 0;
        
        if (currentCell.getY() > 0) {
            if (currentCell.getUpperWall() == true || visitedTwice(0)) {
                blockedDirections++;
            }
        } else {
            blockedDirections++;
        }
        
        if (currentCell.getX() > 0) {
            if (currentCell.getLeftWall() == true || visitedTwice(1)) {
                blockedDirections++;
            }
        } else {
            blockedDirections++;
        }
        
        if (currentCell.getY() < maze.getHeight() - 1) {
            if (currentCell.getLowerWall() == true || visitedTwice(2)) {
                blockedDirections++;
            }
        } else {
            blockedDirections++;
        }
        
        if (currentCell.getX() < maze.getWidth() - 1) {
            if (currentCell.getRightWall() == true || visitedTwice(3)) {
                blockedDirections++;
            }
        } else {
            blockedDirections++;
        }
        
        if (blockedDirections == 4) {
            return false;
        }
        return true;
    }
    
    
    /** Find a cell from the first row with upper wall missing.
     *
     * @return true if first cell found, false if not
     */
    public boolean findFirstCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                firstCell = maze.getCell(i, 0);
                currentCell = maze.getCell(i, 0);
                direction = 2;
                return true;
            }
        }
        System.out.println("No first cell");
        return false;
    }
    
    
    /** Find a cell from the last row with lower wall missing.
     *
     * @return true if last cell found, false if not
     */
    
    public boolean findLastCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, maze.getHeight() - 1).getLowerWall() == false) {
                lastCell = maze.getCell(i, maze.getHeight() - 1);
                return true;
            }
        }
        System.out.println("No last cell");
        return false;
    }
}
