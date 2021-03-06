package ICS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class LoginScreen extends JFrame {

	// Variables declaration                   
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;
	private JOptionPane jOptionPane1;
    // End of variables declaration    
	
    /** Creates new form loginScreen */
    public LoginScreen() {
        initComponents();
    }

	//Method to construct the GUI layout of jFrame
    private void initComponents() {
		
        jButton1 = new JButton();
        jButton2 = new JButton();
        jTextField1 = new JTextField();
        jLabel1 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jLabel2 = new JLabel();
		
		this.setTitle("Math Helper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Login");
		jButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				jButton1ActionPerformed(evt);
			}
		});
	
        jButton2.setText("Register");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Username");

        jLabel2.setText("Password");
		
		//Setting up layout of the GUI
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addComponent(jButton1)
                            .addComponent(jPasswordField1, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jTextField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton2)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(41, 41, 41))
        );

        pack();
    }                       

	private void jButton1ActionPerformed(ActionEvent evt){
		String username = jTextField1.getText().trim();
		String password = jPasswordField1.getText().trim();
		long userHash = Hasher.getHash(username);
		long passHash = Hasher.getHash(password);
		try{
			BufferedReader reader = new BufferedReader(new FileReader("Text Files/userinfo.txt"));
				String holder = reader.readLine();
				while (holder != null && !holder.equals("")){
					String hold[] = holder.split(" ", 2);
					if (userHash == Long.parseLong(hold[0]) && passHash == Long.parseLong(hold[1])){
						System.out.println("success");
						this.setVisible(false);
						SelectionMenu sm = new SelectionMenu();
						sm.setVisible(true);
						sm.setLocationRelativeTo(null);
						return;
					}
					holder = reader.readLine();
				}
			jOptionPane1 = new JOptionPane();
			JFrame f = new JFrame(); 
			JOptionPane.showMessageDialog(f,"Your username and password combination is incorrect.");
		}
		catch (IOException e){
			jOptionPane1 = new JOptionPane();
			JFrame f = new JFrame(); 
			JOptionPane.showMessageDialog(f,"An Unexpected Error has occured");
		}
	}
    
    private void jButton2ActionPerformed(ActionEvent evt) {                                         
        RegisterScreen rs = new RegisterScreen();
		rs.setVisible(true);
		rs.setLocationRelativeTo(null);
        rs.setResizable(false);
		//this.setVisible(false);
    }                                                                                                

}
