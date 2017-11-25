package intro._02_java_interop;

import java.awt.*;

public class TextColorFactory {

    public static Color fromRgb(int red, int green, int blue) {
        return fromRgb(red, green, blue, 255);
    }

    public static Color fromRgb(int red, int green, int blue, int alpha) {
        return new Color(red, green, blue, alpha);
    }
}
