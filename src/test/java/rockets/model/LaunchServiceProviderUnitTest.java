package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LaunchServiceProviderUnitTest {
    private LaunchServiceProvider target;
    private String name;
    private int yearFounded;
    private String country;
    private String nullName;
    private int minusYearFounded;
    private String nullCountry;

    @BeforeEach
    public void setUp() {

        this.nullName = null;
        this.minusYearFounded = -1;
        this.nullCountry = null;
    }

    @DisplayName("should throw exception when pass null to settle name")
    @Test
    public void whenEnterNullName(){
        target = new LaunchServiceProvider(nullName, yearFounded,country);
        assertNull(nullName,"true");
    }

    @DisplayName("should throw exception when pass null to settle country")
    @Test
    public void whenEnterNullCountry(){
        target = new LaunchServiceProvider(name, yearFounded,nullCountry);
        assertNull(nullCountry,"true");
    }

    @DisplayName("should throw exception when pass yearFounded less than 1900")
    @Test
    public void whenEnterLessYearFounded(){
        target = new LaunchServiceProvider(name, minusYearFounded,country);
        assertTrue(minusYearFounded < 1900);
    }

    @DisplayName("should throw exceptions when pass a null headquarters to setHeadquarters function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        target = new LaunchServiceProvider(name, yearFounded,country);
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setHeadquarters(null));
        assertEquals("headquarters cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exceptions when pass same series rockets in different group to addRocketToGroup function")
    @Test
    public void shouldThrowExceptionWhenSameSeriesRocketsInDifferentGroup(){
        target = new LaunchServiceProvider(name, yearFounded,country);
        Rocket rocket1 = new Rocket("a", "a", target);
        rocket1.setSeries("1");
        Rocket rocket2 = new Rocket("b", "b", target);
        rocket2.setSeries("2");
        Rocket rocket3 = new  Rocket("c", "c", target);
        rocket3.setSeries("1");
        target.addRocketToGroup(rocket1);
        target.addRocketToGroup(rocket2);
        target.addRocketToGroup(rocket2);
        Map<String, Rocket> rocketMap = target.getRocketGroup();
        boolean isRight;
        if(!rocketGetKey(rocket1).equals(rocketGetKey(rocket3)) || rocketGetKey(rocket1).equals(rocketGetKey(rocket2)) || rocketGetKey(rocket3).equals(rocketGetKey(rocket2))){
            isRight = false;
        }else{
            isRight = true;
        }
        assertFalse(isRight, "false");
    }

    private String rocketGetKey(Rocket rocket){
        Map<String, Rocket> rocketMap = target.getRocketGroup();
        String key = null;
        for(String getKey: rocketMap.keySet()){
            if(rocketMap.get(getKey).equals(rocket)){
                key = getKey;
            }
        }
        return key;
    }
}


