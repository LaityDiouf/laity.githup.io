import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class GestionTache {

	private JFrame frame;
	private JTextField txtidentifiant;
	private JTextField txttitre;
	private JTextField txtetat;
	private JTextField txtddate;
	private JTextField textgid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionTache window = new GestionTache();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionTache() {
		initialize();
		Connect();
		table();
	}

	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gestiontache", "root", "");
		}
		catch (ClassNotFoundException ex){
			
		}
		catch (SQLException ex) {
			
		}
		
	}
	
	public void table() {
		try {
			pst = con.prepareStatement("select * from tache");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setBounds(100, 100, 830, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion Tache");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(186, 11, 282, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 99, 290, 222);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Identifiant");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 31, 123, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Titre");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 83, 111, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Etat");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 121, 123, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("Date de Creation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 164, 123, 21);
		panel.add(lblNewLabel_2);
		
		txtidentifiant = new JTextField();
		txtidentifiant.setBounds(135, 35, 122, 27);
		panel.add(txtidentifiant);
		txtidentifiant.setColumns(10);
		
		txttitre = new JTextField();
		txttitre.setBounds(135, 83, 122, 25);
		panel.add(txttitre);
		txttitre.setColumns(10);
		
		txtetat = new JTextField();
		txtetat.setBounds(135, 121, 122, 32);
		panel.add(txtetat);
		txtetat.setColumns(10);
		
		txtddate = new JTextField();
		txtddate.setBounds(135, 166, 122, 27);
		panel.add(txtddate);
		txtddate.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String identifiant, titre, etat, ddate;
				
				
				identifiant = txtidentifiant.getText();
				titre = txttitre.getText();
				etat = txtetat.getText();
				ddate = txtddate.getText();
				
				try {
					pst = con.prepareStatement("insert into tache(identifiant, titre, etat, date) values(?,?,?,?)");
					pst.setString(1, identifiant);
					pst.setString(2, titre);
					pst.setString(3, etat);
					pst.setString(4, ddate);
					pst.executeUpdate();
					JOptionPane.showInternalMessageDialog(null, "Record Added!!!");
					table();
					
					
					txtidentifiant.setText("");
					txttitre.setText("");
					txtetat.setText("");
					txtddate.setText("");
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(22, 359, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String identifiant, titre, etat, ddate,gid;
				
				
				identifiant = txtidentifiant.getText();
				titre = txttitre.getText();
				etat = txtetat.getText();
				ddate = txtddate.getText();
				gid = textgid.getText();
				
				try {
					pst = con.prepareStatement("update tache set identifiant= ?, titre= ?, etat= ?, date= ?");
					pst.setString(1, identifiant);
					pst.setString(2, titre);
					pst.setString(3, etat);
					pst.setString(4, ddate);
					pst.setString(5, gid);
					pst.executeUpdate();
					JOptionPane.showInternalMessageDialog(null, "Record update!!!");
					table();
					
					
					txtidentifiant.setText("");
					txttitre.setText("");
					txtetat.setText("");
					txtddate.setText("");
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(137, 359, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String gid;
				
				
				gid = textgid.getText();
				
				try {
					pst = con.prepareStatement("delete from tache where id = ?");
					
					pst.setString(1, gid);
					pst.executeUpdate();
					JOptionPane.showInternalMessageDialog(null, "Record Delete!!!");
					table();
					
					
					txtidentifiant.setText("");
					txttitre.setText("");
					txtetat.setText("");
					txtddate.setText("");
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(256, 359, 130, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(402, 329, 221, 53);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textgid = new JTextField();
		textgid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = textgid.getText();
					
					pst = con.prepareStatement("select identifiant, titre, etat, ddate from tache where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true) {
						String identifiant = rs.getString(1);
						String titre = rs.getString(2);
						String etat = rs.getString(3);
						String ddate = rs.getString(4);
						
						txtidentifiant.setText(identifiant);
						txttitre.setText(titre);
						txtetat.setText(etat);
						txtddate.setText(ddate);
						
						
						
					}
					else {
						txtidentifiant.setText("");
						txttitre.setText("");
						txtetat.setText("");
						txtddate.setText("");
					}
				}
				
				catch (SQLException ex) {
					
				}
			}
		});
		textgid.setBounds(124, 24, 89, 20);
		textgid.setColumns(10);
		panel_1.add(textgid);
		
		JButton btnNewButton_3 = new JButton("DernierId");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(10, 21, 104, 23);
		panel_1.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(340, 99, 391, 222);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
