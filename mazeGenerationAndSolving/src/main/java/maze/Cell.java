package maze;

public class Cell {
    
    private int x;
    private int y;
    private int visited;
    
    private boolean upperWall = true;
    private boolean rightWall = true;
    private boolean lowerWall = true;
    private boolean leftWall = true;
    
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        visited = 0;
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
        return visited;
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
        upperWall = false;
    }
    
    public void removeLeftWall() {
        leftWall = false;
    }
    
    public void removeLowerWall() {
        lowerWall = false;
    }
    
    public void removeRightWall() {
        rightWall = false;
    }
    
    public void visit() {
        this.visited++;
    }
    
    
    
    
}
