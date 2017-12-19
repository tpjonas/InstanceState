package cimdata.android.dez2017.instancestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String INSTANCE_KEY_STRING_TEXT = "instance.key.string.text";

    TextView outputText;
    Button changeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String foo = (String) savedInstanceState.get("instance.key.string.text");

        outputText = findViewById(R.id.txt_output);
        changeButton = findViewById(R.id.btn_change);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputText.setText("geändert!");
            }
        });

        // Möglichkeit 1:
        // Den Status direkt in der onCreate wiederherstellen
        // Hier muss abgefragt werden, ob ein Status existiert
//        if (savedInstanceState != null) {
//            String text = savedInstanceState.getString(INSTANCE_KEY_STRING_TEXT);
//            outputText.setText(text);
//        }


    }

    // Möglichkeit 2:
    // Die Callback-Methode onRestoreInstanceState verwenden
    // Diese wird nur aufgerufen, wenn ein Status existiert
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        String text = savedInstanceState.getString(INSTANCE_KEY_STRING_TEXT);
        outputText.setText(text);

        super.onRestoreInstanceState(savedInstanceState);
    }

    // In dieser Methode merken wir uns Werte, die nach einer Statusänderung,
    // z.B. einer Drehung, unsere Activity wiederhergestellt werden sollen

    // !
    // Wir müssen die onSaveInstanceState Methode mit einem einem Parameter überschreiben
    // sonst klappt es nicht
    //!
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Zuerst holen wir uns den Wert, der gemerkt werden soll
        String text = String.valueOf(outputText.getText());

        // Dann schreiben wir den Wert in unser Bundle
        // Parameter:
        // 1. Ein eindeutiger Key
        // 2. Der Wert, der gespeichert werden soll
        outState.putString(INSTANCE_KEY_STRING_TEXT, text);

        // Hier wird das Bundle gespeichert, so dass es beim neuen Laden
        // wieder zur Verfügung steht
        super.onSaveInstanceState(outState);

    }
}
