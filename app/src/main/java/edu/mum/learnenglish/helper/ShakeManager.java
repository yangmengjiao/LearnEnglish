package edu.mum.learnenglish.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipePlaceHolderView;


public class ShakeManager implements SensorEventListener
{
    private Context mContext;
    private SensorManager mSensorManager;
    private SwipePlaceHolderView mSwipeView;
    private Sensor mSensor;
    private int mSpeed=3000;
    private int mInterval=50;
    private long LastTime;
    private float LastX,LastY,LastZ;
    public ShakeManager(Context mContext, SwipePlaceHolderView mSwipeView)
    {
        this.mSwipeView = mSwipeView;
        this.mContext=mContext;
        Start();
    }

    public void Start()
    {
        mSensorManager=(SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager!=null)
        {
            mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if(mSensor!=null)
        {
            mSensorManager.registerListener(this, mSensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void Stop()
    {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent Event)
    {
        long NowTime=System.currentTimeMillis();
        if((NowTime-LastTime)<mInterval)
            return;
        LastTime=NowTime;
        float NowX=Event.values[0];
        float NowY=Event.values[1];
        float NowZ=Event.values[2];
        float DeltaX=NowX-LastX;
        float DeltaY=NowY-LastY;
        float DeltaZ=NowZ-LastZ;
        LastX=NowX;
        LastY=NowY;
        LastZ=NowZ;
        double NowSpeed = Math.sqrt(DeltaX * DeltaX + DeltaY * DeltaY + DeltaZ * DeltaZ)/mInterval * 10000;
        if(NowSpeed>=mSpeed)
        {
          //user shaked the phone
            mSwipeView.doSwipe(true);
        }
    }
}
