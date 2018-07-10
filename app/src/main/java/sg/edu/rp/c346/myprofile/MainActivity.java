package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.RadioGroupGender);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //input from editText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Add key-value
        prefEdit.putString("myName", strName);
        prefEdit.putFloat("myGPA", gpa);
        prefEdit.putInt("myGender", gender);
        //call commit() to save the changes
        prefEdit.commit();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        //obtain an instance of the SharedPreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Retrieved saved data from the SharedPreference
        String msg = prefs.getString("myName","");
        Float theGPA = prefs.getFloat("myGPA", 0.0f);
        int theGender = prefs.getInt("myGender", R.id.radioButtonGenderMale);

        //update UI element with the value
        etName.setText(msg);
        etGPA.setText(theGPA.toString());
        rgGender.check(theGender);
    }
}
