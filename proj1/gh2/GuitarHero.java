package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Iterator;

public class GuitarHero {
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString string[] = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            string[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int c = keyboard.indexOf(key);
                if (c != -1) {
                    string[c].pluck();
                }
            }

            double sample = 0.0;
            // 遍历所有 37 根琴弦，把它们这一刻的 sample 全加起来
            for (int i = 0; i < string.length; i++) {
                sample += string[i].sample();
            }

            // --- 步骤 3：播放这个混合出来的声音 ---
            StdAudio.play(sample);

            // --- 步骤 4：时间流逝（所有琴弦的振动往前推进一格） ---
            // 注意：哪怕没按键盘，旧的声音也要继续衰减！
            for (int i = 0; i < string.length; i++) {
                string[i].tic();
            }
        }

    }
}
