package com.iflytek.tps.config;

import com.iflytek.tps.beans.IpsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = {"ips.properties"},encoding = "UTF-8")
@ConfigurationProperties(prefix = "ats")
public class AtsConfig {

  private List<IpsProperties> data;

    public List<IpsProperties> getData() {
        return data;
    }

    public void setData(List<IpsProperties> data) {
        this.data = data;
    }
}
