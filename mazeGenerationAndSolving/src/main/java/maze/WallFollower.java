package maze;

import java.util.ArrayDeque;

/** Class for solving a maze with wall-follower algorithm.
 *
 * @author julia
 */
public class WallFollower {
    
    ArrayDeque queue;
    Maze maze;
    Cell lastCell;
    
    public WallFollower(Maze maze) {
        this.queue = new ArrayDeque();
        this.maze = maze;
    }
    
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes or the maze has loops.");
        } else {
            
        }
    }
    
    public boolean findRoute() {
        if (!findFirstCell() || findLastCell()) {
            return false;
        }
        
        return true;
    }
    
    public boolean findFirstCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                queue.addLast(maze.getCell(i, 0));
                return true;
            }
        }
        return false;
    }
    
    public boolean findLastCell() {
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, maze.getWidth() - 1).getLowerWall() == false) {
                lastCell = maze.getCell(i, maze.getWidth() - 1);
                return true;
            }
        }
        return false;
    }
}
