import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class PrintingClock {

    private static final String[] hoursList = {"twelve", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
    private static final String[] minuteTensList = {"oh", "ten", "twenty", "thirty", "forty", "fifty"};
    private static final String[] minuteOnesList = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] irregulars = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    private static void resultBuilder(String input) {
        String[] time = input.split(":");

        int hours = Integer.parseInt(time[0]);
        int minuteTens = Integer.parseInt(time[1].substring(0, 1));
        int minuteOnes = Integer.parseInt(time[1].substring(1, 2));

        if (hours < 0 || hours > 23) { hours = 23; }
        if (minuteTens < 0 || minuteTens > 5) { minuteTens = 5; }
        if (minuteOnes < 0 || minuteOnes > 9) { minuteOnes = 9; }

        String AMorPM = "AM";
        if (hours >= 12) {
            hours -= 12;
            AMorPM = "PM";
        }

        String result = "It's ";
        result += hoursList[hours] + " ";

        if (minuteTens == 1 && minuteOnes != 0) {
            result += irregulars[minuteOnes] + " ";
        }
        else {
            if (!(minuteOnes == 0 && minuteTens == 0)) {
                result += minuteTensList[minuteTens] + " ";
            }
            if (minuteOnes != 0) {
                result += minuteOnesList[minuteOnes] + " ";
            }
        }

        result += AMorPM;
        System.out.println(result);
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a time to be written in text (24-hour format): ");
            String input = scanner.nextLine();

            resultBuilder(input);
        }
    }
}