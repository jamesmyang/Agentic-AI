package com.atucity.example.langgraph4j.gettingstarted;

import org.bsc.langgraph4j.state.AgentState;
import org.bsc.langgraph4j.state.Channel;
import org.bsc.langgraph4j.state.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleState extends AgentState {
    private static final Logger logger = LoggerFactory.getLogger(SimpleState.class);

    public static final String MESSAGES_KEY = "messages";

    // Define the schema for the state.
    // MESSAGES_KEY will hold a list of strings, and new messages will be appended.
    public static final Map<String, Channel<?>> SCHEMA = Map.of(
            MESSAGES_KEY, Channels.appender(ArrayList::new)
            /*MESSAGES_KEY, Channels.appender(
                    new Supplier<List<String>>() {
                        @Override
                        public ArrayList<String> get() { return new ArrayList<>(); }
                    })*/
    );

    public SimpleState(Map<String, Object> initData) {
        super(initData);

        logger.info("Init SimpleState: " + initData);
        //logger.info(initData.toString());
        //logger.info("Done Init SimpleState");
        //System.out.println(this);

    }

    public List<String> messages() {
        return this.<List<String>>value("messages")
                .orElse( List.of() );
    }


}

