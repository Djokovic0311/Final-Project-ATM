package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Stock;
import model.customerHeldStock;
public class StockDao {

    private static StockDao stockDao = new StockDao();

    public static StockDao getInstance(){
        return stockDao;
    }

    public int updateStock(Stock stock) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        int flag = 0;
    	ConnectDao ctd = new ConnectDao();
        conn = ctd.connectToDb();
        ps = conn.prepareStatement("Updating");
        ps.setInt(1, stock.getStockID());
        ps.setInt(2, stock.getPrice());
        ps.setInt(3, stock.getQuantity());
        flag = ps.executeUpdate();
        return flag;
    }

    public int removeCustomerStock(customerHeldStock stock) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int flag = 0;
    	ConnectDao ctd = new ConnectDao();
        conn = ctd.connectToDb();
        ps = conn.prepareStatement("Deleting");
        ps.setInt(1, stock.getStockID());
        flag = ps.executeUpdate();
        return flag;
    }

    public int insertCustomerStock(customerHeldStock stock) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int flag = 0;
    	ConnectDao ctd = new ConnectDao();
        conn = ctd.connectToDb();
        ps = conn.prepareStatement("Inserting");
        ps.setInt(1,stock.getStockID());
        ps.setInt(2, stock.getBuyPrice());
        ps.setInt(3,stock.getQuantity());
        ps.setLong(4,stock.getBuyTime());
        flag = ps.executeUpdate();
        return flag;
    }

    public List<Stock> getStockInfoList(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Stock> list = new ArrayList<>();

    	ConnectDao ctd = new ConnectDao();
        conn = ctd.connectToDb();
            String sql = "select * from stock";//
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int sid = rs.getInt("s_ID");
                int sprice = rs.getInt("s_price");
                int squantity = rs.getInt("c_quantity");
                Stock stock = new Stock(sid, sprice, squantity);
                list.add(stock);
            }

        return list;
    }

}
