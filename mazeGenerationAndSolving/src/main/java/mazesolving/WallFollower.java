package mazesolving;

import maze.Maze;


/** Class for solving a maze with right wall-follower algorithm.
 *
 * @author julia
 */
public class WallFollower extends MazeSolvingAlgorithm {
    
    
    /** Start a new wall-follower with given maze.
     *
     * @param maze a new maze
     */
    public WallFollower(Maze maze) {
        this.maze = maze;
    }
    
    
    /** Run the wall-follower algortihm.
     * Find a route or print error message.
     */
    public void run() {
        if (findRoute() == false) {
            System.out.println("No routes or the maze has loops.");            
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
            findDirection();
            if (currentCell.numberOfVisits() > 4) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /** Find direction to which algorithm continues.
     * Up for 0, left for 1, down for 2 and right for 3.
     */
    public void findDirection() {
        int dir = direction;
        boolean found = false;
        
        while (found == false) {
            if (dir == 0) {
                if (currentCell.getRightWall() == false) {
                    currentCell = maze.getCell(currentCell.getX() + 1, currentCell.getY());
                    found = true;
                    direction = 3;
                } else {
                    dir++;
                }
            }
            if (dir == 1) {
                if (currentCell.getUpperWall() == false) {
                    if (currentCell.getY() == firstCell.getY()
                            && currentCell.getX() == firstCell.getX()) {
                        dir++;
                    } else {
                        currentCell = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
                        found = true;
                        direction = 0;
                    }
                } else {
                    dir++;
                }
            }
            if (dir == 2) {
                if (currentCell.getLeftWall() == false) {
                    currentCell = maze.getCell(currentCell.getX() - 1, currentCell.getY());
                    found = true;
                    direction = 1;
                } else {
                    dir++;
                }
            }
            if (dir == 3) {
                if (currentCell.getLowerWall() == false) {
                    currentCell = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
                    found = true;
                    direction = 2;
                } else {
                    dir = 0;
                }
            }
        }
        currentCell.visit();
        currentCell.addToRoute();
    }
    
}
