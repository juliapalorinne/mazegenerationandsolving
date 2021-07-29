package mazeGenerationAndSolving;

public class Cell {
    
    public int x;
    public int y;
    public int visited;
    
    public boolean upperWall = true;
    public boolean rightWall = true;
    public boolean lowerWall = true;
    public boolean leftWall = true;
    
    
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
    
    public void removeUpperWall() {
        upperWall = false;
    }
    
    public void removeRightWall() {
        rightWall = false;
    }
    
    public void removeLowerWall() {
        lowerWall = false;
    }
    
    public void removeLeftWall() {
        leftWall = false;
    }
}
