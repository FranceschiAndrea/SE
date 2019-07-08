package com.anfropippo.soapserver;

import java.text.ParseException;
import java.util.List;


public class Client {

    private static java.util.List<java.lang.String> getOperationDetailsByID(int i){
        com.anfropippo.soapserver.BankImplService service = new com.anfropippo.soapserver.BankImplService();
        com.anfropippo.soapserver.BankInterface port = service.getBankImplPort();
        return port.getOperationDetailsByID(i);
    }
    
    private static java.util.List<java.lang.String> getOperationsByClientID(int i) {
        com.anfropippo.soapserver.BankImplService service = new com.anfropippo.soapserver.BankImplService();
        com.anfropippo.soapserver.BankInterface port = service.getBankImplPort();
        return port.getOperationsByClientID(i);
    }
    
    
    private static java.util.List<java.lang.String> getClients(){
        com.anfropippo.soapserver.MyAAAWSImplService service = new com.anfropippo.soapserver.MyAAAWSImplService();
        com.anfropippo.soapserver.MyAAAWSInterface port = service.getMyAAAWSImplPort();
        return port.getClients();
    }
    
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        
        System.out.println("Clients:");
        List<String> clients = getClients();
        for(String c : clients) {
            System.out.println(c);
        }
        
        System.out.println("------------");
        List<String> operations;
        int i;
        for(i = 1; i < 5; i++) {
            System.out.println("Operations of client " + i);
            operations = getOperationsByClientID(i);
            for(String s : operations) {
                List<String> details = getOperationDetailsByID(Integer.parseInt(s));
                System.out.println(details);
            }
        }
    }
    
}
