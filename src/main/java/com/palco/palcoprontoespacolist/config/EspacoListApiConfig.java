package com.palco.palcoprontoespacolist.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EspacoListApiConfig {// Serve para configurações iniciais do projeto

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
