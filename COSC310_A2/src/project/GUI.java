package project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUI {

	JButton send;
	static JTextField text;
	static JTextArea chat;
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame("IMDb Chat bot");
		frame.setSize(1000, 800);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter text here:");
		panel.add(label);
		// accepts up to 100 characters
		JTextField text = new JTextField(50);
		panel.add(text);
		text.requestFocusInWindow();
		JButton send = new JButton("Send");
		panel.add(send);
		JTextArea chat = new JTextArea();
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text.getText().length() < 1) {
					// do nothing since they have no written anything
				} else {
					chat.append("<User>" + text.getText()+"\n");
					text.setText("");
				}
				
			}
			
		});
	
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, chat);
		frame.setVisible(true);

	}
	
	
	
}

