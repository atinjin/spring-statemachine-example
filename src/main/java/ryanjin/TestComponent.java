package ryanjin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
public class TestComponent implements CommandLineRunner {

    private final StateMachine<StateMachineConfig.NodeStatus, StateMachineConfig.NodeEvent> stateMachine;

    @Autowired
    public TestComponent(StateMachine<StateMachineConfig.NodeStatus, StateMachineConfig.NodeEvent> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(StateMachineConfig.NodeEvent.CONNECT);
        stateMachine.sendEvent(StateMachineConfig.NodeEvent.DISCONNECT);
    }

}
