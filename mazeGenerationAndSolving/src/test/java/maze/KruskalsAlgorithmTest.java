package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class KruskalsAlgorithmTest {
    KruskalsAlgorithm search;
    
        
    @Before
    public void setUp() {
        int width = 50;
        int height = 50;
        
        this.search = new KruskalsAlgorithm(width, height);
    }
    
    
    @Test
    public void generateMazeWorks() {
        assertTrue(this.search.maze.getWidth() == 50);
        assertTrue(this.search.maze.getHeight() == 50);
    }
    
    @Test
    public void getRandomWeightGivesCorrectNumbers() {
        for (int i = 0; i < 50; i++) {
            int weight = search.getRandomWeight();
            assertTrue(weight >= 0 && weight < 50 * 50 * 100);
        }
    }
    
    @Test
    public void createWallsCreatesAllWalls() {
        assertTrue(search.walls.size() == 49 * 50 + 50 * 49);
        Wall wall = search.walls.get(4);
        assertTrue(wall.getCell1().getX() == 2);
        assertTrue(wall.getCell1().getY() == 0);
        assertTrue(wall.getCell2().getX() == 3);
        assertTrue(wall.getCell2().getY() == 0);
        
        wall = search.walls.get(100);
        assertTrue(wall.getCell1().getX() == 0);
        assertTrue(wall.getCell1().getY() == 1);
        assertTrue(wall.getCell2().getX() == 0);
        assertTrue(wall.getCell2().getY() == 2);
    }
    
}
