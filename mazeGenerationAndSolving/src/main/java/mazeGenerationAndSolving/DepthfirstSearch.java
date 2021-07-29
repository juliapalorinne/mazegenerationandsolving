package mazeGenerationAndSolving;

import java.util.*;

public class DepthfirstSearch {  
    
    public Maze maze;
    private ArrayDeque<Cell> arr;
    private Stack stack;
    
//    private List[][] edellinen;
//    private boolean l;
//    int pituus;
//    String vastaus;
    
    public void generateMaze(int wildth, int height) {
        Cell[][] cells = new Cell[wildth][height];
        
        for (int i = 0; i < wildth; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                cells[i][j] = cell;
            }
        }
        
    }
        
        
//    public String etsi(char[][] laby) {
//        int n = laby.length;
//        int m = laby[0].length;
//        taulukko = new boolean[n][m];
//        labyrintti = laby;
//        pituus = 0;
//        l = false;
//        vastaus = "";
//        jono = new ArrayDeque<int[]>();
//        edellinen = new List[n][m];
//        
//        
//        int x = 1;
//        int y = 1;
//        for (int i = 1; i < n-1; i++) {
//            for (int j = 1; j < m-1; j++) {
//                if (laby[i][j] == 'x') {
//                    x = j;
//                    y = i;
//                    break;
//                }
//            }
//        }
//        taulukko[y][x] = true;
//        jono.addLast(new int[] {y, x});
//        
//        int[] loydetty = leveyshaku();
//        
//        if (l == false) {
//            return null;
//        }
//        
//        muodostaVastaus(y, x, loydetty);
//        
//        return vastaus;
//    }
//    
//    private void muodostaVastaus(int ya, int xa, int[] l) {
//        int y = l[0];
//        int x = l[1];
//        ArrayList<String> v = new ArrayList<>();
//        
//        while (y > 0 && x > 0) {
//            if (y == ya && x == xa) {
//                break;
//            }
//            List<Integer> ed = edellinen[y][x];
//            int yuusi = ed.get(0);
//            int xuusi = ed.get(1);
//            
//            if (xuusi == x-1) {
//                v.add("O");
//            } else if (xuusi == x+1) {
//                v.add("V");
//            } else if (yuusi == y-1) {
//                v.add("A");
//            } else if (yuusi == y+1) {
//                v.add("Y");
//            }
//            y = yuusi;
//            x = xuusi;
//        }
//        
//        for (int i = v.size()-1; i >= 0; i--) {
//            vastaus = vastaus + v.get(i);
//        }
//    }
//        
//    public int[] leveyshaku() {
//        while (!jono.isEmpty()) {
//            int[] seuraava = jono.poll();
//            int ynyt = seuraava[0];
//            int xnyt = seuraava[1];
//            
//            if (labyrintti[ynyt][xnyt] == 'y') {
//                l=true;
//                return new int[] {ynyt, xnyt};
//            }
//            
//            for (int i = 1; i <= 4; i++) {
//                if (i == 1 && labyrintti[ynyt][xnyt+1] != '#' && taulukko[ynyt][xnyt+1] == false) {
//                    lisaa(ynyt, xnyt+1);
//                    List<Integer> ed = new ArrayList<Integer>(){{
//                        add(ynyt);
//                        add(xnyt);
//                    }};
//                    edellinen[ynyt][xnyt+1] = ed;
//                } else if (i == 2 && labyrintti[ynyt+1][xnyt] != '#' && taulukko[ynyt+1][xnyt] == false) {
//                    lisaa(ynyt+1, xnyt);
//                    List<Integer> ed = new ArrayList<Integer>(){{
//                        add(ynyt);
//                        add(xnyt);
//                    }};
//                    edellinen[ynyt+1][xnyt] = ed;                    
//                } else if (i == 3 && labyrintti[ynyt][xnyt-1] != '#' && taulukko[ynyt][xnyt-1] == false) {
//                    lisaa(ynyt, xnyt-1);
//                    List<Integer> ed = new ArrayList<Integer>(){{
//                        add(ynyt);
//                        add(xnyt);
//                    }};
//                    edellinen[ynyt][xnyt-1] = ed;                    
//                } else if (i == 4 && labyrintti[ynyt-1][xnyt] != '#' && taulukko[ynyt-1][xnyt] == false) {
//                    lisaa(ynyt-1, xnyt);
//                    List<Integer> ed = new ArrayList<Integer>(){{
//                        add(ynyt);
//                        add(xnyt);
//                    }};
//                    edellinen[ynyt-1][xnyt] = ed;                    
//                }
//            }
//        }
//        return null;
//    }
//    
//    private void lisaa(int y, int x) {
//        int [] ruutu = {y, x};
//        jono.addLast(ruutu);
//        taulukko[y][x] = true;
//    }
}