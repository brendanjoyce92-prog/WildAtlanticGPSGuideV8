package com.wildatlantic.gpsguide;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {
    private TextToSpeech voice;
    private boolean ready = false;
    private boolean paused = false;
    private int tour = 0;
    private int section = 0;
    private TextView status;
    private TextView sectionLabel;
    private Button playButton;

    private final String[] names = {"Cliffs of Moher & Burren", "Connemara", "Aran Islands"};
    private final String[][] headings = {
        {"Welcome aboard", "Farming in Ireland", "The Burren", "Cliffs of Moher"},
        {"Welcome to Connemara", "The Gaeltacht", "Connemara farming", "Kylemore Abbey"},
        {"Welcome aboard", "The Aran Islands", "Island life", "Atlantic coastline"}
    };
    private final String[][] scripts = {
        {
            "Welcome aboard our Cliffs of Moher and Burren tour. Today we will travel through some of the most beautiful countryside on Ireland's west coast.",
            "As we travel, you will see cattle and sheep grazing in the fields. Farming is a major part of rural Irish life. Many farms are family-run, with beef cattle, dairy cows and sheep.",
            "The Burren is famous for its limestone landscape. Although the rocky hills can look bare, they support an extraordinary variety of wildflowers and wildlife.",
            "The Cliffs of Moher rise dramatically above the Atlantic Ocean. Please stay behind the safety barriers and follow the official walking paths."
        },
        {
            "Welcome to Connemara, a region known for mountains, lakes, and traditional Irish culture.",
            "In parts of Connemara, Irish is spoken as an everyday language. These Irish-speaking districts are known as the Gaeltacht.",
            "Sheep farming is a familiar sight across Connemara. The landscape and weather have shaped farming here for generations.",
            "Kylemore Abbey sits beside a beautiful lake, surrounded by mountains. It was originally built as a private home and later became a Benedictine abbey."
        },
        {
            "Welcome aboard our Aran Islands adventure. Today we are heading towards the Atlantic coast and the islands off County Galway.",
            "The Aran Islands are known for their stone walls, Irish language heritage, and dramatic coastal scenery.",
            "Island communities have long depended on fishing, farming and, more recently, tourism. Their traditions remain an important part of Irish culture.",
            "The Atlantic coastline is beautiful but exposed. Ferry sailings and coastal cruises depend on weather and sea conditions."
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildScreen();
        voice = new TextToSpeech(this, result -> {
            if (result == TextToSpeech.SUCCESS) {
                int language = voice.setLanguage(Locale.forLanguageTag("en-IE"));
                if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                    voice.setLanguage(Locale.UK);
                }
                voice.setSpeechRate(0.92f);
                ready = true;
                runOnUiThread(() -> status.setText("Voice ready • Choose a tour"));
            } else {
                runOnUiThread(() -> status.setText("Voice unavailable • Check text-to-speech settings"));
            }
        });
    }

    private Button button(String text, LinearLayout parent) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(17);
        b.setAllCaps(false);
        b.setMinHeight(64);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = 10;
        parent.addView(b, params);
        return b;
    }

    private void buildScreen() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 32, 24, 30);
        root.setBackgroundColor(Color.rgb(13, 33, 45));
        scroll.addView(root);

        TextView title = new TextView(this);
        title.setText("WILD ATLANTIC GPS GUIDE");
        title.setTextColor(Color.WHITE);
        title.setTextSize(24);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        TextView version = new TextView(this);
        version.setText("V9 • Driver Edition");
        version.setTextColor(Color.rgb(146, 211, 219));
        version.setTextSize(16);
        version.setPadding(0, 5, 0, 22);
        root.addView(version);

        status = new TextView(this);
        status.setText("Preparing voice...");
        status.setTextColor(Color.WHITE);
        status.setTextSize(17);
        status.setPadding(0, 0, 0, 18);
        root.addView(status);

        for (int i = 0; i < names.length; i++) {
            final int chosen = i;
            Button select = button(names[i], root);
            select.setOnClickListener(v -> {
                tour = chosen;
                section = 0;
                paused = false;
                if (voice != null) voice.stop();
                updateSection();
                status.setText("Selected: " + names[tour]);
            });
        }

        sectionLabel = new TextView(this);
        sectionLabel.setTextColor(Color.WHITE);
        sectionLabel.setTextSize(19);
        sectionLabel.setPadding(0, 20, 0, 20);
        root.addView(sectionLabel);

        playButton = button("▶ Play commentary", root);
        playButton.setOnClickListener(v -> {
            if (paused) {
                paused = false;
                speakCurrent();
            } else {
                speakCurrent();
            }
        });

        Button pause = button("■ Stop / Pause", root);
        pause.setOnClickListener(v -> {
            if (voice != null) voice.stop();
            paused = true;
            status.setText("Stopped • Play restarts this section");
        });

        Button repeat = button("↻ Repeat section", root);
        repeat.setOnClickListener(v -> speakCurrent());

        Button next = button("▶▶ Next section", root);
        next.setOnClickListener(v -> {
            section = (section + 1) % scripts[tour].length;
            updateSection();
            speakCurrent();
        });

        Button previous = button("◀ Previous section", root);
        previous.setOnClickListener(v -> {
            section = (section + scripts[tour].length - 1) % scripts[tour].length;
            updateSection();
            speakCurrent();
        });

        TextView note = new TextView(this);
        note.setText("Manual playback edition. GPS auto-triggering and background playback are not enabled yet. Set up audio before driving; do not operate your phone while driving.");
        note.setTextColor(Color.rgb(190, 207, 215));
        note.setTextSize(13);
        note.setPadding(0, 18, 0, 0);
        root.addView(note);

        updateSection();
        setContentView(scroll);
    }

    private void updateSection() {
        if (sectionLabel != null) {
            sectionLabel.setText(names[tour] + "\n" + (section + 1) + "/" + scripts[tour].length + " • " + headings[tour][section]);
        }
    }

    private void speakCurrent() {
        if (!ready || voice == null) {
            status.setText("Voice is not ready yet");
            return;
        }
        paused = false;
        voice.speak(scripts[tour][section], TextToSpeech.QUEUE_FLUSH, null, "v9-section");
        status.setText("Speaking: " + headings[tour][section]);
    }

    @Override
    protected void onDestroy() {
        if (voice != null) {
            voice.stop();
            voice.shutdown();
        }
        super.onDestroy();
    }
}
