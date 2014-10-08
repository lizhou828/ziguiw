/**
 * ISonNobleness.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zigui.webservice;

public interface ISonNobleness extends java.rmi.Remote {
    public java.lang.Boolean login(java.lang.String name, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String getAllCourseInfo() throws java.rmi.RemoteException;
    public java.lang.String getPartCourseInfo(java.lang.Integer page, java.lang.Integer number) throws java.rmi.RemoteException;
    public java.lang.String getCoursewareXmlAddress(java.lang.String fileLocal, java.lang.String createDate) throws java.rmi.RemoteException;
    public java.lang.String userReg(java.lang.String json, java.lang.Integer status) throws java.rmi.RemoteException;
    public java.lang.Boolean userCustomize(java.lang.String userId, java.lang.Integer status) throws java.rmi.RemoteException;
    public java.lang.String aidGetCoursewareVersions(java.lang.Integer grade, java.lang.Integer subject) throws java.rmi.RemoteException;
    public java.lang.String aidGetColumnName(java.lang.Integer column) throws java.rmi.RemoteException;
}
