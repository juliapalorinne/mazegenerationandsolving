import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import main.*;
import maze.*;
import mazeGeneration.*;
import mazeSolving.*;

public class Stepdefs {
    BreadthfirstSearch breadthfirst;
    Maze maze;
    
    // StubIo io;
        
    @Before
    public void setup(){
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
        
        breadthfirst = new BreadthfirstSearch(maze);
        breadthfirst.firstCell = maze.getCell(0, 0);
    }
    
    @Given("a maze is created with a depth-first search")
    public void mazeIsGeneratedWithDepthfirstSearch() {
        
    }
    
    @When("command printMaze is selected")
    public void commandPrintMazeIsGiven() {
        
    }
      
    @Then("system will respond with {string}")
    public void mazeIsPrintedCorrectly(String expectedOutput) {
        //assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Then("system's response contains {string}")
    public void responseContains(String expectedOutput) {
        // boolean contains = false;
        // for (String s : io.getPrints()) {
        //     if (s.contains(expectedOutput)) {
        //         contains = true;
        //     }
        // }
        // assertTrue(contains);
    }
}