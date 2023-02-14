package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.cProductos;
import Modelo.Cproducto;
import Modelo.Lproductos;

//import Controlador.cProductos;
//import Modelo.Cproducto;
//import Modelo.Lproductos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dbuscar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textnombre;
	private JTable table;
	private DefaultTableModel dt;
	private Cproducto p;

	private cProductos c = new cProductos();

	private Lproductos lista = c.lista();

	/*
	 * public static void main(String[] args) { Dbuscar frame = new Dbuscar();
	 * frame.setVisible(true); }
	 */

	public Dbuscar() throws SQLException {
		this.lista = c.lista();
		setTitle("Buscar Producto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Buscar producto");
		panel.add(lblNewLabel);

		textnombre = new JTextField();
		textnombre.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				table.setModel(listar(textnombre.getText()));
			}
		});

		panel.add(textnombre);
		textnombre.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nombre del Producto" }));
		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton bCerrar = new JButton("Cerrar");
		bCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(bCerrar);

	}

	public DefaultTableModel listar(String nombre) {
		p = new Cproducto();

		dt = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dt.addColumn("Id");
		dt.addColumn("Nombre del Producto");
		
		lista.cantidadRegistro();

		Object fila[] = new Object[dt.getColumnCount()];
		for (int i = 0; i < lista.cantidadRegistro(); i++) {
			
			p = lista.getProducto(i);
			
			if(p.getNombre().toLowerCase().indexOf(nombre.toLowerCase()) >= 0) {
				fila[0] = p.getId();
				fila[1] = p.getNombre();

				dt.addRow(fila);
			}
		}

		return dt;
	}

}
