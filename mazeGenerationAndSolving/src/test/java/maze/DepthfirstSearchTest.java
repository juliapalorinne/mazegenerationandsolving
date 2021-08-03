package maze;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DepthfirstSearchTest {
    DepthfirstSearch search;
    
        
    @Before
    public void setUp() {
        int width = 50;
        int height = 50;
        
        this.search = new DepthfirstSearch(width, height);
        search.x = 0;
        search.y = 0;
    }
    
    
    @Test
    public void getRandomNumberGivesANumberInTheCorrectRange() {
        for (int i = 0; i < 20; i++) {
            int random = search.getRandomNumber();
            assertTrue(random < 4 && random >= 0);
        }
    }
    
    @Test
    public void chooseDirectionStaysWithinTheMaze() {
       for (int i = 0; i < 10; i++) {
            search.chooseDirection();
            assertTrue(search.x >= 0 && search.x < 50);
            assertTrue(search.y >= 0 && search.y < 50);
        }
    }
    
    
    @Test
    public void chooseDirectionChoosesFreeCell() {
        for (int i = 0; i < 10; i++) {
            search.chooseDirection();
            Maze maze = search.maze;
            Cell c = maze.getCell(search.x, search.y);
            int visited = c.numberOfVisits();
            assertTrue(visited == 0);
            c.visit();
            maze.removeWall(c);
        }
    }
    
}
