package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import Controlador.cCategoria;
//import Controlador.cProductos;
//import Modelo.Lcategoria;
//import Modelo.Lproductos;

public class Pcategoria extends JPanel implements ActionListener {
	private JTextField txtid;
	private JTextField txtcategoria;
	private JTable table;
	
	//private cCategoria c = new cCategoria();
	//private Lcategoria lista;
	
	private JButton bOK;
	private JButton btnmodificar;
	private JButton btneliminar;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bOK ) {
			//table.setModel(c.listarRegistro(c.capturar(txtid.getText(), txtcategoria.getText())));
		}else {
			if(e.getSource()==btneliminar) {
				//table.setModel(c.listarRegistro(c.eliminar(table.getSelectedRow())));
			}else {
				if(e.getSource()==btnmodificar) {
					// table.setModel(c.listarRegistro(c.modificar("0"+String.valueOf(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id a modificar "))-1),JOptionPane.showInputDialog("Ingrese la nueva categoria"))));
				}
			}
		}
	}

	public Pcategoria() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Id");
		panel.add(lblNewLabel);
		
		txtid = new JTextField();
		panel.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de la Categoria");
		panel.add(lblNewLabel_1);
		
		txtcategoria = new JTextField();
		panel.add(txtcategoria);
		txtcategoria.setColumns(10);
		
		bOK = new JButton("OK");
		panel.add(bOK);
		bOK.addActionListener(this);
		
		btnmodificar = new JButton("Modificar");
		panel.add(btnmodificar);
		btnmodificar.addActionListener(this);
		
		btneliminar = new JButton("Eliminar");
		panel.add(btneliminar);
		btneliminar.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Categoria"
			}
		));
		
		//table.setModel(c.listarRegistro(c.CargarArchivo()));
		table.setRowHeight(30);
		
		scrollPane.setViewportView(table);

	}

}
