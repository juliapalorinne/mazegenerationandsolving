package maze;


public class BreadthfirstSearch {
    
    
    
    
    
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
