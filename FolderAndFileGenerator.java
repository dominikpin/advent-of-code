import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FolderAndFileGenerator {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String year = ""; // Change year
        String day = ""; // Change day or leave blank for whole year
        File yearDir = new File("year" + year);
        yearDir.mkdir();
        Path templatePath = Paths.get("fileTemplate");

        createFiles(year, day, templatePath);
    }

    private static void createFiles(String year, String day, Path templatePath) throws IOException, URISyntaxException {
        String content = new String(Files.readAllBytes(templatePath), StandardCharsets.UTF_8);
        for (int i = 1; i <= 25; i++) {
            String dayDefault = day.isEmpty() ? i < 10 ? "0" + i : "" + i : day.length() < 2 ? "0" + day : day;
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
            String input = fetchInput(day, year);
            Files.write(Paths.get("year" + year + "/day" + dayDefault + "/input.txt"),
                    input.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static String fetchInput(String day, String year) throws URISyntaxException, IOException {
        String a = "https://adventofcode.com/" + year + "/day/" + Integer.parseInt(day) + "/input";
        URL url = new URI(a).toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        loadEnvFromFile(".env");
        connection.setRequestProperty("Cookie", "session=" + System.getProperty("AOC_TOKEN"));

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch input: HTTP " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder input = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line).append("\n");
            }
            return input.toString();
        }
    }

    public static void loadEnvFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue;
            }
            String[] keyValue = line.split("=", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                System.setProperty(key, value);
            }
        }
        myReader.close();
    }
}
