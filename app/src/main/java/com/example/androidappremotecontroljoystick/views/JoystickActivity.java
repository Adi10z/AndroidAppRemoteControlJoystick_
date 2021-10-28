package com.example.androidappremotecontroljoystick.views;

import android.content.Context;
//import android.support.annotation.Nullable;
//import androidx.annotation.Nullable;
//import androidx.support.annotation.Nullable;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
//import android.view.SurfaceHolder;
import android.annotation.SuppressLint;
//import android.view.SurfaceView;
//import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

//import com.example.androidappremotecontroljoystick.View_Model.JoystickActivity;



public class JoystickActivity extends View {


    interface JoystickListener {
        void onChange(double x, double y) throws InterruptedException;
    }

    private static final String UP = "UP";
    private static final String RIGHT = "RIGHT";
    private static final String DOWN = "DOWN";
    private static final String LEFT = "LEFT";
    private static final String INSIDE_NONE = "NONE";
    private MainActivity mainactivity;
    //JoystickInterface joystickInterface;
    private Paint background = new Paint();
    private Paint outerCircle = new Paint();
    private Paint innerCircle = new Paint();
    private final Paint paintColors = new Paint();
    private double x;
    private double y;
    private double dist;
    private double curX;
    private double curY;
    public double curR;
    private double userX = 0;
    private double userY = 0;
    public JoystickListener joystickListener;
    //private double WidthdivTwo;
    //private double HeightdivTwo;
    private boolean stopMovement = true;
    private float widthStart;
    private float widthEnd;
    private float heightStart;
    private float heightEnd;
    //private boolean touchInside = true;
    //private int min_distance = 0;
    private float distance;
    //private float curX;
    //private float curY;
    private float r =70;
    //private RectF rect;
    private float heighSE;

    //public static float curX;
    //public static float curY;
    public static float radius1;
    public static float radius2;
    public static float aileron;
    public static float elevator;

    public JoystickActivity(Context context) {
        super(context);
        //getHolder().addCallback(this);
        //setOnTouchListener(this);
        //rudder = joystick.getRudder();
        //throttle = joystick.getThrottle();
    }

    public JoystickActivity(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JoystickActivity(Context context, @Nullable AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);
        //getHolder().addCallback(this);
        //setOnTouchListener(this);
    }

    public JoystickActivity(Context context, @Nullable AttributeSet attributeSet, int style, int styleR){
        super(context, attributeSet, style, styleR);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double inCurX;
        double inCurY;
        paintColors.setAntiAlias(true);
        this.curX = inCurX = (float)getWidth() / 2;
        this.curY = inCurY = (float)getHeight() / 2;
        paintColors.setColor(Color.BLACK);
        this.curR = (float)getWidth() / 4;
        canvas.drawCircle((float)this.curX, (float)this.curY, (float)this.curR, paintColors);
        paintColors.setColor(Color.GRAY);
        double inCurR = (float) getWidth() / 8;
        double ucX = inCurX + userX;
        double ucY = inCurY + userY;
        canvas.drawCircle((float)ucX, (float)ucY, (float)inCurR, paintColors);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            double newX, newY, newPowX, newPowY;
            newX = motionEvent.getX() - this.curX;
            newPowX = newX * newX;
            newY = motionEvent.getY() - this.curY;
            newPowY = newY * newY;
            dist = Math.sqrt(newPowX + newPowY);
            if (dist <= this.curR){
                double WidthdivTwo = getWidth() / 2.0;
                double HeightdivTwo = getHeight()  / 2.0;
                double motionXW = motionEvent.getX() - WidthdivTwo;
                double motionYH = motionEvent.getY() - HeightdivTwo;
                this.userX = motionXW;
                this.userY = motionYH;
                try {
                    joystickListener.onChange(this.userX, this.userY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            invalidate();
            return true;
        } else {
            this.userX = 0;
            this.userY = 0;
            try {
                joystickListener.onChange(this.userX, this.userY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invalidate();
            return true;
        }
    }





/*
    public void setAileronJoyAct(float aileron) {
        //this.aileron = aileron;
        float a = aileron - 133;
        float b = 266;
        float c = (a / b) - 1;
        this.aileron = Math.round(c * 100) / 100;
        joystickInterface.onChange(this.aileron,this.elevator);
    }

    public void setElevatorJoyAct(float elevator) {
        //this.elevator = elevator;
        float a = elevator - 146;
        float b = 266;
        float c = (a / b) - 1;
        this.elevator = (Math.round(c * 100) / 100) * (-1);
        joystickInterface.onChange(this.aileron,this.elevator);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        MoveBackToCenter();
        drawJoystick(curX, curY);
    }

    public void drawJoystick(float newX, float newY){
        Canvas canvas = this.getHolder().lockCanvas();
        Paint colors = new Paint();
        canvas.drawColor(Color.WHITE);
        colors.setARGB(100,0,0,100);
        canvas.drawCircle(newX, newY, radius2, colors);
        getHolder().unlockCanvasAndPost(canvas);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
    }

 */

    /*
    public void MoveBackToCenter(){
        curX = getWidth() / 2;
        curY = getHeight() / 2;
        MoveBackToRadius();
    }

    public void MoveBackToRadius(){
        radius1 = ((float)getWidth() - ((float)getWidth() / 8));
        radius2 = (getHeight() - ((float)getHeight() / 8));
    }

     */
/*
    public boolean onTouch(View view, MotionEvent motionEvent){
        if (view.equals(this)){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN && distance <= r) {
                stopMovement = false;
            } else if (motionEvent.getAction() != motionEvent.ACTION_UP) {
                distance = (float) Math.sqrt(((motionEvent.getX() -curX)*2) + ((motionEvent.getY() - curY)*2));
                if (distance < radius1){
                    setAileronJoyAct(motionEvent.getX());
                    setElevatorJoyAct(motionEvent.getY());
                    drawJoystick(motionEvent.getX(), motionEvent.getY());
                }
                else{
                    float rDivD = radius1 / distance;
                    float newX = motionEvent.getX() - curX;
                    float newPow1 = newX * rDivD;
                    float constX = curX + newPow1;
                    float newY = motionEvent.getY() - curY;
                    float newPow2 = newY * rDivD;
                    float constY = curY + newPow2;
                    setAileronJoyAct(constX);
                    setElevatorJoyAct(constY);
                    drawJoystick(constX, constY);
                }
            } else {
                setAileronJoyAct(curX);
                setElevatorJoyAct(curY);
                drawJoystick(curX, curY);
            }
        }
        return true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
    }

    @Override
    public void onChange(float newAileron, float newElevator) {
    }

 */

    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SeekBar rudderSeekBar=(SeekBar)findViewById(R.id.rudder);
        rudder=rudderSeekBar.getProgress();
        SeekBar throttleSeekBar=(SeekBar)findViewById(R.id.throttle);
        throttle=throttleSeekBar.getProgress();

        FGPlayer.getInstance().Connect();
        setContentView(R.layout.activity_joystick);
    }

    public void setAileron(float aileron) {
        this.aileron = aileron;
    }

    public void setElevator(float elevator) {
        this.elevator = elevator;
    }

    public interface JoystickInterface {
        static void onChange(float interAileron, float interElevator);
    }

     */
}
