package sockets;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 *
 * @author 20202PF.CC0011
 */
public class Admin extends Thread{
    
    private String ip = "172.0.0.1";
    private int port = 5000;
    private boolean rodando = true;
    private Communicator canalAdminServidor;
    private Scanner entrada;
    private short tipoMensagem;
    private int tamanhoMensagem;
    String canal;
    
    public static void main(String[] args) {
        Admin admin = new Admin("127.0.0.1:5000");
    }
    
    public Admin(String canal){
        
        try{
            
            this.canal = "172.0.0.1:5000";
            this.canalAdminServidor = new Communicator();
            this.canalAdminServidor.connectServer(canal);
            System.out.println("Admin conectou ao servidor " + this.canalAdminServidor.clientRemoteChannelDesc());
            this.start();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void run() {
        
        try{
            ByteBuffer buf = null;
            
            System.out.println("Recebendo mensagens...\n");
            
            while(this.rodando){
                buf = this.canalAdminServidor.recebeMensagem();
                this.tipoMensagem = buf.getShort();
                this.tamanhoMensagem = buf.getInt();
                
                switch(this.tipoMensagem){
                    case 1:    
                        System.out.println("A opção escolhida foi EXTRATO");
                        break;
                    case 2:    
                        System.out.println("A opção escolhida foi SAQUE");
                        break;
                    case 3:    
                        System.out.println("A opção escolhida foi DEPÓSITO");
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}
