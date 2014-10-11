package in.iamsugan.rgulp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;

import net.schmizz.sshj.AndroidConfig;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import in.iamsugan.rgulp.R;
import in.iamsugan.rgulp.models.Machine;
import in.iamsugan.rgulp.util.NullHostKeyVerifier;

public class Connect extends Activity {

    private static final String TAG = "ConnectActivity";
    private final Context context = this;
    private Machine machine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        machine = (Machine) getIntent().getSerializableExtra("machine");
        setContentView(R.layout.activity_connect);
    }

    public void connect(View view) {
        FormEditText etUserName = (FormEditText)findViewById(R.id.userNameET);
        FormEditText etPassword = (FormEditText)findViewById(R.id.passwordET);
        FormEditText[] allFields = { etUserName, etPassword };

        boolean allValid = true;
        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }

        if (allValid) {
            ConvertPDF task = new ConvertPDF();
            task.execute(machine.getIp(), String.valueOf(machine.getPort()), etUserName.getText().toString(), etPassword.getText().toString());
        }
    }

    private class ConvertPDF extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        // Method executed before the main process
        @Override
        protected void onPreExecute() {

            // Display a progress dialog
            dialog = ProgressDialog.show(context, "Connect", "Authenticating. Please wait...");
        }

        @Override
        protected String doInBackground(String... arg) {
            String result = "";

            // creates new SSH client
            final SSHClient ssh = new SSHClient(new AndroidConfig());
            Session.Command cmd = null;
            try {

                // Adds a nullHostKeyVerifier
                ssh.addHostKeyVerifier(new NullHostKeyVerifier());

                // default port number
                int pn = 22;
                if (arg[3].length() > 0) {
                    pn = Integer.parseInt(arg[1]);
                }

                // connect to the machine
                try {
                    ssh.connect(arg[0], pn);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }

                // Authenticate with the password entered
                ssh.authPassword(arg[2], arg[3]);

                // start a new session
                final Session session = ssh.startSession();
                try {

                    // runs the commands in the remote machine
                    cmd = session.exec("cd work/erevmax; gulp tasks");

                    // reads the output of the command
                    result = IOUtils.readFully(cmd.getInputStream()).toString();

                    cmd.join(100, TimeUnit.SECONDS);
                } finally {
                    session.close();
                }
            } catch (UserAuthException e) {
                Log.e(TAG, "User Authentication Exception", e);
                return "user not authentic";
            } catch (TransportException e) {
                Log.e(TAG, "Transport Exception", e);
            } catch (ConnectionException e) {
                Log.e(TAG, "Exception", e);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            } finally {
                try {

                    // close the connection
                    ssh.disconnect();
                    ssh.close();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception", e);
                } catch (Exception e) {
                    Log.e(TAG, "Exception", e);
                }
            }
            if (cmd != null && cmd.getExitStatus() == 0) {
                return result;
            } else {
                return "Failure";
            }
        }

        // Executed after the main process
        @Override
        protected void onPostExecute(String result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            try {
                if (!result.equals("Failure")) {
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                } else if (result.equals("user not authentic")) {
                    Toast.makeText(context, "User authentication failed.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(TAG, "Post Execution", e);
            }
        }
    }
}
