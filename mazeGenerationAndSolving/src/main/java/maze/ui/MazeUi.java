package maze.ui;

import java.util.List;
import maze.Maze;
import maze.io.Io;
import mazegeneration.DepthfirstSearch;
import mazegeneration.KruskalsAlgorithm;
import mazesolving.BreadthfirstSearch;
import mazesolving.WallFollower;

/** User interface.
 *
 * @author julia
 */
public class MazeUi {
    private Io io;
    
    
    /** Create new user interface.
     *
     * @param io input-output service
     */
    public MazeUi(Io io) {
        this.io = io;
    }
    
    
    /** Start user interface.
     *
     */
    public void start() {
        io.print("Welcome to maze generation and solving!");
        
        while (true) {
            printGenerationOptions();
            String command = io.read("Give a command:");

            if (command.isEmpty()) {
                break;
            }

            if (command.trim().equals("1")) {
                startDepthfirstSearch();
            } else if (command.trim().equals("2")) {
                startKruskalsAlgorithmWithoutLoops();
            } else if (command.trim().equals("3")) {
                startKruskalsAlgorithmWithLoops();
            } else if (command.trim().equals("4")) {
                break;
            } else {
                io.print("Command does not exist! Try again.");
            }
        }
    }
    
    
    private void startDepthfirstSearch() {
        String heightInput = io.read("Give the height of the maze:");
        String widthInput = io.read("Give the width of the maze:");
        
        int height = Integer.parseInt(heightInput.trim());
        int width = Integer.parseInt(widthInput.trim());
        
        io.print("Maze generated with depth-first search:");
        DepthfirstSearch search = new DepthfirstSearch(width, height);
        search.run();

        Maze maze = search.maze;
        printMaze(maze);
        solve(maze);
    }
    
    private void startKruskalsAlgorithmWithoutLoops() {
        String heightInput = io.read("Give the height of the maze:");
        String widthInput = io.read("Give the width of the maze:");
        
        int height = Integer.parseInt(heightInput.trim());
        int width = Integer.parseInt(widthInput.trim());
        
        io.print("Maze generated with Kruskal's algorithm:");
        KruskalsAlgorithm search = new KruskalsAlgorithm(width, height);
        search.runSimpleMaze();

        Maze maze = search.maze;
        printMaze(maze);
        solve(maze);
    }
    
    private void startKruskalsAlgorithmWithLoops() {
        String heightInput = io.read("Give the height of the maze:");
        String widthInput = io.read("Give the width of the maze:");
        
        int height = Integer.parseInt(heightInput.trim());
        int width = Integer.parseInt(widthInput.trim());
        
        io.print("Maze generated with Kruskal's algorithm:");
        KruskalsAlgorithm search = new KruskalsAlgorithm(width, height);
        search.runLoopedMaze();

        Maze maze = search.maze;
        printMaze(maze);
        solve(maze);
    }
    
    
    private void solve(Maze maze) {
        while (true) {
            printSolvingOptions();
            String command = io.read("Give a command:");

            if (command.isEmpty()) {
                break;
            }

            if (command.trim().equals("1")) {
                startWallFollower(maze);
            } else if (command.trim().equals("2")) {
                startBreadthfirstSearch(maze);
            } else if (command.trim().equals("3")) {
                break;
            } else {
                io.print("Command does not exist! Try again.");
            }
        }
    }
    
    private void startWallFollower(Maze maze) {
        io.print("Solved with wall follower:");
        WallFollower search = new WallFollower(maze);
        search.run();
        printMaze(maze);
        maze.resetVisits();
        maze.resetRoutes();
    }
    
    private void startBreadthfirstSearch(Maze maze) {
        io.print("Solved with breadth-first search:");
        BreadthfirstSearch search = new BreadthfirstSearch(maze);
        search.run();
        printMaze(maze);
        maze.resetVisits();
        maze.resetRoutes();
    }
    
    private void printGenerationOptions() {
        io.print("+--------------------------------------+");
        io.print("| Create a maze with...                |");
        io.print("| 1. Depth-first search                |");
        io.print("| 2. Kruskal's algorithm (no loops)    |");
        io.print("| 3. Kruskal's algorithm (with loops)  |");
        io.print("| 4. Quit                              |");
        io.print("+--------------------------------------+");
        io.print("");
    }
    
    
    private void printSolvingOptions() {
        io.print("+----------------------------+");
        io.print("| Solve the maze with...     |");
        io.print("| 1. Wall-follower           |");
        io.print("| 2. Breadth-first search    |");
        io.print("| 3. Return                  |");
        io.print("+----------------------------+");
        io.print("");
    }
    
    
    private void printMaze(Maze maze) {
        List<String> mazeToString = maze.mazeToString();
        for (int i = 0 ; i < mazeToString.size(); i++) {
            io.print(mazeToString.get(i));
        }
        io.print("");
    }
    
}
