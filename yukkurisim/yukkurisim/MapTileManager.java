package yukkurisim;
import java.io.Serializable;

import yukkurisim.yukkurisim_main;
import yukkurisim.ADV_SpriteGroup_base;
import gamestatus.Const_Value;


/****
 * マップタイルマネージャ。　マップの管理を行う。　MapObjectManagerはマップ上のオブジェクトの
 * 管理を行うもので、それとは異なりマップそのものの管理を行う。
 * @author pochiel
 *
 */
public class MapTileManager implements Serializable {
	private yukkurisim_main		owner;
	private  Const_Value			定数 = new Const_Value();
	public ADV_SpriteGroup_base		map1;

	/* マップ情報管理クラスMapTilesub */
	public class MapTilesub implements Serializable {
		private int sub_x;
		private int sub_y;
		private int sub_type;
		
		private MapTilesub(int x , int y ,int type)
		{
			sub_x = x;
			sub_y = y;
			sub_type = type;
		}
		
		public int Get_x()
		{
			return sub_x;
		}
		
		public int Get_y()
		{
			return sub_y;
		}
		
		public int Get_Type()
		{
			return sub_type;
		}
	}
	private MapTilesub[][] map_info;
	private int[][] mapdef;
	public MapTileManager( yukkurisim_main own , int[][] mapgrid){
		
		owner = own;
		mapdef = mapgrid;
		map1 = new ADV_SpriteGroup_base(owner,"MAP1");
		map_info = new MapTilesub[定数.画面横セル幅][定数.画面縦セル幅];
		
    	for( int i = 0 ; i < 定数.画面縦セル幅 ; i++ )
    	{ 
    		for( int j = 0 ; j < 定数.画面横セル幅 ; j++ )
    		{
    			int type_tmp=0;
    			switch( mapgrid[i][j] )
    			{
    			case 0:
    				//現在は実装していない
    				map1.add(	new Mapchip_base
								(
									owner , 
									//owner.getImages("image/mapchip/paneldefault.gif",1,1),
									ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_タイル0),
									j,
									i,
									定数.TYPE_干渉のみ可能
								)
    				
    				);
    				type_tmp=定数.TYPE_干渉のみ可能;
    				break;
    			case 1:		//草原1
    				map1.add(	new Mapchip_base
    							(
    								owner , 
    								//owner.getImages("image/sougen1.gif",1,1),
    								ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_草原1),
    								j,
    								i,
    								定数.TYPE_通行のみ可能
    							)
    				);
    				type_tmp=定数.TYPE_通行のみ可能;
    				break;
    			case 2:		//草原2
    				map1.add( new Mapchip_base
    						(
    							owner , 
    							//owner.getImages("image/sougen2.gif",1,1),
								ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_草原2),
    							j,
    							i,
    							定数.TYPE_通行のみ可能
    						)
    				);
    				type_tmp=定数.TYPE_通行のみ可能;
    				break;
    			case 3:		//川1
    				map1.add( new Mapchip_base
    						(
    							owner , 
    							//owner.getImages("image/river1.gif",1,1),
								ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_川1),
    							j,
    							i,
    							定数.TYPE_干渉のみ可能
    						)
    				);
    				type_tmp=定数.TYPE_干渉のみ可能;
    				break;
    			case 4:		//川2
    				map1.add( new Mapchip_base
    						(
    							owner , 
    							//owner.getImages("image/river2.gif",1,1),
								ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_川2),
    							j,
    							i,
    							定数.TYPE_干渉のみ可能
    						)
    				);
    				type_tmp=定数.TYPE_干渉のみ可能;
    				break;
    			case 5:		//エントランス
    				map1.add( new Mapchip_base
    						(
    							owner , 
    							//owner.getImages("image/enterance.gif",1,1),
								ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_エントランス),
    							j,
    							i,
    							定数.TYPE_干渉のみ可能
    						)
    				);
    				type_tmp=定数.TYPE_干渉のみ可能;
    				break;
    			}
    			map_info[j][i] = new MapTilesub(j,i,type_tmp);		// マップ管理クラスへ
				// マップ情報を格納

    		}
    	}
	}
	
	/**
	 * Mapの実体へのGetter
	 * @return
	 */
	public	ADV_SpriteGroup_base GetMapGroup()
	{
		return map1;
	}
	
	// セルのマップタイプを返す
	public int Get_Cell_Type(int cellx , int celly)
	{
		return map_info[cellx][celly].sub_type;
	}
	
	
	// 該当オブジェクトが該当マップに進入できるかを返す
	public boolean Check_able_to_Entry(int cell_x,int cell_y,int c_type)
	{
		if( c_type == 定数.TYPE_ゆっくりobject )
		{	
			// ゆっくりオブジェクトの進入可能条件ここから
			int t = this.Get_Cell_Type(cell_x, cell_y);
			if( (t==定数.TYPE_すべてが可能)||(t==定数.TYPE_通行のみ可能))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	// 該当オブジェクトに設置型オンマップオブジェクトが設置可能であるかを返す
	public boolean Check_able_to_Build(int cell_x,int cell_y,int cell_w , int cell_h)
	{
    	double dmy_y = cell_y;
    	double dmy_x = cell_x;
		    	
		for(int j=cell_h-1;j>=0;j--)
		{
			dmy_x=cell_x+(0.5*j);
			dmy_y=cell_y-j;
	    	if(cell_y%2!=0)
			{
				dmy_x+=0.5;		// y位置による横位置補正
			}
			
	    	for(int i=0;i<cell_w;i++)
	    	{	//高さ
	    		int tmpx = (int)dmy_x;
	    		int tmpy = (int)dmy_y;
	    		// チェックしているセルが画面をハミ出した
	    		if( (tmpx<0)||(tmpx>=定数.画面横セル幅))
	    		{
	    			return false;
	    		}
	    		if( (tmpy<0)||(tmpy>=定数.画面縦セル幅))
	    		{
	    			return false;
	    		}
	    		
	    		// 草原以外のマップタイル上にオブジェクトを建てようとした
	    		if( mapdef[tmpy][tmpx]!=1 )
	    		{
	    			return false;
	    		}
				dmy_x+=0.5;
				dmy_y++;
	    	}			
		}
		return true;
	}
	
}
