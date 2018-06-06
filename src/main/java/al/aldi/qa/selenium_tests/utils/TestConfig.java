package al.aldi.qa.selenium_tests.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestConfig {
    public String url = null;

    public String username = null;
    public String password = null;

    // ------------------------------------------------------------------------

    public TestConfig() {

    }

    public TestConfig(String url) {
        this.url = url;
    }

    public TestConfig(String url, String username, String password) {
        this(url);
        this.username = username;
        this.password = password;
    }

    // ------------------------------------------------------------------------

    public static TestConfig toObject(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, TestConfig.class);
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(this);
    }

    // ------------------------------------------------------------------------

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
