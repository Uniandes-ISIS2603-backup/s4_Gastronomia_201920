/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.ejb.PlatoLogic;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 *
 * @author je.canizarez
 */
@Produces("application/json")
@Consumes("application/json")
public class PlatoResource {
    @Inject 
    private PlatoLogic logic;
    
    
    
}
