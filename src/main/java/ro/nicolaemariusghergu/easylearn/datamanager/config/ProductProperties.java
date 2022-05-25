package ro.nicolaemariusghergu.easylearn.datamanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.PriceType;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Status;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("products")
public class ProductProperties {

    private List<Status> status;

    private List<PriceType> priceTypes;
}

