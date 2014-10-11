package in.iamsugan.rgulp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import in.iamsugan.rgulp.R;
import in.iamsugan.rgulp.models.Machine;

public class MachinesList extends Activity {

    static final int ADD_MACHINE_REQUEST = 1;
    ListView machinesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines_list);
        machinesList = (ListView)findViewById(R.id.machinesList);
        machinesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: Open New Activity to connect to the remote machine
            }
        });
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
                String ipAddress = data.getStringExtra("ip");
                int portNumber = Integer.parseInt(data.getStringExtra("port"));
                Machine machine = new Machine();
                machine.setIp(ipAddress);
                machine.setPort(portNumber);
                machine.setName(data.getStringExtra("name"));
                machine.save();
            }
        }
    }
}
