package gromney.moderartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.Random;


public class MainActivity extends Activity {

    int mRectangles[] = {
            R.id.rectangleLeft1,R.id.rectangleLeft2,
            R.id.rectangRight1,R.id.rectangRight2,R.id.rectangRight3
    };
    int originalColor[];

    LinearLayout containerLeft;
    LinearLayout containerRight;
    SeekBar bar;
    View grayRectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random r = new Random();

        containerLeft = (LinearLayout) findViewById(R.id.containerLeft);
        containerRight = (LinearLayout) findViewById(R.id.containerRight);
        grayRectangle = findViewById(mRectangles[r.nextInt(4)]);

        bar = (SeekBar) findViewById(R.id.seek_bar);
//        setInitialColors();

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateBackground();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_text)
                    .setCancelable(false)
                    .setPositiveButton(R.string.visit_moma,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://www.moma.org"));
                            Intent chooser = Intent.createChooser(intent,"Abrir con");
                            startActivity(chooser);
                        }
                    })
                    .setNegativeButton(R.string.not_now,null);
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setInitialColors(){

        Random r = new Random();
        grayRectangle = findViewById(mRectangles[r.nextInt(4)]);
        grayRectangle.setBackgroundColor(Color.GRAY);

        for (int i = 0; i < 4; i++) {
            if (grayRectangle.getId() != mRectangles[i]){
                int color = Color.argb(254, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                findViewById(mRectangles[i]).setBackgroundColor(color);
                originalColor[i] = color;
            }
        }
    }
    private void updateBackground(){
        containerLeft.getChildAt(0).setBackgroundColor(Color.parseColor("#ffaa66cc") - bar.getProgress());
        containerLeft.getChildAt(1).setBackgroundColor(Color.parseColor("#ff00ddff") - bar.getProgress());

        containerRight.getChildAt(0).setBackgroundColor(Color.parseColor("#ffff4444") - bar.getProgress());
        containerRight.getChildAt(2).setBackgroundColor(Color.parseColor("#ff0099cc") - bar.getProgress());
    }
}
