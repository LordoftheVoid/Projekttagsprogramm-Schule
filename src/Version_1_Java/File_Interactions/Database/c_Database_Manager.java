package Version_1_Java.File_Interactions.Database;

import java.sql.*;

/**
 * Created by Aaron on 09.07.2017.
 */
public class c_Database_Manager {

    private Connection Datenbankverbindung;


    private PreparedStatement insertInto;

    private PreparedStatement update_Entry;

    private PreparedStatement delete_Entry;

    public void v_initialization() throws SQLException {
        try {
            Datenbankverbindung= DriverManager.getConnection("jdbc:sqlite:C:/Informatik/Projekte/Projekttagprogramm Schule/Schueler_Datenbank_V1.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public ResultSet read_entrys_one_attribute( String table, String colum) throws  SQLException{
        PreparedStatement extract_entrys= Datenbankverbindung.prepareStatement("SELECT "+colum+" FROM "+table);
        return  extract_entrys.executeQuery();

    }



    public ResultSet read_entrys_all_attributes( String table) throws  SQLException{
        PreparedStatement extract_entrys= Datenbankverbindung.prepareStatement("SELECT * FROM "+table);
        return  extract_entrys.executeQuery();

    }

    public ResultSet read_one_entry_one_attribute( String table, String colum, String unique_id) throws  SQLException{
        PreparedStatement extract_entrys= Datenbankverbindung.prepareStatement("SELECT "+colum+" FROM "+table +" WHERE s_unique_ID = ?");
        extract_entrys.setString(1,unique_id);
        return  extract_entrys.executeQuery();

    }



    public void create_entry (String table, String unique_id ) throws SQLException {

       insertInto = Datenbankverbindung.prepareStatement("INSERT INTO "+table+"  (s_unique_ID) VALUES (?)");
       insertInto.setString(1,unique_id);
       insertInto.executeUpdate();

    }

    public void update_entry (String s_table_tm, String s_unique_ID_tm, String s_colum_tm, String s_value_tm) throws SQLException {
        System.out.println( s_table_tm+"    "+s_unique_ID_tm+"    "+s_value_tm+"    "+s_colum_tm);

        update_Entry= Datenbankverbindung.prepareStatement("UPDATE "+s_table_tm+" SET "+ s_colum_tm +" = '"+s_value_tm+"' WHERE s_unique_ID= ?");
        update_Entry.setString(1,s_unique_ID_tm);
        update_Entry.executeUpdate();

    }




    public void delete_entry (String table, String unique_ID) throws SQLException {
        delete_Entry= Datenbankverbindung.prepareStatement("DELETE FROM "+table+" WHERE s_unique_ID= ?");
        delete_Entry.setString(1,unique_ID);
        delete_Entry.executeUpdate();
    }




    public int v_i_amout_of_entrys_in_Database(String table) throws SQLException {
            PreparedStatement row_count = Datenbankverbindung.prepareStatement("SELECT COUNT(*) FROM "+table);
            ResultSet number_of_rows =row_count.executeQuery();
            number_of_rows.next();
            return number_of_rows.getInt(1);
    }

    public boolean entry_check(String table, String unique_id) throws SQLException {

        PreparedStatement id_check= Datenbankverbindung.prepareStatement("SELECT * FROM "+table+" WHERE s_unique_ID= ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id =id_check.executeQuery();
        return !entrys_with_specific_id.next();
    }

}
