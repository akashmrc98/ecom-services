package com.ecom.user.config;

import com.ecom.user.states.user.UserCreationEvent;
import com.ecom.user.states.user.UserCreationState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<UserCreationState, UserCreationEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<UserCreationState, UserCreationEvent> states) throws Exception {
        states.withStates()
                .initial(UserCreationState.USER_CREATED)
                .states(EnumSet.allOf(UserCreationState.class))
                .end(UserCreationState.COMPLETED)
                .end(UserCreationState.SESSION_INITIALIZATION_FAILURE)
                .end(UserCreationState.CART_INITIALIZATION_FAILURE)
                .end(UserCreationState.WISHLIST_INITIALIZATION_FAILURE)
                .end(UserCreationState.ORDER_INITIALIZATION_FAILURE);

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<UserCreationState, UserCreationEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.USER_CREATED)
                .event(UserCreationEvent.CREATE_USER_SERVICE)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.USER_INITIALIZATION_FAILURE)
                .event(UserCreationEvent.USER_SERVICE_FAILED)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.SESSION_INITIALIZED)
                .event(UserCreationEvent.CREATE_SESSION_SERVICE)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.SESSION_INITIALIZATION_FAILURE)
                .event(UserCreationEvent.SESSION_SERVICE_FAILED)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.CART_INITIALIZED)
                .event(UserCreationEvent.CREATE_CART_SERVICE)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.CART_INITIALIZATION_FAILURE)
                .event(UserCreationEvent.CART_SERVICE_FAILED)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.WISHLIST_INITIALIZED)
                .event(UserCreationEvent.CREATE_WISHLIST_SERVICE)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.WISHLIST_INITIALIZATION_FAILURE)
                .event(UserCreationEvent.WISHLIST_SERVICE_FAILED)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.ORDER_INITIALIZED)
                .event(UserCreationEvent.CREATE_ORDERS_SERVICE)

                .and().withExternal()
                .source(UserCreationState.USER_CREATED)
                .target(UserCreationState.ORDER_INITIALIZATION_FAILURE)
                .event(UserCreationEvent.ORDER_SERVICE_FAILED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<UserCreationState, UserCreationEvent> config) throws Exception {
        StateMachineListenerAdapter<UserCreationState, UserCreationEvent> adapter = new StateMachineListenerAdapter<>(){
            @Override
            public void stateChanged(State<UserCreationState, UserCreationEvent> from, State<UserCreationState, UserCreationEvent> to){
                    log.info(String.format("StateChanged(From %s, to %s", from, to));
            }
        };
        config.withConfiguration().listener(adapter);
    }
}
