package maze;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


public class CellTest {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    
        
    @Before
    public void setUp() {
        this.cell1 = new Cell(0, 0);
        this.cell2 = new Cell(0, 0);
        this.cell3 = new Cell(0, 1);
        this.cell4 = new Cell(1, 1);
    }
    
    
    @Test
    public void getCoordinatesGivesCorrectCoordinates() {
        int [] c = cell3.getCoordinates();
        
        assertTrue(c[0] == 0 && c[1] == 1);
    }
    
    
    @Test
    public void removeUpperWallRemovesCorrectWall() {
        cell1.removeUpperWall();
        assertFalse(cell1.getUpperWall());
    }
    
    @Test
    public void removeLeftWallRemovesCorrectWall() {
        cell1.removeLeftWall();
        assertFalse(cell1.getLeftWall());
    }
    
    @Test
    public void removeLowerWallRemovesCorrectWall() {
        cell1.removeLowerWall();
        assertFalse(cell1.getLowerWall());
    }
    
    @Test
    public void removeRightWallRemovesCorrectWall() {
        cell1.removeRightWall();
        assertFalse(cell1.getRightWall());
    }
    
    
}
