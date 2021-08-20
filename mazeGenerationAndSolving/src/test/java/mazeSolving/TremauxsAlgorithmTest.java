package mazeSolving;

import maze.Cell;
import maze.Maze;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class TremauxsAlgorithmTest {
    TremauxsAlgorithm search;
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
        
        search = new TremauxsAlgorithm(maze);
        
        search.currentCell = maze.getCell(0, 0);
        search.firstCell = maze.getCell(0, 0);
        search.lastCell = maze.getCell(2, 2);
        search.direction = 2;
    }
    
    @Test
    public void findFirstCellFindsCell() {
        assertTrue(search.findFirstCell());
        maze.getCell(0, 0).addUpperWall();
        assertFalse(search.findFirstCell());
        
        maze.getCell(2, 0).removeUpperWall();
        
        assertTrue(search.findFirstCell());
        Cell cell = search.firstCell;
        assertTrue(cell.getY() == 0);
        assertTrue(cell.getX() == 2);
    }
    
    @Test
    public void findLastCellFindsCell() {
        assertTrue(search.findLastCell());
        maze.getCell(2, 2).addLowerWall();
        assertFalse(search.findLastCell());
        
        maze.getCell(1, 2).removeLowerWall();
        
        assertTrue(search.findLastCell());
        Cell cell = search.lastCell;
        assertTrue(cell.getY() == 2);
        assertTrue(cell.getX() == 1);
    }
    
    @Test
    public void getRandomNumberGivesANumberInTheCorrectRange() {
        for (int i = 0; i < 20; i++) {
            int random = search.getRandomNumber();
            assertTrue(random < 4 && random >= 0);
        }
    }
    
    @Test
    public void leftAndRightWallReturnsCorrectAnswer() {
        assertTrue(search.leftAndRightWall());
        search.currentCell = maze.getCell(0, 1);
        assertFalse(search.leftAndRightWall());
    }
    
    @Test
    public void upperAndLowerWallReturnsCorrectAnswer() {
        assertFalse(search.upperAndLowerWall());
        search.currentCell = maze.getCell(1, 2);
        assertTrue(search.upperAndLowerWall());
    }
    
    @Test
    public void moveUpWorksCorrectly() {
        assertFalse(search.moveUp());
        assertTrue(search.currentCell.getY() == 0);
        
        search.currentCell = maze.getCell(0, 2);
        assertTrue(search.moveUp());
        assertTrue(search.currentCell.getY() == 1);
        
        search.maze.getCell(0, 0).visit();
        search.maze.getCell(0, 0).visit();
        assertFalse(search.moveUp());
        assertTrue(search.currentCell.getY() == 1);
        
        search.currentCell = maze.getCell(1, 2);
        assertFalse(search.moveUp());
    }
    
    @Test
    public void moveLeftWorksCorrectly() {
        assertFalse(search.moveLeft());
        assertTrue(search.currentCell.getX() == 0);
        
        search.currentCell = maze.getCell(2, 0);
        assertTrue(search.moveLeft());
        assertTrue(search.currentCell.getX() == 1);
        assertFalse(search.moveLeft());
        assertTrue(search.currentCell.getX() == 1);
        
        search.currentCell = maze.getCell(1, 1);
        search.maze.getCell(0, 1).visit();
        search.maze.getCell(0, 1).visit();
        assertFalse(search.moveLeft());
        assertTrue(search.currentCell.getX() == 1);
    }
    
    @Test
    public void moveDownWorksCorrectly() {
        assertTrue(search.moveDown());
        assertTrue(search.currentCell.getY() == 1);
        
        search.maze.getCell(0, 2).visit();
        search.maze.getCell(0, 2).visit();
        assertFalse(search.moveDown());
        assertTrue(search.currentCell.getY() == 1);
        
        search.currentCell = maze.getCell(1, 0);
        assertTrue(search.moveDown());
        assertTrue(search.currentCell.getY() == 1);
        assertFalse(search.moveDown());
        assertTrue(search.currentCell.getY() == 1);
    }
    
    @Test
    public void moveRightWorksCorrectly() {
        assertFalse(search.moveRight());
        assertTrue(search.currentCell.getX() == 0);
        
        search.currentCell = maze.getCell(0, 1);
        search.maze.getCell(1, 1).visit();
        search.maze.getCell(1, 1).visit();
        assertFalse(search.moveRight());
        assertTrue(search.currentCell.getX() == 0);
        
        search.currentCell = maze.getCell(1, 0);
        assertTrue(search.moveRight());
        assertTrue(search.currentCell.getX() == 2);
        assertFalse(search.moveRight());
        assertTrue(search.currentCell.getX() == 2);
    }
    
    @Test
    public void chooseDirectionStaysWithinTheMaze() {
        for (int i = 0; i < 10; i++) {
            search.chooseDirection();
            assertTrue(search.currentCell.getX() >= 0 && search.currentCell.getX() < 3);
            assertTrue(search.currentCell.getY() >= 0 && search.currentCell.getY() < 3);
        }
    }
    
    
    @Test
    public void chooseDirectionChoosesCellNotVisitedTwice() {
        search.currentCell = search.maze.getCell(1, 1);
        search.maze.getCell(1, 0).visit();
        search.maze.getCell(1, 0).visit();
        
        for (int i = 0; i < 4; i++) {
            search.chooseDirection();
            int visited = search.currentCell.numberOfVisits();
            assertTrue(visited < 2);
            assertFalse(search.currentCell.getX() == 1 && search.currentCell.getY() == 0);
            search.currentCell.visit();
        }
    }
    
    @Test
    public void possibleToMoveReturnsCorrectAnswer() {
        assertTrue(search.possibleToMove());
        search.search();
        assertTrue(search.possibleToMove());
        search.search();
        assertTrue(search.possibleToMove());
        search.search();
        assertTrue(search.possibleToMove());
        search.search();
        assertTrue(search.possibleToMove());
        
        search.currentCell = search.maze.getCell(1, 2);
        search.maze.getCell(0, 2).visit();
        search.maze.getCell(0, 2).visit();
        assertFalse(search.possibleToMove());
    }
    
    @Test
    public void findRouteReturnsFalseIfFirstCellMissing() {
        maze.getCell(0, 0).addUpperWall();
        assertTrue(search.findRoute() == false);
    }
    
    @Test
    public void findRouteReturnsFalseIfLastCellMissing() {
        maze.getCell(2, 2).addLowerWall();
        assertTrue(search.findRoute() == false);
    }
    
//    @Test
//    public void findRouteReturnsFalseIfNoRouteIsFound() {
//        maze.getCell(1, 1).addUpperWall();
//        maze.getCell(1, 0).addLowerWall();
//        assertTrue(search.findRoute() == false);
//    }
    
//    @Test
//    public void findRouteReturnsTrueIfRouteIsFound() {
//        assertTrue(search.findRoute() == true);
//    }
}
