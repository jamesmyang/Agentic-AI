package com.atucity.example.langgraph4j.gettingstarted;

import org.bsc.langgraph4j.action.NodeAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ResponderNode implements NodeAction<SimpleState> {

    private static final Logger logger = LoggerFactory.getLogger(ResponderNode.class);

    @Override
    public Map<String, Object> apply(SimpleState state) {
        logger.info("ResponderNode executing. Current messages: " + state.messages());
        List<String> currentMessages = state.messages();
        if (currentMessages.contains("Hello from GreeterNode!")) {
            return Map.of(SimpleState.MESSAGES_KEY, "Acknowledged greeting!");
        }
        return Map.of(SimpleState.MESSAGES_KEY, "No greeting found.");
    }
}