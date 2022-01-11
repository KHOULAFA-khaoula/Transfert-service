package com.example.clientservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ClientDuplicatedException extends Exception{
    /**
     * Manage Exception here
     */
    private long mid;
}
