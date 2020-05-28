/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import steps.dev.myfinance7.webclient.formatter.MyFinanceDateFormatter;

/**
 *
 * @author stepin
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateFormatter());
    }
    
    @Bean
    public MyFinanceDateFormatter dateFormatter(){
        return new MyFinanceDateFormatter();
    }

}
