import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Data {

	private JFrame frame;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtcourse;
	private JTable table;
	private JTextField stid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Data window = new Data();
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
	public Data() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/data", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	
	
	
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("select * from student");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		 {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.setBounds(100, 100, 597, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(251, 11, 121, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fill up form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(33, 79, 283, 228);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblFirstName.setBounds(10, 31, 69, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblLastName.setBounds(10, 71, 69, 14);
		panel.add(lblLastName);
		
		JLabel lblNewLabel_1 = new JLabel("Course:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 107, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtfname = new JTextField();
		txtfname.setBounds(80, 28, 162, 20);
		panel.add(txtfname);
		txtfname.setColumns(10);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(80, 68, 162, 20);
		panel.add(txtlname);
		
		txtcourse = new JTextField();
		txtcourse.setColumns(10);
		txtcourse.setBounds(80, 104, 162, 20);
		panel.add(txtcourse);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstname,lastname,course;
				
				firstname = txtfname.getText();
				lastname = txtlname.getText();
				course = txtcourse.getText();
				
				try {
					pst = con.prepareStatement("insert into student(firstname,lastname,course)values(?,?,?)");
					pst.setString(1, firstname);
					pst.setString(2, lastname);
					pst.setString(3, course);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Added");
					table_load();				          
					txtfname.setText("");
					txtlname.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(33, 318, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);

			}
		});
		btnExit.setBounds(130, 318, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				txtfname.setText("");
				txtlname.setText("");
				txtcourse.setText("");
				txtfname.requestFocus();
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnClear.setBounds(227, 318, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(326, 49, 234, 255);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 341, 283, 63);
		frame.getContentPane().add(panel_1);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		panel_1.add(lblStudentId);
		
		stid = new JTextField();
		stid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = stid.getText();
		 
		                pst = con.prepareStatement("select firstname,lastname,course from student where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String firstname = rs.getString(1);
		                String lastname = rs.getString(2);
		                String course = rs.getString(3);
		                
		                txtfname.setText(firstname);
		                txtlname.setText(lastname);
		                txtcourse.setText(course);
		                
		                
		            }  
		            else
		            {
		             txtfname.setText("");
		             txtlname.setText("");
		             txtcourse.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		stid.setColumns(10);
		panel_1.add(stid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					String firstname,lastname,course,studentid;
				
				firstname = txtfname.getText();
				lastname = txtlname.getText();
				course = txtcourse.getText();
				studentid = stid.getText();
				
				try {
					pst = con.prepareStatement("Update student set firstname= ?, lastname=?, course=? where id =?");
					pst.setString(1, firstname);
					pst.setString(2, lastname);
					pst.setString(3, course);
					pst.setString(4, studentid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					table_load();				          
					txtfname.setText("");
					txtlname.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnUpdate.setBounds(344, 306, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String studentid;
				
				studentid = stid.getText();
				
				try {
					pst = con.prepareStatement("Delete from student where id =?");
					pst.setString(1, studentid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Deleted");
					table_load();				          
					txtfname.setText("");
					txtlname.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnDelete.setBounds(460, 306, 89, 23);
		frame.getContentPane().add(btnDelete);
	}
}
