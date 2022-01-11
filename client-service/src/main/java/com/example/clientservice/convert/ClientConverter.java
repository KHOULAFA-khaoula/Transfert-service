package com.example.clientservice.convert;

import com.example.clientservice.dto.ClientDTO;
import com.example.clientservice.model.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@Component
public class ClientConverter extends AbstractConverter<Client, ClientDTO> {
    private  ModelMapper modelMapper;
    public ClientConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper = modelMapper;
    }
    @Override
    public Client convertToDM(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    @Override
    public ClientDTO convertToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
}
