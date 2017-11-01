package ryanjin;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateMachineConfig.NodeStatus, StateMachineConfig.NodeEvent> {

    public enum NodeStatus {
        OFF,
        ON
    }

    public enum NodeEvent {
        DISCONNECT,
        CONNECT
    }

    @Override
    public void configure(StateMachineStateConfigurer<NodeStatus, NodeEvent> states) throws Exception {
        states
                .withStates()
                .initial(NodeStatus.OFF)
                .states(EnumSet.allOf(NodeStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<NodeStatus, NodeEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(NodeStatus.OFF).target(NodeStatus.ON).event(NodeEvent.CONNECT)
                    .and()
                .withExternal()
                    .source(NodeStatus.ON).target(NodeStatus.OFF).event(NodeEvent.DISCONNECT);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<NodeStatus, NodeEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

}
