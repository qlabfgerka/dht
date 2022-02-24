package inc.premzl.dht.Files.Wrappers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPOutputStreamWrapper extends GZIPOutputStream {

    public GZIPOutputStreamWrapper(OutputStream out) throws IOException {
        super(out);
    }

    public void setLevel(int level) {
        def.setLevel(level);
    }
}
