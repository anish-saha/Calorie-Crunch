package com.example.anish.crunchtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.View;
import android.view.Gravity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView calories = (TextView) findViewById(R.id.textView6);
                Spinner eSelected = (Spinner) findViewById(R.id.spinner);
                String exercise = eSelected.getSelectedItem().toString();
                EditText nSelected = (EditText) findViewById(R.id.editText);
                int checker = 0;
                if (exercise.equals("Pushups") || exercise.equals("Situps")
                        || exercise.equals("Squats") || exercise.equals("Pullups")) {
                    checker = 0;
                } else {
                    checker = 1;
                }
                RadioButton reps = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton min = (RadioButton) findViewById(R.id.radioButton3);
                int number = 0;
                if (nSelected.getText().toString().equals("")
                        || (checker == 0 && min.isChecked())
                        || (checker == 1 && reps.isChecked())) {
                    number = 0;
                    if (!nSelected.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(MainActivity.this,
                                "Pushups, Situps, Squats, and Pullups are counted in reps. " +
                                        "All other exercises are recorded in minutes. " +
                                        "Please check that the appropriate choice " +
                                        "has been selected", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } else {
                    number = Integer.parseInt(nSelected.getText().toString());
                }
                double res = numCals(exercise, number);
                String text = Double.toString(res);
                calories.setText(text);
                TextView others = (TextView) findViewById(R.id.textView11);
                String s = equivExercises(exercise, number);
                others.setText(s);
                EditText calSelected = (EditText) findViewById(R.id.editText4);
                int goal = 0;
                if (calSelected.getText().toString().equals("")) {
                    goal = 0;
                } else {
                    goal = Integer.parseInt(calSelected.getText().toString());
                }
                TextView goalShow = (TextView) findViewById(R.id.textView19);
                String n = "";
                n += reverseCal("Pushups", (double) goal);
                n += " reps Pushups. ";
                n += reverseCal("Situps", (double) goal);
                n += " reps Situps. ";
                n += reverseCal("Squats", (double) goal);
                n += " reps Squats. ";
                n += reverseCal("Pullups", (double) goal);
                n += " reps Pullups. ";
                n += reverseCal("Leg-lifts", (double) goal);
                n += " minutes of Leg-lifts. ";
                n += reverseCal("Planks", (double) goal);
                n += " minutes of Planks. ";
                n += reverseCal("Jumping Jacks", (double) goal);
                n += " minutes of Jumping Jacks. ";
                n += reverseCal("Cycling", (double) goal);
                n += " minutes of Cycling. ";
                n += reverseCal("Walking", (double) goal);
                n += " minutes of Walking. ";
                n += reverseCal("Jogging", (double) goal);
                n += " minutes of Jogging. ";
                n += reverseCal("Swimming", (double) goal);
                n += " minutes of Swimming. ";
                n += reverseCal("Stair-Climbing", (double) goal);
                n += " minutes of Stair Climbing.";
                goalShow.setText(n);
            }
        });
    }

    public double numCals(String exercise, int number) {
        double cal = 0.0;
        if (exercise.equals("Pushups")) {
            cal = number/3.5;
        }
        if (exercise.equals("Situps")) {
            cal = number/2.0;
        }
        if (exercise.equals("Squats")) {
            cal = number/2.25;
        }
        if (exercise.equals("Leg-lifts")) {
            cal = number * 4;
        }
        if (exercise.equals("Planks")) {
            cal = number * 4;
        }
        if (exercise.equals("Jumping Jacks")) {
            cal = number * 10;
        }
        if (exercise.equals("Pullups")) {
            cal = number;
        }
        if (exercise.equals("Cycling")) {
            cal = number * (100/12);
        }
        if (exercise.equals("Walking")) {
            cal = number * 5;
        }
        if (exercise.equals("Jogging")) {
            cal = number * (100/12);
        }
        if (exercise.equals("Swimming")) {
            cal = number * (100/13);
        }
        if (exercise.equals("Stair-Climbing")) {
            cal = number * (100/15);
        }
        return Math.round(cal * 10.0)/10.0;
    }

    public double reverseCal(String exercise, double calories) {
        double number = 0.0;
        if (exercise.equals("Pushups")) {
            number = calories/0.2857;
        }
        if (exercise.equals("Situps")) {
            number = calories/0.5;
        }
        if (exercise.equals("Squats")) {
            number = calories/0.44444444444;
        }
        if (exercise.equals("Leg-lifts")) {
            number = calories/4;
        }
        if (exercise.equals("Planks")) {
            number = calories/4;
        }
        if (exercise.equals("Jumping Jacks")) {
            number = calories/10;
        }
        if (exercise.equals("Pullups")) {
            number = calories;
        }
        if (exercise.equals("Cycling")) {
            number = calories/8.3333;
        }
        if (exercise.equals("Walking")) {
            number = calories/5;
        }
        if (exercise.equals("Jogging")) {
            number = calories/8.3333;
        }
        if (exercise.equals("Swimming")) {
            number = calories/7.6923;
        }
        if (exercise.equals("Stair-Climbing")) {
            number = calories/6.6667;
        }
        return Math.round(number * 10.0)/10.0;
    }

    public String equivExercises(String exercise, int number) {
        String[] allExercises = {"Pushups", "Situps", "Squats", "Pullups",
                "Leg-lifts","Planks", "Jumping Jacks", "Cycling", "Walking",
                "Jogging", "Swimming", "Stair-Climbing"};
        String res = "";
        for (int i = 0; i < allExercises.length; i++) {
            String s = allExercises[i];
            if (!s.equals(exercise)) {
                double cal = numCals(exercise, number);
                res += reverseCal(s, cal);
                res += " ";
                if (i < 4) {
                    res += "reps ";
                } else {
                    res += "minutes of ";
                }
                res += s;
                res += ". ";
            }
        }
        return res;
    }
}
