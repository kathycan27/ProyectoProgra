public class Productos {
    int stock;
    String producto;

    public Productos() {
        stock = 0;
        producto = "";

    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return getProducto();
    }
}
