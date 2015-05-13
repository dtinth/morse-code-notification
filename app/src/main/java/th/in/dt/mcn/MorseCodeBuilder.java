package th.in.dt.mcn;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MorseCodeBuilder {
    private String[] chars = ".-/-.../-.-./-.././..-./--./..../../.---/-.-/.-../--/-./---/.--./--.-/.-./.../-/..-/...-/.--/-..-/-.--/--..".split("/");
    private List<Boolean> list = new LinkedList<Boolean>();
    private int period = 80;
    public void add(String text) {
        for (int i = 0; i < text.length(); i ++) {
            add(text.charAt(i));
        }
    }
    public void add(char c) {
        if (c >= 'a' && c <= 'z') {
            addCode(chars[c - 'a']);
            pause();
        }
        if (c >= 'A' && c <= 'Z') {
            addCode(chars[c - 'A']);
            pause();
        }
    }
    private void addCode(String code) {
        for (int i = 0; i < code.length(); i ++) {
            char c = code.charAt(i);
            if (c == '.') dit();
            if (c == '-') dah();
        }
    }
    public void dit() {
        list.add(true);
        list.add(false);
    }
    public void dah() {
        list.add(true);
        list.add(true);
        list.add(true);
        list.add(false);
    }
    public void pause() {
        list.add(false);
        list.add(false);
    }
    public long[] build() {
        long now = 0;
        boolean state = false;
        List<Long> durations = new LinkedList<Long>();
        Log.d("meow", list + "");
        for (boolean status : list) {
            if (state != status) {
                durations.add(now);
                state = status;
                now = 0;
            }
            now += period;
        }
        long[] out = new long[durations.size()];
        int j = 0;
        for (int i = 0; i < out.length; i ++) {
            out[j ++] = durations.get(i);
        }
        return out;
    }
}
