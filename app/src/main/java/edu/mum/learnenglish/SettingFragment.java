package edu.mum.learnenglish;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import edu.mum.learnenglish.helper.SettingManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private View settingFragmentView;
    private Switch autoLightSwitch;
    private Switch onlyEnglishSwitch;
    private Switch shakeSwitch;

    public int value=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingFragmentView = inflater.inflate(R.layout.fragment_setting, container, false);
        autoLightSwitch = settingFragmentView.findViewById(R.id.switch3);
        onlyEnglishSwitch = settingFragmentView.findViewById(R.id.onlyEnglishSwitch);
        shakeSwitch = settingFragmentView.findViewById(R.id.shakeSwitch);
        autoLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    SettingManager.setLightSensorFlag(getContext(),1);
                }else {
                    SettingManager.setLightSensorFlag(getContext(),0);
                }
            }
        });

        onlyEnglishSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    SettingManager.setEnglishOnlyFlag(getContext(),1);
                }else {
                    SettingManager.setEnglishOnlyFlag(getContext(),0);
                }
            }
        });

        shakeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SettingManager.setShakeFlag(getContext(),1);
                }else {
                    SettingManager.setShakeFlag(getContext(),0);
                }
            }
        });


        final String[] values = {"forever","5 seconds","10 minutes","30 minutes","1 hour"};//1 minutes is for test
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,values);
        final Spinner spinner = (Spinner) settingFragmentView.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        int position = adapter.getPosition(SettingManager.getPlayTime(getContext()));   //根据该选项获取位置
        spinner.setSelection(position);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                SettingManager.setPlayTime(getContext(),item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return settingFragmentView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
