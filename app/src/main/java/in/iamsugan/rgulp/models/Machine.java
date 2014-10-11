package in.iamsugan.rgulp.models;

import com.orm.SugarRecord;

/**
 * Created by sugan on 30/09/14.
 */
public class Machine extends SugarRecord<Machine> {
    String name;
    String ip;
    int port;
    public Machine() {

    }

    public Machine(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
