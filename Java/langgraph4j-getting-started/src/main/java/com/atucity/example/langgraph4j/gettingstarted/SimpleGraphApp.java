package com.atucity.example.langgraph4j.gettingstarted;

import org.bsc.langgraph4j.GraphStateException;
import org.bsc.langgraph4j.NodeOutput;
import org.bsc.langgraph4j.StateGraph;
import org.bsc.langgraph4j.state.AgentStateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.bsc.langgraph4j.StateGraph.END;
import static org.bsc.langgraph4j.StateGraph.START;
import static org.bsc.langgraph4j.action.AsyncNodeAction.node_async;

public class SimpleGraphApp {

    private static final Logger logger = LoggerFactory.getLogger(SimpleGraphApp.class);

    public static void main(String[] args) throws GraphStateException {
        // Initialize nodes
        GreeterNode greeterNode = new GreeterNode();
        ResponderNode responderNode = new ResponderNode();

        // Define the graph structure
        logger.info("Define the graph structure");
        //var
        StateGraph<SimpleState> stateGraph = new StateGraph<>(SimpleState.SCHEMA, //initData -> new SimpleState(initData))
                new AgentStateFactory<SimpleState>() {
                    @Override
                    public SimpleState apply(Map<String, Object> stringObjectMap) {
                        return new SimpleState(stringObjectMap);
                    }
                })
                .addNode("greeter", node_async(greeterNode))
                .addNode("responder", node_async(responderNode))
                // Define edges
                .addEdge(START, "greeter") // Start with the greeter node
                .addEdge("greeter", "responder")
                .addEdge("responder", END)   // End after the responder node
                ;
        // Compile the graph
        var compiledGraph = stateGraph.compile();

        // Run the graph
        // The `stream` method returns an AsyncGenerator.
        // For simplicity, we'll collect results. In a real app, you might process them as they arrive.
        // Here, the final state after execution is the item of interest.
        System.out.println("=======================================================================================");
        for (NodeOutput<SimpleState> item : compiledGraph.stream( Map.of( SimpleState.MESSAGES_KEY, "Let's, begin!" ) ) ) {
            System.out.println("--------------------------------------------------------");
            System.out.println( item );
            System.out.println("********************************************************");
        }

    }
}
