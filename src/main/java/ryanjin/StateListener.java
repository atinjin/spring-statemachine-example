package ryanjin;

import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class StateListener {

    @Application.StatesOnTransition(source = StateMachineConfig.NodeStatus.OFF, target = StateMachineConfig.NodeStatus.ON)
    public void serverOn() {
        System.out.println("serve on");
    }

    @Application.StatesOnTransition(source = StateMachineConfig.NodeStatus.ON, target = StateMachineConfig.NodeStatus.OFF)
    public void serverOff() {
        System.out.println("serve off");
    }
}
