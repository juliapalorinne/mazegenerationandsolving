package main;

import java.text.DecimalFormat;
import java.util.Arrays;
import mazesolving.*;
import maze.Maze;
import mazegeneration.*;


public class PerformanceTester {
    private final int[] nums = {10, 50, 100, 200, 500};
    private final int numberOfRuns = nums.length;
    
    private final double[] depthfirstSetUps = new double[numberOfRuns];
    private final double[] depthfirstRuns = new double[numberOfRuns];
    private final double[] depthfirstSetUpSds = new double[numberOfRuns];
    private final double[] depthfirstSds = new double[numberOfRuns];
    private final Maze[] mazesD = new Maze[100];
    
    private final double[] kruskalSetUps = new double[numberOfRuns];
    private final double[] kruskalSetUpSds = new double[numberOfRuns];
    private final double[] kruskalSimpleRuns = new double[numberOfRuns];
    private final double[] kruskalSimpleSds = new double[numberOfRuns];
    private final double[] kruskalLoopedRuns = new double[numberOfRuns];
    private final double[] kruskalLoopedSds = new double[numberOfRuns];
    private final Maze[] mazesKS = new Maze[100];
    private final Maze[] mazesKL = new Maze[100];
    
    private final double[] wallfollowerSetUps = new double[numberOfRuns];
    private final double[] wallfollowerSetUpSds = new double[numberOfRuns];
    private final double[] wallfollowerRuns1 = new double[numberOfRuns];
    private final double[] wallfollowerSds1 = new double[numberOfRuns];
    private final double[] wallfollowerRuns2 = new double[numberOfRuns];
    private final double[] wallfollowerSds2 = new double[numberOfRuns];
    private final double[] wallfollowerRuns3 = new double[numberOfRuns];
    private final double[] wallfollowerSds3 = new double[numberOfRuns];
    
