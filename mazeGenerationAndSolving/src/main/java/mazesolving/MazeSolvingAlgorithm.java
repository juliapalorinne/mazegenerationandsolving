package mazesolving;

import maze.Cell;
import maze.Maze;

/** Superclass for maze solving algorithms.
 *
 * @author julia
 */
public class MazeSolvingAlgorithm {
    
    public Maze maze;
    public Cell firstCell;
    public Cell lastCell;
    public Cell currentCell;
    
    public int visited;
    public int numberOfCells;
    public int[][] distance;
    public int[][] previousCell;
    public boolean loops = false;
    public boolean routes;
    public int direction;
    
    
    /** Find a cell from the first row with upper wall missing.
     *
     * @return true if first cell found, false if not
     */
    public boolean findFirstCell() {
        this.numberOfCells = maze.getHeight() * maze.getWidth();
        this.distance = new int[maze.getHeight()][maze.getWidth()];
        this.previousCell = new int[maze.getHeight()][maze.getWidth()];
        this.visited = 0;
        
        for (int i = 0; i < maze.getWidth(); i++) {
            if (maze.getCell(i, 0).getUpperWall() == false) {
                firstCell = maze.getCell(i, 0);
                currentCell = maze.getCell(i, 0);
                distance[firstCell.getY()][firstCell.getX()] = 0;
                previousCell[firstCell.getY()][firstCell.getX()] = 1;
                return true;
            }
        }
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
        return false;
    }
}
