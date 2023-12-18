package Interfaz;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Principal;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Inventario.Inventario;
import Inventario.Producto;
import Inventario.Venta;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class InterfazInvetario extends JFrame {

	private Inventario principal;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable Tabla_Productos;
	private JTextField txt_Total_Venta;
	private JTextField txt_Nombre_Producto;
	private JTextField txt_Valor_A_Pagar;
	private JTextField txt_id_producto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazInvetario frame = new InterfazInvetario();
					frame.setVisible(true);
					frame.llenarYActualizarTabla();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazInvetario() {
		principal = new Inventario();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_Inventario = new JPanel();
		panel_Inventario.setBounds(0, 0, 830, 460);
		contentPane.add(panel_Inventario);
		panel_Inventario.setLayout(null);

		JLabel lblTitulo = new JLabel("SISTEMA DE INVENTARIO UNIMINUTO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(220, 11, 374, 14);
		panel_Inventario.add(lblTitulo);

		JPanel panel_venta = new JPanel();
		panel_venta.setForeground(new Color(0, 0, 0));
		panel_venta.setToolTipText("Formulario de venta");
		panel_venta.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulario de Venta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_venta.setBounds(3, 91, 420, 210);
		panel_Inventario.add(panel_venta);
		panel_venta.setLayout(null);

		JLabel lblTotalVendido = new JLabel("Total Ventas:  $");
		lblTotalVendido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalVendido.setBounds(10, 49, 140, 14);
		panel_Inventario.add(lblTotalVendido);

		txt_Total_Venta = new JTextField();
		txt_Total_Venta.setEditable(false);
		txt_Total_Venta.setBounds(161, 48, 112, 20);
		panel_Inventario.add(txt_Total_Venta);
		txt_Total_Venta.setColumns(10);
		double valor = principal.obtenerSumaVentas();
		String textoFormateado = String.format("%.2f", valor); 
		txt_Total_Venta.setText(textoFormateado);



		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(10, 60, 131, 14);
		panel_venta.add(lblProducto);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 90, 131, 14);
		panel_venta.add(lblCantidad);

		JSpinner spiner_cantidad = new JSpinner();
		spiner_cantidad.setBounds(151, 90, 54, 20);
		panel_venta.add(spiner_cantidad);


		txt_Nombre_Producto = new JTextField();
		txt_Nombre_Producto.setBounds(151, 60, 260, 20);
		panel_venta.add(txt_Nombre_Producto);
		txt_Nombre_Producto.setColumns(10);

		JLabel lblPrecioTotalVenta = new JLabel("Valor a pagar: ");
		lblPrecioTotalVenta.setBounds(10, 120, 131, 14);
		panel_venta.add(lblPrecioTotalVenta);

		JButton btnGuardarVenta = new JButton("Guardar Venta");
		btnGuardarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					
					principal.RegistrarVenta(
							Integer.parseInt(txt_id_producto.getText()), 
							Integer.parseInt(spiner_cantidad.getValue().toString()), 
							Integer.parseInt(txt_Valor_A_Pagar.getText())
							);
					llenarYActualizarTabla();

					double valor = principal.obtenerSumaVentas();
					String textoFormateado = String.format("%.2f", valor); 
					txt_Total_Venta.setText(textoFormateado);
				}
				catch (Exception esx) {
					JOptionPane.showMessageDialog( null, "Error", "Datos incorretos - no se pudo completar la venta", JOptionPane.ERROR_MESSAGE );
				}
			}
		});

		btnGuardarVenta.setBounds(198, 160, 124, 23);
		panel_venta.add(btnGuardarVenta);
		
		txt_Valor_A_Pagar = new JTextField();
		txt_Valor_A_Pagar.setFont(new Font("Tahoma", Font.BOLD, 13));
		txt_Valor_A_Pagar.setColumns(10);
		txt_Valor_A_Pagar.setBounds(151, 120, 260, 20);
		panel_venta.add(txt_Valor_A_Pagar);
		
		JLabel lbl_id_producto = new JLabel("ID:");
		lbl_id_producto.setBounds(10, 23, 46, 14);
		panel_venta.add(lbl_id_producto);
		
		txt_id_producto = new JTextField();
		txt_id_producto.setEditable(false);
		txt_id_producto.setBounds(151, 20, 50, 20);
		panel_venta.add(txt_id_producto);
		txt_id_producto.setColumns(10);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ALMACEN", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(430, 92, 400, 299);
		panel_Inventario.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 380, 264);
		panel.add(scrollPane);

		Tabla_Productos = new JTable();

		ArrayList<Producto> productos = principal.listaProductos();

		DefaultTableModel modeloTabla = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"ID", "PRODUCTO", "CANT_DISPONIBLE", "PRECIO UNITARIO"
				}
				);

		for (int i = 0; i < productos.size(); i++) {
			Producto producto = productos.get(i);

			Object[] fila = {
					producto.getIdProducto(),
					producto.getNombreProducto(),
					producto.getCantidadDisponible(),
					producto.getPrecioProducto()
			};

			modeloTabla.addRow(fila);
		}

		Tabla_Productos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "PRODUCTO", "DISP", "PRECIO UNITARIO"
				}
				));
		Tabla_Productos.getColumnModel().getColumn(0).setPreferredWidth(31);
		Tabla_Productos.getColumnModel().getColumn(1).setPreferredWidth(134);
		Tabla_Productos.getColumnModel().getColumn(2).setPreferredWidth(52);
		Tabla_Productos.getColumnModel().getColumn(3).setPreferredWidth(112);
		scrollPane.setViewportView(Tabla_Productos);

		Tabla_Productos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = Tabla_Productos.getSelectedRow();
                if (selectedRow >= 0) {
                	String id =  Tabla_Productos.getValueAt(selectedRow, 0).toString();
                    String nombre = Tabla_Productos.getValueAt(selectedRow, 1).toString();
                    String descripcion = ""; 
                    int cantidad = Integer.parseInt(Tabla_Productos.getValueAt(selectedRow, 2).toString());
                    double precio = Double.parseDouble(Tabla_Productos.getValueAt(selectedRow, 3).toString());

                    	
                    txt_id_producto.setText(id);
                    txt_Nombre_Producto.setText(nombre);
                    //txt_area_descripcion.setText(descripcion);
                    spiner_cantidad.setValue(cantidad);
                    int precioEntero = (int) precio;
                    txt_Valor_A_Pagar.setText(String.valueOf(precioEntero));

                    
                }
            }
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		panel_1.setBounds(3, 312, 420, 113);
		panel_Inventario.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton = new JButton("Agregar Nuevo Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto: ");
					String descripcion = JOptionPane.showInputDialog("Ingrese descripción producto: ");
					int precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor unitario del producto: "));
					int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto: "));

					principal.agregarNuevoProducto(nombre, descripcion, precio, cantidad);
					llenarYActualizarTabla();
					JOptionPane.showMessageDialog(null,"Se registro el producto: "
							+ "PRODUCTO: "+nombre
							+ "DESCRIPCION: "+ descripcion
							+ "PRECIO UNITARIO: " + precio
							+ "CANTIDAD STOCK: " + cantidad);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog( null, "Error", "Datos incorretos - no se pudo agregar el producto.", JOptionPane.ERROR_MESSAGE );
				}






			}
		});
		panel_1.add(btnNewButton);

		JButton btn_Actualizar_Producto = new JButton("Actualizar Producto");
		btn_Actualizar_Producto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese | id | o | codigo | del producto: "));
					String nombreProducto = JOptionPane.showInputDialog("Ingrese nombre producto: ");
					String descripcion = JOptionPane.showInputDialog("Ingrese descripción producto");
					int precioProducto= Integer.parseInt(JOptionPane.showInputDialog("Ingrese precio producto"));
					int cantidadDispo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad del producto"));
					
					principal.actualizarProducto(
							idProducto, 
							nombreProducto, 
							descripcion, 
							precioProducto, 
							cantidadDispo
							);
					llenarYActualizarTabla();
					
				}
				catch (Exception es) {
				}
				
			}
		});
		panel_1.add(btn_Actualizar_Producto);

		JButton btn_Eliminar_Producto = new JButton("Eliminar Producto");
		btn_Eliminar_Producto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese codigio o id del producto: "));
					principal.eliminarProducto(idProducto);
					llenarYActualizarTabla();
				}
				catch (Exception et){
					JOptionPane.showMessageDialog( null, "Error", "Codigo incorrecto o no encontrado", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		panel_1.add(btn_Eliminar_Producto);


	}

	public void llenarYActualizarTabla() {
		ArrayList<Producto> productos = principal.listaProductos();

		DefaultTableModel modeloTabla = new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID", "PRODUCTO", "DISP", "PRECIO UNITARIO"}
				);

		for (int i = 0; i < productos.size(); i++) {
			Producto producto = productos.get(i);

			Object[] fila = {
					producto.getIdProducto(),
					producto.getNombreProducto(),
					producto.getCantidadDisponible(),
					producto.getPrecioProducto()
			};

			modeloTabla.addRow(fila);
		}

		Tabla_Productos.setModel(modeloTabla);
		Tabla_Productos.getColumnModel().getColumn(0).setPreferredWidth(31);
		Tabla_Productos.getColumnModel().getColumn(2).setPreferredWidth(125);
		Tabla_Productos.getColumnModel().getColumn(3).setPreferredWidth(124);
		
		
	}
	
	
	
}
