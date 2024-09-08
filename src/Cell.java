public class Cell {
    int i, j;
    Cell next, prev;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        next = prev = null;
    }
}
