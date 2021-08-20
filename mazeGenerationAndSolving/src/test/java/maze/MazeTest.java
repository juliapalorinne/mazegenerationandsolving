package maze;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
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
        assertFalse(maze.getCell(2, 1).getLowerWall());
        
        maze.removeWall(c, 1);
        assertFalse(maze.getCell(1, 2).getRightWall());
        
        maze.removeWall(c, 2);
        assertFalse(maze.getCell(2, 3).getUpperWall());
        
        maze.removeWall(c, 3);
        assertFalse(maze.getCell(3, 2).getLeftWall());
    }
    
    @Test
    public void wallIsNotRemovedFromBorder() {
        Cell c = new Cell(0, 0);
        maze.removeWall(c, 0);
        assertTrue(maze.getCell(0, 0).getUpperWall());
    }
    
    @Test
    public void wallIsNotRemovedWhenCellIsOutsideMaze() {
        maze.removeWall(new Cell(100, 0), 0);
        assertTrue(maze.getCell(99, 0).getRightWall());
        
        maze.removeWall(new Cell(99, -1), 3);
        assertTrue(maze.getCell(99, 0).getUpperWall());
        
        maze.removeWall(new Cell(0, 50), 1);
        assertTrue(maze.getCell(0, 49).getLowerWall());
        
        maze.removeWall(new Cell(-1, 49), 2);
        assertTrue(maze.getCell(0, 49).getLeftWall());
    }
    
    
    @Test
    public void addWallAddsCorrectWall() {
        Cell c = new Cell(2, 2);
        maze.removeWall(c, 0);
        maze.removeWall(c, 1);
        maze.removeWall(c, 2);
        maze.removeWall(c, 3);
        
        maze.addWall(c, 0);
        assertTrue(maze.getCell(2, 1).getLowerWall());
        
        maze.addWall(c, 1);
        assertTrue(maze.getCell(1, 2).getRightWall());
        
        maze.addWall(c, 2);
        assertTrue(maze.getCell(2, 3).getUpperWall());
        
        maze.addWall(c, 3);
        assertTrue(maze.getCell(3, 2).getLeftWall());
    }
}
