package mazegeneration;

import maze.Cell;
import maze.Maze;


/** Superclass for maze generation algorithms.
 *
 * @author julia
 */
public class MazeGenerationAlgorithm {
    
    public Maze maze;
    public int cells;
    Cell[] parents;
    
    
    
    /** Generate a new maze with given dimensions.
     *
     * @param width number of cells
     * @param height number of cells
     */
    public void generateNewMaze(int width, int height) {
        this.cells = width * height;
        int number = 0;
        
        Cell[][] cellArray = new Cell[height][width];
        this.parents = new Cell[cells];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cell.addNumber(number);
                cellArray[j][i] = cell;
                parents[number] = cell;
                number++;
            }
        }
        this.maze = new Maze(cellArray);
    }
}
