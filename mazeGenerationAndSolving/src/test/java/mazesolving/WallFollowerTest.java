package mazesolving;

import maze.Cell;
import maze.Maze;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


public class WallFollowerTest {
    WallFollower wallFollower;
    Maze maze;
    
    @Before
    public void setUp() {
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[j][i] = new Cell(i, j);
            }
        }
        
        maze = new Maze(cells);
        maze.getCell(0, 0).removeUpperWall();
        maze.removeWall(maze.getCell(0, 0), 2);
        maze.removeWall(maze.getCell(0, 1), 2);
        maze.removeWall(maze.getCell(0, 1), 3);
        maze.removeWall(maze.getCell(0, 2), 3);
        maze.removeWall(maze.getCell(1, 1), 0);
        maze.removeWall(maze.getCell(1, 0), 3);
        maze.removeWall(maze.getCell(2, 0), 2);
        maze.removeWall(maze.getCell(2, 1), 2);
        maze.getCell(2, 2).removeLowerWall();
        
        wallFollower = new WallFollower(maze);
        
        wallFollower.currentCell = maze.getCell(0, 0);
        wallFollower.firstCell = maze.getCell(0, 0);
        wallFollower.direction = 2;
    }
    
    
    @Test
    public void findFirstCellFindsCell() {
        maze.getCell(0, 0).addUpperWall();
        assertFalse(wallFollower.findFirstCell());
        
        maze.getCell(2, 0).removeUpperWall();
        
        assertTrue(wallFollower.findFirstCell());
        assertTrue(wallFollower.firstCell.getY() == 0);
        assertTrue(wallFollower.firstCell.getX() == 2);
    }
    
    @Test
    public void findLastCellFindsCell() {
        maze.getCell(2, 2).addLowerWall();
        assertFalse(wallFollower.findLastCell());
        
        maze.getCell(1, 2).removeLowerWall();
        
        assertTrue(wallFollower.findLastCell());
        Cell cell = wallFollower.lastCell;
        assertTrue(cell.getY() == 2);
        assertTrue(cell.getX() == 1);
        // System.out.println("2");
    }
    
    @Test
    public void findDirectionFindsDirection() {
        
        // Step 1
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 2);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 1);
        
        // Step 2
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 2);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 3
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 3);
        assertTrue(wallFollower.currentCell.getX() == 1);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 4
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 1);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 5
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 0);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 1);
        
        // Step 6
        
        wallFollower.findDirection();
        
        assertTrue(wallFollower.direction == 3);
        assertTrue(wallFollower.currentCell.getX() == 1);
        assertTrue(wallFollower.currentCell.getY() == 1);
    }
    
    @Test
    public void findRouteReturnsFalseIfFirstCellMissing() {
        maze.getCell(0, 0).addUpperWall();
        assertFalse(wallFollower.findRoute());
    }
    
    @Test
    public void findRouteReturnsFalseIfLastCellMissing() {
        maze.getCell(2, 2).addLowerWall();
        assertFalse(wallFollower.findRoute());
    }
    
    @Test
    public void findRouteReturnsFalseIfNoRouteIsFound() {
        maze.getCell(1, 1).addUpperWall();
        maze.getCell(1, 0).addLowerWall();
        assertFalse(wallFollower.findRoute());
    }
    
    @Test
    public void findRouteReturnsTrueIfRouteIsFound() {
        assertTrue(wallFollower.findRoute());
    }
}
