package org.app.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherApiClientTest {

    WeatherApiClient weatherApiClient = new WeatherApiClient("123");

    @ParameterizedTest()
    @ValueSource(ints = {-1, 8})
    void exceptionRaisedWhenInvalidForecastDays(int forecastDays) {

        Assertions.assertThrows(WeatherApiClient.InvalidForecastDays.class,
                () -> weatherApiClient.getWeatherTempForecast("London", forecastDays));

    }

    @Test
    void parsingWeatherDataFromJson() throws IOException, WeatherApiClient.InvalidForecastDays {

        String weatherDataJsonString = "{" +
                "  \"lat\": 51.5085," +
                "  \"lon\": -0.1257," +
                "  \"timezone\": \"Europe/London\"," +
                "  \"timezone_offset\": 0," +
                "  \"current\": {" +
                "    \"dt\": 1614785722," +
                "    \"sunrise\": 1614753682," +
                "    \"sunset\": 1614793428," +
                "    \"temp\": 6.94," +
                "    \"feels_like\": 4.07," +
                "    \"pressure\": 1027," +
                "    \"humidity\": 100," +
                "    \"dew_point\": 6.94," +
                "    \"uvi\": 0.05," +
                "    \"clouds\": 90," +
                "    \"visibility\": 1500," +
                "    \"wind_speed\": 3.09," +
                "    \"wind_deg\": 80," +
                "    \"weather\": [" +
                "      {" +
                "        \"id\": 502," +
                "        \"main\": \"Rain\"," +
                "        \"description\": \"heavy intensity rain\"," +
                "        \"icon\": \"10d\"" +
                "      }" +
                "    ]," +
                "    \"rain\": {" +
                "      \"1h\": 0.56" +
                "    }" +
                "  }," +
                "  \"daily\": [" +
                "    {" +
                "      \"dt\": 1614772800," +
                "      \"sunrise\": 1614753682," +
                "      \"sunset\": 1614793428," +
                "      \"temp\": {" +
                "        \"day\": 10.9," +
                "        \"min\": 4.45," +
                "        \"max\": 11.79," +
                "        \"night\": 7.8," +
                "        \"eve\": 9.11," +
                "        \"morn\": 4.49" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 9.16," +
                "        \"night\": 5.57," +
                "        \"eve\": 6.86," +
                "        \"morn\": 1.9" +
                "      }," +
                "      \"pressure\": 1028," +
                "      \"humidity\": 68," +
                "      \"dew_point\": 5.25," +
                "      \"wind_speed\": 0.94," +
                "      \"wind_deg\": 146," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 501," +
                "          \"main\": \"Rain\"," +
                "          \"description\": \"moderate rain\"," +
                "          \"icon\": \"10d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 19," +
                "      \"pop\": 0.61," +
                "      \"rain\": 2.84," +
                "      \"uvi\": 2.09" +
                "    }," +
                "    {" +
                "      \"dt\": 1614859200," +
                "      \"sunrise\": 1614839950," +
                "      \"sunset\": 1614879933," +
                "      \"temp\": {" +
                "        \"day\": 6.86," +
                "        \"min\": 3.51," +
                "        \"max\": 7.7," +
                "        \"night\": 3.51," +
                "        \"eve\": 6.53," +
                "        \"morn\": 6.09" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 2.78," +
                "        \"night\": -1.68," +
                "        \"eve\": 1.93," +
                "        \"morn\": 2.14" +
                "      }," +
                "      \"pressure\": 1025," +
                "      \"humidity\": 71," +
                "      \"dew_point\": 2," +
                "      \"wind_speed\": 3.43," +
                "      \"wind_deg\": 31," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 500," +
                "          \"main\": \"Rain\"," +
                "          \"description\": \"light rain\"," +
                "          \"icon\": \"10d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 98," +
                "      \"pop\": 0.84," +
                "      \"rain\": 3.37," +
                "      \"uvi\": 0.98" +
                "    }," +
                "    {" +
                "      \"dt\": 1614945600," +
                "      \"sunrise\": 1614926218," +
                "      \"sunset\": 1614966438," +
                "      \"temp\": {" +
                "        \"day\": 5.66," +
                "        \"min\": 3.2," +
                "        \"max\": 5.82," +
                "        \"night\": 3.31," +
                "        \"eve\": 4.74," +
                "        \"morn\": 3.29" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 0.94," +
                "        \"night\": -0.53," +
                "        \"eve\": 0.03," +
                "        \"morn\": -2.03" +
                "      }," +
                "      \"pressure\": 1031," +
                "      \"humidity\": 62," +
                "      \"dew_point\": -3.64," +
                "      \"wind_speed\": 3.69," +
                "      \"wind_deg\": 55," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 804," +
                "          \"main\": \"Clouds\"," +
                "          \"description\": \"overcast clouds\"," +
                "          \"icon\": \"04d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 94," +
                "      \"pop\": 0," +
                "      \"uvi\": 1.63" +
                "    }," +
                "    {" +
                "      \"dt\": 1615032000," +
                "      \"sunrise\": 1615012485," +
                "      \"sunset\": 1615052942," +
                "      \"temp\": {" +
                "        \"day\": 6.71," +
                "        \"min\": 1.92," +
                "        \"max\": 6.71," +
                "        \"night\": 4.03," +
                "        \"eve\": 5.79," +
                "        \"morn\": 2.07" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 3.01," +
                "        \"night\": 0.8," +
                "        \"eve\": 2.65," +
                "        \"morn\": -1.25" +
                "      }," +
                "      \"pressure\": 1034," +
                "      \"humidity\": 58," +
                "      \"dew_point\": -3.26," +
                "      \"wind_speed\": 2.25," +
                "      \"wind_deg\": 110," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 803," +
                "          \"main\": \"Clouds\"," +
                "          \"description\": \"broken clouds\"," +
                "          \"icon\": \"04d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 81," +
                "      \"pop\": 0," +
                "      \"uvi\": 1.81" +
                "    }," +
                "    {" +
                "      \"dt\": 1615118400," +
                "      \"sunrise\": 1615098752," +
                "      \"sunset\": 1615139446," +
                "      \"temp\": {" +
                "        \"day\": 6.74," +
                "        \"min\": 1.36," +
                "        \"max\": 8.12," +
                "        \"night\": 6.1," +
                "        \"eve\": 6.86," +
                "        \"morn\": 1.36" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 3.65," +
                "        \"night\": 3.5," +
                "        \"eve\": 3.49," +
                "        \"morn\": -1.48" +
                "      }," +
                "      \"pressure\": 1026," +
                "      \"humidity\": 58," +
                "      \"dew_point\": -3.71," +
                "      \"wind_speed\": 1.39," +
                "      \"wind_deg\": 244," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 803," +
                "          \"main\": \"Clouds\"," +
                "          \"description\": \"broken clouds\"," +
                "          \"icon\": \"04d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 82," +
                "      \"pop\": 0," +
                "      \"uvi\": 1.69" +
                "    }," +
                "    {" +
                "      \"dt\": 1615204800," +
                "      \"sunrise\": 1615185018," +
                "      \"sunset\": 1615225950," +
                "      \"temp\": {" +
                "        \"day\": 9.01," +
                "        \"min\": 3.03," +
                "        \"max\": 9.96," +
                "        \"night\": 6.4," +
                "        \"eve\": 8.29," +
                "        \"morn\": 3.03" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 6.27," +
                "        \"night\": 2.94," +
                "        \"eve\": 4.88," +
                "        \"morn\": 0.27" +
                "      }," +
                "      \"pressure\": 1023," +
                "      \"humidity\": 53," +
                "      \"dew_point\": -0.16," +
                "      \"wind_speed\": 1.07," +
                "      \"wind_deg\": 208," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 801," +
                "          \"main\": \"Clouds\"," +
                "          \"description\": \"few clouds\"," +
                "          \"icon\": \"02d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 20," +
                "      \"pop\": 0," +
                "      \"uvi\": 2" +
                "    }," +
                "    {" +
                "      \"dt\": 1615291200," +
                "      \"sunrise\": 1615271283," +
                "      \"sunset\": 1615312453," +
                "      \"temp\": {" +
                "        \"day\": 8.36," +
                "        \"min\": 4.56," +
                "        \"max\": 9.87," +
                "        \"night\": 9.1," +
                "        \"eve\": 9.87," +
                "        \"morn\": 4.56" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 3.85," +
                "        \"night\": 4.45," +
                "        \"eve\": 5.86," +
                "        \"morn\": 0.75" +
                "      }," +
                "      \"pressure\": 1018," +
                "      \"humidity\": 77," +
                "      \"dew_point\": 4.57," +
                "      \"wind_speed\": 4.72," +
                "      \"wind_deg\": 203," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 500," +
                "          \"main\": \"Rain\"," +
                "          \"description\": \"light rain\"," +
                "          \"icon\": \"10d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 91," +
                "      \"pop\": 0.62," +
                "      \"rain\": 0.36," +
                "      \"uvi\": 2" +
                "    }," +
                "    {" +
                "      \"dt\": 1615377600," +
                "      \"sunrise\": 1615357548," +
                "      \"sunset\": 1615398956," +
                "      \"temp\": {" +
                "        \"day\": 12.15," +
                "        \"min\": 8.17," +
                "        \"max\": 12.15," +
                "        \"night\": 8.17," +
                "        \"eve\": 9.68," +
                "        \"morn\": 10.9" +
                "      }," +
                "      \"feels_like\": {" +
                "        \"day\": 5.1," +
                "        \"night\": -0.07," +
                "        \"eve\": 1.07," +
                "        \"morn\": 4.43" +
                "      }," +
                "      \"pressure\": 1001," +
                "      \"humidity\": 82," +
                "      \"dew_point\": 9.34," +
                "      \"wind_speed\": 9.82," +
                "      \"wind_deg\": 230," +
                "      \"weather\": [" +
                "        {" +
                "          \"id\": 501," +
                "          \"main\": \"Rain\"," +
                "          \"description\": \"moderate rain\"," +
                "          \"icon\": \"10d\"" +
                "        }" +
                "      ]," +
                "      \"clouds\": 100," +
                "      \"pop\": 1," +
                "      \"rain\": 12.7," +
                "      \"uvi\": 2" +
                "    }" +
                "  ]" +
                "}";


        WeatherApiClient spy = Mockito.spy(weatherApiClient);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.doReturn(objectMapper.readTree(weatherDataJsonString)).when(spy).requestWeatherData("London");

        Map<Integer, DailyForecastData> actualWeatherTempForecast = spy.getWeatherTempForecast("London",
                3);

        Map<Integer, DailyForecastData> expectedWeatherTempForecast = new LinkedHashMap<>();
        expectedWeatherTempForecast.put(Integer.parseInt("1614772800"), new DailyForecastData("10.9", "10d"));
        expectedWeatherTempForecast.put(Integer.parseInt("1614859200"), new DailyForecastData("6.86", "10d"));
        expectedWeatherTempForecast.put(Integer.parseInt("1614945600"), new DailyForecastData("5.66", "04d"));

        MatcherAssert.assertThat(actualWeatherTempForecast, Matchers.aMapWithSize(3));
        Assertions.assertEquals(expectedWeatherTempForecast, actualWeatherTempForecast);

    }

}
