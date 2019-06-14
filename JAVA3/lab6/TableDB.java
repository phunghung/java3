package baitap.lab6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableDB {

    JFrame frame;
    JTable table;
    DefaultTableModel model;
    JButton btn;
    JToolBar toolbar;
    JButton btnAdd, btnSave, btnDelete;
    JLabel statusBar;

    private static String DB_URL = "jdbc:mysql://localhost:3306/NorthWind";
    private static String USER_NAME = "hunga";
    private static String PASSWORD = "ABCabc.12";
//    private ArrayList<int> arrRowsChange = new ArrayList<int>();
//    private int[]  arrRowsChange  = new int[10];   
    Set<Integer> arrRowsChange = new HashSet<>();
    Set<Integer> arrRowsInsert = new HashSet<>();
    Connection conn = null;

    public void connectSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                System.out.println("Ket noi thanh cong");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareGUI() {
        frame = new JFrame();
        frame.setSize(720, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    private void createToolbar() {
        frame.add(toolbar = new JToolBar(), BorderLayout.NORTH);
        toolbar.add(btnAdd = new JButton("Insert"));
        toolbar.add(btnSave = new JButton("Save"));
        toolbar.add(btnDelete = new JButton("Delete"));

        btnAdd.addActionListener(new actionButtonInsert());
        btnSave.addActionListener(new actionButtonSave());
        btnDelete.addActionListener(new actionButtonDelete());

        btnAdd.setToolTipText("Them hang moi");
        btnSave.setToolTipText("Cap nhat du lieu tu database");
        btnDelete.setToolTipText("Xoa hang duoc chon");
        frame.add(statusBar = new JLabel("ready..."), BorderLayout.SOUTH);
    }
    
    public TableDB() {
        prepareGUI();
        createToolbar();
        table = new JTable();
        table.setPreferredSize(new Dimension(500, 500));
        frame.add(new JScrollPane(table));

        loadDataIntoJTable();
        table.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
               System.out.println("colInsert: " + e.getColumn() +", rowInsert: "+e.getFirstRow());
               arrRowsChange.add(e.getFirstRow());
            } 
        });
//        table.setEnabled(false);

    }
    private Employee getEmInTable(DefaultTableModel tm, int rowIndex) {
    	Vector v = new Vector();
    	Employee emp = new Employee();
    	for(int i = 0; i < tm.getColumnCount(); i++) {
    		v.add(tm.getValueAt(rowIndex, i).toString());
    	}
    	emp = new Employee(v.get(0).toString(), v.get(1).toString(), v.get(2).toString(), v.get(3).toString(),v.get(4).toString());
    	return emp;
    }
    

    private void loadDataIntoJTable() {
        
        model = new DefaultTableModel();
        //Set Column Title
        Vector column = new Vector();
        column.add("Employee ID");
        column.add("Employee Name");
        column.add("Employee Email");
        column.add("Employee Password");
        column.add("Employee Country");
        model.setColumnIdentifiers(column);
        
        List<Employee> list = getEmpFromDB();
        for (int i = 0; i < list.size(); i++) {
            Employee empl = (Employee) list.get(i);
            Vector row = new Vector();
            row.add(empl.getId());
            row.add(empl.getName());
            row.add(empl.getEmail());
            row.add(empl.getPassword());
            row.add(empl.getCountry());
            model.addRow(row);
        }
        // Set model cho Table
        table.setModel(model);
    }

    private List<Employee> getEmpFromDB() {
        try {
            connectSQL();
            String sql = "SELECT EmpID, Uname, Email, Password, Country FROM emp";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Employee> list = new ArrayList<Employee>();
            while (rs.next()) {
                Employee empl = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(empl);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class actionButtonInsert implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "INSERT");
            String[] row = {"", "", "", "", ""};         
            model.addRow(row);
            arrRowsInsert.add(model.getRowCount()-1);           
        }
    }
    
    private boolean insertEmp(Employee e) {
      String sql = "INSERT INTO `emp` (`EmpID`, `Uname`, `Email`, `Password`, `Country`) VALUES ('"+e.getId()+"',"
		+ " '"+e.getName()+"', '"+e.getEmail()+"', '"+e.getPassword()+"', '"+e.getCountry()+"');";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			return pst.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
		    	
    }
    
    private boolean updateEmp(Employee e) {
    	String sql = "UPDATE `emp` SET `Uname`='"+e.getName()+"',`Email`='"+e.getEmail()+"',`Password`='"+e.getPassword()+"',`Country`='"+e.getCountry()+"' WHERE EmpID= '" + e.getId() +"'";
    	PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			return pst.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	return false;
    }
    
    class actionButtonSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "SAVE");
            for (int row : arrRowsInsert) {
            	Employee emp = getEmInTable(model, (int) row);
            	insertEmp(emp);
            	arrRowsChange.remove(row);
            }
            for (int row : arrRowsChange) {
            	Employee emp = getEmInTable(model, (int) row);
            	updateEmp(emp);
            }
        }
    }

    class actionButtonDelete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "DELETE");
            System.out.println( table.getSelectedRow());
            String sql = "DELETE FROM `emp` WHERE EmpID= '" + model.getValueAt(table.getSelectedRow(), 0) + "'";
           model.removeRow(table.getSelectedRow());
            PreparedStatement pst;
    		try {
    			pst = conn.prepareStatement(sql);
    			 pst.execute();
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
        }
    }

    public static void main(String[] args) throws Exception {
        new TableDB();
    }
}