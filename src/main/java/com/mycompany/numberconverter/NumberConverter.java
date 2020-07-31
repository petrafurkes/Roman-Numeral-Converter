/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.numberconverter;

/**
 *
 * @author Petra Furkes, 28th June, 2020
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam; 
import javax.ws.rs.core.Response;

@Path("/numconverter") public class NumberConverter {
    
    //decimal to roman
    //curl -vi -X GET -G "http://localhost:49000/api/numconverter/decimal/414"
    //PathParam http://localhost:49000/api/numberconverter/decimal/{param}
    @GET
    @Path("/decimal/{param}")
    public Response decimalConverter(@PathParam("param") int number) {
        
        String romanNu = " ";
        int R = number; //Part of Number that still has to be converted to RN
        String roman[]= new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
        int[] romanValues= new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
        
        if(R > 3999 || R < 1){
            String output = "Not a valid number!";
            return Response.status(200).entity(output).build();
        }else{
            for (int i = romanValues.length - 1; i >= 0; i--) {
                while (R >= romanValues[i]) {
                    romanNu += roman[i];
                    R -= romanValues[i];
                }
            }
            String output = "Decimal number " + number + " is " + romanNu + " in Roman Numerals!";
            return Response.status(200).entity(output).build();
        }
    }
    
    //roman to decimal
    //curl -vi -X GET -G "http://localhost:49000/api/numconverter/roman/CDXIV"
    //PathParam http://localhost:49000/api/numberconverter/roman/{param}
  
    @GET
    @Path("/roman/{param}")
    public Response romanConverter(@PathParam("param") String number) { 
        
        int sum = 0;
        String roman[]= new String[] { "I", "V", "X", "L", "C", "D", "M" };
        int decimal[]= new int[] { 1, 5, 10, 50, 100, 500, 1000 };
            //sum of each number character
            for (int i = 0; i < number.length(); i++) {
                for (int j = roman.length-1; j>=0; j--){
                    String letter = Character.toString(number.charAt(i));
                    if(letter.equals(roman[j])){
                        sum = sum + (decimal[j]);
                    }  
                }
            } 
            //subtraction of values 
            if(number.contains("IV")){
                sum = sum - 2;
            }
            if(number.contains("IX")){
                sum = sum - 2;
            }
            if(number.contains("XL")){
                sum = sum - 20;
            }
            if(number.contains("XC")){
                sum = sum - 20;
            }
            if(number.contains("CD")){
                sum = sum - 200;
            }
            if(number.contains("CM")){
                sum = sum - 200;
            }
        String output = "Roman number " +number+ " is " +  sum + " in Decimal Number!";
        return Response.status(200).entity(output).build();
    }   
}
    
    
    

