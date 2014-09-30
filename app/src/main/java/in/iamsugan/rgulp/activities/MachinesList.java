package in.iamsugan.rgulp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import in.iamsugan.rgulp.R;


public class MachinesList extends Activity {

    static final int ADD_MACHINE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.machines_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_machine) {
            Intent addMachineIntent = new Intent(this, AddMachine.class);
            startActivityForResult(addMachineIntent, ADD_MACHINE_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_MACHINE_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Do something with the contact here (bigger example below)
            }
        }
    }
}
