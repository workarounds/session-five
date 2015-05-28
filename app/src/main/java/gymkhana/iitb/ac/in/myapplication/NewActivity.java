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


public class NewActivity extends Activity {
    EditText mPassword;
    EditText mRetypePassword;
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        if(checkForPassword()) {
            Intent intent = new Intent(NewActivity.this, CheckPasswordActivity.class);
            startActivity(intent);
        } else {
            mPassword = (EditText) findViewById(R.id.etPassword);
            mRetypePassword = (EditText) findViewById(R.id.etRetypePassword);
            mSubmit = (Button) findViewById(R.id.btnSubmit);

            mSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String passwd = mPassword.getText().toString();
                    String retypePasswd = mRetypePassword.getText().toString();

                    if(passwd.equals(retypePasswd)) {
                        storePassword(passwd);
                    } else {
                        Toast.makeText(NewActivity.this,
                                "Given passwords dont match",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean checkForPassword() {
        SharedPreferences prefs = getSharedPreferences(
                "androidbc",
                Context.MODE_PRIVATE
        );
        return prefs.contains("myPassword");
    }

    private void storePassword(String passwd) {
        SharedPreferences preferences = getSharedPreferences(
                "androidbc",
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("myPassword", passwd);
        editor.apply();
        Toast.makeText(NewActivity.this, "Password saved", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(NewActivity.this, CheckPasswordActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkForPassword()) {
            Intent intent = new Intent(NewActivity.this, CheckPasswordActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
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

    public void newButtonClick(View v) {
        Toast.makeText(NewActivity.this,
                "New Button clicked",
                Toast.LENGTH_SHORT).show();
    }
}