    private final double[] breadthfirstSetUps = new double[numberOfRuns];
    private final double[] breadthfirstSetUpSds = new double[numberOfRuns];
    private final double[] breadthfirstRuns1 = new double[numberOfRuns];
    private final double[] breadthfirstSds1 = new double[numberOfRuns];
    private final double[] breadthfirstRuns2 = new double[numberOfRuns];
    private final double[] breadthfirstSds2 = new double[numberOfRuns];
    private final double[] breadthfirstRuns3 = new double[numberOfRuns];
    private final double[] breadthfirstSds3 = new double[numberOfRuns];
    
    
    public void run() {
        for (int run = 0; run < nums.length; run++) {
            compareSetUpTimesForGenerationAlgorithms(run);
            compareRunTimesForGenerationAlgorithms(run);
            compareSetUpTimesForSolvingAlgorithms(run);
            compareRunTimesForSolvingAlgorithms(run);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Depth-first search set up times:\n");
        appendResults(sb, depthfirstSetUps, depthfirstSetUpSds);
        
        sb.append("\nDepth-first search run times:\n");
        appendResults(sb, depthfirstRuns, depthfirstSds);
        
        
        sb.append("\nKruskal's algorithm set up times:\n");
        appendResults(sb, kruskalSetUps, kruskalSetUpSds);
        
        sb.append("\nKruskal's algorithm run times for simple maze:\n");
        appendResults(sb, kruskalSimpleRuns, kruskalSimpleSds);
        
        sb.append("\nKruskal's algorithm run times for looped maze:\n");
        appendResults(sb, kruskalLoopedRuns, kruskalLoopedSds);
        
        
        sb.append("\nWall-follower set up times:\n");
        appendResults(sb, wallfollowerSetUps, wallfollowerSetUpSds);
        
        sb.append("\nWall-follower run times for simple maze "
                + "created with depth-first search:\n");
        appendResults(sb, wallfollowerRuns1, wallfollowerSds1);
        
        sb.append("\nWall-follower run times for simple maze "
                + "created with Kruskal's algorithm:\n");
        appendResults(sb, wallfollowerRuns2, wallfollowerSds2);
        
        sb.append("\nWall-follower run times for looped maze "
                + "created with Kruskal's algorithm:\n");
        appendResults(sb, wallfollowerRuns3, wallfollowerSds3);
        
        
        sb.append("\nBreadth-first-search set up times:\n");
        appendResults(sb, breadthfirstSetUps, breadthfirstSetUpSds);
        
        sb.append("\nBreadth-first-search run times for simple maze "
                + "created with depth-first search:\n");
        appendResults(sb, breadthfirstRuns1, breadthfirstSds1);
        
        sb.append("\nBreadth-first-search run times for simple maze "
                + "created with Kruskal's algorithm:\n");
        appendResults(sb, breadthfirstRuns2, breadthfirstSds2);
        
        sb.append("\nBreadth-first-search run times for looped maze "
                + "created with Kruskal's algorithm:\n");
        appendResults(sb, breadthfirstRuns3, breadthfirstSds3);
        
        
        return sb.toString();
    }

    private void appendResults(StringBuilder sb, double[] arr, double[] std) {
        for (int i = 0; i < nums.length; i++) {
            String num = Integer.toString(nums[i]);
            for (int j = 0; j < 8 - num.length(); j++) {
                sb.append(" ");
            }
            sb.append(num);
            sb.append(": ");
            sb.append(formatTime(arr[i]));
            if (std != null) {
                sb.append(", SD ");
                sb.append(formatTime(std[i]));
            }
            sb.append("\n");
        }
    }

    private double getSD(long[] times, double mean) {
        double s = 0;
        for (long time : times) {
            s += Math.pow(time - mean, 2);
        }
        return Math.sqrt(s / (times.length - 1));
    }

    private double getAverage(long[] times) {
        double s = 0;
        for (long time : times) {
            s += time;
        }
        return s / times.length;
    }
    
    private String formatTime(double d) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        
        if (d < 1) {
            return df.format(d * 1000) + " ps";
        } else if (d >= 1 && d < 1000) {
            return df.format(d) + " ns";
        } else if (d >= 1000 && d < 1000000) {
            return df.format(d / 1000) + " us";
        } else if (d >= 1000000 && d < 1000000000) {
            return df.format(d / 1000000) + " ms";
        } return df.format(d / 1000000000) + " s";
    }
    
    
    private void compareSetUpTimesForGenerationAlgorithms(int run) {
        int n = 100;
        int num = nums[run];
        long[] times = new long[n];
        long t;

        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            DepthfirstSearch depthfirst = new DepthfirstSearch(num, num);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        depthfirstSetUps[run] = getAverage(times);
        depthfirstSetUpSds[run] = getSD(times, depthfirstSetUps[run]);
        

        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            KruskalsAlgorithm kruskal = new KruskalsAlgorithm(num, num);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        kruskalSetUps[run] = getAverage(times);
        kruskalSetUpSds[run] = getSD(times, kruskalSetUps[run]);
    }
    

