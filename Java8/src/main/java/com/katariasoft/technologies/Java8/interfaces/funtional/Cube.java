package com.katariasoft.technologies.Java8.interfaces.funtional;

//No problem if Cylender has same name method .
//Will not compile if Cylender has some other name method like getVolume.
@FunctionalInterface
public interface Cube extends Cylender {

	public double getArea(double radius);

}
