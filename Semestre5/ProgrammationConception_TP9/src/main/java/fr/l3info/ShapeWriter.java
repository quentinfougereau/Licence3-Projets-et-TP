package fr.l3info;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShapeWriter {
    public static void write (File file, List<Shape> shapes) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (Shape shape : shapes) {
            String line = shape.accept(new ShapeWriterVisitorImpl<>());
            fileWriter.write(line);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
