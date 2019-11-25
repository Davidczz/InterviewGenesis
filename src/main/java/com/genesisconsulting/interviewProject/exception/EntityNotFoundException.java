package com.genesisconsulting.interviewProject.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, String entityName) {
        super("Entity "+ entityName +" not found with id " +id);
    }

}
