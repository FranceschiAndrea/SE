/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myaaawsserver;

import javax.jws.WebService;

/**
 *
 * @author biar
 */

@WebService(endpointInterface = "com.mycompany.myaaawsserver.MyAAAWSInterface")
public class MyAAAWSImpl implements MyAAAWSInterface {
    
    
    public String[] getClients() {
        String[] result = {"1,Massimo Mecella","2,Miguel Ceriani","3,Mario Rossi","4,Mario Bianchi"};
       
        return result;
        
    }

}
