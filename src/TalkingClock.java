import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class TalkingClock {

    private static final String[] hoursList = {"12.wav", "1.wav", "2.wav", "3.wav", "4.wav", "5.wav", "6.wav", "7.wav", "8.wav", "9.wav", "10.wav", "11.wav"};
    private static final String[] minuteTensList = {"o.wav", "10.wav", "twen.wav", "thir.wav", "for.wav", "fif.wav"};
    private static final String[] minuteOnesList = {"", "1.wav", "2.wav", "3.wav", "4.wav", "5.wav", "6.wav", "7.wav", "8.wav", "9.wav"};
    private static final String[] irregulars = {"", "11.wav", "12.wav", "thir.wav", "for.wav", "fif.wav", "six.wav", "seven.wav", "eight.wav", "nine.wav"};

    private static void playAudioFile(String file) {
        try {
            File yourFile = new File("src/voice/" + file);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void resultBuilder(String input) {
        String[] time = input.split(":");

        int hours = Integer.parseInt(time[0]);
        int minuteTens = Integer.parseInt(time[1].substring(0, 1));
        int minuteOnes = Integer.parseInt(time[1].substring(1, 2));

        if (hours < 0 || hours > 23) { hours = 23; }
        if (minuteTens < 0 || minuteTens > 5) { minuteTens = 5; }
        if (minuteOnes < 0 || minuteOnes > 9) { minuteOnes = 9; }

        String AMorPM = "am.wav";
        if (hours >= 12) {
            hours -= 12;
            AMorPM = "pm.wav";
        }

        ArrayList<String> result = new ArrayList<>();
        // result.add("announcement.wav");
        result.add(hoursList[hours]);

        if (minuteTens == 1 && minuteOnes != 0) {
            result.add(irregulars[minuteOnes]);
            if (minuteOnes >= 3) {
                result.add("teen.wav");
            }
        }
        else {
            if (!(minuteOnes == 0 && minuteTens == 0)) {
                result.add(minuteTensList[minuteTens]);
                if (minuteTens != 0) {
                    result.add("ty.wav");
                }
            }
            if (minuteOnes != 0) {
                result.add(minuteOnesList[minuteOnes]);
            }
        }

        result.add(AMorPM);

        for (String s : result) {
            try {
                if(!s.matches("")) {
                    playAudioFile(s);
                    Thread.sleep(400);
                }
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a time to be outputted as speech (24-hour format): ");
            String input = scanner.nextLine();

            resultBuilder(input);
        }
    }
}