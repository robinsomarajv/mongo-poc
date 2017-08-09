package com.ust.item.mdm.dto;
import java.io.Serializable;

public class ValidValue implements Serializable
{

private String id;
private String value;
private final static long serialVersionUID = -8913812852667793837L;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

}