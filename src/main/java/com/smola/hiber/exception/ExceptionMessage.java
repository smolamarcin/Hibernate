package com.smola.hiber.exception;

public final class ExceptionMessage {

    private ExceptionMessage() {
    }

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User not found!";
    public static final String USER_EMAIL_ALREADY_EXISTS = "User with this email already exists!";
    public static final String ROUTE_DOES_NOT_EXIST = "Route with this id does not exist.";
    public static final String ALREADY_RATED_EXCEPTION_MESSAGE = "Rate from user already exists!";
}
