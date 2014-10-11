package in.iamsugan.rgulp.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.iamsugan.rgulp.R;
import in.iamsugan.rgulp.models.Machine;

/**
 * Created by sugan on 10/10/14.
 */
public class MachineListAdapter extends ArrayAdapter<Machine> {
    public MachineListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MachineListAdapter(Context context, int resource, List<Machine> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.machine_list_item, null);
        }

        Machine machine = getItem(position);

        if (machine != null) {
            TextView machineNameTV = (TextView) v.findViewById(R.id.machineNameTV);
            TextView ipAddressTV = (TextView) v.findViewById(R.id.ipAddressTV);
            TextView portNumberTV = (TextView) v.findViewById(R.id.portNumberTV);
            machineNameTV.setText(machine.getName());
            ipAddressTV.setText(machine.getIp());
            portNumberTV.setText(String.valueOf(machine.getPort()));
        }

        return v;

    }
}
