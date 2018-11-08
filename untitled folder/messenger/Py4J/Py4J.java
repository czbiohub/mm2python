/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger.Py4J;

import org.micromanager.Studio;
import interfaces.messengerInterface;
import UI.reports;
import py4j.GatewayServer;
import javax.swing.JTextArea;


/**
 *
 * @author bryant.chhun
 */
public class Py4J implements messengerInterface {
    private static Studio mm;
    private GatewayServer gatewayServer;
    private final reports reports;
    
    
    public Py4J(Studio mm_, JTextArea UI_textArea) {
        mm = mm_;
        reports = new reports(UI_textArea);
    }
    
    @Override
    public void startConnection(int port) {
        gatewayServer = new GatewayServer(new py4jEntryPoint(mm), port);
        gatewayServer.start();
        reports.set_report_area("Gateway Started at port = "+port);
        mm.logs().logMessage("Gateway Started at port = "+port);
    }
    
    @Override
    public void startConnection() {
        gatewayServer = new GatewayServer(new py4jEntryPoint(mm));
        gatewayServer.start();
        reports.set_report_area("Gateway Started at default port");
        mm.logs().logMessage("Gateway Started at default port");
    }
    
    @Override
    public void stopConnection(int port) {
        gatewayServer.shutdown();
        reports.set_report_area(String.format("Gateway at port %04d shut down", port));
        mm.logs().logMessage(String.format("Gateway at port %04d shut down", port));
    }
}
