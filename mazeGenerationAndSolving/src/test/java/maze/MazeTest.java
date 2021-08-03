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
        maze.removeWall(c, 0);
        assertTrue(maze.getCell(2, 1).getLowerWall() == false);
        
        maze.removeWall(c, 1);
        assertTrue(maze.getCell(1, 2).getRightWall() == false);
        
        maze.removeWall(c, 2);
        assertTrue(maze.getCell(2, 3).getUpperWall() == false);
        
        maze.removeWall(c, 3);
        assertTrue(maze.getCell(3, 2).getLeftWall() == false);
    }
    
    @Test
    public void wallIsNotRemovedFromBorder() {
        Cell c = new Cell(0, 0);
        maze.removeWall(c, 0);
         assertTrue(maze.getCell(0, 0).getUpperWall() == true);
    }
    
    
}
