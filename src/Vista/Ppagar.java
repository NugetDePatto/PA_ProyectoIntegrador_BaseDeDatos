package Vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ppagar extends JPanel {
	private JTable table;

	public Ppagar() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Id", "Nombre del Producto", "Cantidad a comprar"
			}
		));
		scrollPane.setViewportView(table);

	}

}
