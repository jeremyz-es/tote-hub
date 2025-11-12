package com.edgestackers.pgi.services.feed.publisher;

public record PgiNatsTopicContext(String pgiCycleUpdatesTopic,
                                  String toteMarketCycleUpdatesTopic,
                                  String toteWinMarketCycleUpdatesTopic,
                                  String pgiScratchingTopic,
                                  String toteScratchingTopic,
                                  String toteRaceOfficialTopic) {
}
