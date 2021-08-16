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
        }
    }
    
    
    /** Find a route through maze.
     *
     * @return true if route found, false if not
     */
    public boolean findRoute() {
        if (!findFirstCell() || !findLastCell()) {
            return false;
        }
        
        currentCell.addToRoute();
        direction = 2;
        
        while (currentCell.getX() != lastCell.getX() || currentCell.getY() != lastCell.getY()) {
            search();
            if (currentCell.numberOfVisits() > 2) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /** Search for unvisited cell neighbouring current cell.
     * Move to cell and repeat search.
     */
    public void search() {
        currentCell.visit();
        
        if (direction == 0) {
            if (moveUp()) {
                search();
            }
        } else if (direction == 1) {
            if (moveLeft()) {
                search();
            }
        } else if (direction == 2) {
            if (moveDown()) {
                search();    
            }
        } else if (direction == 3) {
            if (moveRight()) {
                search();    
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
        if (checkIfLeftAndRightWall()) {
            currentCell.visit();
        }
        
        if (currentCell.getY() > 0) {
            if (!checkIfVisitedTwice(0)) {
                currentCell = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
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
        if (checkIfUpperAndLowerWall()) {
            currentCell.visit();
        }
        
        if (currentCell.getX() > 0) {
            if (!checkIfVisitedTwice(1)) {
                currentCell = maze.getCell(currentCell.getX() - 1, currentCell.getY());
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
        if (checkIfLeftAndRightWall()) {
            currentCell.visit();
        }
        
        if (currentCell.getY() < maze.getHeight() - 1) {
            if (!checkIfVisitedTwice(2)) {
                currentCell = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
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
        if (checkIfUpperAndLowerWall()) {
            currentCell.visit();
        }
        
        if (currentCell.getX() < maze.getWidth() - 1) {
            if (!checkIfVisitedTwice(3)) {
                currentCell = maze.getCell(currentCell.getX() + 1, currentCell.getY());
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
    public boolean checkIfVisitedTwice(int direction) {
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
        
        if (visits < 2) {
            return false;
        }
        return true;
    }
    
    
    /** When moving left or right, check if walls on both sides.
     *
     * @return true if yes, false if no
     */
    public boolean checkIfUpperAndLowerWall() {
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
    public boolean checkIfLeftAndRightWall() {
        if (currentCell.getLeftWall() == true
                && currentCell.getRightWall() == true) {
            return true;
        }
        return false;
    }
    
    
    /** Find a cell from the first row with upper wall missing.
     *
     * @return true if first cell found, false if not
     */
    public boolean findFirstCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                queue.push(maze.getCell(i, 0));
                firstCell = maze.getCell(i, 0);
                currentCell = maze.getCell(i, 0);
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
