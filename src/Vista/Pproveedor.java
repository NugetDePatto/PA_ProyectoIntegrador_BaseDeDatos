package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import Controlador.cProductos;
//import Controlador.cProveedor;
//import Modelo.Lproductos;
//import Modelo.Lproveedor;

public class Pproveedor extends JPanel implements ActionListener {
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtdireccion;
	private JTable table;
	private JButton bOK;

	//private cProveedor c = new cProveedor();
	private JButton btneliminar;
	private JButton btnmodificar;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bOK) {
			//table.setModel(c.listarRegistro(c.capturar(txtid.getText(), txtnombre.getText(), txtdireccion.getText())));
		} else if (e.getSource() == btnmodificar) {
			/*
			table.setModel(c.listarRegistro(c.modificar(
					"0" + String
							.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id a modificar ")) - 1),
					JOptionPane.showInputDialog("Ingrese el nuevo nombre"),
					JOptionPane.showInputDialog("Ingrese la nueva direccion"))));
			*/
		} else if (e.getSource() == btneliminar) {
			//table.setModel(c.listarRegistro(c.eliminar(table.getSelectedRow())));
		}
	}

	public Pproveedor() {
		setLayout(new BorderLayout(0, 0));

		JPanel ptxt_2 = new JPanel();
		add(ptxt_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Id");
		ptxt_2.add(lblNewLabel);

		txtid = new JTextField();
		txtid.setColumns(10);
		ptxt_2.add(txtid);

		JLabel lblNewLabel_5 = new JLabel("Nombre del proveedor");
		ptxt_2.add(lblNewLabel_5);

		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		ptxt_2.add(txtnombre);

		JLabel lblNewLabel_2 = new JLabel("Direccion");
		ptxt_2.add(lblNewLabel_2);

		txtdireccion = new JTextField();
		txtdireccion.setColumns(10);
		ptxt_2.add(txtdireccion);

		bOK = new JButton("Agregar");
		ptxt_2.add(bOK);
		bOK.addActionListener(this);


		btnmodificar = new JButton("Modificar");
		ptxt_2.add(btnmodificar);
		btnmodificar.addActionListener(this);

		btneliminar = new JButton("Eliminar");
		ptxt_2.add(btneliminar);
		btneliminar.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pos = table.rowAtPoint(e.getPoint());
				txtid.setText(table.getValueAt(pos, 0).toString());
				txtnombre.setText(table.getValueAt(pos, 1).toString());
				txtdireccion.setText(table.getValueAt(pos, 2).toString());
			}
		});
		
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nombre del Proveedor", "Direccion" }));

		//table.setModel(c.listarRegistro(c.CargarArchivo()));
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

	}

}
