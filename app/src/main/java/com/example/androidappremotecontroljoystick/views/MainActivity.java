package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import com.example.androidappremotecontroljoystick.Model.FGPlayer;
import com.example.androidappremotecontroljoystick.view_model.ViewModel;
import com.example.androidappremotecontroljoystick.R;
import com.example.androidappremotecontroljoystick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private ActivityMainBinding binding;

    //@SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        FGPlayer fgPlayer = new FGPlayer();
        this.viewModel = new ViewModel(fgPlayer);
        binding.setViewModel(viewModel);

        binding.joystickActivity.joystickListener = (a, e) -> {
            //viewModel.newSetAileronAndElevator(ail / binding.JoystickActivity.curR, ele / binding.JoystickActivity.curR);
            viewModel.newSetAileron(a / binding.joystickActivity.curR);
            viewModel.newSetElevator(e  / binding.joystickActivity.curR);
        };

        binding.connectButton.setOnClickListener(v -> {
            try {
                new Thread(() -> viewModel.connect(binding.ipText.getText().toString(), Integer.parseInt(binding.portText.getText().toString()))).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /*
        editIp = (EditText) findViewById(R.id.editIp);
        editPort = (EditText) findViewById(R.id.editPort);
        editIpTV = (TextView) findViewById(R.id.editIpTV);
        editPortTV = (TextView) findViewById(R.id.editPortTV);
        rudderTV = (TextView) findViewById(R.id.rudderTV);
        float tr1 = 50;
        rudderTV.setText("" + Math.round(((tr1 / 50) - 1) * 100) / 100);
        throttleTV = (TextView) findViewById(R.id.throttleTV);
        float tr2 = 0;
        throttleTV.setText("" + Math.round((tr2 / 100) * 100) / 100);

        connectButton = (Button) findViewById(R.id.connectButton);

        joystickActivity = (JoystickActivity) findViewById(R.id.joystickActivity);
        joystickActivity.joystickInterface = (float aileron, float elevator)-> viewModel.newSetAileronAndElevator(aileron, elevator);

        rudderSK = (SeekBar) findViewById(R.id.rudderSK);
        rudderSK.getProgress();
        rudderSK.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser) {
                progressChangedValue = prog;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float x = (((float)progressChangedValue/100)-1);
                if (x>0.5) {x=1;
                } else if(x<=-0.5) {x=-1;
                } else x=0;
                viewModel.newSetRudder(x);
                rudderTV.setText(" " + String.valueOf(x));
            }
        });

        throttleSK = (SeekBar) findViewById(R.id.throttleSK);
        throttleSK.getProgress();
        throttleSK.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser) {
                progressChangedValue = prog;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                viewModel.newSetThrottle((float)progressChangedValue);
                rudderTV.setText(" " + String.valueOf((float)progressChangedValue/1000));
            }
        });

         */

        binding.throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser) {
                int pDiv = prog / 100;
                viewModel.newSetThrottle(pDiv);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean fromUser) {
                int pMinus = prog - 50;
                int pMinusDiv = pMinus / 50;
                viewModel.newSetRudder(pMinusDiv);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
/*
    public void OnClickConnection (View MainActivity) {
        viewModel.newConnectServer(editIpTV.toString(), editPortTV.toString());
    }

 */

    /*
    public void sendJoystickActivity(Intent intent) {
        startActivity(intent);
    }

    public interface JoystickListener
    {
        void joystickTouched(float xVal, float yVal);
    }

    public void JoystickListener() {
        joystickLayout.setOnTouchListener(new View.OnTouchListener() {
            //@SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent mEvent) {
                joystick.drawTouch(mEvent);
                float x = mEvent.getX();
                float y = mEvent.getY();
                String inside = joystick.IsInside(x, y);
                SendCommand(x, y, inside);
                return true;
            }
        });
    }

    public void SendCommand(float x,float y, String inside) {
        if (inside.equals(RIGHT) || inside.equals(LEFT)) {
            FGPlayer.getInstance().SendCommandsToSimulator(aileronSetCommand + " " + String.valueOf(x));
        } else if (inside.equals(UP) || inside.equals(DOWN)) {
            FGPlayer.getInstance().SendCommandsToSimulator(elavatorSetCommand + " " + String.valueOf(y));
        }
    }

     */
}
