package inc.premzl.dht;

import inc.premzl.dht.Models.CompressionWrapper;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static inc.premzl.dht.Compression.Compression.*;
import static inc.premzl.dht.Files.FileOperations.*;

public class DHT {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) throw new Exception("Invalid arguments size");

        if (Objects.equals(args[1], "c")) {
            Mat image = openImage(args[0]);
            List<double[][][]> blocks = getBlocks(image);
            blocks = haar(blocks, true);
            List<float[]> quantized = quantization(zigzag(blocks), Integer.parseInt(args[3]));
            compress(new CompressionWrapper(quantized, image.rows(), image.cols()), args[2]);
            // sizeof(original image) / sizeof(compressed binary)
            System.out.printf("COMPRESSION RATIO: %f ", ((float) Files.size(Path.of(args[0])) / (float) Files.size(Path.of(args[2]))));
        } else if (Objects.equals(args[1], "d")) {
            CompressionWrapper wrapper = decompress(args[0]);
            List<double[][][]> blocks = reverseZigzag(wrapper.getBlocks());
            blocks = haar(blocks, false);
            writeImage(args[2], getMat(blocks, wrapper.getHeight(), wrapper.getWidth()));
            // sizeof(compressed binary) / sizeof(decompressed image)
            System.out.printf("DECOMPRESSION RATIO: %f ", ((float) Files.size(Path.of(args[0])) / (float) Files.size(Path.of(args[2]))));
        }
    }
}
