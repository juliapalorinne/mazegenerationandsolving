package maze;

import java.util.Arrays;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MazeTest {
    int wildth;
    int height;
    Maze maze;
    
        
    @Before
    public void setUp() {
        this.wildth = 100;
        this.height = 50;
        
        Cell[][] cells = new Cell[height][wildth];
        
        for (int i = 0; i < wildth; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cells[j][i] = cell;
            }
        }
        this.maze = new Maze(cells);
    }
    
    
    @Test
    public void removeWallRemovesCorrectWall() {
        Cell c = new Cell(2, 2);
        c.removeUpperWall();
        maze.removeWall(c);
        assertTrue(maze.getCell(2, 1).getLowerWall() == false);
        
        c.removeLeftWall();
        maze.removeWall(c);
        assertTrue(maze.getCell(1, 2).getRightWall() == false);
        
        c.removeLowerWall();
        maze.removeWall(c);
        assertTrue(maze.getCell(2, 3).getUpperWall() == false);
        
        c.removeRightWall();
        maze.removeWall(c);
        assertTrue(maze.getCell(3, 2).getLeftWall() == false);
    }
    
    
}
