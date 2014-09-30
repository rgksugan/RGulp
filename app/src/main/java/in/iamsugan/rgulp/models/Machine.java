package in.iamsugan.rgulp.models;

import com.orm.SugarRecord;

/**
 * Created by sugan on 30/09/14.
 */
public class Machine extends SugarRecord<Machine> {
    String ip;
    String port;
    public Machine() {

    }

    public Machine(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
}
