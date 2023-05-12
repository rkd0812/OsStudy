package shrm;

import java.io.File;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class Process1 {

    public static void main(String[] args) throws Exception {
        File f = new File("shrm-file");
        FileChannel fileChannel = FileChannel.open(f.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer b = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        CharBuffer charBuffer = b.asCharBuffer();

        char[] string = "Hello client\0".toCharArray();
        charBuffer.put(string);

        System.out.println("Wait for other process.");
        while( charBuffer.get(0) != '\0');
        System.out.println("Finished waiting.");
    }
}
