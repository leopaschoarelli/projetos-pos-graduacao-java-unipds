import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("benchmark.txt");

        // Java IO - Classico;
        long t1_ini, t1_fim;
        t1_ini = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        while (br.readLine() != null); // não faz nada apenas lê o arquivo
        br.close();
        t1_fim = System.currentTimeMillis();
        System.out.println("JAVA IO - Demorou: " + (t1_fim-t1_ini) + " ms. ");

        // JAVA NIO usando FileChannel
        long t2_ini, t2_fim;
        t2_ini = System.currentTimeMillis();
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(32768); //  8192 - 8 kb / 16384 - 16 kb / 32768 - 32 kb
        while (channel.read(buffer) != -1) {
            buffer.flip();
            buffer.clear();
        }
        channel.close();
        t2_fim = System.currentTimeMillis();
        System.out.println("JAVA NIO Channel - Demorou: " + (t2_fim-t2_ini) + " ms. ");

        // Java NIO2 - ReadAllLines
        long t3_ini, t3_fim;
        t3_ini = System.currentTimeMillis();
        List<String> linhas = Files.readAllLines(path);
        t3_fim = System.currentTimeMillis();
        System.out.println("JAVA NIO2 - Demorou: " + (t3_fim-t3_ini) + " ms. ");
    }
}
