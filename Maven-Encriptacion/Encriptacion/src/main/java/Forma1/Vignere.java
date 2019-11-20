/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma1;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author PC23
 */
@Named(value = "vignere")
@SessionScoped
public class Vignere implements Serializable {

    /**
     * Creates a new instance of Vignere
     */
    public Vignere() {
    }
    
}
