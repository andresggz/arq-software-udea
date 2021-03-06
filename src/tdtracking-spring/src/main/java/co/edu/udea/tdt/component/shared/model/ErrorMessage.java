package co.edu.udea.tdt.component.shared.model;

import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
public class ErrorMessage {

    private String exception;

    private String message;

    private String path;

    public ErrorMessage(Exception exception, String path){
        this.exception = exception.getClass().getName();
        this.message = exception.getMessage();
        this.path = path;
    }
}
