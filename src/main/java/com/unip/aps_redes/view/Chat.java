package com.unip.aps_redes.view;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static javax.swing.JOptionPane.*;
import java.net.*;

public class Chat extends javax.swing.JFrame {

    private Socket socket;
    private String name;
    private BufferedReader buffer;
    private InputStreamReader reader;
    private String ipServer;

    public Chat(String name, String ipServer) {
        initComponents();
        this.name = name;
        this.ipServer = ipServer;
        try {
            socket = new Socket(ipServer, 5000);
            showMessageDialog(null, "Logado com sucesso ! \nConectado " + this.ipServer);
        } catch (Exception e) {
            showMessageDialog(null, "Não conectado");
            System.exit(0);
        }

        Thread();
    }

    private void Thread() {
        Thread thread = new Thread(new Runnable() {
            String message;

            @Override
            public void run() {
                try {
                    reader = new InputStreamReader(socket.getInputStream());
                    buffer = new BufferedReader(reader);

                    while ((message = buffer.readLine()) != null) {
                        messageReceived.setText(messageReceived.getText() + message + "\n");
                    }

                } catch (IOException e) {
                    showMessageDialog(null, "Erro na conexão com servidor", "", ERROR_MESSAGE);
                }
            }
        });
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageReceived = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageSent = new javax.swing.JTextArea();
        muralB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setBackground(new java.awt.Color(0, 51, 102));

        jPanel1.setBackground(new java.awt.Color(14, 99, 27));
        jPanel1.setToolTipText("");

        messageReceived.setEditable(false);
        messageReceived.setColumns(20);
        messageReceived.setRows(5);
        jScrollPane1.setViewportView(messageReceived);

        sendButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sendButton.setText("Enviar");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        messageSent.setColumns(20);
        messageSent.setRows(5);
        messageSent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageSentKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(messageSent);

        muralB.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        muralB.setText("Mural");
        muralB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muralBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(muralB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(muralB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed

        String message = name + " disse: ";

        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            message += messageSent.getText();
            ps.println(message);
            ps.flush();
            messageSent.setText("");

        } catch (IOException e) {
            e.printStackTrace();
            showMessageDialog(null, "Mensagem não enviada", "", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageSentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageSentKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String message = name + " disse: ";

            try {
                PrintStream ps = new PrintStream(socket.getOutputStream());
                message += messageSent.getText();
                ps.println(message);
                ps.flush();

                messageSent.setText("");

            } catch (IOException e) {
                e.printStackTrace();
                showMessageDialog(null, "Mensagem não enviada", "", ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_messageSentKeyPressed

    private void muralBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muralBActionPerformed
        Mural mural = new Mural(ipServer, name);
        mural.setVisible(true);
    }//GEN-LAST:event_muralBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea messageReceived;
    private javax.swing.JTextArea messageSent;
    private javax.swing.JButton muralB;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
