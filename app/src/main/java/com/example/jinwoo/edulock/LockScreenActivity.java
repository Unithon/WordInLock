package com.example.jinwoo.edulock;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jinwoo.edulock.patternview.PatternView;
import com.example.jinwoo.edulock.patternview.cells.Cell;
import com.example.jinwoo.edulock.patternview.Pattern;

/**
 * Created by Jinwoo on 2016-02-11.
 */
public class LockScreenActivity extends Activity {

    private PatternView patternView;

    private String patternString = "";

    //int arrX[] = {0, 0, 1, 2, 2};
    //int arrY[] = {0, 1, 1, 1, 2};


/*
    private void patternSetting() {
        for (int i = 0; i < 4; i++) {
            Cell cell = new Cell(arrX[i], arrY[i]);
            patternString += cell.getId();
            if(i < arrX.length - 1) patternString += "&";
        }
    }
*/

    private void patternSetting(int[] resultX, int[] resultY) {
        for(int i=0; i<resultX.length; i++) {
            Cell cell = new Cell(resultX[i], resultY[i]);
            patternString += cell.getId();

            if(i < resultX.length - 1)
                patternString += "&";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lockscreen);

        patternView = (PatternView) findViewById(R.id.patternView);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        Pattern.do_Process();
        patternSetting(Pattern.get_ResultX(), Pattern.get_ResultY());

        System.out.println("cdy initPattern = " + patternString);

        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
            @Override
            public void onPatternDetected() {
                if (patternString.equals(patternView.getPatternString())) {
                    Toast.makeText(getApplicationContext(), "PATTERN CORRECT", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "PATTERN NOT CORRECT", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();
            }
        });
    }
    //나머지 구성하기.
}