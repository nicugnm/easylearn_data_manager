package ro.nicolaemariusghergu.easylearn.datamanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("download")
public class UrlPropertiesPath {

    private String url;
}
