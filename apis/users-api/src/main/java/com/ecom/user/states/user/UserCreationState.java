package com.ecom.user.states.user;

public enum UserCreationState {
    USER_CREATED,
    USER_INITIALIZATION_FAILURE,
    SESSION_INITIALIZED,
    SESSION_INITIALIZATION_FAILURE,
    CART_INITIALIZED,
    CART_INITIALIZATION_FAILURE,
    WISHLIST_INITIALIZED,
    WISHLIST_INITIALIZATION_FAILURE,
    ORDER_INITIALIZED,
    ORDER_INITIALIZATION_FAILURE,
    COMPLETED
}