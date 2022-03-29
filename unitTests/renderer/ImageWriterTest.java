package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        Color pink = new Color(190, 37, 167);
        Color blueish = new Color(37, 151, 190);
        Color white = new Color(255, 255, 255);
        ImageWriter imageWriter = new ImageWriter("firstImage", 800, 500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                Color color = pink;
                // on grid
                if (i % 50 == 0 || j % 50 == 0)
                    color = white;
                imageWriter.writePixel(j, i, color);
            }
        }
        imageWriter.writeToImage();
    }

    @Test
    void testWritePixel() {
    }
}