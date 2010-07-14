package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Comparator;

import yukkurisim.*;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import yukkurisim.Cursor_Manager;
import yukkurisim.ImageLoader;
import yukkurisim.ItemManager;
import yukkurisim.MapObject_Manager;
import yukkurisim.Object_base;
import yukkurisim.yukkurisim_main;
import yukkurisim.Mapchip_base;
import yukkurisim.yukkuri_base;
import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.MapTileManager;
import yukkurisim.Physical_Law_Facade;

/*********************************************************
 * 
 * @author ポチエル
 *
 * ゲームシーン１.
 *********************************************************/

public class LoadGame extends Scene_Base {
    MapObject_Manager 	OnMapObj;					// 霊夢を含めた全てのオブジェクト
    												// エリア上の木等のオブジェクトも含まれる
    												// これらを分けてグルーピングできないのは
    												// レンダリングの関係である。

    ItemManager			itemMan;					// アイテムマネージャ
    
    ADV_SpriteGroup_base	map1;					// マップ
    Widget_Manager			window;					// ウィンドウグループ
    
    ////////////////////////////////////////////////////////
    private int			Message_State = 1;		// メッセージ表示用カウンタ
    private MapTileManager	MapMan;					// マップマネージャ
    private Cursor_Manager	OwnCursor;				// カーソル

    ////////////////////////////////////////////////////////
    
    private Background 	background; // 背景
    private ImageLoader	iLoader;	// イメージローダ
    ////////////////////////////////////////////////////////
   
	
	/*******************************************************
	 * <p>コンストラクタ</p>
	 * @param yukkuri
	 * @param map
	 * @param own
	 */
	/*public TestField( yukkurisim_main own , ADV_SpriteGroup_base yukkuri , ADV_SpriteGroup_base map )
	{
		super(own);
		OnMapObj = yukkuri;
		map1 = map;
	}*/
    public LoadGame( yukkurisim_main own )
    {
    	super(own);
    }
    public void initResources() {
    	
    	this.background = new ColorBackground(Color.black, 定数.画面幅, 定数.画面高さ);
    	
    	iLoader = ImageLoader.Get_Instance(owner);

    	//OnMapObj = new MapObject_Manager(owner,"YUKKURI_REIMU");
    	OnMapObj = owner.phy_law.Get_ObjectManager();
    	
    	/********** マップの宣言 ***********************/
    	int[][] mapgrid = {
    			{0,0,0,0,0,0,1,0,0,0,0,0,0},
    			{0,0,0,0,0,1,1,0,0,0,0,0,0},
    			{0,0,0,0,0,1,1,1,0,0,0,0,0},
    			{0,0,0,0,1,1,1,1,0,0,0,0,0},
    			{0,0,0,0,1,1,1,1,1,0,0,0,0},
    			{0,0,0,1,1,1,1,1,1,0,0,0,0},
    			{0,0,0,1,1,1,1,1,1,1,0,0,0},
    			{0,0,3,1,1,1,1,1,1,1,0,0,0},
    			{0,0,3,1,1,1,1,1,1,1,1,0,0},
    			{0,1,4,3,1,1,1,1,1,1,1,0,0},
    			{0,1,4,3,1,1,1,1,1,1,1,1,0},
    			{1,1,1,4,3,1,1,1,1,1,1,1,0},
    			{1,1,1,4,3,1,1,1,1,1,1,1,1},
    			{1,1,1,1,4,3,1,1,1,1,1,1,1},
    			{0,1,1,1,4,3,1,1,1,1,1,1,1},
    			{0,1,1,1,1,1,1,1,1,1,1,1,0},
    			{0,0,1,1,1,1,1,1,1,1,1,1,0},
    			{0,0,1,1,1,1,1,1,1,1,1,0,0},
    			{0,0,0,1,1,1,1,1,1,1,1,0,0},
    			{0,0,0,1,1,1,1,1,1,1,0,0,0},
    			{0,0,0,0,1,1,1,1,1,1,0,0,0},
    			{0,0,0,0,1,1,1,1,1,0,0,0,0},
    			{0,0,0,0,0,1,1,1,1,0,0,0,0},
    			{0,0,0,0,0,1,1,1,0,0,0,0,0},
    			{0,0,0,0,0,0,1,1,0,0,0,0,0},
    			{0,0,0,0,0,0,1,0,0,0,0,0,0},

    	};
  
    	// maptilemanagerとmapobjectmanagerを作成し、物理法則クラスへ登録する
    	MapMan = new MapTileManager(owner , mapgrid);		//マップマネージャでマップを作成する。
    	map1 = MapMan.GetMapGroup();						//マップマネージャでマップを作成する。
    	
    	// アイテムマネージャクラスの登録
    	this.itemMan = ItemManager.Get_Instance(owner);
    	
    	// 物理法則クラスへの登録
    	owner.phy_law.Set_Manager(MapMan, OnMapObj,itemMan);

    	// ************** セーブデータのロード ****************/
    	GameSaveLoader loader = new GameSaveLoader();
    	loader.LoadState(owner);
    	
    	/*********** Comparatorをセット ここから **************/
    	/*********** マップ上のソート順序の指定はこちら *******/
    	OnMapObj.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(( s1.getY() + s1.getHeight() ) - ( s2.getY() + s2.getHeight()) );
    				}
    			}
    	);
    	/*********** Comparatorをセット ここまで **************/
    	
    	/*********** Backgroundをセット ここから **************/

    	OnMapObj.setBackground(background);
    	map1.setBackground(background);
    	
    	/*********** Backgroundをセット ここまで **************/
    	
       	//windowグループを取得
       	window = Widget_Manager.Get_Instance(owner).GetWindowGroup();
       	/*********** widget をセット ここまで **************/

       	OwnCursor = Cursor_Manager.Get_Instance(owner);		// カーソルスクロール
    }
    
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		background.update(elapsedTime);
		map1.update(elapsedTime);
		if(window.GTimer.isMoving())
		{
			OnMapObj.update(elapsedTime);
		}
		OnMapObj.doClickEvent();	// クリックイベントはタイマの状態に強制されるべきではない
		
		//メッセージウインドウ系とか
		window.update(elapsedTime);
		
		OwnCursor.update(elapsedTime);			// カーソルスクロール
		
		owner.phy_law.checkCollision();			// 衝突検出
	}
	
	public void render(Graphics2D g)
	{		
		super.render(g);
		//background.render(g);	//バックグラウンドの描画
		map1.render(g);			//マップの描画
		OwnCursor.render(g);	//カーソルの描画
		OnMapObj.render(g);		//マップ上のオブジェクトの描画
		window.render(g);		//メニュー等、ウインドウの描画
	}
}
