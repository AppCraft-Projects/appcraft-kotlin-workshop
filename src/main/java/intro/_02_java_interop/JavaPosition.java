package intro._02_java_interop;

public class JavaPosition {

    public static final JavaPosition TOP_LEFT_CORNER = new JavaPosition(0, 0);

    // TODO: convert me to @JvmField in `KotlinPosition`!
    public static final JavaPosition UNKNOWN = new JavaPosition(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final int column;
    private final int row;

    private JavaPosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    // TODO: convet me to @JvmStatic in `KotlinPosition`!
    /**
     * Factory method for [KotlinPosition].
     */
    public static JavaPosition of(int column, int row) {
        return new JavaPosition(column, row);
    }

}
