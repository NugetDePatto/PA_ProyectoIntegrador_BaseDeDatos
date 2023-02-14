package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.cProductos;

//import Controlador.cProductos;

import javax.swing.JSpinner;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Palmacen extends JPanel implements ActionListener{
	private JTextField txtid;
	private JTable table;
	private JSpinner spinCantidad;
	private JButton bAñadir;
	private cProductos c = new cProductos();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bAñadir) {
			System.out.println(spinCantidad.getValue().toString());
		//	table.setModel(c.listarRegistro(c.modificarCantidad(txtid.getText(), Integer.parseInt(spinCantidad.getValue().toString()))));
			try {
				table.setModel(c.modificarCantidad(txtid.getText(), spinCantidad.getValue().toString()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

	public Palmacen() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		JPanel ptxt_3 = new JPanel();
		add(ptxt_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("Id del producto");
		ptxt_3.add(lblNewLabel_3);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		ptxt_3.add(txtid);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad a agregar");
		ptxt_3.add(lblNewLabel_4);
		
		spinCantidad = new JSpinner();
		spinCantidad.setPreferredSize(new Dimension(50, 20));
		ptxt_3.add(spinCantidad);
		
		bAñadir = new JButton("A\u00F1adir");
		ptxt_3.add(bAñadir);
		bAñadir.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pos = table.rowAtPoint(e.getPoint());
				txtid.setText(table.getValueAt(pos, 0).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre del Producto", "Categoria", "Categoria"
			}
		));
		table.setModel(c.listar());
		//table.setModel(c.listarRegistro(c.CargarArchivo()));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

	}



}
