package com.ceiba.trm.superintendencia;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.Calendar;

public class Tcrm implements Serializable{
    private static final String W3_XMLSCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

    private static final long serialVersionUID = 1L;
    private static final TypeDesc typeDesc = new TypeDesc(Tcrm.class, true);

    static {
        typeDesc.setXmlType(new QName("http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "tcrm"));
        typeDesc.addFieldDesc(obtenerElemntDesc("displayToUser","displayToUser","boolean"));
        typeDesc.addFieldDesc(obtenerElemntDesc("id","id","long"));
        typeDesc.addFieldDesc(obtenerElemntDesc("unit","unit","string"));
        typeDesc.addFieldDesc(obtenerElemntDesc("validityFrom","validityFrom","dateTime"));
        typeDesc.addFieldDesc(obtenerElemntDesc("validityTo","validityTo","dateTime"));
        typeDesc.addFieldDesc(obtenerElemntDesc("value","value","float"));
    }

    private static ElementDesc obtenerElemntDesc(String fieldName,String localPartXmlName, String localPartXmlType){
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName(fieldName);
        elemField.setXmlName(new QName("", localPartXmlName));
        elemField.setXmlType(new QName(W3_XMLSCHEMA_URI, localPartXmlType));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        return elemField;
    }

    private Boolean displayToUser;
    private Long id;
    private String unit;
    private Calendar validityFrom;
    private Calendar validityTo;
    private Float value;

    /**
     * Empty constructor. Please do not delete. Is used by introspection.
     */
    public Tcrm() {
        //
        // Empty constructor. Is used by introspection.
    }

    /**
     * Return type metadata object
     */
    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static Serializer getSerializer( Class<?> javaType, QName xmlType) {
        return new BeanSerializer(javaType, xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static Deserializer getDeserializer( Class<?> javaType, QName xmlType) {
        return new BeanDeserializer(javaType, xmlType, typeDesc);
    }

    /**
     * Gets the displayToUser value for this Trm.
     *
     * @return displayToUser tru if the value is displayed to the user
     */
    public Boolean getDisplayToUser() {
        return displayToUser;
    }

    /**
     * Sets the displayToUser value for this Trm.
     *
     * @param displayToUser true is the value should be displayed to the final user
     */
    public void setDisplayToUser(Boolean displayToUser) {
        this.displayToUser = displayToUser;
    }

    /**
     * Gets the id value for this Trm.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id value for this Trm.
     *
     * @param id Trm identification
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the unit value for this Trm.
     *
     * @return unit TRM units
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit value for this Trm.
     *
     * @param unit units to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the validityFrom value for this Trm.
     *
     * @return validityFrom Trm valid from
     */
    public Calendar getValidityFrom() {
        return validityFrom;
    }

    /**
     * Sets the validityFrom value for this Trm.
     *
     * @param validityFrom the date to set
     */
    public void setValidityFrom(Calendar validityFrom) {
        this.validityFrom = validityFrom;
    }

    /**
     * Gets the validityTo value for this Trm.
     *
     * @return validityTo Trm valid to
     */
    public Calendar getValidityTo() {
        return validityTo;
    }

    /**
     * Sets the validityTo value for this Trm.
     *
     * @param validityTo the date to set
     */
    public void setValidityTo(Calendar validityTo) {
        this.validityTo = validityTo;
    }

    /**
     * Gets the value value for this Trm.
     *
     * @return Trm value
     */
    public Float getValue() {
        return value;
    }

    /**
     * Sets the value value for this Trm.
     *
     * @param value the value to set
     */
    public void setValue(Float value) {
        this.value = value;
    }
}
