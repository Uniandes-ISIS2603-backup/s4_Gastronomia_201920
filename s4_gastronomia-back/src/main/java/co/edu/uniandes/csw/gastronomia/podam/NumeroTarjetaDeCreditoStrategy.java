/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.podam;

/**
 *
 * @author je.canizarez
 */
import java.security.SecureRandom;
import uk.co.jemos.podam.common.AttributeStrategy;
public class NumeroTarjetaDeCreditoStrategy implements AttributeStrategy<Long> {
    

    @Override
    public Long getValue() {
       SecureRandom rand = new SecureRandom();
       int retorno = rand.nextInt(3); 
       if(retorno % 2 == 0)
       {
       String  visaString = "4" + (Math.abs(rand.nextLong())+"").substring(0, 15);
       long visaNum = Math.abs(Long.parseLong(visaString));
       return visaNum;
       }
       else
       {
       String mastercardString = "5"  + (Math.abs(rand.nextLong())+"").substring(0,15);
       long mastercard = Math.abs(Long.parseLong(mastercardString));
       return mastercard;
       }
    }
}
