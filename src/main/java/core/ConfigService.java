package core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigService {
    private static Config singleInstance;

    public static Config getInstance() {
        if (singleInstance == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String configFilePath = System.getProperty("user.dir")+"/src/main/resources/Config.json";
            File configFile = new File(configFilePath);
            try{
                singleInstance = objectMapper.readValue(configFile, Config.class);
            } catch (IOException e) {
                throw new RuntimeException("Exception while reading config file" + configFilePath, e);
            }
            return singleInstance;
        }else {
            return singleInstance;
        }
    }
}
