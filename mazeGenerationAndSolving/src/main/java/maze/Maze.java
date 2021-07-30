package maze;

/** Class creates an empty maze with given dimensions.
 *
 * @author julia
 */
public class Maze {
    
    private final int height;
    private final int width;
    
    public Cell[][] cells;
    
    
    /** Creates a maze with given cells.
     *
     * @param cells array of cells
     */
    public Maze(Cell[][] cells) {
        this.cells = cells;
        this.height = cells.length;
        this.width = cells[0].length;
    }
    
    /** Removes a wall from the given cell and its neighbour(s).
     *
     * @param cell the cell where the wall is removed
     */
    public void removeWall(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        
        cells[y][x] = cell;
        
        if (cell.getUpperWall() == false && y > 0) {
            cells[y - 1][x].removeLowerWall();
        }
        if (cell.getLeftWall() == false && x > 0) {
            cells[y][x - 1].removeRightWall();
        }
        if (cell.getLowerWall() == false && y < height - 2) {
            cells[y + 1][x].removeUpperWall();
        }
        if (cell.getRightWall() == false && x < width - 2) {
            cells[y][x + 1].removeLeftWall();
        }
    }
    
    /** Returns the cell with given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     * @return a cell
     */
    public Cell getCell(int x, int y) {
        return cells[y][x];
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void printMaze() {
        
        for(int i = 0; i < width; i++) {
            System.out.print("##");
        }
        System.out.print("#\r\n");
        
        for (int j = 0; j < height; j++) {
            String s = "";
            
            for (int i = 0; i < width; i++) {
                Cell c = cells[j][i];
                
                if (c.getRightWall() == true) {
                    System.out.print("#");
                } else {
                    System.out.print("o");
                }
                System.out.print("o");
                
                if (c.getLowerWall() == true) {
                    s = s + "##";
                } else {
                    s = s + "#o";
                }
            }
            System.out.print("#\r\n");
            System.out.println(s + "#");
        }
    }
}
