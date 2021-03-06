package maze;

import java.util.ArrayList;
import java.util.List;

/** Class for handling a maze.
 *
 * @author julia
 */
public class Maze {
    
    private final int height;
    private final int width;
    
    public Cell[][] cells;
    
    
    /** Create a maze with given cells.
     *
     * @param cells array of cells
     */
    public Maze(Cell[][] cells) {
        this.cells = cells;
        this.height = cells.length;
        this.width = cells[0].length;
    }
    
       
    public int getWidth() {
        return this.width;
    }
    
    
    public int getHeight() {
        return this.height;
    }
    
    
    /** Get the cell with given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     * @return a cell
     */
    public Cell getCell(int x, int y) {
        return cells[y][x];
    }
    
    
    /** Remove a wall from the given cell and its neighbour.
     *
     * @param cell the cell where the wall is removed
     * @param dir number between 0 and 3 for direction
     */
    public void removeWall(Cell cell, int dir) {
        int x = cell.getX();
        int y = cell.getY();
        
        if (dir == 0 && y > 0) {
            cell.removeUpperWall();
            cells[y - 1][x].removeLowerWall();
        }
        if (dir == 1 && x > 0) {
            cell.removeLeftWall();
            cells[y][x - 1].removeRightWall();
        }
        if (dir == 2 && y < height - 1) {
            cell.removeLowerWall();
            cells[y + 1][x].removeUpperWall();
        }
        if (dir == 3 && x < width - 1) {
            cell.removeRightWall();
            cells[y][x + 1].removeLeftWall();
        }
    }
    
    
    /** Add a wall from the given cell and its neighbour.
     *
     * @param cell the cell where the wall is removed
     * @param dir number between 0 and 3 for direction
     */
    public void addWall(Cell cell, int dir) {
        int x = cell.getX();
        int y = cell.getY();
        
        if (dir == 0 && y > 0) {
            cell.addUpperWall();
            cells[y - 1][x].addLowerWall();
        }
        if (dir == 1 && x > 0) {
            cell.addLeftWall();
            cells[y][x - 1].addRightWall();
        }
        if (dir == 2 && y < height - 1) {
            cell.addLowerWall();
            cells[y + 1][x].addUpperWall();
        }
        if (dir == 3 && x < width - 1) {
            cell.addRightWall();
            cells[y][x + 1].addLeftWall();
        }
    }
    
    
    /**
     * Reset visits to all cells.
     */
    public void resetVisits() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[j][i].resetVisits();
            }
        }
    }
    
    
    /**
     * Remove all cells from routes.
     */
    public void resetRoutes() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[j][i].removeFromRoute();
            }
        }
    }
    
    
    /** Print the maze.
     * Character # for wall and o for route.
     */
    public void printMaze() {
        
        for (int i = 0; i < width; i++) {
            if (cells[0][i].getUpperWall() == true) {
                System.out.print("##");
            } else if (cells[0][i].checkIfInRoute() == false) {
                System.out.print("# ");
            } else {
                System.out.print("#o");
            }            
        }
        
        System.out.print("#\r\n");
        
        for (int j = 0; j < height; j++) {
            String s = "";
            
            for (int i = 0; i < width; i++) {
                Cell c = cells[j][i];
                
                if (c.getLeftWall() == true) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
                
                if (c.checkIfInRoute() == true) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
                
                if (c.getLowerWall() == true) {
                    s = s + "##";
                } else {
                    s = s + "# ";
                }
            }
            System.out.print("#\r\n");
            System.out.println(s + "#");
        }
    }
    
    
    /** Maze to string.
     * Character # for wall and o for route.
     *
     * @return the maze as string
     */
    public List<String> mazeToString() {
        ArrayList<String> rows = new ArrayList<>();
        
        String current = "";
        
        for (int i = 0; i < width; i++) {
            if (cells[0][i].getUpperWall() == true) {
                current = current + "##";
            } else if (cells[0][i].checkIfInRoute() == false) {
                current = current + "# ";
            } else {
                current = current + "#o";
            }            
        }
        
        current = current + "#";
        rows.add(current);
        
        
        for (int j = 0; j < height; j++) {
            current = "";
            String next = "";
            
            for (int i = 0; i < width; i++) {
                Cell c = cells[j][i];
                
                if (c.getLeftWall() == true) {
                    current = current + "#";
                } else {
                    current = current + " ";
                }
                
                if (c.checkIfInRoute() == true) {
                    current = current + "o";
                } else {
                    current = current + " ";
                }
                
                if (c.getLowerWall() == true) {
                    next = next + "##";
                } else {
                    next = next + "# ";
                }
            }
            current = current + "#";
            next = next + "#";
            
            rows.add(current);
            rows.add(next);
        }
        
        return rows;
    }
}