    private void compareRunTimesForGenerationAlgorithms(int run) {
        int n = 100;
        int num = nums[run];
        long[] times = new long[n];
        long t;

        for (int i = 0; i < n; i++) {
            DepthfirstSearch depthfirst = new DepthfirstSearch(num, num);
            t = System.nanoTime();
            depthfirst.run();
            t = System.nanoTime() - t;
            times[i] = t;
            mazesD[i] = depthfirst.maze;
        }
        depthfirstRuns[run] = getAverage(times);
        depthfirstSds[run] = getSD(times, depthfirstRuns[run]);
        
        for (int i = 0; i < n; i++) {
            KruskalsAlgorithm kruskal = new KruskalsAlgorithm(num, num);
            t = System.nanoTime();
            kruskal.runSimpleMaze();
            t = System.nanoTime() - t;
            times[i] = t;
            mazesKS[i] = kruskal.maze;
        }
        kruskalSimpleRuns[run] = getAverage(times);
        kruskalSimpleSds[run] = getSD(times, kruskalSimpleRuns[run]);

        for (int i = 0; i < n; i++) {
            KruskalsAlgorithm kruskal = new KruskalsAlgorithm(num, num);
            t = System.nanoTime();
            kruskal.runLoopedMaze();
            t = System.nanoTime() - t;
            times[i] = t;
            mazesKL[i] = kruskal.maze;
        }
        kruskalLoopedRuns[run] = getAverage(times);
        kruskalLoopedSds[run] = getSD(times, kruskalLoopedRuns[run]);
    }
    
    
    private void compareSetUpTimesForSolvingAlgorithms(int run) {
        int n = 100;
        long[] times = new long[n];
        long t;
        
        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            WallFollower wallfollower = new WallFollower(mazesD[i]);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        wallfollowerSetUps[run] = getAverage(times);
        wallfollowerSetUpSds[run] = getSD(times, wallfollowerSetUps[run]);

        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            BreadthfirstSearch breadthfirst = new BreadthfirstSearch(mazesD[i]);
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        breadthfirstSetUps[run] = getAverage(times);
        breadthfirstSetUpSds[run] = getSD(times, breadthfirstSetUps[run]);
    }
    

    private void compareRunTimesForSolvingAlgorithms(int run) {
        int n = 100;
        long[] times = new long[n];
        long t;
        
        Maze[] mazesDCopy1 = mazesD;
        Maze[] mazesDCopy2 = mazesD;
        Maze[] mazesKSCopy1 = mazesKS;
        Maze[] mazesKSCopy2 = mazesKS;
        Maze[] mazesKLCopy1 = mazesKL;
        Maze[] mazesKLCopy2 = mazesKL;

        for (int i = 0; i < n; i++) {
            WallFollower wallfollower = new WallFollower(mazesDCopy1[i]);
            t = System.nanoTime();
            wallfollower.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        wallfollowerRuns1[run] = getAverage(times);
        wallfollowerSds1[run] = getSD(times, wallfollowerRuns1[run]);
        
        for (int i = 0; i < n; i++) {
            WallFollower wallfollower = new WallFollower(mazesKSCopy1[i]);
            t = System.nanoTime();
            wallfollower.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        wallfollowerRuns2[run] = getAverage(times);
        wallfollowerSds2[run] = getSD(times, wallfollowerRuns2[run]);
        
        for (int i = 0; i < n; i++) {
            WallFollower wallfollower = new WallFollower(mazesKLCopy1[i]);
            t = System.nanoTime();
            wallfollower.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        wallfollowerRuns3[run] = getAverage(times);
        wallfollowerSds3[run] = getSD(times, wallfollowerRuns3[run]);
        
        
        for (int i = 0; i < n; i++) {
            BreadthfirstSearch breadthfirst = new BreadthfirstSearch(mazesDCopy2[i]);
            t = System.nanoTime();
            breadthfirst.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        breadthfirstRuns1[run] = getAverage(times);
        breadthfirstSds1[run] = getSD(times, breadthfirstRuns1[run]);
        
        for (int i = 0; i < n; i++) {
            BreadthfirstSearch breadthfirst = new BreadthfirstSearch(mazesKSCopy2[i]);
            t = System.nanoTime();
            breadthfirst.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        breadthfirstRuns2[run] = getAverage(times);
        breadthfirstSds2[run] = getSD(times, breadthfirstRuns2[run]);
        
        for (int i = 0; i < n; i++) {
            BreadthfirstSearch breadthfirst = new BreadthfirstSearch(mazesKLCopy2[i]);
            t = System.nanoTime();
            breadthfirst.run();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        breadthfirstRuns3[run] = getAverage(times);
        breadthfirstSds3[run] = getSD(times, breadthfirstRuns3[run]);
    }
}
