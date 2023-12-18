package Inventario;

import java.util.ArrayList;

public class Inventario {

	public Inventario() {
		
	}
	
	public void RegistrarVenta(int pidProducto, int pCantidadVendida, int pPrecioTotalVenta) {
		Venta miVenta = new Venta(pidProducto, pCantidadVendida, pPrecioTotalVenta);
		miVenta.registrarVenta();
	}
	
	public void agregarNuevoProducto(String pNombreProducto, String pDescripcionProducto, int pPrecioProducto, int pCantidadDisponible) {
		Producto miProducto = new Producto(pNombreProducto, pDescripcionProducto, pPrecioProducto, pCantidadDisponible);
		miProducto.agregarProducto();
	}

	public void eliminarProducto(int pIdProducto) {
        Producto miProducto = new Producto(null, null, 0, 0);
        miProducto.eliminarProducto(pIdProducto);
    }
	
	public static ArrayList<Producto> listaProductos(){
		Producto miProducto = new Producto(null, null, 0, 0);
		
		return Producto.listaProductos();
	}
	public void actualizarProducto(int pIdProducto, String pNombreProducto, String pDescripcion, int pPrecioProducto, int pCantidadDisponible) {
		Producto miProducto = new Producto(pNombreProducto, pDescripcion, pPrecioProducto, pCantidadDisponible);
		miProducto.actualizarProducto(pIdProducto, pNombreProducto, pDescripcion, pPrecioProducto, pCantidadDisponible);
	}
	public static double obtenerSumaVentas() {
		Venta miVenta = new Venta(0, 0, 0);
		return miVenta.totalVentas();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
