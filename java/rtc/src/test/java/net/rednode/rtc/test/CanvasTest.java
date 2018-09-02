package net.rednode.rtc.test;

import net.rednode.rtc.Canvas;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static net.rednode.rtc.Tuple.color;
import static net.rednode.rtc.Tuple.epsilon;
import static net.rednode.rtc.Tuple.tuplesEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasTest {

    @Test
    public void testCanvasCreate() {
        // create a canvas
        int w = 10;
        int h = 20;
        double[][][] canvas = Canvas.create(w, h);
        // every element should be 0.0
        assertEquals(w, canvas.length);
        for (int width = 0; width < canvas.length; width++) {
            assertEquals(h, canvas[width].length);
            for (int height = 0; height < canvas[width].length; height++) {
                assertEquals(4, canvas[width][height].length);
                for (int i = 0; i < 4; i++) assertEquals(0, canvas[width][height][i], epsilon);
            }
        }
    }

    @Test
    public void testCanvasPixelWrite() {
        // create a canvas
        int w = 10;
        int h = 20;
        double[] red = color(1, 0, 0);
        double[][][] canvas = Canvas.create(w, h);
        Canvas.writePixel(canvas, 2, 3, red);
        assertTrue(tuplesEqual(red, canvas[2][3]));
    }

    @Test
    public void testPPMHeader() {
        int w = 10;
        int h = 15;
        double[][][] canvas = Canvas.create(w, h);
        String header = Canvas.generatePPMHeader(canvas);
        String[] lines = header.split("\n");
        assertEquals("P3", lines[0]);
        assertEquals(w + " " + h, lines[1]);
        assertEquals("255", lines[2]);
    }

    @Test
    public void testPPMPixelData() {
        int w = 5;
        int h = 3;
        double[][][] canvas = Canvas.create(w, h);

        // create colors
        double[] color1 = color(1.5, 0, 0);
        double[] color2 = color(0, 0.5, 0);
        double[] color3 = color(-0.5, 0, 1);

        // write colors to specific pixels
        Canvas.writePixel(canvas, 0, 0, color1);
        Canvas.writePixel(canvas, 2, 1, color2);
        Canvas.writePixel(canvas, 4, 2, color3);

        // write out ppm data to an outputstream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            Canvas.writeCanvasPPMData(canvas, new OutputStreamWriter(bos));
        } catch (IOException ioe) {
            Assert.fail(ioe.getMessage());
        }

        // check lines 1 to 3 (0 to 2 zero based)
        // of the output to see that we get what we're expecting
        String output = bos.toString();
        String[] lines = output.split("\n");
        String expected_line_1 = "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        String expected_line_2 = "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0";
        String expected_line_3 = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255";
        assertEquals(expected_line_1, lines[0]);
        assertEquals(expected_line_2, lines[1]);
        assertEquals(expected_line_3, lines[2]);
    }

    @Test
    public void testPPMPixelDataSplitsCorrectly() {
        int w = 10;
        int h = 2;
        double[][][] canvas = Canvas.create(w, h);

        // create color
        double[] color = color(1, 0.8, 0.6);

        // set every pixel color to the one color
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Canvas.writePixel(canvas, x, y, color);
            }
        }

        // write out ppm data to an outputstream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            Canvas.writeCanvasPPMData(canvas, new OutputStreamWriter(bos));
        } catch (IOException ioe) {
            Assert.fail(ioe.getMessage());
        }

        // check lines 1 to 4 (0 to 3 zero based)
        // of the output to see that we get what we're expecting
        String output = bos.toString();
        String[] lines = output.split("\n");
        String expected_line_1 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String expected_line_2 = "153 255 204 153 255 204 153 255 204 153 255 204 153";
        String expected_line_3 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String expected_line_4 = "153 255 204 153 255 204 153 255 204 153 255 204 153";
        assertEquals(expected_line_1, lines[0]);
        assertEquals(expected_line_2, lines[1]);
        assertEquals(expected_line_3, lines[2]);
        assertEquals(expected_line_4, lines[3]);
    }

    @Test
    public void testPPMPixelDataEndsWithNewLine() {
        int w = 10;
        int h = 2;
        double[][][] canvas = Canvas.create(w, h);

        // write out ppm data to an outputstream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            Canvas.writeCanvasPPMData(canvas, new OutputStreamWriter(bos));
        } catch (IOException ioe) {
            Assert.fail(ioe.getMessage());
        }

        String output = bos.toString();
        //check that the last character in the output is a newline
        assertEquals("\n", output.substring(output.length()-1));
    }
}
