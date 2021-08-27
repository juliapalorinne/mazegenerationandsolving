package main;

import maze.Maze;
import mazeGeneration.DepthfirstSearch;
import mazeGeneration.KruskalsAlgorithm;
import mazeGeneration.LoopedMaze;
import mazeSolving.BreadthfirstSearch;
import mazeSolving.TremauxsAlgorithm;
import mazeSolving.WallFollower;


/** Main class.
 * Run maze generation and maze solving algorithms
 */
public class Main {

    
    /** Run the algorithms with given arguments and print the results.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // Create simple maze with depth-first search
        // Solve it with wall-follower
        
        System.out.println("Depth-first search:");
        DepthfirstSearch search = new DepthfirstSearch(20, 10);
        search.run();

        Maze simpleMaze = search.maze;
        simpleMaze.printMaze();
        System.out.println("");
        
        System.out.println("Solved with wall follower:");
        WallFollower follower = new WallFollower(simpleMaze);
        follower.run();
        simpleMaze.printMaze();
        System.out.println("");
        System.out.println("");
        
        
        
        // Create simple maze with Kruskal's algorithm
        // Solve it with wall-follower
        
        System.out.println("Kruskal's algorithm:");
        KruskalsAlgorithm kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runSimpleMaze();

        Maze maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        System.out.println("Solved with wall follower:");
        follower = new WallFollower(maze);
        follower.run();
        maze.printMaze();
        System.out.println("");
        System.out.println("");
        
        
        
        // Create maze with loops with Kruskal's algorithm
        // Solve it with wall-follower
        
        System.out.println("Kruskal's algorithm with loops:");
        kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runLoopedMaze();
        
        maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        System.out.println("Solved with wall follower:");
        follower = new WallFollower(maze);
        follower.run();
        maze.printMaze();
        System.out.println("");
        System.out.println("");
        
        
        
        // Create simple maze with Kruskal's algorithm
        // Solve it with breadth-first search
        
        System.out.println("Kruskal's algorithm:");
        kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runSimpleMaze();
        
        maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        System.out.println("Solved with breadth-first search:");
        BreadthfirstSearch findShortestPath = new BreadthfirstSearch(maze);
        findShortestPath.run();
        maze.printMaze();
        System.out.println("");
        System.out.println("");
        
        
        
        // Create maze with loops with Kruskal's algorithm
        // Solve it with breadth-first search
        
        System.out.println("Kruskal's algorithm with loops:");
        kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runLoopedMaze();
        
        maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        System.out.println("Solved with breadth-first search:");
        findShortestPath = new BreadthfirstSearch(maze);
        findShortestPath.run();
        maze.printMaze();
        System.out.println("");
        System.out.println("");
        
        
        
        // Create maze with loops
        // Solve it with Trémaux's algorithm
        
        
//        LoopedMaze loopedMaze = new LoopedMaze(20, 10);
//        loopedMaze.run();
//        
//        maze = loopedMaze.maze;
//        maze.printMaze();
//        System.out.println("");
//        
//        TremauxsAlgorithm tremaux = new TremauxsAlgorithm(maze);
//        tremaux.run();
//        maze.printMaze();
    }
    
}
