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

    private TextView mText;
    public  EditText wt;
    public double weight=0.0;
    public int value=0;
    String x;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingFragmentView = inflater.inflate(R.layout.fragment_setting, container, false);
        autoLightSwitch = settingFragmentView.findViewById(R.id.switch3);
        onlyEnglishSwitch = settingFragmentView.findViewById(R.id.onlyEnglishSwitch);

        mText = settingFragmentView.findViewById(R.id.textView7);
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

        String[] values = {"10 minutes","30 minutes","40 minutes","1 hour ","2 hour","forever" };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,values);
        final Spinner spinner = (Spinner) settingFragmentView.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        wt = (EditText) settingFragmentView.findViewById(R.id.miss);
        // Called when the focus state of a view has changed.
        wt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String item = parent.getItemAtPosition(position).toString();
                            x = wt.getText().toString();
                            value = Integer.parseInt(x);
                            int mass =value;
                            switch (item){
                                case "10 minutes": weight = mass * 0.38; break;
                                case "30 minutes": weight = mass * 0.91; break;
                                case "40 minutes": weight = mass * 1; break;
                                case "1 hour": weight = mass * 0.38; break;
                                default : weight = 0.0; break;
                            }
                            Toast.makeText(getContext(), weight + " LBs", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
        });

        return settingFragmentView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
