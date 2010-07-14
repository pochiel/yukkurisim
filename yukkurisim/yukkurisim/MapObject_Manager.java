 package yukkurisim;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Vector;

import gamestatus.Const_Value;
import yukkurisim.Object_base;

public class MapObject_Manager extends ADV_SpriteGroup_base{
	private  Const_Value 定数 = new Const_Value();
	private Object_base Map_grid[][];
	private int Map_style[][];
	//private static MapObject_Manager  myself;
	// 農地を管理する配列
	private Vector<Object_base> FarmVector = new Vector<Object_base>();
	public Vector<Object_base> getFarmVector() {
		return FarmVector;
	}

	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	/*public static synchronized MapObject_Manager Get_Instance( yukkurisim_main own , String g_name)
	{
		if( myself == null)
		{
			myself = new MapObject_Manager(own,g_name);
		}

		return myself;
	}*/

	
	/**
	 * 農地ベクタを返す。
	 * @param own
	 * @param g_name
	 */
	public MapObject_Manager(yukkurisim_main own,String g_name)
	{
		super(own, g_name);
		
		Map_grid = new Object_base[定数.画面横セル幅][定数.画面縦セル幅];
		Map_style = new int[定数.画面横セル幅][定数.画面縦セル幅];
		
		for(int x = 0 ; x<定数.画面横セル幅 ; x++)
		{
			for(int y = 0 ; y < 定数.画面縦セル幅 ; y++)
			{
				Map_style[x][y] = 0;
				Map_grid[x][y] = null;
			}
		}
		/*********** マップ上のソート順序の指定はこちら *******/
		this.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(( s1.getY() + s1.getHeight() ) - ( s2.getY() + s2.getHeight()) );
    				}
    			}
    	);

	}
	
	// objectの管理を始める。
	public void add(Object_base obj,int c_type)
	{
		try
		{
			Map_grid[obj.my_x][obj.my_y] = obj; //objを格納
			Map_style[obj.my_x][obj.my_y] = c_type;
			this.add(obj);
			if(obj.Get_Type()==定数.TYPE_農地object)
			{
				System.out.println("農地追加→"+obj.my_x+","+obj.my_y);
				FarmVector.add(obj);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("obj x→"+obj.my_x+"  y→"+obj.my_y);
			System.exit(-1);
		}
	}
	
	// objectの管理を終わる
	public void kill(int x, int y)
	{
		System.out.println("x->" + x + "   y->" + y);
		Map_grid[x][y] = null;
	}
	
	// objectへの参照を返す
	public Object_base Get_obj_pointer( int x , int y )
	{
		return Map_grid[x][y];
	}

	// objectのタイプを返す
	public int Get_obj_type( int x , int y )
	{
		//System.out.println(" == x->" + x + " , y->" + y + " ==");
		return Map_style[x][y];
	}

	// 該当オブジェクトが該当マップに進入できるかを返す
	// MapTileとの違いは基本進入「可能」であり、進入不可なオブジェクトを逐一設定する事
	public boolean Check_able_to_Entry(int cell_x,int cell_y,int c_type)
	{
		if( c_type == 定数.TYPE_ゆっくりobject )
		{	
			// ゆっくりオブジェクトの進入可能条件ここから
			int t = this.Get_obj_type(cell_x, cell_y);
			if( t==定数.TYPE_家object )
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return true;
	}
};
