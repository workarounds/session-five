package gymkhana.iitb.ac.in.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CheckPasswordActivity extends Activity {
    EditText mPassword;
    Button mCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);
        mPassword = (EditText) findViewById(R.id.etCheckPassword);
        mCheck = (Button) findViewById(R.id.btnCheck);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validatePassword(View v) {
        String input = mPassword.getText().toString();
        if(verifyPassword(input)) {
            Toast.makeText(CheckPasswordActivity.this, "Perfecto!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CheckPasswordActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(CheckPasswordActivity.this, "Thou shall not pass!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verifyPassword(String input) {
        SharedPreferences prefs = getSharedPreferences("androidbc", Context.MODE_PRIVATE);
        String storedPassword = prefs.getString("myPassword", "");
        return storedPassword.equals(input);
    }

    public void resetPassword(View v) {
        String input = mPassword.getText().toString();
        if(verifyPassword(input)) {
            removePassword();
            Intent intent = new Intent(CheckPasswordActivity.this, NewActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(CheckPasswordActivity.this, "Thou shall not pass!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void removePassword() {
        SharedPreferences prefs = getSharedPreferences("androidbc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("myPassword");
        editor.apply();
    }
}
