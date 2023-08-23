import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String APIKEY = "replace this"; //api key from https://www.weatherapi.com
    public static void main(String[] args) {
        System.out.println("Weather Forecast");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the city name: ");
        String location = scanner.next();
        CheckWeather(location);
    }

    public static void CheckWeather(String location) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + APIKEY + "&q="+ location +"&aqi=no");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String jsonResponse = response.toString();

            int nameIndex = jsonResponse.indexOf("\"name\":") + 8;
            int nameEndIndex = jsonResponse.indexOf("\"", nameIndex);
            String cityName = jsonResponse.substring(nameIndex, nameEndIndex);

            int countryIndex = jsonResponse.indexOf("\"country\":") + 11;
            int countryEndIndex = jsonResponse.indexOf("\"", countryIndex);
            String country = jsonResponse.substring(countryIndex, countryEndIndex);

            int timezoneIndex = jsonResponse.indexOf("\"tz_id\":") + 9;
            int timzoneEndIndex = jsonResponse.indexOf("\"", timezoneIndex);
            String timezone = jsonResponse.substring(timezoneIndex, timzoneEndIndex);

            int weatherIndex = jsonResponse.indexOf("\"text\"") + 8;
            int weatherendindex = jsonResponse.indexOf("\"", weatherIndex);
            String weather = jsonResponse.substring(weatherIndex,weatherendindex);

            int tempCIndex = jsonResponse.indexOf("\"temp_c\":") + 9;
            int tempCEndIndex = jsonResponse.indexOf(",", tempCIndex);
            double temperatureC = Double.parseDouble(jsonResponse.substring(tempCIndex, tempCEndIndex));


            System.out.println("\nLocation: "+cityName+ " ," + country +
                    "\nTimezone: "+ timezone +
                    "\nWeather: "+ weather +
                    "\nTemperature: "+ temperatureC + "°C"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}