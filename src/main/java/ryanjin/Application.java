package ryanjin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class command implements CommandLineRunner {

        @Autowired
        StateMachine stateMachine;
        @Autowired
        StateListener listener;

        @Override
        public void run(String... args) throws Exception {
//            stateMachine.addStateListener(listener);

            stateMachine.sendEvent(StateMachineConfig.NodeEvent.CONNECT);
            stateMachine.sendEvent(StateMachineConfig.NodeEvent.DISCONNECT);
        }
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @OnTransition
    public @interface StatesOnTransition {

        StateMachineConfig.NodeStatus[] source() default {};

        StateMachineConfig.NodeStatus[] target() default {};

    }
}
