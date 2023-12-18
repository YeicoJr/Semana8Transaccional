package Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Producto {
	int idProducto;
	String nombreProducto;
	String descripcionProducto;
	int precioProducto;
	int cantidadDisponible;

	public Producto(String pNombreProducto, String pDescripcionProducto, int pPrecioProducto, int pCantidadDisponible) {
		this.nombreProducto = pNombreProducto;
		this.descripcionProducto = pDescripcionProducto;
		this.precioProducto = pPrecioProducto;
		this.cantidadDisponible = pCantidadDisponible;
	}

	public int getIdProducto() {		
		return this.idProducto;
	}
	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public String getDescripcionProducto() {
		return this.descripcionProducto;
	}

	public double getPrecioProducto() {
		return this.precioProducto;
	}

	public int getCantidadDisponible() {
		return this.cantidadDisponible;
	}
	
	public void setIdProducto(int pId) {
		this.idProducto = pId;
	}

	public void setNombreProducto(String pNombreProducto) {
		this.nombreProducto = pNombreProducto;
	}

	public void setDescripcionProducto(String pDescripcion) {
		this.descripcionProducto = pDescripcion;
	}

	public void setPrecioProducto(int pPrecioProducto) {
		this.precioProducto = pPrecioProducto;
	}

	public void setCantidadDisponible(int pCantidadProducto) {
		this.cantidadDisponible = pCantidadProducto;
	}

	public static ArrayList<Producto> listaProductos() {
		ArrayList<Producto> misProductos = new ArrayList<>();

		try (Connection connection = Conexion.obtenerConexion()) {
			String sql = "SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_disponible FROM Productos;";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						int idProducto = resultSet.getInt("id_producto");
						String nombreProducto = resultSet.getString("nombre_producto");
						String descripcionProducto = resultSet.getString("descripcion_producto");
						int precioProducto = resultSet.getInt("precio_producto");
						int cantidadDisponible = resultSet.getInt("cantidad_disponible");

						Producto producto = new Producto(nombreProducto, descripcionProducto, precioProducto, cantidadDisponible);
						producto.setIdProducto(idProducto);
						producto.setNombreProducto(nombreProducto);
						producto.setDescripcionProducto(descripcionProducto);
						producto.setPrecioProducto(precioProducto);
						producto.setCantidadDisponible(cantidadDisponible);

						misProductos.add(producto);
					}
				}
			}
			System.out.println(misProductos);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return misProductos;
	}

	//------ metodo para registrar un producto en base de datos
	public void agregarProducto() {
		try (Connection connection = Conexion.obtenerConexion()) {
			String sql = "INSERT INTO Productos (nombre_producto, descripcion_producto, precio_producto, cantidad_disponible) VALUES (?, ?, ?, ?)";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, getNombreProducto());
				statement.setString(2, getDescripcionProducto());
				statement.setDouble(3, getPrecioProducto());
				statement.setInt(4, getCantidadDisponible());

				int filasAfectadas = statement.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("PRODUCTO AGREGADO A LA BASE DE DATOS.");
					JOptionPane.showMessageDialog( null, "Producto registrado", "Se agregó el producto a la base de datos.", JOptionPane.INFORMATION_MESSAGE );
				} else {
					System.out.println("ERROR AL REGISTRAR EL PRODUCTO.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarProducto(int idProducto) {
        try (Connection connection = Conexion.obtenerConexion()) {
            String sql = "DELETE FROM Productos WHERE id_producto = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idProducto);

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("PRODUCTO ELIMINADO DE LA BASE DE DATOS.");
                    JOptionPane.showMessageDialog(null, "Producto eliminado", "Se eliminó el producto de la base de datos.", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("ERROR AL ELIMINAR EL PRODUCTO. Puede que el ID no exista.");
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto. Puede que el ID no exista.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto. Producto relacionado con una venta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	public void actualizarCantidades(int pIdProducto, int pCantidad) {
	    try (Connection connection = Conexion.obtenerConexion()) {
	        String sql = "UPDATE Productos SET cantidad_disponible = cantidad_disponible - ? WHERE id_producto = ?;";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	           
	            statement.setInt(1, pCantidad);
	            statement.setInt(2, pIdProducto);

	            int filasAfectadas = statement.executeUpdate();
	            if (filasAfectadas > 0) {
	                System.out.println("CANTIDAD ACTUALIZADA.");
	            } else {
	                System.out.println("ERROR AL ACTUALIZAR LA CANTIDAD DEL PRODUCTO. Puede que el ID no exista.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad del producto.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void actualizarProducto(int pIdProducto, String pNombreProducto, String pDescripcion, int pPrecioProducto, int pCantidadDisponible) {
	    try (Connection connection = Conexion.obtenerConexion()) {
	        String sql = "UPDATE productos SET nombre_producto = ?, descripcion_producto = ?, precio_producto = ?, cantidad_disponible = ? WHERE id_producto = ?;";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, pNombreProducto);
	            statement.setString(2, pDescripcion);
	            statement.setInt(3, pPrecioProducto);
	            statement.setInt(4, pCantidadDisponible);
	            statement.setInt(5, pIdProducto);

	            int filasAfectadas = statement.executeUpdate();
	            if (filasAfectadas > 0) {
	                System.out.println("PRODUCTO ACTUALIZADO.");
	                JOptionPane.showMessageDialog(null, "Producto Actualizado", "Se actualizó el producto en la base de datos.", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                System.out.println("ERROR AL ACTUALIZAR DATOS DEL PRODUCTO. Puede que el ID no exista.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar la cantidad del producto.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	
	
	public static void main(String[] args) {
		Producto miProducto = new Producto("", "", 0, 0);
		miProducto.listaProductos();


	}

}
