package com.edgestackers.pgi.common.client;

public record GwsClientServiceConnectionConfig(String username,
                                               String password,
                                               String community,
                                               String baseEndpoint,
                                               String masterAccountNumber,
                                               String masterAccountPin,
                                               String rebateAccountNumber,
                                               String rebateAccountPin) {
}
