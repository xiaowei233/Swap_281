package com.swap281.repository;

import com.swap281.model.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.heroku.api.request.RequestConfig.Data;

public class Database {
    /**
     * A prepared statement used for getting the most recently created itemData
     * entry id
     */
    static PreparedStatement p_getMostRecentId;
    /****************************************************************/
    /****************************************************************/
    /*******                                                  *******/
    /******* ITEM DATA TABLE *******/
    /*******                                                  *******/
    /****************************************************************/
    /****************************************************************/

    /**
     * A prepared statement for getting all items in the database
     */
    static PreparedStatement p_selectAllItemData;

    /**
     * A prepared statement for getting one item in the database
     */
    static PreparedStatement p_selectOneItemData;

    /**
     * A prepared statement for getting all item by provided id list
     */
    static PreparedStatement p_selectAllItemDataById;

    /**
     * A prepared statement for deleting a item from the database
     */
    static PreparedStatement p_deleteOneItemData;

    /**
     * A prepared statement for creating a new item in the database
     */
    static PreparedStatement p_insertNewItemData;

    /**
     * A prepared statement for selecting all items in one category
     */
    static PreparedStatement p_selectAllFromCategory;

    /**
     * A prepared statement for selecting all items in one category
     */
    static PreparedStatement p_selectAllFromPrice;

    /**
     * A prepared statement for updating info for itemData
     */
    static PreparedStatement p_updateItemData;

    /**
     * A prepared statement for creating the item table in our database
     */
    static PreparedStatement p_createItemDataTable;

    /**
     * A prepared statement for dropping the item table in our database
     */
    static PreparedStatement p_dropItemDataTable;

    /****************************************************************/
    /****************************************************************/
    /*******                                                  *******/
    /******* TRADING INFO DATA TABLE *******/
    /*******                                                  *******/
    /****************************************************************/
    /****************************************************************/

    /**
     * A prepared statement for creating the trading info table in our database
     */
    static PreparedStatement p_createTradingInfoDataTable;

    /**
     * A prepared statement for dropping the trading info table in our database
     */
    static PreparedStatement p_dropTradingInfoDataTable;

    /**
     * A prepared statement for inserting new trading info to the table
     */
    static PreparedStatement p_insertNewTradingInfoData;

    /**
     * A prepared statement for selecting all trading info to the table
     */
    static PreparedStatement p_selectAllTradingInfoData;

    /**
     * A prepared statement for selecting all trading info by idList to the table
     */
    static PreparedStatement p_selectAllTradingInfoDataById;

    /**
     * A prepared statement for selecting one trading info to the table
     */
    static PreparedStatement p_selectOneTradingInfoData;
    /**
     * A prepared statement for inserting new item category to the table
     */
    static PreparedStatement p_deleteTradingInfoData;

    /****************************************************************/
    /****************************************************************/
    /*******                                                  *******/
    /******* DATABASE CONNECTION *******/
    /*******                                                  *******/
    /****************************************************************/
    /****************************************************************/

    /**
     * The connection to the database. When there is no connection, it should be
     * null. Otherwise, there is a valid open connection
     */
    private static Connection mConnection;

    /**
     * priavate instance for accessing and calling functions in ItemDataTable
     */
    private static ItemDataTable itemDT;

    /**
     * The Database constructor is private: we only create Database objects through
     * the getDatabase() method.
     */
    private Database() {
        itemDT = new ItemDataTable();
    }

    public static Database getDatabase(String ip, String port, String user, String pass) {
        // conn is a connection to the database. In this simple example, it is
        // a local variable, though in a realistic program it might not be
        Database db = new Database();
        // Connect to the database or fail
        System.out.print("Connecting to " + ip + ":" + port);
        try {
            // Open a connection, fail if we cannot get one
            Connection conn = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/", user, pass);
            if (conn == null) {
                System.out.println("\n\tError: DriverManager.getConnection() returned a null object");
                return null;
            }
            mConnection = conn;

        } catch (SQLException e) {
            System.out.println("\n\tError: DriverManager.getConnection() threw a SQLException");
            e.printStackTrace();
            return null;
        }
        System.out.println(" ... successfully connected");
        // Attempt to create all of our prepared statements. If any of these fail, the
        // whole getDatabase() call should fail
        SetUpPrepareStatement();
        return db;
    }

    /**
     * @param url the url to connect to database
     */
    public static Database getDatabase(String url) {
        // Create an new unconfigured Database object
        Database db = new Database();

        // Give the Database object a connection, fail if we cannot get one
        try {
            Class.forName("org.postgresql.Driver");
            URI dbUri = new URI(url);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            if (conn == null) {
                System.err.println("Error: DriverManager.getConnection() returned a null object");
                return null;
            }
            mConnection = conn;
        } catch (SQLException e) {
            System.err.println("Error: DriverManager.getConnection() threw a SQLException");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Unable to find postgresql driver");
            return null;
        } catch (URISyntaxException s) {
            System.out.println("URI Syntax Error");
            return null;
        }

        // Attempt to create all of our prepared statements. If any of these fail, the
        // whole getDatabase() call should fail
        SetUpPrepareStatement();
        return db;
    }

