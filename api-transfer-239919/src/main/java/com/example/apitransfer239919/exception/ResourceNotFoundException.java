package com.example.apitransfer239919.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}

