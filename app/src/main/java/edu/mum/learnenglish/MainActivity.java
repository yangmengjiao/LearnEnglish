package edu.mum.learnenglish;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import edu.mum.learnenglish.helper.SettingManager;

public class MainActivity extends AppCompatActivity  implements SensorEventListener  {

    private final String FRAGMENT_HELP = "fragment_help";
    private final String FRAGMENT_WORDS = "fragment_words";
    private final String FRAGMENT_SETTING = "fragment_setting";
    private final String FRAGMENT_RANDOM = "fragment_random";

    private final Fragment fragmentHelp = new HelpFragment();
    private final Fragment fragmentFood = new WordsFragment();
    private final Fragment fragmentSetting = new SettingFragment();

    private final Fragment fragmentAnimal = new WordsFragment();
    private final Fragment fragmentRandom = new RandomWordFragment();
    private static float oldLightSensorValue = 0;//for control the light sensor value.

    SensorManager sensorManager;
    Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Random words ");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root,fragmentRandom,FRAGMENT_RANDOM)
                .commit();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) { Toast.makeText(getApplicationContext(), "No Sensor", Toast.LENGTH_SHORT).show(); }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle data = new Bundle();
        switch (item.getItemId()) {

            case R.id.random:
                getSupportActionBar().setTitle(R.string.random_fragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root,fragmentRandom,FRAGMENT_RANDOM)
                        .commit();
                break;
            case R.id.food:

                data.putString("type", "food");

                data.putString("jsonfilename", "random_words.json");
                fragmentFood.setArguments(data);
                getSupportActionBar().setTitle(R.string.food_fragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root,fragmentFood,FRAGMENT_WORDS)
                        .commit();
                break;
            case R.id.animal:
                data.putString("type", "animal");
                data.putString("jsonfilename", "animals.json");
                fragmentAnimal.setArguments(data);
                getSupportActionBar().setTitle(R.string.animal_fragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root,fragmentAnimal,FRAGMENT_WORDS)
                        .commit();
                break;

            case R.id.setting:
                getSupportActionBar().setTitle(R.string.setting_fragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, fragmentSetting, FRAGMENT_SETTING)
                        .commit();
                break;
            case R.id.help:
                getSupportActionBar().setTitle(R.string.help_fragment);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root,fragmentHelp,FRAGMENT_HELP)
                        .commit();
                break;
            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Exit");
                builder.setMessage("Do you really want to exit?");
                builder.setIcon(R.drawable.alert);
                AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==dialog.BUTTON_POSITIVE){
                            dialog.dismiss();
                            finish();
                        }
                        else if(which==dialog.BUTTON_NEGATIVE){
                            dialog.dismiss();
                        }
                    }
                };
                builder.setPositiveButton("Yes",listener);
                builder.setNegativeButton("No",listener);
                builder.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(SettingManager.getLightSensorFlag(getApplicationContext()) == 0){
            return;
        }
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float currentReading = event.values[0];
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            float differ = currentReading - oldLightSensorValue;
            if(Math.abs(differ)>40){
                lp.screenBrightness = Float.valueOf(currentReading) * (1f / 255f);
                getWindow().setAttributes(lp);
                oldLightSensorValue = currentReading;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void clickPlayer(View v){

    }

}
