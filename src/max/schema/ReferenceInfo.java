package max.schema;

public class ReferenceInfo {
	private String dbName;
    private String tableName;
    private String columnName;

    public ReferenceInfo(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }
    public ReferenceInfo(String tableName, String columnName, String dbName) {
    	this(tableName, columnName);
    	this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }
    
    public String getDbName() {
    	return dbName;
    }
}
