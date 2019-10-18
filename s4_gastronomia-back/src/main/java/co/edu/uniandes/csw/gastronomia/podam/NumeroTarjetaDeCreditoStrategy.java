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
import org.apache.commons.lang3.RandomStringUtils;
import uk.co.jemos.podam.common.AttributeStrategy;
public class NumeroTarjetaDeCreditoStrategy implements AttributeStrategy<Long> {
    

    @Override
    public Long getValue() {
       SecureRandom rand = new SecureRandom();
       int retorno = rand.nextInt(3); 
       if(retorno % 2 == 0)
       {
       
       String  visaString = "4" + RandomStringUtils.randomNumeric(15);
       return Long.parseLong(visaString);
       }
       else
       {
       String mastercardString = "5"  + RandomStringUtils.randomNumeric(15);
       return Long.parseLong(mastercardString);
       }
    }
}
