package maze;

/** Class handles cells within the maze.
 *
 * @author julia
 */
public class Cell {
    
    private final int x;
    private final int y;
    private int visited;
    
    private boolean upperWall = true;
    private boolean rightWall = true;
    private boolean lowerWall = true;
    private boolean leftWall = true;
    
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = 0;
    }
    
    public int[] getCoordinates() {
        int[] coordinates = {x, y};
        return coordinates;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int numberOfVisits() {
        return this.visited;
    }
    
    public boolean getUpperWall() {
        return this.upperWall;
    }
    
    public boolean getLeftWall() {
        return this.leftWall;
    }
    
    public boolean getLowerWall() {
        return this.lowerWall;
    }
    
    public boolean getRightWall() {
        return this.rightWall;
    }
    
    public void removeUpperWall() {
        this.upperWall = false;
    }
    
    public void removeLeftWall() {
        this.leftWall = false;
    }
    
    public void removeLowerWall() {
        this.lowerWall = false;
    }
    
    public void removeRightWall() {
        this.rightWall = false;
    }
    
    public void visit() {
        this.visited++;
    }
    
    
    
    
}
