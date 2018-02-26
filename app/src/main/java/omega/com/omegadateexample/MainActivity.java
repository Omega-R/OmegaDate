package omega.com.omegadateexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import omega.com.omegadate.OmegaDate;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        updateText();
    }

    private void updateText() {
        OmegaDate date = new OmegaDate();
        textView.setText("Current date " + date + "\n");
        textView.append(date.formatDate() + "\n");
        textView.append("Day of month " + date.getDayOfMonth() + "\n");
        textView.append("Month " + date.getMonth() + "\n");
        textView.append("Year " + date.getYear() + "\n");
        textView.append("\n");

        date.addDays(1).addSeconds(1);
        textView.append("Updated date " + date + "\n");
        textView.append(date.formatDate() + "\n");
        textView.append("Day of month " + date.getDayOfMonth() + "\n");
        textView.append("Month " + date.getMonth() + "\n");
        textView.append("Year " + date.getYear() + "\n");
        textView.append("\n");

        OmegaDate currentDate = new OmegaDate();
        long differenceInMillis = date.differenceBetweenDatesInMillis(currentDate);
        long differenceInSec = date.differenceBetweenDatesInSec(currentDate);
        long differenceInMin = date.differenceBetweenDatesInMin(currentDate);
        long differenceInDays = date.differenceBetweenDatesInDays(currentDate);

        textView.append("Difference between new date In Millis = " + differenceInMillis + "\n");
        textView.append("Difference between new date In Sec = " + differenceInSec + "\n");
        textView.append("Difference between new date In Min = " + differenceInMin + "\n");
        textView.append("Difference between new date In Days = " + differenceInDays + "\n");
    }

}
