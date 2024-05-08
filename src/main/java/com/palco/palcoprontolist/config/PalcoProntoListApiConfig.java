package com.palco.palcoprontolist.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PalcoProntoListApiConfig {// Serve para configurações iniciais do projeto

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
