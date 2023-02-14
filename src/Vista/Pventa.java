package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Libreria;
import Controlador.cProductos;
import Controlador.cVenta;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pventa extends JPanel implements ActionListener{
	private JTextField txtId;
	private JTable table;
	private JButton bAñadir, bBuscar, bPagar, bQuitar;
	private DefaultTableModel dt;
	//private cVenta v = new cVenta();	
	//private cProductos c = new cProductos();
	//private Lproductos lista = c.CargarArchivo();
	boolean b = true;
	
	private cVenta c = new cVenta();
	private JButton bLimpiar;
	private JScrollPane scrollPane;
	private JTextArea txtTotal;
	
	int pos;
	
	boolean sel = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bAñadir) {
			try {
				table.setModel(c.añadir(dt, txtId.getText()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == bBuscar) {
			Dbuscar d = null;
			try {
				d = new Dbuscar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			d.setVisible(true);
		} else if (e.getSource() == bPagar) {
			try {
				txtTotal.setText(txtTotal.getText()+"\n"+c.pagar(dt));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == bQuitar) {
			if(sel) {
				dt.removeRow(pos);
				table.setModel(dt);
			}else {
				JOptionPane.showMessageDialog(this, "No has seleccionado un producto");
			}
			
			sel = false;
			
		} else if (e.getSource() == bLimpiar) {
			int tam = dt.getRowCount();
			for(int i = 0; i < tam; i++) {
				dt.removeRow(0);
			}
			table.setModel(dt);
			
		}
	}

	public Pventa() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Id del Producto");
		panel.add(lblNewLabel);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		bAñadir = new JButton("A\u00F1adir");
		panel.add(bAñadir);
		bAñadir.addActionListener(this);
		
		bBuscar = new JButton("Buscar Producto");
		panel.add(bBuscar);
		bBuscar.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.EAST);
		
		txtTotal = new JTextArea();
		txtTotal.setColumns(30);
		txtTotal.setRows(10);
		scrollPane.setViewportView(txtTotal);
		
		bPagar = new JButton("Pagar");
		panel_1.add(bPagar);
		bPagar.addActionListener(this);
		
		bQuitar = new JButton("Quitar");
		panel_1.add(bQuitar);
		
		bLimpiar = new JButton("Limpiar tabla");
		panel_1.add(bLimpiar);
		bQuitar.addActionListener(this);
		
		bLimpiar.addActionListener(this);
		
		JScrollPane scroll = new JScrollPane();
		add(scroll, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = table.getSelectedRow();
				sel = true;
			}
		});
		
		
		dt = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dt.addColumn("Id");
		dt.addColumn("Nombre");
		dt.addColumn("Precio");
		dt.addColumn("Categoria");
		dt.addColumn("Cantidad");
		
		
		table.setModel(dt);
		
		scroll.setViewportView(table);

	}

}
