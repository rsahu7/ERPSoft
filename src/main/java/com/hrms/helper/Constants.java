package com.hrms.helper;

public class Constants {

	// All constants string should be listed here..
	public static String SUPPLIER_PREFIX_CODE = "SUP";
	public static String CUSTOMER_PREFIX_CODE = "CUS";
	public static String PROJECT_PREFIX_CODE = "PRJ";
	public static String SUPPLIER_HEADER_COLS = "ID,Name,EmailID,ContactNo,Designation,Country,Address,"
			+ "ZipCode,PaymentTerm,ShortName,Website,Region,Telephone,AccountCode,Fax,ContactPerson";
	
	public static String CUSTOMER_HEADER_COLS = "ID,Name,EmailID,ContactNo,Designation,Country,Address,ZipCode,"
			+ "PaymentTerm,ShortName,Website,Region,Telephone,AccountCode,Fax,ContactPerson,creditlimit";
	
	public static String ITEM_HEADER_COLS = "ID,Description,CustomerId,BrandName,AverageCost,UnitOfMeasure,PrimaryUnit,"
			+ "CostPrice,RetailPrice,WholeSalePrice,CurrentStock,ReservedStock,MinimumStock,RetailMarkup,WholeSaleMarkup,"
			+ "Barcode,CustomerBarcode,Volume,PackingSize,ReorderLevel";
	
	public static String NOVINVENTORYITEM_HEADER_COLS = "ID,Description,GroupName";
	
	public static String PROJECT_HEADER_COLS = "ID,ProjectNo,Description,Address";
	
	public static String WAREHOUSE_HEADER_COLS = "ID,WarehouseNo,Description,Address1,Address2,Address3";
}
