package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.HrmTestData;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class TestDataProviderAdmin {
    @DataProvider
    public static Object[][] getCredentialsFromTest() {
        return new Object[][]{
                {"admin", "admin"},
                {"user2", "demouserpwd"}
        };
    }
    @DataProvider(name = "getCredentialsFromJson1")
    public static Object[][] getCredentialsFromJson1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        final String testDataFilePath = System.getProperty("user.dir") + "/src/main/resources/hrms-login.json";
        File testDataFile = new File(testDataFilePath);
        final HrmTestData[] hrmTestData = objectMapper.readValue(testDataFile, HrmTestData[].class);
        final Object[][] objects = new Object[hrmTestData.length][2];
        for (int i = 0; i < hrmTestData.length; i++) {
            objects[i][0] = hrmTestData[i].getUsername();
            objects[i][1] = hrmTestData[i].getPassword();
        }
        return objects;
    }
}
