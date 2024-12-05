import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderAndFileGenerator {
    public static void main(String[] args) throws IOException {
        String year = "2015"; // Change year
        String day = "11"; // Change day or leave blank for whole year
        File yearDir = new File("year" + year);
        yearDir.mkdir();
        Path templatePath = Paths.get("fileTemplate");

        createFiles(year, day, templatePath);
    }

    private static void createFiles(String year, String day, Path templatePath) throws IOException {
        String content = new String(Files.readAllBytes(templatePath), StandardCharsets.UTF_8);
        for (int i = 1; i <= 25; i++) {
            String dayDefault = day.isEmpty() ? i < 10 ? "0" + i : "" + i : day;
            File dayDir = new File("year" + year + "/day" + dayDefault);
            if (!dayDir.mkdir()) {
                continue;
            }
            for (int j = 1; j <= 2; j++) {
                String fileContent = content.replace("{year}", year);
                fileContent = fileContent.replace("{day}", dayDefault + "");
                fileContent = fileContent.replaceAll("\\{part\\}", j + "");
                Files.write(Paths.get("year" + year + "/day" + dayDefault + "/Part" + j + ".java"),
                        fileContent.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
