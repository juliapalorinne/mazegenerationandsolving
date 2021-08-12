package maze;

/** Class handles walls between the cells of the maze.
 *
 * @author julia
 */
public class Wall {
    
    private boolean inRoute = false;
    private boolean open = false;
    private final Cell cell1;
    private final Cell cell2;
    private int weight;
    
    
    /** Create a wall between given cells.
     *
     * @param cell1 first cell
     * @param cell2 second cell
     */
    public Wall(Cell cell1, Cell cell2, int weight) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.weight = weight;
    }
    
    public Cell[] getCells() {
        Cell[] cells = {cell1, cell2};
        return cells;
    }
    
    public Cell getCell1() {
        return this.cell1;
    }
    
    public Cell getCell2() {
        return this.cell2;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public boolean checkIfInRoute() {
        return this.inRoute;
    }
    
    public void addToRoute() {
        this.inRoute = true;
    }
    
    public boolean checkIfOpen() {
        return this.open;
    }
    
    public void openTheWall() {
        this.open = true;
    }
    
    public void closeTheWall() {
        this.open = false;
    }
    
}
