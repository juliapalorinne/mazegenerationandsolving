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
    
   
    
}
