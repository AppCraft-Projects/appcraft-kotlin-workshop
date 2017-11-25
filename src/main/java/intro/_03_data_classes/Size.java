package intro._03_data_classes;

import java.util.Objects;

public class Size {

    private final int columns;
    private final int rows;

    public Size(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return getColumns() == size.getColumns() &&
                getRows() == size.getRows();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumns(), getRows());
    }

    @Override
    public String toString() {
        return "Size{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }
}
