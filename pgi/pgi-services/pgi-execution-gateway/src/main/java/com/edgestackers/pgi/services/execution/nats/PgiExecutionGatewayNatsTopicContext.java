package com.edgestackers.pgi.services.execution.nats;

public record PgiExecutionGatewayNatsTopicContext(String orderResponseTopic,
                                                  String accountBalanceTopic,
                                                  String fileBetStatusCheckTopic,
                                                  String toteOrderResponseTopic) {
}
