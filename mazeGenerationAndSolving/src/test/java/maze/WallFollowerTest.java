package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class WallFollowerTest {
    WallFollower wallFollower;
    DepthfirstSearch search;
    int width;
    int height;
    
    
    @Before
    public void setUp() {
        this.width = 50;
        this.height = 50;
        
        this.search = new DepthfirstSearch(width, height);
        search.run();
        
        this.wallFollower = new WallFollower(search.maze);
    }
    
    
    @Test
    public void findFirstCellFindsCell() {
        search.maze.getCell(0, 0).addUpperWall();
        assertTrue(wallFollower.findFirstCell() == false);
        
        search.maze.getCell(7, 0).removeUpperWall();
        
        assertTrue(wallFollower.findFirstCell() == true);
        Cell cell = (Cell) wallFollower.queue.pop(); 
        assertTrue(cell.getY() == 0);
        assertTrue(cell.getX() == 7);
    }
    
    @Test
    public void findLastCellFindsCell() {
        search.maze.getCell(width - 1, height - 1).addLowerWall();
        assertTrue(wallFollower.findLastCell() == false);
        
        search.maze.getCell(4, height - 1).removeLowerWall();
        
        assertTrue(wallFollower.findLastCell() == true);
        Cell cell = wallFollower.lastCell;
        assertTrue(cell.getY() == height - 1);
        assertTrue(cell.getX() == 4);
    }
    
    @Test
    public void findDirectionFindsDirection() {
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[j][i] = new Cell(i, j);
            }
        }
        
        Maze maze = new Maze(cells);
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
        
        maze.printMaze();
        
        wallFollower = new WallFollower(maze);
        
        wallFollower.currentCell = maze.getCell(0, 0);
        wallFollower.directions.addLast(2);
        
        // Step 1
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 2);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 1);
        
        // Step 2
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 2);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 3
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 3);
        assertTrue(wallFollower.currentCell.getX() == 1);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 4
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 1);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 2);
        
        // Step 5
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 0);
        assertTrue(wallFollower.currentCell.getX() == 0);
        assertTrue(wallFollower.currentCell.getY() == 1);
        
        // Step 6
        
        wallFollower.findDirection();
        
        assertTrue((int) wallFollower.directions.getLast() == 3);
        assertTrue(wallFollower.currentCell.getX() == 1);
        assertTrue(wallFollower.currentCell.getY() == 1);
    
    }
            
}
