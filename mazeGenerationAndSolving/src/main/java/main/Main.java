package main;

import maze.DepthfirstSearch;
import maze.KruskalsAlgorithm;
import maze.Maze;
import maze.WallFollower;


/** Main calss.
 * Run maze generation and maze solving algorithms
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DepthfirstSearch search = new DepthfirstSearch(20, 10);
        search.run();

        Maze simpleMaze = search.maze;
        simpleMaze.printMaze();
        System.out.println("");
        
        WallFollower follower = new WallFollower(simpleMaze);
        follower.run();
        simpleMaze.printMaze();
        System.out.println("");
        
        
        KruskalsAlgorithm kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runSimpleMaze();

        Maze maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        follower = new WallFollower(maze);
        follower.run();
        maze.printMaze();
        System.out.println("");
        
        kruskal = new KruskalsAlgorithm(20, 10);
        kruskal.runLoopedMaze();
        
        maze = kruskal.maze;
        maze.printMaze();
        System.out.println("");
        
        follower = new WallFollower(maze);
        follower.run();
        maze.printMaze();
    }
    
}
