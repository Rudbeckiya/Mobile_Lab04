package g313.avchuhov.lab_04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox[] check = new CheckBox[4];
    EditText[] amount = new EditText[4];
    EditText[] price = new EditText[4];
    RadioButton radioToast, radioDialog;
    boolean runToast, success = true;
    String strDialogResult = "";
    float result = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check[0] = findViewById(R.id.cb_apple);
        check[1] = findViewById(R.id.cb_strawberry);
        check[2] = findViewById(R.id.cb_blueberry);
        check[3] = findViewById(R.id.cb_potato);

        amount[0] = findViewById(R.id.et_appleAmount);
        amount[1] = findViewById(R.id.et_strawberryAmount);
        amount[2] = findViewById(R.id.et_blueberryAmount);
        amount[3] = findViewById(R.id.et_potatoAmount);

        price[0] = findViewById(R.id.et_applePrice);
        price[1] = findViewById(R.id.et_strawberryPrice);
        price[2] = findViewById(R.id.et_blueberryPrice);
        price[3] = findViewById(R.id.et_potatoPrice);

        radioToast = findViewById(R.id.rb_toastClass);
        radioDialog = findViewById(R.id.rb_dialogWindow);
    }

    public void incorrectInput()
    {
		if (radioToast.isChecked()) Toast.makeText(this, "Incorrect input...", Toast.LENGTH_SHORT).show();
		else
		{
			AlertDialog.Builder build = new AlertDialog.Builder(this);
            AlertDialog dRes = build.create();
            dRes.setTitle("Error");
            strDialogResult = "Incorrect input :(";
            dRes.setMessage(strDialogResult);
            dRes.setIcon(R.drawable.stickman_icon);
            dRes.setButton(Dialog.BUTTON_NEGATIVE, "OK", (dialog, id) -> dialog.cancel());
            dRes.show();
            strDialogResult = ""; 
		}
    }

    public void onCalculate(View v)
    {
        int mnt = 0, p = 0;
        float prc = 0;

        if (radioToast.isChecked()) runToast = true;
        else runToast = false;

        for (int i = 0; i < 4; i++)
        {
            if (check[i].isChecked())
            {
                p = p + 1;
                try
                {
                    mnt = Integer.parseInt(amount[i].getText().toString());
                    prc = Float.parseFloat(price[i].getText().toString());
                }
                catch (Exception e)
                {
                        incorrectInput();
						return;
                }

                if (mnt == 0 || prc == 0)
                {
                        incorrectInput();
						return;
                }

                float sum = mnt * prc;
                result += sum;

                if (radioToast.isChecked()) 
                    Toast.makeText(this, check[i].getText().toString() + ": " + mnt +
                        " pcs. * " + prc + " $ = " + sum + " $", Toast.LENGTH_SHORT).show();
                else
                {
                    strDialogResult += check[i].getText().toString() + ": " + mnt + " pcs. * " + prc + " $ = " + sum + " $\n";
                }
            }
        }
        if (p == 0)
        {
            incorrectInput();
            return;
        }
        if (radioToast.isChecked()) 
            Toast.makeText(this,"Total: " + result + " $", Toast.LENGTH_SHORT).show();
        else
        {
            strDialogResult += "\nTotal: " + result + " $";
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            AlertDialog dRes = build.create();
            dRes.setTitle("Result");
            dRes.setMessage(strDialogResult);
            dRes.setIcon(R.drawable.stickman_icon);
            dRes.setButton(Dialog.BUTTON_NEGATIVE, "OK", (dialog, id) -> dialog.cancel());
            dRes.show();
            strDialogResult = "";
        }
    }
}