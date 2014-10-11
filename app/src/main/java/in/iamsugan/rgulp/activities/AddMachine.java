package in.iamsugan.rgulp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andreabaccega.widget.FormEditText;

import in.iamsugan.rgulp.R;

public class AddMachine extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
    }

    public void addMachine(View view) {
        FormEditText etMachineName = (FormEditText)findViewById(R.id.machineNameET);
        FormEditText etIPAddress = (FormEditText)findViewById(R.id.ipAddressET);
        FormEditText etPortNumber = (FormEditText)findViewById(R.id.portNumberET);
        FormEditText[] allFields = { etIPAddress, etPortNumber, etMachineName };

        boolean allValid = true;
        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }

        if (allValid) {
            Intent machineListIntent = new Intent();
            machineListIntent.putExtra("name", etMachineName.getText().toString());
            machineListIntent.putExtra("ip", etIPAddress.getText().toString());
            machineListIntent.putExtra("port", etPortNumber.getText().toString());
            setResult(RESULT_OK, machineListIntent);
            finish();
        }
    }
}
