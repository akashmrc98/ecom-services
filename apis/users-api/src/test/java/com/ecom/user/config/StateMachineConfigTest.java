package com.ecom.user.config;

import com.ecom.user.states.user.UserCreationEvent;
import com.ecom.user.states.user.UserCreationState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;


@SpringBootTest
@Slf4j
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<UserCreationState, UserCreationEvent> factory;

    @Test
    void testNewStateMachine(){
        StateMachine<UserCreationState, UserCreationEvent> sm = factory.getStateMachine();
        sm.start();
        log.info(sm.getStates().toString());
    }

}