package sg.edu.rp.c347.knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button btnReadLtr;

    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager);
        btnReadLtr = findViewById(R.id.btnReadLater);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new frag1());
        al.add(new frag2());
        al.add(new frag3());
        al.add(new frag4());
        al.add(new frag5());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);

        btnReadLtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                Intent intent = new Intent(MainActivity.this, ScheduledNotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent
                        .getBroadcast(MainActivity.this, reqCode
                                , intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == R.id.action_previous) {
            if (vPager.getCurrentItem() > 0) {
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage, true);
            }
        } else if (id == R.id.action_next){
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max -1) {
                int nextPage = vPager.getCurrentItem() +1;
                vPager.setCurrentItem(nextPage, true);
            }
        } else {
            Random rand = new Random();
            int randomInt = rand.nextInt(6);
            vPager.setCurrentItem(randomInt, true);
            Log.d("random: ", randomInt + "");
        }

        return super.onOptionsItemSelected(item);
    }

    
}
