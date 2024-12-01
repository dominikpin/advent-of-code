import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            System.err.println("Wrong amount of arguments! Intended use:\njava Main [year] [day] [star]");
            System.exit(1);
        }
        String year = args[0];
        String day = args[1];
        String formattedDay = day.length() == 1 ? "0" + day : day;
        String star = args[2];

        String className = "year" + year + ".day" + day + ".Part" + star;

        try {
            Class<?> partClass = Class.forName(className);
            partClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.err.printf("year %s, day %s, problem %s not found.\n", year, formattedDay, star);
            System.err.println(e);
            e.printStackTrace();
        }
    }
}