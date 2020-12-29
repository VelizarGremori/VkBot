import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final String PROPERTIES_FILE = "config.properties";
    private static final String APIPROPERTIES_FILE = "apikey.properties";
    public static String DOWNLOAD_PATH;

    public static void main(String[] args) throws IOException, ClientException, ApiException {
        Properties properties = readProperties(PROPERTIES_FILE);
        Properties apiProperties = readProperties(APIPROPERTIES_FILE);
        DOWNLOAD_PATH = apiProperties.getProperty("download");
        GroupActor groupActor = createGroupActor(properties);

        HttpTransportClient httpClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(httpClient);

        if (!vk.groups().getLongPollSettings(groupActor, groupActor.getGroupId()).execute().getIsEnabled()) {
            vk.groups().setLongPollSettings(groupActor, groupActor.getGroupId()).enabled(true).wallPostNew(true).execute();
        }

        CallbackHandler handler = new CallbackHandler(vk, groupActor);
        handler.run();
    }

    private static GroupActor createGroupActor(Properties properties) {
        String groupId = properties.getProperty("groupId");
        String accessToken = properties.getProperty("token");
        return new GroupActor(Integer.parseInt(groupId), accessToken);
    }

    private static Properties readProperties(String path) throws IOException {
        InputStream inputStream = Application.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + path + "' not found in the classpath");
        }
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect properties file");
        } finally {
            inputStream.close();
        }
    }
}