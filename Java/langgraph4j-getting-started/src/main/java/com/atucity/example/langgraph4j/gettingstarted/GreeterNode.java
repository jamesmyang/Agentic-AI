package com.atucity.example.langgraph4j.gettingstarted;

import org.bsc.langgraph4j.action.NodeAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class GreeterNode implements NodeAction<SimpleState> {

    private static final Logger logger = LoggerFactory.getLogger(GreeterNode.class);

    @Override
    public Map<String, Object> apply(SimpleState state) throws Exception {
        //System.out.println("GreeterNode executing. Current messages: " + state.messages());
        //logger.info(state.toString());
        //logger.info("Greeter node action executed");
        logger.info("GreeterNode executing. Current messages: " + state.messages());
        return Map.of(SimpleState.MESSAGES_KEY, "Hello from GreeterNode!");
    }
}
