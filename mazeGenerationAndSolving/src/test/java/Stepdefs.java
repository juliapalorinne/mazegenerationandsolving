import mazesolving.BreadthfirstSearch;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import maze.*;
import maze.io.StubIo;
import maze.ui.MazeUi;

public class Stepdefs {
    Maze maze;
    
    StubIo io;
    MazeUi ui;
    List<String> inputLines;
        
    @Before
    public void setup(){
        inputLines = new ArrayList<>();     
        io = new StubIo(inputLines);
        ui = new MazeUi(io);
    }
    
    @Given("a maze is created")
    public void mazeIsCreated() {
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
    }
    
    
    @When("the maze is printed")
    public void mazeIsPrinted() {
        List<String> list = maze.mazeToString();
        for (int i = 0; i < list.size(); i++) {
            io.print(list.get(i));
        }
    }
    
    @Then("system will respond with {string}")
    public void mazeIsPrintedCorrectly(String expectedOutput) {
        String print = "";
        for (String s : io.getPrints()) {
             print = print + s + "/";
         }
        assertTrue(print.equals(expectedOutput));
    }
    
    @Given("the option {string} is selected")
    public void optionSelected(String option) {
        inputLines.add(option);
    }
    
    @Given("the height is set to {string}")
    public void setHeight(String height) {
        inputLines.add(height);
    }
    
    @Given("the width is set to {string}")
    public void setWidth(String width) {
        inputLines.add(width);
    }
    
    
    @When("the the maze is generated and printed")
    public void mazeIsGenerated() {
        io = new StubIo(inputLines);
        ui = new MazeUi(io);
        ui.start();
    }
    
    @Then("the width of maze is {string} characters")
    public void theWidthIsCorrect(String width) {
        int w = Integer.parseInt(width);
        
        for (String s : io.getPrints()) {
            if (s.contains("#")) {
                assertTrue(s.length() == w);
            }
        }
    }
    
    @Then("the length of maze is {string} characters")
    public void theLengthIsCorrect(String length) {
        int l = Integer.parseInt(length);
        int lines = 0;
        
        for (String s : io.getPrints()) {
            if (s.contains("#")) {
                lines++;
            }
        }
        assertTrue(l == lines);
    }
    
    @Then("system's response contains {string}")
    public void responseContains(String expectedOutput) {
        boolean contains = false; 
        for (String s : io.getPrints()) {
             if (s.contains(expectedOutput)) {
                 contains = true;
             }
         }
         assertTrue(contains);
    }
}