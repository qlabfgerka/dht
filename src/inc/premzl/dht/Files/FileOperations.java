package inc.premzl.dht.Files;

import inc.premzl.dht.Files.Wrappers.GZIPOutputStreamWrapper;
import inc.premzl.dht.Models.CompressionWrapper;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;

public class FileOperations {
    public static Mat openImage(String path) {
        return Imgcodecs.imread(path);
    }

    public static void writeImage(String filename, Mat image) {
        Imgcodecs.imwrite(filename, image);
    }

    public static void compress(CompressionWrapper wrapper, String path) throws IOException {
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            GZIPOutputStreamWrapper gzipOutputStream = new GZIPOutputStreamWrapper(fileOutputStream);
            gzipOutputStream.setLevel(Deflater.BEST_COMPRESSION);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);
            objectOutputStream.writeObject(wrapper);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static CompressionWrapper decompress(String path) throws IOException, ClassNotFoundException {
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream);

            return (CompressionWrapper) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
