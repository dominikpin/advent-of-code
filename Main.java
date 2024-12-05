import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Wrong amount of arguments! Intended use:\njava Main [year] [day] [star]");
            System.exit(1);
        }
        String year = args[0];
        String day = args[1];
        String formattedDay = day.length() == 1 ? "0" + day : day;
        String star = args[2];

        String yearPath = "year" + year;

        try {
            System.setProperty("user.dir", yearPath + "/day" + formattedDay);
            Class<?> partClass = Class.forName(yearPath + ".day" + formattedDay + ".Part" + star);
            partClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.err.printf("year %s, day %s, problem %s not found.\n", year, day, star);
            System.err.println(e);
            e.printStackTrace();
        }
    }
}