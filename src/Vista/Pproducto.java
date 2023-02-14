package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.cProductos;

//import Controlador.cCategoria;
//import Controlador.cProductos;
//import Modelo.Ccategoria;
//import Modelo.Lcategoria;
//import Modelo.Lproductos;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Pproducto extends JPanel implements ActionListener{
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtprecio;
	private JTable tabla;
	private JComboBox comboBox;
	
	cProductos c = new cProductos();
	
	private JButton bAgregar;
	private JScrollPane pTabla;
	private JButton bEliminar;
	private JButton bModificar;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bAgregar ) {
			try {
				tabla.setModel(c.insertar(txtid.getText(), txtnombre.getText(), txtprecio.getText(), comboBox.getSelectedItem().toString()));
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("El codigo ya existe");
			}
		}else if(e.getSource() == bEliminar) {
			try {
				tabla.setModel(c.eliminar(txtid.getText()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == bModificar) {
			try {
				tabla.setModel(c.modificar(txtid.getText(), txtnombre.getText(), txtprecio.getText(), comboBox.getSelectedItem().toString()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Pproducto() throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pComp = new JPanel();
		add(pComp, BorderLayout.NORTH);
		pComp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbli_1 = new JLabel("Id");
		pComp.add(lbli_1);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		pComp.add(txtid);
		
		JLabel lbln_1 = new JLabel("Nombre");
		pComp.add(lbln_1);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		pComp.add(txtnombre);
		
		JLabel lblp_1 = new JLabel("Precio");
		pComp.add(lblp_1);
		
		txtprecio = new JTextField();
		txtprecio.setColumns(10);
		pComp.add(txtprecio);
		
		JLabel lblNewLabel = new JLabel("Categoria");
		pComp.add(lblNewLabel);
		
		comboBox = c.comboBox();
		
		pComp.add(comboBox);
		
		bAgregar = new JButton("Agregar");
		pComp.add(bAgregar);
		bAgregar.addActionListener(this);
		
		bEliminar = new JButton("Eliminar");
		pComp.add(bEliminar);
		bEliminar.addActionListener(this);
		
		bModificar = new JButton("Modificar");
		pComp.add(bModificar);
		bModificar.addActionListener(this);
		
		
		pTabla = new JScrollPane();
		add(pTabla, BorderLayout.CENTER);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pos = tabla.rowAtPoint(e.getPoint());
				txtid.setText(tabla.getValueAt(pos, 0).toString());
				txtnombre.setText(tabla.getValueAt(pos, 1).toString());
				txtprecio.setText(tabla.getValueAt(pos, 2).toString());
				for (int i = 0; i < comboBox.getItemCount(); i++) {
					if (comboBox.getItemAt(i).equals(tabla.getValueAt(pos, 3)))
						comboBox.setSelectedItem(comboBox.getItemAt(i));
				}

			}
		});
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre del Producto", "Precio", "Categoria"
			}
		));
		
		tabla.setModel(c.listar());
		tabla.setRowHeight(30);
		pTabla.setViewportView(tabla);

	}

}
