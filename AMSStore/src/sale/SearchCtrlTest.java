package sale;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchCtrlTest 
{

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException 
	{
		SearchCtrl searcher = new SearchCtrl();
		String cate = args[0];
		String title = args[1].equals("null") ? "" : args[1];
		String singer = args[2].equals("null") ? "" : args[2];
		int qty = Integer.parseInt(args[3]);
		
		ArrayList<sale.Item> item_list = new ArrayList<sale.Item>();
		if(!cate.equals("") && !title.equals("") && 
		   !singer.equals(""))
			item_list = searcher.searchAll(title, cate, singer, qty);
		//the following control flow will always search for 
		//category if presented, then title if presented, 
		//last singer
		else if(!cate.equals(""))
			item_list = searcher.searchByCategory(cate, qty);
		else if(!title.equals(""))
			item_list = searcher.searchByTitle(title, qty);
		else if(!singer.equals(""))
			item_list = searcher.searchBySinger(singer, qty);
		
		
		for(int row = 0; row < item_list.size(); row++)
		{
			System.out.println(item_list.get(row).getUPC());
		}

	}

}
