import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDATA {
    public final Location location;
    public final Current current;

    public WeatherDATA(@JsonProperty("location") Location location,
                       @JsonProperty("current") Current current) {
        this.location = location;
        this.current = current;
    }
}

class Location {
    public final String name;
    public final String region;
    public final String country;
    public final String tz_id;
    public final String localtime;

    public Location(@JsonProperty("name") String name,
                    @JsonProperty("region") String region,
                    @JsonProperty("country") String country,
                    @JsonProperty("tz_id") String tz_id,
                    @JsonProperty("localtime") String localtime) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.tz_id = tz_id;
        this.localtime = localtime;
    }
}

class Current {
    public final double temp_c;
    public final Condition condition;

    public Current(@JsonProperty("temp_c") double temp_c,
                   @JsonProperty("condition") Condition condition) {
        this.temp_c = temp_c;
        this.condition = condition;
    }
}

class Condition {
    public final String text;

    public Condition(@JsonProperty("text") String text) {
        this.text = text;
    }
}
