package Database;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class DatabaseTableModel
	extends javax.swing.table.AbstractTableModel
{
	protected String[] titles;	//列标题
	protected int[] types;			//各列的数据类型
	protected Vector data;			//二维表的数据
	
	public DatabaseTableModel(ResultSet rs)
	{
		try{
			//取的结果集中的元数据
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			titles = new String[columnCount];
			types = new int[columnCount];
			for(int index = 1; index <= columnCount; index ++){
				titles[index - 1] = meta.getColumnName(index);
				types[index - 1] = meta.getColumnType(index);
			}
			
			data = new Vector(1000, 100);
			while(rs.next()){
				Vector row = new Vector(30);
				for(int index = 1; index <= columnCount; index++)
					row.addElement(rs.getObject(index));
				row.trimToSize();
				data.addElement(row);
			}
			data.trimToSize();
		}catch(SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
	}

	public void printData() {
		System.out.print(">>>通话记录详单"+'\n');
		for(int i=0;i<data.size();i++) {
			System.out.print('\n'+"电话号码："+'\t'+getValueAt(i, 0));
			System.out.print('\n'+"起止时间：");
			System.out.print(getValueAt(i, 1).toString()+'\t'+getValueAt(i, 2).toString());
		
		}
	}
	
	public int getRowCount(){
		return data.size();
	}
	
	public int getColumnCount(){
		return titles.length;
	}
	
	public String getColumnName(int col){
		return titles[col];
	}
	
	public Object getValueAt(int row, int col){
		return ((Vector)data.elementAt(row)).elementAt(col);
	}
}
