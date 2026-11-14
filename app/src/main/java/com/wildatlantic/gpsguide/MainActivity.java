
package com.wildatlantic.gpsguide;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech voice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        voice = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                voice.setLanguage(
                    Locale.forLanguageTag("en-IE")
                );
            }
        });

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 60, 30, 30);

        TextView title = new TextView(this);
        title.setText("Wild Atlantic GPS Guide V8");
        title.setTextSize(24);
        layout.addView(title);

        addTour(layout, "Cliffs of Moher & Burren",
            "Welcome aboard our Cliffs of Moher and Burren tour.");

        addTour(layout, "Connemara",
            "Welcome to Connemara, one of Ireland's most beautiful regions.");

        addTour(layout, "Aran Islands",
            "Welcome to our Aran Islands adventure.");

        setContentView(layout);
    }

    private void addTour(LinearLayout layout,
                         String name, String message) {
        Button button = new Button(this);
        button.setText(name);
        button.setOnClickListener(v -> {
            if (voice != null) {
                voice.speak(message,
                    TextToSpeech.QUEUE_FLUSH,
                    null, "tour");
            }
        });
        layout.addView(button);
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
