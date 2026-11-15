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
        {
            "Welcome aboard",
            "Galway history",
            "Employment in Galway",
            "Cattle farming",
            "Clarinbridge",
            "Kinvara and Dunguaire Castle",
            "Hazel Mountain Chocolate",
            "Entering the Burren",
            "Dry stone walls",
            "Sheep farming",
            "Winterage farming",
            "Flowers and wildlife",
            "Corkscrew Hill",
            "Irish history and the Famine",
            "Doolin village",
            "Approaching the Cliffs",
            "Cliffs of Moher safety and visit",
            "Leaving the Cliffs",
            "Ballyryan coast",
            "Fanore",
            "Black Head",
            "Ballyvaughan",
            "Thank you and farewell"
    },
        {
            "Welcome aboard",
            "Leaving Galway",
            "Galway jobs and industries",
            "The Gaeltacht",
            "Farming and sheep",
            "Bogs and peat",
            "Screeb Waterfall",
            "Derryclare and Pine Island",
            "The Twelve Bens",
            "Clifden",
            "Sky Road",
            "Kylemore Abbey",
            "Connemara National Park option",
            "Local wildlife",
            "Inagh Valley",
            "Return to Galway"
    },
        {
            "Welcome aboard",
            "Leaving Galway",
            "Irish farming",
            "Kinvara and Galway Bay",
            "The Burren landscape",
            "Doolin and the ferry",
            "Crossing to Inisheer",
            "Introducing the Aran Islands",
            "Inisheer",
            "Irish language on the islands",
            "Island farming and stone walls",
            "Fishing and island employment",
            "History and heritage",
            "Aran knitwear",
            "Cliffs cruise introduction",
            "Cliffs from the sea",
            "Coastal return",
            "Thank you and farewell"
    }
    };
    private final String[][] scripts = {
        {
            "Good morning everyone, and welcome aboard Wild Atlantic Way Day Tours. Today we travel from Galway through Kinvara and the Burren, with a stop at Hazel Mountain Chocolate, lunch in Doolin, and a visit to the magnificent Cliffs of Moher. We return along the Atlantic coast. Sit back, relax, and enjoy the scenery.",
            "Galway began as a medieval trading town and grew into an important Atlantic port. Today it is famous for its colourful streets, traditional music, festivals and friendly atmosphere. The Irish language remains a living part of the culture of County Galway.",
            "Galway is an important centre for medical technology. Companies including Medtronic and Boston Scientific make medical devices used around the world. Healthcare, education, technology, hospitality, tourism and construction also provide employment across the region.",
            "You will see plenty of cattle as we leave Galway. Our mild, damp climate helps grass grow for much of the year. Some farmers keep dairy cows for milk, while others raise cattle for beef. Much of the food produced on Irish farms is exported.",
            "Clarinbridge is well known for oysters from Galway Bay. The countryside around us is a patchwork of fields, hedgerows and family farms. Rural villages have long been connected to farming, fishing and local trade.",
            "We are approaching Kinvara on Galway Bay. Dunguaire Castle, overlooking the water, is a sixteenth-century tower house. Its name recalls Guaire, a legendary king remembered for generosity. Kinvara is also known for music and its attractive harbour.",
            "Our stop at Hazel Mountain Chocolate introduces us to a small artisan food business in the Burren region. Rural producers like this help support local jobs and give visitors a taste of Irish food craftsmanship.",
            "The name Burren comes from an Irish word meaning rocky place. Its extraordinary limestone began as sediment beneath an ancient tropical sea. Millions of years of geological change, water and weather shaped the landscape around us.",
            "Many Burren fields are divided by dry stone walls, built without mortar. Farmers used stones cleared from their land to make boundaries for animals. The walls are an important part of the character of this region.",
            "Sheep are well suited to many of Ireland’s hill and rocky landscapes. Farmers raise sheep mainly for lamb and other meat products. Spring is lambing season, when newborn lambs need close attention, especially in bad weather.",
            "The Burren has a distinctive tradition called winterage. Some cattle graze on upland limestone pastures during winter. The free-draining ground and hardy vegetation make this possible, and careful grazing helps protect the local habitats.",
            "The Burren looks bare from a distance, but it supports remarkable wildflowers, insects and birds. In season, orchids and gentians grow among the limestone. Please leave plants and wildlife undisturbed.",
            "Corkscrew Hill is known for its sharp winding bends and striking views. Watch the limestone slopes, stone walls and green valleys unfold around us as we travel through this beautiful part of County Clare.",
            "The Great Famine of the eighteen forties devastated Ireland after potato blight destroyed a crucial food crop. Around a million people died, and many more emigrated. Its effects on families and rural communities were felt for generations.",
            "Doolin is famous for traditional Irish music, with fiddles, flutes and other instruments often heard in its pubs. It is also a departure point for ferries to the Aran Islands. We will stop here for lunch before visiting the Cliffs of Moher.",
            "The Cliffs of Moher extend for about eight kilometres along the Atlantic coast, reaching approximately two hundred and fourteen metres at their highest point. The rock layers were formed from ancient sediments over three hundred million years ago.",
            "Welcome to the Cliffs of Moher. In clear weather you may see the Aran Islands and Connemara. Seabirds nest along these cliffs, including puffins during the breeding season. Please stay on official paths, obey barriers and signs, and keep well away from exposed cliff edges.",
            "I hope you enjoyed the spectacular views. We are now taking the coastal road back towards Galway, with more beautiful Atlantic scenery and the limestone hills of the Burren ahead.",
            "Around Ballyryan the rocky Burren meets the sea. The shoreline is shaped by powerful Atlantic waves, weather and erosion. Notice how the pale limestone seems to run straight down towards the water.",
            "Fanore is a small coastal village with a sandy beach set between the Atlantic Ocean and the Burren hills. Farming and tourism are both important here, and the coast is especially beautiful in clear weather.",
            "We are passing Black Head, a headland overlooking Galway Bay. Across the water, on a clear day, you may be able to make out the hills of Connemara. This is one of the great scenic stretches of the Wild Atlantic Way.",
            "Ballyvaughan is a village on the southern side of Galway Bay, surrounded by limestone scenery. From here we continue through the countryside towards Galway, bringing our journey around the Burren towards its end.",
            "Ladies and gentlemen, we are approaching Galway. Thank you for joining Wild Atlantic Way Day Tours. We hope you enjoyed the Burren, Doolin and the Cliffs of Moher. If you enjoyed your day, a Google review would mean a great deal to our team. Safe travels and we hope to welcome you again."
    },
        {
            "Good morning and welcome aboard our Connemara tour with Wild Atlantic Way Day Tours. Today we explore Irish-speaking communities, mountains, lakes, coastal villages and some of the most beautiful landscapes in Ireland.",
            "Galway is a lively city known for music, education, tourism and technology. As we head west, urban streets give way to fields, lakes and the rugged scenery of Connemara.",
            "Galway is known internationally for medical device manufacturing, with employers such as Medtronic and Boston Scientific. Universities, hospitals, hotels, shops and technology firms also support thousands of local jobs.",
            "Parts of Connemara are in the Gaeltacht, areas where Irish is used in daily life. You may notice bilingual road signs. Irish is a living language, and local schools, radio, music and community groups help sustain it.",
            "Connemara has rocky land, bogs and mountains, so farming differs from the richer pastures farther east. Sheep are a familiar sight, and some farms also keep cattle. Many farms are small and family run.",
            "Connemara has extensive boglands formed over thousands of years in wet conditions. Peat was traditionally cut for home heating. Today bogs are also valued for storing carbon, supporting wildlife and protecting water.",
            "Our route takes us towards Screeb, where streams flow through the rocky landscape. This is a lovely place to appreciate the rain-fed rivers, lakes and wild countryside of Connemara.",
            "Derryclare Lough is one of Connemara’s famous scenic lakes. The little tree-covered island often called Pine Island makes a striking photograph against the Twelve Bens mountain range.",
            "The Twelve Bens are a group of rugged quartzite mountains. They are popular with experienced hillwalkers, but the weather can change quickly. Their dramatic shapes define much of the Connemara skyline.",
            "Clifden is often called the capital of Connemara. It is a lively market town with shops, cafés and a long history of serving the surrounding rural area. We will have time here to enjoy the town.",
            "The Sky Road near Clifden offers wide views over the Atlantic, offshore islands and Connemara’s coastline. The road is especially memorable in clear weather, when the sea and mountains seem to stretch for miles.",
            "Kylemore Abbey was originally built in the nineteenth century as a private home. It later became a Benedictine abbey. Its lakeside setting, gardens and mountain backdrop make it one of Connemara’s best-known attractions.",
            "Connemara National Park protects mountains, heath, grassland and bog. Visitors can enjoy marked walking trails and learn about the region’s wildlife and landscape. Our stop today may be at the national park or Kylemore Abbey, depending on the chosen tour option.",
            "Connemara is home to many bird species and hardy animals adapted to the Atlantic climate. You may also see Connemara ponies, a native Irish pony breed known for its strength and sure-footed nature.",
            "The Inagh Valley is a beautiful route between mountains and lakes. Watch the changing colours of the hills and the reflections in the water as we make our way back towards Galway.",
            "We are returning to Galway after a day exploring Connemara. Thank you for travelling with Wild Atlantic Way Day Tours. We hope you enjoyed the mountains, lakes, culture and stories of the west of Ireland. Safe travels."
    },
        {
            "Good morning and welcome aboard our Aran Islands and Cliffs Cruise tour with Wild Atlantic Way Day Tours. Today we travel from Galway to Doolin, take a ferry to Inisheer, and, when weather and sea conditions permit, enjoy a cruise beneath the Cliffs of Moher.",
            "Galway is a city of music, festivals and Atlantic history. We are heading south through farmland and villages towards County Clare and the coast.",
            "As we travel, look out for cattle, sheep and traditional stone walls. Ireland’s grass-growing climate supports dairy and beef farming, while sheep are common in rougher landscapes.",
            "Kinvara is a picturesque harbour village on Galway Bay. Nearby Dunguaire Castle dates from the sixteenth century. The bay has long connected fishing communities, traders and travellers.",
            "The Burren is famous for limestone hills, stone walls and rare flowers. Its rock formed from ancient seabed sediments, while farming and grazing helped shape the landscape seen today.",
            "Doolin is a small village known for traditional Irish music and its ferry links to the Aran Islands. Please follow your driver’s instructions and the ferry crew’s directions when boarding.",
            "We are travelling across Atlantic waters towards Inisheer, the smallest of the three main Aran Islands. The crossing can be breezy, so take care on deck and follow all crew safety instructions.",
            "The three main Aran Islands are Inisheer, Inishmaan and Inishmore. They are known for limestone scenery, Irish-speaking communities, archaeological remains and a distinctive island culture.",
            "Inisheer, also called Inis Oírr, has narrow lanes, stone walls, beaches and traditional cottages. It is a wonderful place to explore at a relaxed pace while respecting residents and private property.",
            "Irish remains an important everyday language on the Aran Islands. Many residents speak both Irish and English. Place names and local traditions reflect a deep connection to the language.",
            "Islanders created small fields by building stone walls and improving thin soil. Farming on exposed limestone required enormous effort. These walls remain one of the islands’ most striking features.",
            "Fishing has been important to island life for generations. Today tourism, accommodation, food services, transport, farming, education and public services also provide employment. Island communities work hard to maintain year-round services.",
            "The islands contain ancient forts, churches and other archaeological sites. Their heritage tells stories of settlement, faith, trade and life on the edge of the Atlantic over many centuries.",
            "Aran sweaters are famous for their textured cable patterns. They became strongly associated with the islands and with Irish knitwear. Today they are sold around the world as a symbol of Irish craftsmanship.",
            "If sea conditions allow, our ferry will travel beneath the Cliffs of Moher. From the water you can appreciate the scale of the cliffs and their layered rock formations. The cruise may be altered or cancelled for safety because of weather or sea conditions.",
            "The Cliffs of Moher rise to around two hundred and fourteen metres at their highest point. Their ledges are important nesting sites for seabirds. Enjoy the view, but please stay within the ferry crew’s designated passenger areas.",
            "As we return towards Galway, we travel through the Burren and along the Atlantic coast. Look out for rocky headlands, Galway Bay and traditional farming landscapes.",
            "Thank you for joining our Aran Islands and Cliffs Cruise tour. We hope you enjoyed the island culture, Atlantic scenery and local stories. If you enjoyed the day, we would really appreciate a Google review. Safe travels from Wild Atlantic Way Day Tours."
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
        version.setText("V10 • Full Tour Commentary");
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
        note.setText("Full tour commentary, manual playback. GPS auto-triggering and background playback are not enabled yet. Set up audio before driving; do not operate your phone while driving.");
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
        voice.speak(scripts[tour][section], TextToSpeech.QUEUE_FLUSH, null, "v10-section");
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
