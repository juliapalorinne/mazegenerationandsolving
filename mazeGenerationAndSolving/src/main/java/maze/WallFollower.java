package maze;

import java.util.ArrayDeque;
import java.util.Stack;

/** Class for solving a maze with wall-follower algorithm.
 *
 * @author julia
 */
public class WallFollower {
    
    Stack queue;
    ArrayDeque directions;
    Maze maze;
    Cell lastCell;
    Cell currentCell;
    
    
    /** Start a new wall-follower with given maze
     *
     * @param maze a new maze
     */
    public WallFollower(Maze maze) {
        this.queue = new Stack();
        this.directions = new ArrayDeque();
        
        this.maze = maze;
    }
    
    
    /** Run the wall-follower.
     * Find a route and print it if possible.
     */
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes or the maze has loops.");            
        } else {
            maze.printMaze();
        }
    }
    
    
    public boolean findRoute() {
        if (!findFirstCell() || !findLastCell()) {
            return false;
        }
        
        queue.push(currentCell);
        directions.addLast(2);
        
        while (currentCell.getX() != lastCell.getX() && currentCell.getY() != lastCell.getY()) {
            findDirection();
        }
        
        return true;
    }

    
    public void findDirection() {
        int dir = (int) directions.getLast();
        boolean found = false;
        
        while (found == false) {
            if (dir == 0) {
                if (currentCell.getRightWall() == false) {
                    currentCell = maze.getCell(currentCell.getX() + 1, currentCell.getY());
                    found = true;
                } else {
                    dir++;
                }
            } else if (dir == 1) {
                if (currentCell.getUpperWall() == false) {
                    currentCell = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
                    found = true;
                } else {
                    dir++;
                }
            } else if (dir == 2) {
                if (currentCell.getLeftWall() == false) {
                    currentCell = maze.getCell(currentCell.getX() - 1, currentCell.getY());
                    found = true;
                } else {
                    dir++;
                }
            } else if (dir == 3) {
                if (currentCell.getLowerWall() == false) {
                    currentCell = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
                    found = true;
                } else {
                    dir = 0;
                }
            }
        }
        
        directions.addLast(dir);
        queue.push(currentCell);
    }
    
    
    public boolean findFirstCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                queue.push(maze.getCell(i, 0));
                currentCell = maze.getCell(i, 0);
                return true;
            }
        }
        System.out.println("No first cell");
        return false;
    }
    
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
