package Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Venta {
	int idProducto;
	int cantidadVendida;
	int precioTotalVenta;

	public Venta(int pidProducto, int pCantidadVendida, int pPrecioTotalVenta) {
		this.idProducto = pidProducto;
		this.cantidadVendida = pCantidadVendida;
		this.precioTotalVenta = pPrecioTotalVenta;
	}

	public int getIdProducto() {
		return this.idProducto;
	}
	
	public int getCantidadVendida() {
		return this.cantidadVendida;
	}
	
	public int getPrecioTotalVenta() {
		return this.precioTotalVenta;
	}
	
	public void setIdProducto(int pIdProducto) {
		this.idProducto = pIdProducto;
	}
	
	public void setPrecioTotalVenta(int pPrecioTotalVenta) {
		this.precioTotalVenta = pPrecioTotalVenta;
	}
	
	public void registrarVenta() {
		Producto miProducto = new Producto(null, null, 0, 0);
		
        try (Connection connection = Conexion.obtenerConexion()) {
            String sql = "INSERT INTO Ventas (id_producto_vendido, cantidad_vendida, precio_de_venta) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, getIdProducto());
                statement.setInt(2, getCantidadVendida());
                statement.setInt(3, getPrecioTotalVenta());

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Venta registrada en la base de datos.");
                    miProducto.actualizarCantidades(getIdProducto(), getCantidadVendida());
                    JOptionPane.showMessageDialog(null, "Venta Exitosa", "Se realizó la venta.", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("Error al registrar la venta.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public double totalVentas() {
	    double sumaTotalVentas = 0.0;

	    try (Connection connection = Conexion.obtenerConexion()) {
	        String sql = "SELECT SUM(cantidad_vendida * precio_de_venta) AS suma_total_ventas FROM Ventas;";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    sumaTotalVentas = resultSet.getDouble("suma_total_ventas");
	                    System.out.println("Suma total de ventas: " + sumaTotalVentas);
	                } else {
	                    System.out.println("No hay datos en la tabla Ventas.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return sumaTotalVentas;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Venta miVenta = new Venta(1, 5, 25100);
		//miVenta.registrarVenta();

	}

}
