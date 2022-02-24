package inc.premzl.dht.Models;

import java.io.Serializable;
import java.util.List;

public class CompressionWrapper implements Serializable {
    private List<float[]> blocks;
    private int height;
    private int width;

    public CompressionWrapper(List<float[]> blocks, int height, int width) {
        this.blocks = blocks;
        this.height = height;
        this.width = width;
    }

    public List<float[]> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<float[]> blocks) {
        this.blocks = blocks;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
