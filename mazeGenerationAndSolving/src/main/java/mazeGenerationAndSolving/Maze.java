package mazeGenerationAndSolving;

public class Maze {
    
    public int height;
    public int wildth;
    
    public Cell[][] cells;
    
    
    public Maze(Cell[][] cells) {
        this.cells = cells;
    }
    
    public void addCell(Cell cell) {
        cells[cell.getX()][cell.getY()] = cell;
    }
    
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
    
}
