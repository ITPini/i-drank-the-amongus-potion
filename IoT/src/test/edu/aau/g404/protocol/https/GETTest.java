package edu.aau.g404.protocol.https;

import edu.aau.g404.device.Light;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class GETTest {

    private static final GET get = new GET();

    @BeforeEach
    void setUp() {
        get
                .setUrl("https://192.168.0.134/clip/v2/resource/light")
                .setApplicationKey("XAxUnLEodCpkcqb0hnLYi--mdL0x4J3MbQZZ5iuc");
    }

    @Test
    void request() {
        try {
            SSLHelper.disableSSLVerification();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Response<List<Light>> response = get.request();
        assertEquals(200, response.getResponseCode());

        List<Light> lights = response.getData();
        assertFalse(lights.isEmpty());

        for (Light light : lights) {
            assertNotNull(light);
            assertNotNull(light.getIdentifier());
            assertNotNull(light.getMetaData().getName());
        }
    }

    @Test
    void deserializeLights() {
        String response = "{\"errors\":[],\"data\":[{\"id\":\"ac37190b-eafe-4181-a6d6-89a938714b73\",\"id_v1\":\"/lights/4\",\"owner\":{\"rid\":\"c7a3c19e-d209-4f0c-a227-afdf9dcab08a\",\"rtype\":\"device\"},\"metadata\":{\"name\":\"Signe gradient floor 2\",\"archetype\":\"hue_signe\"},\"on\":{\"on\":true},\"dimming\":{\"brightness\":100.0,\"min_dim_level\":0.10000000149011612},\"dimming_delta\":{},\"color_temperature\":{\"mirek\":369,\"mirek_valid\":true,\"mirek_schema\":{\"mirek_minimum\":153,\"mirek_maximum\":500}},\"color_temperature_delta\":{},\"color\":{\"xy\":{\"x\":0.4585,\"y\":0.4103},\"gamut\":{\"red\":{\"x\":0.6915,\"y\":0.3083},\"green\":{\"x\":0.17,\"y\":0.7},\"blue\":{\"x\":0.1532,\"y\":0.0475}},\"gamut_type\":\"C\"},\"dynamics\":{\"status\":\"none\",\"status_values\":[\"none\",\"dynamic_palette\"],\"speed\":0.0,\"speed_valid\":false},\"alert\":{\"action_values\":[\"breathe\"]},\"signaling\":{\"signal_values\":[\"no_signal\",\"on_off\"]},\"mode\":\"normal\",\"gradient\":{\"points\":[],\"mode\":\"interpolated_palette\",\"points_capable\":5,\"mode_values\":[\"interpolated_palette\"],\"pixel_count\":10},\"powerup\":{\"preset\":\"safety\",\"configured\":true,\"on\":{\"mode\":\"on\",\"on\":{\"on\":true}},\"dimming\":{\"mode\":\"dimming\",\"dimming\":{\"brightness\":100.0}},\"color\":{\"mode\":\"color_temperature\",\"color_temperature\":{\"mirek\":366}}},\"type\":\"light\"}]}";
        List<Light> lights = get.deserializeLights(response);
        assertEquals("Signe gradient floor 2", lights.get(0).getMetaData().getName());
        assertEquals("ac37190b-eafe-4181-a6d6-89a938714b73", lights.get(0).getIdentifier());
        assertEquals(100.0, lights.get(0).getDimming().getBrightness());
        assertEquals(true, lights.get(0).getOnState().isOn());
    }

    @Test
    void getUrl() {
        assertEquals("https://192.168.0.134/clip/v2/resource/light", get.getUrl());
    }

    @Test
    void setUrl() {
        get.setUrl("https://192.168.0.100/clip/v2/resource/light");
        assertEquals("https://192.168.0.100/clip/v2/resource/light", get.getUrl());
    }

    @Test
    void getApplicationKey() {
        assertEquals("XAxUnLEodCpkcqb0hnLYi--mdL0x4J3MbQZZ5iuc", get.getApplicationKey());
    }

    @Test
    void setApplicationKey() {
        get.setApplicationKey("aYvLvawY7PE2Y4yZ9of1FYkMS7onTlIkWOZfuAex");
        assertEquals("aYvLvawY7PE2Y4yZ9of1FYkMS7onTlIkWOZfuAex", get.getApplicationKey());
    }
}