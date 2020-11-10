package main;

public class Data {
    private String ip;
    private String domain;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Data(String ip, String domain) {
        this.ip = ip;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ip='" + ip + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
