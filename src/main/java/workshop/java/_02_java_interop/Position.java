package workshop.java._02_java_interop;

public class Position {

    public static final Position TOP_LEFT_CORNER = new Position(0, 0);

    public static final Position UNKNOWN = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    /**
     * Factory method for [Position].
     */
    public static Position of(int column, int row) {
        return new Position(column, row);
    }

}
