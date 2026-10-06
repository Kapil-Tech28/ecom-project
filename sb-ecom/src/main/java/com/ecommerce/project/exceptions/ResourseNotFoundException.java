package com.ecommerce.project.exceptions;

public class ResourseNotFoundException extends RuntimeException
{
    String resourseName;
    String field;
    String fieldName;
    Long fieldId;


    public ResourseNotFoundException(String field, String fieldName, String resourseName) {
        super(String.format("%s not found  with %s: %s ",resourseName,field,fieldName));
        this.field = field;
        this.fieldName = fieldName;
        this.resourseName = resourseName;
    }

    public ResourseNotFoundException(String field, String resourseName, Long fieldId) {
        super(String.format("%s not found  with %s: %d ",resourseName,field,fieldId));

        this.field = field;
        this.resourseName = resourseName;
        this.fieldId = fieldId;
    }

    public ResourseNotFoundException()
    {

    }
}
