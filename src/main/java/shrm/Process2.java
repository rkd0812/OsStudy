package shrm;

import java.io.File;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class Process2 {

    public static void main(String[] args) throws Exception {
        File f = new File("shrm-file");
        FileChannel channel = FileChannel.open(f.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer b = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        CharBuffer charBuffer = b.asCharBuffer();

        char c;
        while ((c = charBuffer.get()) != 0) {
            System.out.println(c);
        }
        System.out.println();

        charBuffer.put(0, '\0');

    }
}
