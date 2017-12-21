package workshop.java._01_convert_from_java;

public class ConverterProblem {

    /**
     * This comment goes to the wrong place.
     */
    public ConverterProblem() {
        this("foo");
    }

    public ConverterProblem(String param) {
    }
}

interface InterfacePropertyProblem {

    /**
     * This gets converted to a val.
     */
    String getBar();

    /**
     * What is `shl`?
     */
    default int bitwiseProblem(int param) {
        return param << 1;
    }
}

