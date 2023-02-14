package Vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;

public class pestañas extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTabbedPane pestaña, pVentas;
	JButton bSalir;

	private JPanel panel;
	private JLabel lblNewLabel;
	JButton bRecargar;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bSalir) {
			dispose();
		} else if (e.getSource() == bRecargar) {
			int pos = pestaña.getSelectedIndex();
			pestaña.removeTabAt(5);
			pestaña.removeTabAt(4);
			pestaña.removeTabAt(3);
			pestaña.removeTabAt(2);
			pestaña.removeTabAt(1);
			pVentas.removeTabAt(1);
			pVentas.removeTabAt(0);
			try {
				paneles();
			} catch (SQLException e1) {
				System.out.println("Error paneles");
				e1.printStackTrace();
			}
			pestaña.setSelectedIndex(pos);
			
		}

	}

	public void paneles() throws SQLException {
		pestaña.insertTab("Productos", null, new Pproducto(), null, 1);
		pestaña.insertTab("Categorias", null, new Pcategoria(), null, 2);
		pestaña.insertTab("Proveedores", null, new Pproveedor(), null, 3);
		pestaña.insertTab("Almacen", null, new Palmacen(), null, 4);
		pVentas = new JTabbedPane(JTabbedPane.BOTTOM);
		pestaña.insertTab("Ventas", null, pVentas, null, 5);
		pVentas.insertTab("Pagar", null, new Pventa(), null, 0);
		pVentas.insertTab("Ventas", null, new Ppagar(), null, 1);
	}

	public pestañas() throws SQLException {
		setTitle("meomeow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pestaña = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(pestaña, BorderLayout.CENTER);

		panel = new JPanel();
		pestaña.insertTab("Inicio", null, panel, null, 0);

		lblNewLabel = new JLabel("BIENVENIDO");
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("ROG Fonts", Font.BOLD, 80));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(5)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);

		paneles();

		JPanel panelBoton = new JPanel();
		FlowLayout fl_panelBoton = (FlowLayout) panelBoton.getLayout();
		fl_panelBoton.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelBoton, BorderLayout.NORTH);

		bRecargar = new JButton("Recargar Pagina");
		bRecargar.setBackground(SystemColor.activeCaption);
		bRecargar.setForeground(SystemColor.text);
		panelBoton.add(bRecargar);
		bRecargar.addActionListener(this);

		bSalir = new JButton("Salir");
		bSalir.setForeground(SystemColor.menu);
		bSalir.setBackground(SystemColor.windowBorder);
		panelBoton.add(bSalir);

		bSalir.addActionListener(this);
	}

	public static void main(String[] args) throws SQLException {
		pestañas frame = new pestañas();
		frame.setVisible(true);
		frame.setExtendedState(pestañas.MAXIMIZED_BOTH);
	}
	
	//REDUNDANCIA DE INFORMACION
}
