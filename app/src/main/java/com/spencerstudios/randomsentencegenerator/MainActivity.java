package com.spencerstudios.randomsentencegenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_sentence);
        textView.setText(buildRandomSentence());
    }

    private String getRandomWord(String[] strings) {
        return strings[new Random().nextInt(strings.length)];
    }

    private String buildRandomSentence() {
        return "\"" +
                getRandomWord(Words.PRONOUNS) + " " +
                getRandomWord(Words.ADJECTIVES) + " " +
                getRandomWord(Words.NOUNS) + " " +
                getRandomWord(Words.VERBS) + " " +
                getRandomWord(Words.PREPOSITIONS) + " " +
                getRandomWord(Words.PRONOUNS) + " " +
                getRandomWord(Words.ADJECTIVES) + " " +
                getRandomWord(Words.NOUNS) +
                ".\"";
    }

    public void clickEventGenerateNextSentence(View view) {
        textView.setText(buildRandomSentence());
    }

    public void clickEventCopyToClipboard(View view) {
        String s = textView.getText().toString();
        if (!s.isEmpty()) {
            copySentenceToClipboard(s);
        } else {
            toast(getString(R.string.tv_empty_message));
        }
    }

    private void copySentenceToClipboard(String s) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", s);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            toast(getString(R.string.copied_to_clipboard));
        } else {
            toast(getString(R.string.error_copy_to_clipboard));
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}