    /**
     * Close the current connection to the database, if one exists.
     * 
     * NB: The connection will always be null after this call, even if an error
     * occurred during the closing operation.
     * 
     * @return True if the connection was cleanly closed, false otherwise
     */
    boolean disconnect() {
        if (mConnection == null) {
            System.err.println("Unable to close connection: Connection was null");
            return false;
        }
        try {
            mConnection.close();
        } catch (SQLException e) {
            System.err.println("Error: Connection.close() threw a SQLException");
            e.printStackTrace();
            mConnection = null;
            return false;
        }
        mConnection = null;
        return true;
    }

    /**
     * Helper function used for setting up all SQL prepared statements
     */
    static private void SetUpPrepareStatement() {
        try {
            Database.p_getMostRecentId = mConnection.prepareStatement("SELECT MAX(itemId) FROM itemData");
            //////////////////////////////////////////
            // Item Data Table
            //////////////////////////////////////////
            Database.p_createItemDataTable = mConnection.prepareStatement("CREATE TABLE itemData"
                    + "(itemId SERIAL PRIMARY KEY," + " userId INTEGER," + " title VARCHAR(50) NOT NULL,"
                    + " description VARCHAR(500) NOT NULL," + " category INTEGER," + " postDate INTEGER, "
                    + " tradeMethod INTEGER," + " price float," + " availability boolean,"
                    + " availableTime VARCHAR(40)," + " wantedItemDescription VARCHAR(50))");
            Database.p_dropItemDataTable = mConnection.prepareStatement("DROP TABLE itemData");
            // Standard CRUD operations for item
            Database.p_deleteOneItemData = mConnection.prepareStatement("DELETE FROM itemData WHERE itemId = ?");
            Database.p_insertNewItemData = mConnection.prepareStatement(
                    "INSERT INTO itemData VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    p_insertNewItemData.RETURN_GENERATED_KEYS);
            Database.p_selectAllItemData = mConnection.prepareStatement("SELECT * FROM itemData");
            Database.p_selectOneItemData = mConnection.prepareStatement("SELECT * FROM itemData WHERE itemId=?");
            Database.p_selectAllItemDataById = mConnection.prepareStatement("SELECT * FROM itemData WHERE itemId in ?");
            Database.p_selectAllFromCategory = mConnection
                    .prepareStatement("SELECT * FROM itemData WHERE category = ANY(?)");
            Database.p_selectAllFromPrice = mConnection
                    .prepareStatement("SELECT * FROM itemData WHERE price BETWEEN ? AND ?");
            Database.p_updateItemData = mConnection.prepareStatement("UPDATE");

        } catch (SQLException e) {
            System.err.println("Error creating prepared statement");
            e.printStackTrace();
            return;
        }
    }

    /**
     * @param res the arraylist to be converted to SQL Array type
     * @return the converted Array type of res
     */
    static Array ConvertToIntArray(ArrayList<Integer> res) {
        try {
            return mConnection.createArrayOf("INTEGER", res.toArray());
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * @param res the arraylist to be converted to SQL Array type
     * @return the converted Array type of res
     */
    static Array ConvertToStringArray(String[] res) {
        try {
            return mConnection.createArrayOf("text", res);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Creating all tables in the database
     */
    public void createAllTables() {
        itemDT.createItemDataTable();
    }

    /**
     * Dropping all tables in the database
     */
    public void dropAllTables() {
        itemDT.dropItemDataTable();
    }

    /**
     * 
     * @param userId                Id of the user who posted this item
     * @param title                 title of the item
     * @param description           description of the item
     * @param categories            category of the item, for rn we only have 4
     *                              categories: 1 for car, 2 for school, 3 for
     *                              electronics, 4 for furnitures
     * @param postDate              date of the when the item was posted, format
     *                              yyyymmdd
     * @param tradeMethod           how the item is gonna be traded, 1 for "Sell", 2
     *                              for "Trade", 3 for "Rent", 4 for "GiveAway"
     * @param price                 price of the item
     * @param availability          whether the item is currently available
     * @param availableTime         available for how long, unit is day
     * @param wantedItemDescription some keywords of wanted item
     * @return the id of the new items
     */
    public int insertNewItem(int userId, String title, String description, int categories, int postDate,
            int tradeMethod, float price, boolean availability, String availableTime, String wantedItemDescription) {
        int res = itemDT.insertNewItemData(userId, title, description, categories, postDate, tradeMethod, price,
                availability, availableTime, wantedItemDescription);
        return res;
    }

    /**
     * 
     * @param item An ItemData object that will be inserted
     * @return if non negative if successfully inserted
     */
    public int insertNewItem(ItemData item) {
        int res = itemDT.insertNewItemData(item.itemSeller, item.itemTitle, item.itemDescription, item.itemCategory,
                item.itemPostDate, item.itemTradeMethod, item.itemPrice, item.itemAvailability, item.itemAvailableTime,
                item.itemWantedItemDescription);
        return res;
    }

    public int deleteItem(int itemId) {
        int res = itemDT.deleteItem(itemId);
        return res;
    }

    public ArrayList<ItemData> selectAllItems() {
        return itemDT.selectAllItems();
    }

    public ArrayList<ItemData> selectAllItemsFromCategory(ArrayList<Integer> category) {
        return itemDT.selectAllItemFromCategory(category);
    }

    public ArrayList<ItemData> selectAllItemsFromPrice(float low, float high) {
        return itemDT.selectAllItemFromPrice(low, high);
    }

    public ItemData selectOneItem(int itemId) {
        return itemDT.selectOneItem(itemId);
    }

}